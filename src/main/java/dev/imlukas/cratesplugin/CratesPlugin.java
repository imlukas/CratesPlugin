package dev.imlukas.cratesplugin;

import dev.imlukas.cratesplugin.crate.impl.AbstractCrate;
import dev.imlukas.cratesplugin.crate.registry.CrateParser;
import dev.imlukas.cratesplugin.crate.registry.CrateRegistry;
import dev.imlukas.cratesplugin.item.registry.CrateItemRegistry;
import dev.imlukas.cratesplugin.item.registry.ItemParser;
import dev.imlukas.cratesplugin.listener.PhysicalCrateListener;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CratesPlugin extends JavaPlugin {

    private CrateItemRegistry crateItemRegistry;
    private ItemParser itemParser;
    private CrateRegistry crateRegistry;
    private CrateParser crateParser;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.crateItemRegistry = new CrateItemRegistry();
        this.itemParser = new ItemParser(this);
        this.crateRegistry = new CrateRegistry();
        this.crateParser = new CrateParser(this);

        registerListener(new PhysicalCrateListener(this));
    }

    @Override
    public void onDisable() {
        for (AbstractCrate crate : crateRegistry.getCrates().values()) {
            crate.despawn();
        }
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
