package com.main.wopaz;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class MobSpawnerTask extends BukkitRunnable {
    private final SiegeOfOrlov plugin;
    private final Random random = new Random();

    public MobSpawnerTask(SiegeOfOrlov plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        World world = Bukkit.getWorld("SiegeOfOrlov");
        if (world == null) return;

        // Pour chaque zone de 100x100 blocs, vérifiez la présence de joueurs et générez des groupes de mobs
        for (int i = 0; i < 4; i++) {
            Location spawnLocation = getRandomLocationWithinWorld(world);
            if (spawnLocation == null) continue;

            // Vérifier la proximité d'un joueur
            boolean playerNearby = isPlayerNearby(spawnLocation, 100);
            if (playerNearby) {
                plugin.getMobManager().spawnSSGroup(spawnLocation);
            }
        }

        // 5% de chance de faire apparaître le Commandant SS toutes les 5 minutes
        if (random.nextDouble() < 0.05) {
            for (Player player : world.getPlayers()) {
                Location commandantSpawnLocation = getRandomLocationAroundPlayer(player);
                if (commandantSpawnLocation != null) {
                    plugin.getMobManager().spawnSSCommandant(commandantSpawnLocation);
                    Bukkit.broadcastMessage("Commandant SS : Un russe, à l'attaque !");
                }
            }
        }
    }

    private Location getRandomLocationWithinWorld(World world) {
        int x = random.nextInt(100) - 50;
        int z = random.nextInt(100) - 50;
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }

    private boolean isPlayerNearby(Location location, double radius) {
        for (Player player : location.getWorld().getPlayers()) {
            if (player.getLocation().distance(location) <= radius) {
                return true;
            }
        }
        return false;
    }

    private Location getRandomLocationAroundPlayer(Player player) {
        double angle = random.nextDouble() * Math.PI * 2; // Angle aléatoire
        double radius = random.nextDouble() * 20; // Rayon aléatoire jusqu'à 20 blocs
        double x = player.getLocation().getX() + radius * Math.cos(angle);
        double z = player.getLocation().getZ() + radius * Math.sin(angle);
        double y = player.getWorld().getHighestBlockYAt((int) x, (int) z);
        return new Location(player.getWorld(), x, y, z);
    }
}
