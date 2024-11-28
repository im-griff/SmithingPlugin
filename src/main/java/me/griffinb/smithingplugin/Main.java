package me.griffinb.smithingplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Code to run when the plugin is enabled
        getLogger().info("[SmithingPlugin] Plugin enabled!");
    }

    @Override
    public void onDisable() {
        // Code to run when the plugin is disabled
        getLogger().info("[SmithingPlugin] Plugin disabled!");
    }
}
