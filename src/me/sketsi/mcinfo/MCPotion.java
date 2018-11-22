package me.sketsi.mcinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

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

public class MCPotion extends MCCommand {

	private Map<String, VanillaPotion> createPotions() {
		Map<String, VanillaPotion> internalMap = new HashMap<>();
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
			internalMap.put(potion.getName().toLowerCase(), potion);

		return internalMap;
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

//	Potion Command	
	public boolean onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            // Send help to the user when the command is executed with no arguments -- can be replaced with any function call.
            return sendHelp(sender, CommandType.POTION);
        }

        switch (args[1].toLowerCase()) {
            case CommandType.INFO:
                return handleInfoCommand(sender, args);
            case CommandType.LIST:
                return handleListCommand(sender, args);
            default:
                return sendHelp(sender, CommandType.POTION);
        }
    }	
	
//	Help	
	private boolean sendHelp(CommandSender sender, String command) {
		if (command == null || command.isEmpty()) {
			return false;
		}

		switch (command) {
			case CommandType.INFO:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cIs that a potion effect? \"&4&o/mc potion info <effect>&c\""));
				break;
			case CommandType.LIST:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc potion list [type]&c\" for available options!"));
				break;	
			case CommandType.HELP: // Falls through	
			default:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Minecraft Information &bPotion&6:"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/mc &bpotion &einfo &c<effect> &8- &7Get information on a potion."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/mc &bpotion &elist &a<[group]> &8- &7Lists all effects, if you specify group, it'll show the category."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &c<Required> &a<[Optional]>"));
				return true;
		}

		return false;
	}
	
//	Info	
	private boolean handleInfoCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to send info for a particular potionment
		if (args.length < 2) {
			return sendHelp(sender, CommandType.INFO);
		}

		// User entered an potionment that doesn't exist in our list
		if (!potions.containsKey(args[1].toLowerCase())) {
			return sendHelp(sender, CommandType.INFO);
		}

		sender.sendMessage(potions.get(args[1].toLowerCase()).toString());

		return true;
	}
	
//	List
	private boolean handleListCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to
		if (args.length < 2 || !args[1].equals("list")) {
			return sendHelp(sender, CommandType.LIST);
		}

		if (args.length == 2) {
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			for (String group : defaultPotionGroups) sendPotionList(sender, group);
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			return true;
		}

		if (!potionMap.containsKey(args[2]))
			return sendHelp(sender, CommandType.LIST);

		return sendPotionList(sender, args[2]);
	}
	
	private boolean sendPotionList(CommandSender sender, String group) {
		VanillaPotionGroup potiongroup = potionMap.get(group);

		sendMessage(sender, String.format("&b&l%s&b:", potionMap.get(group).getName()));
		sendMessage(sender, String.format("&7[%s&7]", potiongroup.getPotions().stream().parallel()
				.map(key -> potions.get(key).getFormattedName()).collect(Collectors.joining("&7, "))));

		return true;
	}

}