package be.seeseemelk.plugingenerator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.commons.lang3.SystemUtils;

public class PluginGenerator
{
	private Options options;
	private Map<String, String> conversionMappings = new HashMap<>();

	public PluginGenerator(Options options)
	{
		this.options = options;
		conversionMappings.put("pluginName", options.getPluginName());
		conversionMappings.put("pluginPackage", options.getPackageName());
		if (options.useMockBukkit())
			conversionMappings.put("useMockBukkit", "");
		else
			conversionMappings.put("useMockBukkit", "//");
	}
	
	public void generate() throws InterruptedException, IOException
	{
		String directory = options.getPluginName() + "/";
		String packageDirectory = options.getPackageName().replace('.', '/');
		
		copyFile("build.gradle", directory+"build.gradle");
		copyFile("settings.gradle", directory+"settings.gradle");
		File gradleFile = copyFile("gradlew", directory+"gradlew");
		copyFile("gradlew.bat", directory+"gradlew.bat");
		copyFile("plugin.yml", directory+"src/main/resources/plugin.yml");
		copyFile("Plugin.txt", directory+"src/main/java/"+packageDirectory+"/"+options.getPluginName()+".java");
		if (options.useMockBukkit())
			copyFile("PluginTest.txt", directory+"src/test/java/"+packageDirectory+"/"+options.getPluginName()+".java");
		copyBinaryFile("gradle-wrapper.jar", directory+"gradle/wrapper/gradle-wrapper.jar");
		copyBinaryFile("gradle-wrapper.properties", directory+"gradle/wrapper/gradle-wrapper.properties");
		
		gradleFile.setExecutable(true);
		File dir = gradleFile.getParentFile();
		if (SystemUtils.IS_OS_WINDOWS)
			runCommand(dir, "gradlew.bat", "eclipse");
		else
			runCommand(dir, "./gradlew", "eclipse");
	}
	
	private void runCommand(File dir, String... args) throws IOException, InterruptedException
	{
		ProcessBuilder builder = new ProcessBuilder(args);
		builder.directory(dir);
		builder.inheritIO();
		Process process = builder.start();
		process.waitFor();
	}
	
	private File copyFile(String input, String output)
	{
		URL inputURL = ClassLoader.getSystemResource(input);
		if (inputURL != null)
		{
			return copyFile(inputURL, new File(output));
		}
		else
		{
			System.err.println("Input file " + input + " not found");
			return null;
		}
	}
	
	private File copyBinaryFile(String input, String output)
	{
		URL inputURL = ClassLoader.getSystemResource(input);
		if (inputURL != null)
		{
			return copyBinaryFile(inputURL, new File(output));
		}
		else
		{
			System.err.println("Input file " + input + " not found");
			return null;
		}
	}
	
	private File copyFile(URL input, File output)
	{
		output = output.getAbsoluteFile();
		File outputFolder = output.getParentFile();
		if (!outputFolder.exists())
		{
			System.out.println("MKDIR " + outputFolder.getAbsolutePath());
			outputFolder.mkdirs();
		}
		
		System.out.println("COPY " + input.getPath() + " TO " + output.getAbsolutePath());
		
		try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(input.openStream()));
		     BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output));)
		{
			Stream<String> lines = inputReader.lines();
			lines = lines.map(str -> {
				for (Entry<String, String> mappings : conversionMappings.entrySet())
				{
					str = str.replace("%"+mappings.getKey()+"%", mappings.getValue());
				}
				return str;
			});
			
			Iterator<String> iterator = lines.iterator();
			while (iterator.hasNext())
			{
				outputWriter.write(iterator.next());
				outputWriter.write("\n");
			}
		}
		catch (IOException e)
		{
			System.err.println("Failed to write to file: " + e.getMessage());
		}
		
		return output;
	}
	
	private File copyBinaryFile(URL input, File output)
	{
		output = output.getAbsoluteFile();
		File outputFolder = output.getParentFile();
		if (!outputFolder.exists())
		{
			System.out.println("MKDIR " + outputFolder.getAbsolutePath());
			outputFolder.mkdirs();
		}
		
		System.out.println("COPY BIN " + input.getPath() + " TO " + output.getAbsolutePath());
		
		try (BufferedInputStream inputStream = new BufferedInputStream(input.openStream());
		     BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));)
		{
			byte[] b = new byte[4096];
			int read = inputStream.read(b);
			while (read != -1)
			{
				outputStream.write(b, 0, read);
				read = inputStream.read(b);
			}
		}
		catch (IOException e)
		{
			System.err.println("Failed to write to file: " + e.getMessage());
		}
		
		return output;
	}

	public static void main(String[] args) throws InterruptedException, IOException
	{
		Options options = new Options();
		options.ask();
		PluginGenerator generator = new PluginGenerator(options);
		generator.generate();
	}

}
