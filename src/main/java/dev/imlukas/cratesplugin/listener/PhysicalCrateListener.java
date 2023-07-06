package dev.imlukas.cratesplugin.listener;

import dev.imlukas.cratesplugin.CratesPlugin;
import dev.imlukas.cratesplugin.crate.impl.AbstractCrate;
import dev.imlukas.cratesplugin.crate.registry.CrateRegistry;
import dev.imlukas.cratesplugin.menu.PreviewCrateMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PhysicalCrateListener implements Listener {
    private final CratesPlugin plugin;
    private final CrateRegistry crateRegistry;

    public PhysicalCrateListener(CratesPlugin plugin) {
        this.plugin = plugin;
        this.crateRegistry = plugin.getCrateRegistry();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.hasBlock()) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block == null) {
            System.out.println("Block is null");
            return;
        }

        Location blockLocation = block.getLocation();
        AbstractCrate crate = crateRegistry.getCrateByLocation(blockLocation);

        if (crate == null) {
            return;
        }

        event.setCancelled(true);

        if (event.getAction().isRightClick()) {
            if (crate.isUnboxing(player)) {
                return;
            }
            crate.unbox(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> crate.setUnboxing(player, false), 100L);
        }

        if (event.getAction().isLeftClick()) {
            new PreviewCrateMenu(player, crate);
        }
    }
}
