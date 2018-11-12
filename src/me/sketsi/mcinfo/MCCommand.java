package me.sketsi.mcinfo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		/*		/mc info <enchantment>
		 * 			0			1
		 * 		/mc ven list [gear]
		 * 			0	1		2 
		 * 		/mc help
		 * 			0
		 */		
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
	
	
	
	
	