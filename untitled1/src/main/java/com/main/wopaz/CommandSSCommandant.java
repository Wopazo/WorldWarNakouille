package com.main.wopaz;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSSCommandant implements CommandExecutor {
    private final SiegeOfOrlov plugin;

    public CommandSSCommandant(SiegeOfOrlov plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande doit être exécutée par un joueur.");
            return true;
        }

        Player player = (Player) sender;
        plugin.getMobManager().spawnSSCommandant(player.getLocation());
        player.sendMessage("Un commandant SS a été spawn.");
        return true;
    }
}
