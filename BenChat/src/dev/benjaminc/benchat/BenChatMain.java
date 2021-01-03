package dev.benjaminc.benchat;

import java.util.List;

//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BenChatMain extends JavaPlugin {
	
	public List<String> worlds;
	
//	private FileConfiguration cfg = this.getConfig();
	
	/**
	 * Fired with the plugin is enabled
	 */
    @Override
    public void onEnable() {
//    	cfg = getConfig();
//    	saveDefaultConfig();
//    	worlds = cfg.getStringList(Keys.CONFIG_WORLDS);

    	for(char ch = 'a'; ch <= 'z'; ch++) {
    		getCommand(ch + "").setExecutor(new BenChatCommand(this));
    	}
//    	getCommand(Keys.COMMAND_WS).setTabCompleter(new BenChatCommandTabComplete(this));
    }
    
    /*
     *  Fired when plugin is disabled
     */
    @Override
    public void onDisable() {
//    	cfg.set(Keys.CONFIG_WORLDS, worlds);
//    	saveConfig();
    }
}
