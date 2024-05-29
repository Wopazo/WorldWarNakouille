package com.main.wopaz;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class MobManager {
    private final Random random = new Random();

    public void spawnSSGroup(Location location) {
        World world = location.getWorld();
        if (world == null) return;

        // Spawner trois soldats SS
        for (int i = 0; i < 3; i++) {
            Zombie soldier = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
            soldier.setCustomName("Soldat SS");
            soldier.setCustomNameVisible(true);
            soldier.setMaxHealth(25.0);
            soldier.setHealth(25.0);
            soldier.getEquipment().setHelmet(createItem(Material.IRON_HELMET, "Casque SS", "Description historique", Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 1));
            soldier.getEquipment().setChestplate(createItem(Material.LEATHER_CHESTPLATE, "Plastron SS", "Description historique", Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 1, Color.RED));
            soldier.getEquipment().setItemInMainHand(createItem(Material.STONE_SWORD, "Gode Mode"));
        }

        // Spawner un officier SS
        WitherSkeleton officer = (WitherSkeleton) world.spawnEntity(location, EntityType.WITHER_SKELETON);
        officer.setCustomName("Officier SS");
        officer.setCustomNameVisible(true);
        officer.setMaxHealth(40.0);
        officer.setHealth(40.0);
        officer.getEquipment().setChestplate(createItem(Material.IRON_CHESTPLATE, "Plastron Officier SS", "Description historique", Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 2));
        officer.getEquipment().setLeggings(createItem(Material.LEATHER_LEGGINGS, "Jean SS", "Description historique", Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 2, Color.BLACK));
        officer.getEquipment().setItemInMainHand(createItem(Material.DIAMOND_SWORD, "Matraque", Enchantment.DAMAGE_ALL, 2));
    }

    private ItemStack createItem(Material material, String name, String lore, Enchantment... enchantments) {
        return createItem(material, name, lore, Color.WHITE, enchantments);
    }

    private ItemStack createItem(Material material, String name, String lore, Color color, Enchantment... enchantments) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(List.of(lore));
            for (int i = 0; i < enchantments.length; i += 2) {
                meta.addEnchant(enchantments[i], enchantments[i + 1], true);
            }
            if (material == Material.LEATHER_CHESTPLATE || material == Material.LEATHER_LEGGINGS) {
                ((LeatherArmorMeta) meta).setColor(color);
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}
