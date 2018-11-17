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

final class CommandType {
	static final String HELP = "help";
	static final String INFO = "info";
	static final String ENCHANT = "enchant";
	static final String POTION = "potion";
}
//	Enchant
class VanillaEnchant {
	private String name;
	private String description;
	private int maxLevel;
	private boolean isCurse;

	VanillaEnchant(String name, String description, int maxLevel) {
		this.name = name;
		this.description = description;
		this.maxLevel = maxLevel;
		this.isCurse = false;
	}

	VanillaEnchant(String name, String description, int maxLevel, boolean isCurse) {
		this.name = name;
		this.description = description;
		this.maxLevel = maxLevel;
		this.isCurse = isCurse;
	}

	String getName() {
		return name;
	}

	public String getFormattedName() {
		return ChatColor.translateAlternateColorCodes('&', (this.isCurse ? "&6" : "&a") + this.name);
	}

	public String toString() {
		return ChatColor.translateAlternateColorCodes('&', String.format("%s&l%s &b(1 - %d): &3%s",
				(this.isCurse ? "&c" : "&b"), this.name, this.maxLevel, this.description));
	}
}

class VanillaEnchantGroup {
	private String name;
	private List<String> enchants;

	VanillaEnchantGroup(String name, List<String> enchants) {
		this.name = name;
		this.enchants = enchants;
	}

	String getName() {
		return name;
	}

	List<String> getEnchants() {
		return enchants;
	}
}
//	Potion
class VanillaPotion{
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
		return ChatColor.translateAlternateColorCodes('&', (this.isNegative ? "&6" : "&a") + this.name);
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
// Enchants
	private static Map<String, VanillaEnchant> createEnchants() {
		Map<String, VanillaEnchant> internalMap = new HashMap<>();
		List<VanillaEnchant> enchantList = Stream.of(
				new VanillaEnchant("AquaAffinity", "Increases underwater mining rate.", 1),
				new VanillaEnchant("BaneOfArthropods", "Increases damage to arthropods.", 5),
				new VanillaEnchant("BlastProtection", "Reduces explosion damage.", 4),
				new VanillaEnchant("Channeling", "Trident \"channels\" a bolt of lightning towards a hit entity. Only functions during thunderstorms.", 1),
				new VanillaEnchant("CurseOfBinding", "Prevents removal of items (except for in creative).", 1, true),
				new VanillaEnchant("CurseOfVanishing", "Item Destroyed on death.", 1, true),
				new VanillaEnchant("DepthStrider", "Increases underwater movement speed.", 3),
				new VanillaEnchant("Efficiency", "Increases mining speed.", 5),
				new VanillaEnchant("FeatherFalling", "Reduces fall damage.", 4),
				new VanillaEnchant("FireAspect", "Sets target on fire.", 2),
				new VanillaEnchant("FireProtection", "Reduces fire damage.", 4),
				new VanillaEnchant("Flame", "Arrows set target on fire.", 1),
				new VanillaEnchant("Fortune", "Increases block drops.", 3),
				new VanillaEnchant("FrostWalker", "Turns water beneath the player into ice.", 2),
				new VanillaEnchant("Impaling", "Trident deals additional damage to mobs that spawn naturally in the ocean.", 5),
				new VanillaEnchant("Infinity", "Shooting consumes no arrows.", 1),
				new VanillaEnchant("Knockback", "Increases knockback.", 2),
				new VanillaEnchant("Looting", "Increases mob loot.", 3),
				new VanillaEnchant("Loyalty", "Trident returns after being thrown. Higher levels reduce return time.", 3),
				new VanillaEnchant("LuckofTheSea", "Increases fishing luck.", 3),
				new VanillaEnchant("Lure", "Increases fishing rate.", 3),
				new VanillaEnchant("Mending", "Repair items with experience.", 1),
				new VanillaEnchant("Multishot", "Shoot 3 arrows at the cost of one.", 1),
				new VanillaEnchant("Piercing", "Arrows pass through multiple entities.", 4),
				new VanillaEnchant("Power", "Increases arrow damage.", 5),
				new VanillaEnchant("ProjectileProtection", "Reduces projectile damage.", 4),
				new VanillaEnchant("Protection", "Reduces most types of damage.", 4),
				new VanillaEnchant("Punch", "Increases arrow knockback.", 2),
				new VanillaEnchant("QuickCharge", "Decreases crossbow reloading time.", 3),
				new VanillaEnchant("Respiration", "Extends underwater breathing time.", 3),
				new VanillaEnchant("Riptide", "Trident launches player with itself when thrown. Only functions in water or rain.", 3),
				new VanillaEnchant("Sharpness", "Increases damage.", 5),
				new VanillaEnchant("SilkTouch", "Mined blocks drop themselves.", 1),
				new VanillaEnchant("Smite", "Increases damage to undead mobs.", 5),
				new VanillaEnchant("SweepingEdge", "Increases sweeping attack damage.", 3),
				new VanillaEnchant("Thorns", "Damages attackers.", 3),
				new VanillaEnchant("Unbreaking", "Increases effective durability.", 3)
		).collect(Collectors.toList());

		for (VanillaEnchant enchant : enchantList)
			internalMap.put(enchant.getName().toLowerCase(), enchant);

		return internalMap;
	}

	private static Map<String, VanillaEnchantGroup> createEnchantMap() {
		Map<String, VanillaEnchantGroup> enchantMap = new HashMap<>();
		// /mc enchants list
		enchantMap.put("armor", new VanillaEnchantGroup("Armor", Stream.of("aquaaffinity", "blastprotection", "curseofbinding", "depthstrider", "featherfalling", "fireprotection", "frostwalker", "respiration", "projectileprotection", "protection", "thorns").collect(Collectors.toList())));
		enchantMap.put("bows", new VanillaEnchantGroup("Bows", Stream.of("flame", "infinity", "multishot", "piercing", "punch", "power", "quickcharge").collect(Collectors.toList())));
		enchantMap.put("other", new VanillaEnchantGroup("Other Enchantments", Stream.of("curseofvanishing", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("tools", new VanillaEnchantGroup("Tools", Stream.of("channeling", "efficiency", "fortune", "loyalty", "luckofthesea", "lure", "riptide", "silktouch").collect(Collectors.toList())));
		enchantMap.put("weapons", new VanillaEnchantGroup("Weapons", Stream.of("baneofarthropods", "fireaspect", "impaling",  "knockback", "looting", "sharpness", "smite", "sweepingedge").collect(Collectors.toList())));
		// /mc enchants list <item>
		enchantMap.put("helmet", new VanillaEnchantGroup("Helmet", Stream.of("aquaaffinity", "blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "respiration", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("turtleshell", new VanillaEnchantGroup("Turtle Shell", Stream.of("aquaaffinity", "blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "respiration", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("chestplate", new VanillaEnchantGroup("Chestplate", Stream.of("blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("leggings", new VanillaEnchantGroup("Leggings", Stream.of("blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("boots", new VanillaEnchantGroup("Boots", Stream.of("blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("bow", new VanillaEnchantGroup("Bow", Stream.of("curseofvanishing", "flame", "infinity", "mending", "punch", "power").collect(Collectors.toList())));
		enchantMap.put("crossbow", new VanillaEnchantGroup("Crossbow", Stream.of("multishot", "piercing", "quickcharge").collect(Collectors.toList())));
		enchantMap.put("trident", new VanillaEnchantGroup("Trident", Stream.of("channeling", "curseofvanishing", "impaling", "loyalty", "riptide", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("sword", new VanillaEnchantGroup("Sword", Stream.of("baneofarthropods", "curseofvanishing", "fireaspect", "knockback", "looting", "mending", "sharpness", "smite", "sweepingedge", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("pickaxe", new VanillaEnchantGroup("Pickaxe", Stream.of("curseofvanishing", "efficiency", "fortune", "mending", "silktouch", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("shovel", new VanillaEnchantGroup("Shovel", Stream.of("curseofvanishing", "efficiency", "fortune", "mending", "silktouch", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("axe", new VanillaEnchantGroup("Axe", Stream.of("baneofarthropods", "curseofvanishing", "efficiency", "fireaspect", "fortune", "mending", "sharpness", "smite", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("hoe", new VanillaEnchantGroup("Hoe", Stream.of("curseofvanishing", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("fishingrod", new VanillaEnchantGroup("Fishing Rod", Stream.of("curseofvanishing", "luckofthesea", "lure", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("shield", new VanillaEnchantGroup("Shield", Stream.of("curseofvanishing", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("shears", new VanillaEnchantGroup("Shears", Stream.of("curseofvanishing", "efficiency", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("elytra", new VanillaEnchantGroup("Elytra", Stream.of("curseofbinding", "curseofvanishing", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("pumpkin", new VanillaEnchantGroup("Pumpkin", Stream.of("curseofbinding", "curseofvanishing").collect(Collectors.toList())));
		enchantMap.put("head", new VanillaEnchantGroup("Head", Stream.of("curseofbinding", "curseofvanishing").collect(Collectors.toList())));
		enchantMap.put("skull", new VanillaEnchantGroup("Skull", Stream.of("curseofbinding", "curseofvanishing").collect(Collectors.toList())));
		enchantMap.put("flintandsteel", new VanillaEnchantGroup("Flint and Steel", Stream.of("curseofvanishing", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("book", new VanillaEnchantGroup("Book", Stream.of("aquaaffinity", "baneofarthropods", "blastprotection", "channeling", "curseofbinding", "curseofvanishing", "depthstrider", "efficiency", "featherfalling",
				"fireaspect", "fireprotection", "flame", "fortune", "frostwalker", "impaling", "infinity", "knockback", "looting", "loyalty", "luckofthesealure", "mending", "multishot", "piercing", "power",
				"projectileprotection", "protection", "punch", "quickcharge", "respiration", "riptide", "sharpness", "silktouch", "smite", "sweepingedge", "thorns", "unbreaking").collect(Collectors.toList())));

		return enchantMap;
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
	
	private boolean sendPotionList(CommandSender sender, String group) {
		VanillaPotionGroup potiongroup = potionMap.get(group);

		sendMessage(sender, String.format("&b&l%s&b:", potionMap.get(group).getName()));
		sendMessage(sender, String.format("&7[%s&7]", potiongroup.getPotions().stream().parallel()
				.map(key -> potions.get(key).getFormattedName()).collect(Collectors.joining("&7, "))));

		return true;
	}
	
	@SuppressWarnings("unused")
	private boolean sendDefault(CommandSender sender) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc help&c\"!"));
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
//				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc potion brew [effect] &8- &7Lists the ingredients for the potion effect."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc potion list &8- &7Lists all vanilla effects."));
				return true;
		}

		return false;
	}

	private boolean sendHelp(CommandSender sender) {
		return sendHelp(sender, CommandType.HELP);
	}

	private boolean handleInfoCommand(CommandSender sender, String[] args) {
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

	private boolean handleEnchantCommand(CommandSender sender, String[] args) {
		// User didn't enter enough arguments to
		if (args.length < 2 || !args[1].equals("list") || !args[1].equals("info")) {
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
	
	private boolean handlePotionCommand(CommandSender sender, String[] args) {
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
			case CommandType.INFO:
				return handleInfoCommand(sender, args);
			case CommandType.ENCHANT:
				return handleEnchantCommand(sender, args);
			case CommandType.POTION:
				return handlePotionCommand(sender, args);
			default:
// 				return sendDefault(sender);
				return sendHelp(sender);
		}
	}

	

}
