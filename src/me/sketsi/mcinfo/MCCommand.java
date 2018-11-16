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
}

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

public class MCCommand implements CommandExecutor {
	private static void sendMessage(CommandSender sender, String message) {
		try {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		} catch (Exception ignored) {
		}
	}

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

		enchantMap.put("armor", new VanillaEnchantGroup("Armor", Stream.of("aquaaffinity", "blastprotection", "curseofbinding", "depthstrider", "featherfalling", "fireprotection", "frostwalker", "respiration", "projectileprotection", "protection", "thorns").collect(Collectors.toList())));
		enchantMap.put("bow", new VanillaEnchantGroup("Bow", Stream.of("flame", "infinity", "multishot", "piercing", "punch", "power", "quickcharge").collect(Collectors.toList())));
		enchantMap.put("other", new VanillaEnchantGroup("Other Enchantments", Stream.of("curseofvanishing", "mending", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("tools", new VanillaEnchantGroup("Tools", Stream.of("channeling", "efficiency", "fortune", "loyalty", "luckofthesea", "lure", "riptide", "silktouch").collect(Collectors.toList())));
		enchantMap.put("weapons", new VanillaEnchantGroup("Weapons", Stream.of("baneofarthropods", "fireaspect", "knockback", "looting", "sharpness", "smite", "sweepingedge").collect(Collectors.toList())));
		enchantMap.put("trident", new VanillaEnchantGroup("Trident", Stream.of("channeling", "curseofvanishing", "loyalty", "riptide", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("helmet", new VanillaEnchantGroup("Helmet", Stream.of("aquaaffinity", "blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "respiration", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("turtleshell", new VanillaEnchantGroup("Turtle Shell", Stream.of("aquaaffinity", "blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "respiration", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("chestplate", new VanillaEnchantGroup("Chestplate", Stream.of("blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("leggings", new VanillaEnchantGroup("Leggings", Stream.of("blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "thorns", "unbreaking").collect(Collectors.toList())));
		enchantMap.put("boots", new VanillaEnchantGroup("Boots", Stream.of("blastprotection", "curseofbinding", "curseofvanishing", "fireprotection", "mending", "projectileprotection", "protection", "thorns", "unbreaking").collect(Collectors.toList())));
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

	private final String[] defaultGroups = new String[]{"armor", "bow", "other", "tools", "weapons"};

	private boolean sendEnchantList(CommandSender sender, String group) {
		VanillaEnchantGroup enchantgroup = enchantMap.get(group);

		sendMessage(sender, String.format("&b&l%s&b:", enchantMap.get(group).getName()));
		sendMessage(sender, String.format("&7[%s&7]", enchantgroup.getEnchants().stream().parallel()
				.map(key -> enchants.get(key).getFormattedName()).collect(Collectors.joining("&7, "))));

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
			case CommandType.HELP: // Falls through
			default:
				sender.sendMessage(ChatColor.GOLD + "Minecraft Information Help:");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc info <enchantment> &8- &7Get information on an enchantment."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc help &8- &7Brings this list up."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mc enchant list [item] &8- &7Lists all enchantments, if specify armor/tool it'll show specific."));
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
		if (args.length < 2 || !args[1].equals("list")) {
			return sendHelp(sender, CommandType.ENCHANT);
		}

		if (args.length == 2) {
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			for (String group : defaultGroups) sendEnchantList(sender, group);
			sender.sendMessage(ChatColor.AQUA + "=========================================");
			return true;
		}

		if (!enchantMap.containsKey(args[2]))
			return sendHelp(sender, CommandType.ENCHANT);

		return sendEnchantList(sender, args[2]);
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
			default:
// 				return sendDefault(sender);
				return sendHelp(sender);
		}
	}

}
