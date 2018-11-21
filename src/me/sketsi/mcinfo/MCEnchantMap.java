package me.sketsi.mcinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MCEnchantMap extends MCEnchant {

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

}

