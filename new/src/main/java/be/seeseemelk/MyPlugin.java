import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class MyPluginPlugin extends JavaPlugin
{
	public MyPluginPlugin()
	{
		super();
	}
	
	protected MyPluginPlugin(final JavaPluginLoader loader, final PluginDescriptionFile description, final File dataFolder, final File file)
	{
		super(loader, description, dataFolder, file);
	}
	
	@Override
	public void onLoad()
	{
		// TODO Stub
	}
	
	@Override
	public void onEnable()
	{
		// TODO Stub
	}
	
	@Override
	public void onDisable()
	{
		// TODO Stub
	}
}
































