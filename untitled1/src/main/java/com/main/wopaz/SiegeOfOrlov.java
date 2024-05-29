package com.main.wopaz;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class SiegeOfOrlov extends JavaPlugin {
    private final MobManager mobManager = new MobManager();

    @Override
    public void onEnable() {
        // Enregistrer les commandes
        this.getCommand("ssgroup").setExecutor(new CommandSSGroup(this));
        this.getCommand("sscommandant").setExecutor(new CommandSSCommandant(this));
        this.getCommand("ssboss").setExecutor(new CommandSSBoss(this));

        // Enregistrer les événements
        Bukkit.getPluginManager().registerEvents(new EventListeners(), this);

        // Démarrer la tâche de spawn des mobs
        new MobSpawnerTask(this).runTaskTimer(this, 0, 1200); // Toutes les minutes (1200 ticks)
    }

    @Override
    public void onDisable() {
        // Logic to execute on plugin disable
    }

    public MobManager getMobManager() {
        return mobManager;
    }
}

