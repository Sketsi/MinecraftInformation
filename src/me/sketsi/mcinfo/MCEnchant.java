package me.sketsi.mcinfo;

import java.util.List;

import org.bukkit.ChatColor;

public class MCEnchant extends MCCommand {
	
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
	
}
