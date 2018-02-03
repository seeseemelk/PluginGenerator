package be.seeseemelk.plugingenerator;

import java.util.Scanner;
import java.util.function.Predicate;

public class Options
{
	private String pluginName = "MyPlugin";
	private String packageName = "be.seeseemelk";
	private boolean useMockBukkit = true;

	public void ask()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("What is the name of the plugin? ");
		pluginName = ask(scanner, Converters::asNonEmptyString, Validators::isSimpleString);
		
		System.out.print("What is the package of the plugin? ");
		packageName = ask(scanner, Converters::asNonEmptyString, Validators::isGoodPackage);
		
		System.out.print("Should the plugin use MockBukkit for testing? (Y/N) ");
		useMockBukkit = ask(scanner, Converters::asBoolean, Validators::ignore);

		scanner.close();
	}

	public String getPluginName()
	{
		return pluginName;
	}
	
	public String getPackageName()
	{
		return packageName;
	}
	
	public boolean useMockBukkit()
	{
		return useMockBukkit;
	}
	
	private static <T> T ask(Scanner scanner, Converter<T> converter, Predicate<T> validator)
	{
		while (true)
		{
			try
			{
				T value = converter.convert(scanner.nextLine());	
				if (validator.test(value))
					return value;
			}
			catch (ConversionException e)
			{
				System.out.print(e.getMessage());
				System.out.print(": ");
			}
		}
	}

}














