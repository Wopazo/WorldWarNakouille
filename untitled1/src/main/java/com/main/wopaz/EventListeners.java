package com.main.wopaz;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class EventListeners implements Listener {
    private final Random random = new Random();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getCustomName() == null) {
            return;
        }

        String entityName = event.getEntity().getCustomName();

        // Ajouter les drops spéciaux en fonction du nom de l'entité
        if (entityName.equals("Soldat SS")) {
            addDrop(event, 0.07, Material.DIAMOND); // 7% de chance de drop un diamant
            addDrop(event, 0.08, Material.IRON_INGOT); // 8% de chance de drop un lingot de fer
            addDrop(event, 0.20, Material.GOLD_NUGGET, "Badge Allemand", "Vous l'avez trouvé en tabassant les ennemis, qui sait ce qu'il peut faire"); // 20% de chance de drop un Badge Allemand
        } else if (entityName.equals("Officier SS")) {
            addDrop(event, 0.07, Material.DIAMOND); // 7% de chance de drop un diamant
            addDrop(event, 0.15, Material.IRON_INGOT); // 15% de chance de drop un lingot de fer
            addDrop(event, 0.40, Material.GOLD_NUGGET, "Badge Allemand", "Vous l'avez trouvé en tabassant les ennemis, qui sait ce qu'il peut faire"); // 40% de chance de drop un Badge Allemand
        } else if (entityName.equals("Commandant SS")) {
            addDrop(event, 0.20, Material.DIAMOND); // 20% de chance de drop un diamant
            addDrop(event, 0.05, Material.NETHERITE_INGOT); // 5% de chance de drop un lingot de netherite
            addDrop(event, 1.00, Material.GOLD_NUGGET, "Badge Allemand", "Vous l'avez trouvé en tabassant les ennemis, qui sait ce qu'il peut faire"); // 100% de chance de drop un Badge Allemand
            addDrop(event, 0.30, Material.GOLD_NUGGET, "Badge Allemand", "Vous l'avez trouvé en tabassant les ennemis, qui sait ce qu'il peut faire"); // 30% de chance de drop un deuxième Badge Allemand
        }
    }

    private void addDrop(EntityDeathEvent event, double chance, Material material) {
        if (random.nextDouble() < chance) {
            event.getDrops().add(new ItemStack(material));
        }
    }

    private void addDrop(EntityDeathEvent event, double chance, Material material, String name, String lore) {
        if (random.nextDouble() < chance) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(name);
                meta.setLore(List.<String>of(lore));
                item.setItemMeta(meta);
            }
            event.getDrops().add(item);
        }
    }
}
