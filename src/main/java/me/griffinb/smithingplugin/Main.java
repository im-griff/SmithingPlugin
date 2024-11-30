package me.griffinb.smithingplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.type.SmithingTable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Main extends JavaPlugin implements Listener {

    private Set<Material> allowedItems;

    @Override
    public void onEnable() {
        // This will be called when the plugin is enabled
        getLogger().info("Smithing Plugin Enabled!");
        // Register events
        getServer().getPluginManager().registerEvents(this, this);
        // Register the /smithing command
        this.getCommand("smithing").setExecutor(new SmithingCommand());
        // Initialize allowed items
        initializeAllowedItems();
    }

    @Override
    public void onDisable() {
        // This will be called when the plugin is disabled
        getLogger().info("Smithing Plugin Disabled!");
    }

    private void initializeAllowedItems() {
        allowedItems = new HashSet<>();
        allowedItems.add(Material.IRON_BLOCK);
        allowedItems.add(Material.DIAMOND_BLOCK);
        allowedItems.add(Material.GOLD_BLOCK);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block != null && block.getType() == Material.SMITHING_TABLE) {
            Player player = event.getPlayer();
            player.openInventory(createSmithingStation());
            event.setCancelled(true);
        }
    }

    private Inventory createSmithingStation() {
        Inventory smithingStation = getServer().createInventory(null, InventoryType.SMITHING, "Smithing Station");
        return smithingStation;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Smithing Station")) {
            ItemStack currentItem = event.getCurrentItem();
            if (currentItem != null && !allowedItems.contains(currentItem.getType())) {
                event.setCancelled(true);
                ((Player) event.getWhoClicked()).sendMessage("You can only use Iron, Diamond, or Gold blocks in the Smithing Station.");
            }
        }
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
