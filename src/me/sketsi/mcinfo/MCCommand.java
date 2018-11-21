package me.sketsi.mcinfo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.sketsi.mcinfo.MCEnchant.VanillaEnchant;
import me.sketsi.mcinfo.MCEnchant.VanillaEnchantGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class CommandType {
	// First Arg
	static final String ENCHANT = "enchant";
	static final String POTION = "potion";
	static final String HELP = "help";
	// Second Arg
	static final String INFO = "info";
	static final String LIST = "list";
}

//	Potion
class VanillaPotion {
	private String name;
	private String description;
	private boolean enhance;
	private boolean extend;
	private boolean isNegative;
	private boolean isMixed;
	
	public VanillaPotion(String name, String description, boolean enhance, boolean extend) {
		this.name = name;
		this.description = description;
		this.enhance = enhance;
		this.extend = extend;
		this.isNegative = false;
		this.isMixed = false;
	}
	
	public VanillaPotion(String name, String description, boolean enhance, boolean extend, boolean isNegative, boolean isMixed) {
		this.name = name;
		this.description = description;
		this.enhance = enhance;
		this.extend = extend;
		this.isNegative = isNegative;
		this.isMixed = isMixed;
	}

	String getName() {
		return name;
	}

	public Object getFormattedName() {
		return ChatColor.translateAlternateColorCodes('&', (this.isNegative ? "&c" : "&a") + (this.isMixed ? "&6" : "") + this.name);
	}
	
	public String toString() {
		return ChatColor.translateAlternateColorCodes('&', String.format("%s&l%s &b: &3%s %s %s",
				(this.isNegative ? "&c" : "&a"), (this.isMixed ? "&6" : ""), this.name, this.description, (this.enhance ? "" : "&aEnhanceable"), (this.extend ? "" : "&aExtendable")));
	}
	
}

class VanillaPotionGroup {
	private String name;
	private List<String> potions;

	VanillaPotionGroup(String name, List<String> potions) {
		this.name = name;
		this.potions = potions;
	}

	String getName() {
		return name;
	}

	List<String> getPotions() {
		return potions;
	}
}

public class MCCommand implements CommandExecutor {
	private static void sendMessage(CommandSender sender, String message) {
		try {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		} catch (Exception ignored) {
		}
	}

	private final Map<String, VanillaEnchant> enchants = createEnchants();

	private final Map<String, VanillaEnchantGroup> enchantMap = createEnchantMap();

	private final String[] defaultEnchantGroups = new String[]{"armor", "bows", "other", "tools", "weapons"};

	private boolean sendEnchantList(CommandSender sender, String group) {
		VanillaEnchantGroup enchantgroup = enchantMap.get(group);

		sendMessage(sender, String.format("&b&l%s&b:", enchantMap.get(group).getName()));
		sendMessage(sender, String.format("&7[%s&7]", enchantgroup.getEnchants().stream().parallel()
				.map(key -> enchants.get(key).getFormattedName()).collect(Collectors.joining("&7, "))));

		return true;
	}
//	Potion
	private Map<String, VanillaPotion> createPotions() {
		Map<String, VanillaPotion> internalPMap = new HashMap<>();
		List<VanillaPotion> potionList = Stream.of(
				//	Base
				new VanillaPotion("Awkward", "Water Bottle + Nether Wart", false, false),
				new VanillaPotion("Mundane", "Water Bottle + (Blaze Powder, Ghast Tear, Glistering Melon, Golden Carrot, Magma Cream, Phantom Membrane, Pufferfish, Rabbit's Foot, Redstone, Spider Eye,Sugar or Turtle Shell)", false, false),
				new VanillaPotion("Thick", "Water Bottle + Glowstone", false, false),
				//	Positive
				new VanillaPotion("FireResistance", "Awkward Potion + Magma Cream", false, true),
				new VanillaPotion("Healing", "Awkward Potion + Gliering Melon", true, false),
				new VanillaPotion("Invisibility", "NightVision Potion + Fermented Spider Eye", false, true),
				new VanillaPotion("Leaping", "Awkward Potion + Rabbit's Foot", true, true),
				new VanillaPotion("NightVision", "Awkward Potion + Golden Carrot", false, true),
				new VanillaPotion("Regeneration", "Awkward Potion + Ghast Tear", true, true),
				new VanillaPotion("SlowFalling", "Awkward Potion + Phantom Membrane", false, true),
				new VanillaPotion("Strength", "Awkward Potion + Blaze Powder", true, true),
				new VanillaPotion("Swiftness", "Awkward Potion + Sugar", true, true),
				new VanillaPotion("WaterBreathing", "Awkward Potion + Pufferfish", false, true),
				//	Mixed
				new VanillaPotion("TurtleMaster", "Awkward Potion + Turtle Shell", true, true, false, true),
				//	Negative
				new VanillaPotion("Harming", "Health Potion + Fermented Spider Eye", true, false, true, false),
				new VanillaPotion("Poison", "Awkward Potion + Spider Eye", false, true, true, false),
				new VanillaPotion("Slowness", "Swiftness + Fermented Spider Eye", true, true, true, false),
				new VanillaPotion("Weakness", "Awkward/Strength Potion + Fermented Spider Eye", true, false, true, false)
				).collect(Collectors.toList());

		for (VanillaPotion potion : potionList)
			internalPMap.put(potion.getName().toLowerCase(), potion);

		return internalPMap;
	}
	
	private static Map<String, VanillaPotionGroup> createPotionMap() {
		Map<String, VanillaPotionGroup> potionMap = new HashMap<>();
		potionMap.put("base", new VanillaPotionGroup("Base", Stream.of("Awkward", "Mundane", "Thick").collect(Collectors.toList())));
		potionMap.put("positive", new VanillaPotionGroup("Positive", Stream.of("FireResistance", "Healing", "Leaping", "NightVision", "SlowFalling", "Strength", "Swiftness", "WaterBreathing").collect(Collectors.toList())));
		potionMap.put("mixed", new VanillaPotionGroup("Mixed", Stream.of("TurtleMaster").collect(Collectors.toList())));
		potionMap.put("negative", new VanillaPotionGroup("Negative", Stream.of("Harming", "Poison", "Slowness", "Weakness").collect(Collectors.toList())));
		
		return potionMap;
	}
	
	private final Map<String, VanillaPotion> potions = createPotions();
	
	private final Map<String,VanillaPotionGroup> potionMap = createPotionMap();
	
	private final String[] defaultPotionGroups = new String[]{"base", "positive", "mixed", "negative"};
	
	private boolean sendPotionList(CommandSender sender, String group) {
		VanillaPotionGroup potiongroup = potionMap.get(group);

		sendMessage(sender, String.format("&b&l%s&b:", potionMap.get(group).getName()));
		sendMessage(sender, String.format("&7[%s&7]", potiongroup.getPotions().stream().parallel()
				.map(key -> potions.get(key).getFormattedName()).collect(Collectors.joining("&7, "))));

		return true;
	}
	
	private boolean sendHelp(CommandSender sender, String command) {
		if (command == null || command.isEmpty()) {
			return false;
		}

		switch (command) {
			case CommandType.INFO:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cIs that an enchantment? \"&4&o/mc enchant list&c\""));
				break;
			case CommandType.ENCHANT:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc enchant list [item]&c\" for available options!"));
				break;
			case CommandType.POTION:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc potion list&c\" for more help!"));	
			case CommandType.HELP: // Falls through	
			default:
				sender.sendMessage(ChatColor.GOLD + "Minecraft Information Help:");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc help &8- &7Brings this list up."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc info <enchantment> &8- &7Get information on an enchantment."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc enchant list [item] &8- &7Lists all enchantments, if you specify item, it'll show specifics."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc potion info <effect> &8- &7Lists the ingredients for the potion effect."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc potion list &8- &7Lists all vanilla effects."));
				return true;
		}

		return false;
	}

	private boolean sendHelp(CommandSender sender) {
		return sendHelp(sender, CommandType.HELP);
	}
	
//	Enchant
	public boolean onEnchantCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            // Send help to the user when the command is executed with no arguments -- can be replaced with any function call.
            return sendHelp(sender, CommandType.ENCHANT);
        }

        switch (args[1].toLowerCase()) {
            case CommandType.INFO:
                return handleEnchantInfoCommand(sender, args);
            case CommandType.LIST:
                return handleEnchantListCommand(sender, args);
            default:
                return sendHelp(sender, CommandType.ENCHANT);
        }
    }
	
	private boolean handleEnchantInfoCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to send info for a particular enchantment
		if (args.length < 2) {
			return sendHelp(sender, CommandType.INFO);
		}

		// User entered an enchantment that doesn't exist in our list
		if (!enchants.containsKey(args[1].toLowerCase())) {
			return sendHelp(sender, CommandType.INFO);
		}

		sender.sendMessage(enchants.get(args[1].toLowerCase()).toString());

		return true;
	}

	private boolean handleEnchantListCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to
		if (args.length < 2 || !args[1].equals("list")) {
			return sendHelp(sender, CommandType.ENCHANT);
		}

		if (args.length == 2) {
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			for (String group : defaultEnchantGroups) sendEnchantList(sender, group);
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			return true;
		}

		if (!enchantMap.containsKey(args[2]))
			return sendHelp(sender, CommandType.ENCHANT);

		return sendEnchantList(sender, args[2]);
	}

// Potion	
	public boolean onPotionCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            // Send help to the user when the command is executed with no arguments -- can be replaced with any function call.
            return sendHelp(sender, CommandType.POTION);
        }

        switch (args[1].toLowerCase()) {
            case CommandType.INFO:
                return handlePotionInfoCommand(sender, args);
            case CommandType.LIST:
                return handlePotionListCommand(sender, args);
            default:
                return sendHelp(sender, CommandType.POTION);
        }
    }

	private boolean handlePotionInfoCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to send info for a particular enchantment
		if (args.length < 2) {
			return sendHelp(sender, CommandType.INFO);
		}

		// User entered an enchantment that doesn't exist in our list
		if (!potions.containsKey(args[1].toLowerCase())) {
			return sendHelp(sender, CommandType.INFO);
		}

		sender.sendMessage(potions.get(args[1].toLowerCase()).toString());

		return true;
	}

	private boolean handlePotionListCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to
		if (args.length < 2 || !args[1].equals("list")) {
			return sendHelp(sender, CommandType.POTION);
		}

		if (args.length == 2) {
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			for (String group : defaultPotionGroups) sendPotionList(sender, group);
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			return true;
		}

		if (!potionMap.containsKey(args[2]))
			return sendHelp(sender, CommandType.POTION);

		return sendPotionList(sender, args[2]);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 1) {
			// Send help to the user when the command is executed with no arguments -- can be replaced with any function call.
			return sendHelp(sender);
		}

		switch (args[0].toLowerCase()) {
			case CommandType.HELP:
				return sendHelp(sender);
			case CommandType.ENCHANT:
				return onEnchantCommand(sender, args);
			case CommandType.POTION:
				return onPotionCommand(sender, args);
			default:
// 				return sendDefault(sender);
				return sendHelp(sender);
		}
	}
	
}

