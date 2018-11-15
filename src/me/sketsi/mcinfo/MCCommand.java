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

	public VanillaEnchantGroup(String name, List<String> enchants) {
		this.name = name;
		this.enchants = enchants;
	}

	public String getName() {
		return name;
	}

	public List<String> getEnchants() {
		return enchants;
	}
}

public class MCCommand implements CommandExecutor {
	private static boolean sendMessage(CommandSender sender, String message) {
		try {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		} catch (Exception e) {
			return false;
		}
		return true;
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
				new VanillaEnchant("Silktouch", "Mined blocks drop themselves.", 1),
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
		enchantMap.put("tools", new VanillaEnchantGroup("Tools", Stream.of("efficiency", "fortune", "silktouch", "luckofthesea", "lure").collect(Collectors.toList())));
		enchantMap.put("trident", new VanillaEnchantGroup("Trident", Stream.of("channeling", "impaling", "loyalty", "riptide").collect(Collectors.toList())));
		enchantMap.put("weapons", new VanillaEnchantGroup("Weapons", Stream.of("baneofarthropods", "fireaspect", "knockback", "looting", "sharpness", "smite", "sweepingedge").collect(Collectors.toList())));
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
		enchantMap.put("book", new VanillaEnchantGroup("Book", Stream.of("aquaaffinity", "baneofarthropods", "blastprotection", "channeling", "curseofbinding", "curseofvanishing", "depthstrider", "efficiency", "featherfalling",
				"fireaspect", "fireprotection", "flame", "fortune", "frostwalker", "impaling", "infinity", "knockback", "looting", "loyalty", "luckofthesealure", "mending", "multishot", "piercing", "power",
				"projectileprotection", "protection", "punch", "quickcharge", "respiration", "riptide", "sharpness", "silktouch", "smite", "sweepingedge", "thorns", "unbreaking").collect(Collectors.toList())));

		return enchantMap;
	}

	private final Map<String, VanillaEnchant> enchants = createEnchants();

	private final Map<String, VanillaEnchantGroup> enchantMap = createEnchantMap();

	private final String[] defaultGroups = new String[]{"armor", "bow", "other", "tools", "trident", "weapons"};

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

	private boolean sendEnchantList(CommandSender sender, String group) {
		VanillaEnchantGroup enchantgroup = enchantMap.get(group);

		sendMessage(sender, String.format("&b&l%s&b:", enchantMap.get(group).getName()));
		sendMessage(sender, String.format("&7[%s&7]", enchantgroup.getEnchants().stream().parallel()
				.map(key -> enchants.get(key).getName()).collect(Collectors.joining(", "))));

		return true;
	}

	private boolean sendDefault(CommandSender sender) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc help\"&c!"));
		return true;
	}

	private boolean sendHelp(CommandSender sender, String command) {
		if (command == null || command.isEmpty()) {
			return false;
		}

		switch (command) {
			case CommandType.INFO:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cIs that an enchantment? &4&o\"/mc ven list\""));
				break;
			case CommandType.ENCHANT:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc ven list [item]\" &cfor available options!"));
				break;
			case CommandType.HELP: // Falls through
			default:
				sender.sendMessage(ChatColor.GOLD + "Minecraft Information Help:");
				sender.sendMessage(ChatColor.RED + "/mc info <enchantment>" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Get information on an enchantment.");
				sender.sendMessage(ChatColor.RED + "/mc help" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Brings this list up.");
				sender.sendMessage(ChatColor.RED + "/mc ven list [item]" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Lists all enchantments, if specify armor/tool it'll show specific.");
				return true;
		}

		return false;
	}

	private boolean sendHelp(CommandSender sender) {
		return sendHelp(sender, CommandType.HELP);
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


		if (args.length >= 1) {
			//	mc info
			if ((args[0].equalsIgnoreCase("info")) && (args.length == 0)) {
				//	mc info <enchantment>
				if (args[1].equalsIgnoreCase("AquaAffinity")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lAqua Affinity &b(1 - 1): &3Increases underwater mining rate."));
				}
				else if (args[1].equalsIgnoreCase("BaneOfArthropods")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lBane Of Arthropods &b(1 - 5): &3Increases damage to arthropods."));
				}
				else if (args[1].equalsIgnoreCase("BlastProtection")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lBlast Protection &b(1 - 4): &3Reduces explosion damage."));
				}
				else if (args[1].equalsIgnoreCase("Channeling")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lChanneling &b(1 - 1): &3Trident \"channels\" a bolt of lightning towards a hit entity. Only functions during thunderstorms."));
				}
				else if (args[1].equalsIgnoreCase("CurseOfBinding")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lCurse of Binding &c(1 - 1): &3Prevents removal of items (except for in creative)."));
				}
				else if (args[1].equalsIgnoreCase("CurseOfVanishing")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lCurse of Vanishing &c(1 - 1): &3Item Destroyed on death."));
				}
				else if (args[1].equalsIgnoreCase("DepthStrider")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lDepth Strider &b(1 - 3): &3 	Increases underwater movement speed."));
				}
				else if (args[1].equalsIgnoreCase("Efficiency")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lEfficiency &b(1 - 5): &3Increases mining speed."));
				}
				else if (args[1].equalsIgnoreCase("FeatherFalling")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFeather Falling &b(1 - 4): &3Reduces fall damage."));
				}
				else if (args[1].equalsIgnoreCase("FireAspect")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFire Aspect &b(1 - 2): &3Sets target on fire."));
				}
				else if (args[1].equalsIgnoreCase("FireProtection")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFire Protection &b(1 - 4): &3Reduces fire damage."));
				}
				else if (args[1].equalsIgnoreCase("Flame")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFlame &b(1 - 1): &3Arrows set target on fire."));
				}
				else if (args[1].equalsIgnoreCase("Fortune")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFortune &b(1 - 3): &3Increases block drops."));
				}
				else if (args[1].equalsIgnoreCase("FrostWalker")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFrost Walker &b(1 - 2): &3Turns water beneath the player into ice."));
				}
				else if (args[1].equalsIgnoreCase("Impaling")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lImpaling &b(1 - 5): &3Trident deals additional damage to mobs that spawn naturally in the ocean."));
				}
				else if (args[1].equalsIgnoreCase("Infinity")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lInfinity &b(1 - 1): &3Shooting consumes no arrows."));
				}
				else if (args[1].equalsIgnoreCase("Knockback")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lKnockback &b(1 - 2): &3Increases knockback."));
				}
				else if (args[1].equalsIgnoreCase("Looting")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lLooting &b(1 - 3): &3Increases mob loot."));
				}
				else if (args[1].equalsIgnoreCase("Loyalty")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lLoyalty &b(1 - 3): &3Trident returns after being thrown. Higher levels reduce return time."));
				}
				else if (args[1].equalsIgnoreCase("LuckofTheSea")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lLock of the Sea &b(1 - 3): &3Increases fishing luck."));
				}
				else if (args[1].equalsIgnoreCase("Lure")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lLure &b(1 - 3): &3Increases fishing rate."));
				}
				else if (args[1].equalsIgnoreCase("Mending")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lMending &b(1 - 1): &3Repair items with experience."));
				}
				else if (args[1].equalsIgnoreCase("Multishot")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lMultishot &b(1 - 1): &3Shoot 3 arrows at the cost of one."));
				}
				else if (args[1].equalsIgnoreCase("Piercing")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPiercing &b(1 - 4): &3Arrows pass through multiple entities."));
				}
				else if (args[1].equalsIgnoreCase("Power")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPower &b(1 - 5): &3Increases arrow damage."));
				}
				else if (args[1].equalsIgnoreCase("ProjectileProtection")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lProjectile Protection &b(1 - 4): &3Reduces projectile damage."));
				}
				else if (args[1].equalsIgnoreCase("Protection")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lProtection &b(1 - 4): &3Reduces most types of damage."));
				}
				else if (args[1].equalsIgnoreCase("Punch")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPunch &b(1 - 2): &3Increases arrow knockback."));
				}
				else if (args[1].equalsIgnoreCase("QuickCharge")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lQuick Charge &b(1 - 3): &3Decreases crossbow reloading time."));
				}
				else if (args[1].equalsIgnoreCase("Respiration")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lRespiration &b(1 - 3): &3Extends underwater breathing time."));
				}
				else if (args[1].equalsIgnoreCase("Riptide")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lRiptide &b(1 - 3): &3Trident launches player with itself when thrown. Only functions in water or rain."));
				}
				else if (args[1].equalsIgnoreCase("Sharpness")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lSharpness &b(1 - 5): &3Increases damage."));
				}
				else if (args[1].equalsIgnoreCase("Silktouch")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lSilk Touch &b(1 - 1): &3Mined blocks drop themselves."));
				}
				else if (args[1].equalsIgnoreCase("Smite")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lSmite &b(1 - 5): &3Increases damage to undead mobs."));
				}
				else if (args[1].equalsIgnoreCase("SweepingEdge")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lSweeping Edge &b(1 - 3): &3Increases sweeping attack damage."));
				}
				else if (args[1].equalsIgnoreCase("Thorns")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lThorns &b(1 - 3): &3Damages attackers."));
				}
				else if (args[1].equalsIgnoreCase("Unbreaking")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lUnbreaking &b(1 - 3): &3Increases effective durability."));

					// If /mc info <enchantment> is invalid
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cIs that an enchantment? &4&o\"/mc ven list\""));
				}



				//	List Command
				//	mc ven
			} else if (args[0].equalsIgnoreCase("ven") && (args.length == 0)) {
				//	mc ven list
				if (args[1].equalsIgnoreCase("list") && (args.length == 1)) {
					player.sendMessage(ChatColor.AQUA + "=========================================");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lArmor&b:"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aAquaAffinity&7, &aBlastProtection&7, &6CurseofBinding&7, &aDepthStrider&7, &aFeatherFalling&7, &aFireProtection&7, &aFrostWalker&7, &aRespiration&7, &aProjectileProtection&7, &aProtection&7, &aThorns&7]"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lBow&b:"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aFlame&7, &aInfinity&7, &aMultishot&7, &aPiercing&7, &aPunch&7, &aPower&7, &aQuickCharge&7&7]"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lOther Enchants&b:"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseofVanishing&7, &aMending&7, &aUnbreaking&7]"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lTools&b:"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aEfficiency&7, &aFortune&7, &aSilkTouch&7, &aLuckoftheSea&7, &aLure&7]"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lTrident&b:"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aChanneling&7, &aImpaling&7, &aLoyalty&7, &aRiptide&7]"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lWeapons&b:"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aBaneofArthropods&7, &aFireAspect&7, &aKnockback&7, &aLooting&7, &aSharpness&7, &aSmite&7, &aSweepingEdge&7]"));
					player.sendMessage(ChatColor.AQUA + "=========================================");

					//	If player uses /mc ven list <item>, this will start.
					if (args[2].equalsIgnoreCase("Helmet")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lHelmet&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aAquaAffinity&7, &aBlastProtection&7, &6CurseOfBinding&7, &6CurseOfVanishing&7, &aFireProtection&7, &aMending&7, &aProjectileProtection&7, &aProtection&7, &aRespiration&7, &aThorns&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("TurtleShell")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lTurtle Shell&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aAquaAffinity&7, &aBlastProtection&7, &6CurseOfBinding&7, &6CurseOfVanishing&7, &aFireProtection&7, &aMending&7, &aProjectileProtection&7, &aProtection&7, &aRespiration&7, &aThorns&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Chestplate")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lChestplate&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aBlastProtection&7, &6CurseOfBinding&7, &6CurseOfVanishing&7, &aFireProtection&7, &aMending&7, &aProjectileProtection&7, &aProtection&7, &aThorns&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Leggings")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lLeggings&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aBlastProtection&7, &6CurseOfBinding&7, &6CurseOfVanishing&7, &aFireProtection&7, &aMending&7, &aProjectileProtection&7, &aProtection&7, &aThorns&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Boots")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lBoots&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aBlastProtection&7, &6CurseOfBinding&7, &6CurseOfVanishing&7, &aFireProtection&7, &aMending&7, &aProjectileProtection&7, &aProtection&7, &aThorns&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Sword")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lSword&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aBaneOfArthropods&7, &6CurseOfVanishing&7, &aFireAspect&7, &aKnockback&7, &aLooting&7, &aMending&7, &aSharpness&7, &aSmite&7, &aSweepingEdge&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Pickaxe")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPickaxe&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfVanishing&7, &aEfficiency&7, &aFortune&7, &aMending&7, &aSilkTouch&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Shovel")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lShovel&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfVanishing&7, &aEfficiency&7, &aFortune&7, &aMending&7, &aSilkTouch&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Axe")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lAxe&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aBaneOfArthropods&7, &6CurseOfVanishing&7, &aEfficiency&7, &aFireAspect&7, &aFortune&7, &aMending&7, &aSharpness&7, &aSmite&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Hoe")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lHoe&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfVanishing&7, &aMending&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Bow")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lBow&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aFlame&7, &aInfinity&7, &aMultishot&7, &aPiercing&7, &aPunch&7, &aPower&7, &aQuickCharge&7&7]"));
					}
					else if (args[2].equalsIgnoreCase("Trident")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lTrident&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aChanneling&7, &aImpaling&7, &aLoyalty&7, &aRiptide, &aMending, &aUnbreaking&7&7]"));
					}
					else if (args[2].equalsIgnoreCase("FishingRod")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFishing Rod&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfVanishing&7, &aLuckOfTheSea&7, &aLure&7, &aMending&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Shield")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lShield&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfVanishing&7, &aMending&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Shears")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lShears&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfVanishing&7, &aEfficiency&7, &aMending&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Elytra")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lElytra&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfBinding&7, &6CurseOfVanishing&7, &aMending&7, &aUnbreaking&7]"));
					}
					else if (args[2].equalsIgnoreCase("Pumpkin")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPumpkin&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfBinding&7, &6CurseOfVanishing&7]"));
					}
					else if (args[2].equalsIgnoreCase("Head")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lHead&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfBinding&7, &6CurseOfVanishing&7]"));
					}
					else if (args[2].equalsIgnoreCase("Skull")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lSkull&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6CurseOfBinding&7, &6CurseOfVanishing&7]"));
					}
					else if (args[2].equalsIgnoreCase("Book")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lShears&b:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aAquaAffinity&7, &aBaneofArthropods&7, &aBlastProtection&7, &aChanneling&7, &6CurseofBinding&7, &6CurseOfVanishing&7,"
								+ " &aDepthStrider&7, &aEfficiency&7, &aFeatherFalling&7, &aFireAspect&7, &aFireProtection&7, &aFlame&7, &aFortune&7, &aFrostWalker&7, &aImpaling&7, &aInfinity&7, &aKnockback&7,"
								+ " &aLooting&7, &aLoyalty&7, &aLuckoftheSeaLure&7, &aMending&7, &aMultishot&7, &aPiercing&7, &aPower&7, &aProjectileProtection&7, &aProtection&7, &aPunch&7, &aQuickCharge&7,"
								+ " &aRespiration&7, &aRiptide&7, &aSharpness&7, &aSilkTouch&7, &aSmite&7, &aSweepingEdge&7, &aThorns&7, &aUnbreaking&7]"));
					} else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cCan you enchant that?"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&fAxe&7, &fBook&7, &fBoots&7, &fBow&7, &fChestplate&7, &fElytra&7, &fFishingRod&7, &fHead&7, &fHelmet&7, &fHoe&7, &fLeggings&7, &fPickaxe&7, &fPumpkin&7, &fShears&7, &fShield&7, &fShovel&7, &fSkull&7, &fSword&7, &fTrident&7, &fTurtleShell&7]"));
					}
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc ven list [item]\" &cfor available options!"));
				}



				// Help Command
			} else if (args[0].equalsIgnoreCase("help")){
				player.sendMessage(ChatColor.GOLD + "Minecraft Information Help:");
				player.sendMessage(ChatColor.RED + "/mc info <enchantment>" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY +  "Get information on an enchantment.");
				player.sendMessage(ChatColor.RED + "/mc help" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Brings this list up.");
				player.sendMessage(ChatColor.RED + "/mc ven list [item]" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Lists all enchantments, if specify armor/tool it'll show specific.");
			}

			// If the command doesn't match anything, send them this.
		} else {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6MCInfo: &cUse \"&4&o/mc help\"&c!"));
		}

		return false;
	}

}
