package me.sketsi.mcinfo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MCCommand implements CommandExecutor {
	protected static void sendMessage(CommandSender sender, String message) {
		try {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		} catch (Exception ignored) {
		}
	}

	
	private boolean sendHelp(CommandSender sender, String command) {
		if (command == null || command.isEmpty()) {
			return false;
		}

		switch (command) {
			case CommandType.ENCHANT:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc enchant list [item]&c\" for available options!"));
				break;
			case CommandType.POTION:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc potion list&c\" for more help!"));	
			case CommandType.HELP: // Falls through	
			default:
				sender.sendMessage(ChatColor.GOLD + "Minecraft Information Help:");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc enchant &8- &7Information regarding vanilla commands."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc potion &8- &7Lists all vanilla potion commands."));
				return true;
		}

		return false;
	}

	private boolean sendHelp(CommandSender sender) {
		return sendHelp(sender, CommandType.HELP);
	}
	
	private boolean sendMCEnchant(CommandSender sender) {
		return send
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 1) {
			// Send help to the user when the command is executed with no arguments -- can be replaced with any function call.
			return sendHelp(sender);
		}

		switch (args[0].toLowerCase()) {
			case CommandType.ENCHANT:
				return onEnchantCommand(sender, args);
			case CommandType.POTION:
				return onPotionCommand(sender, args);
			case CommandType.HELP: // Falls through
			default:
				return sendHelp(sender);
		}
	}
	
}

