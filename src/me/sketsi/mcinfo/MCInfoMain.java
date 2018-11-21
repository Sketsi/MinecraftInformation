package me.sketsi.mcinfo;


import org.bukkit.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

public class MCInfoMain extends JavaPlugin {

	public void onEnable() {
		System.out.println(ChatColor.translateAlternateColorCodes('&', "&6Minecraft Information &ahas been enabled!"));
		
		getCommand("mc").setExecutor(new MCCommand());
	}
	
}