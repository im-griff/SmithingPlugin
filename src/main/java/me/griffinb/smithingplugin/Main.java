package me.griffinb.smithingplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // This will be called when the plugin is enabled
        getLogger().info("Smithing Plugin Enabled!");
        // Register the /smithing command
        this.getCommand("smithing").setExecutor(new SmithingCommand());
    }

    @Override
    public void onDisable() {
        // This will be called when the plugin is disabled
        getLogger().info("Smithing Plugin Disabled!");
    }

    public class SmithingCommand implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Smithing Plugin Version: " + getDescription().getVersion());
                return true;
            } else {
                sender.sendMessage("This command can only be run by a player.");
                return true;
            }
        }
    }
}
