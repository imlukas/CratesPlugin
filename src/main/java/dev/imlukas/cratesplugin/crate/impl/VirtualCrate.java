package dev.imlukas.cratesplugin.crate.impl;

import dev.imlukas.cratesplugin.crate.data.CrateData;
import dev.imlukas.cratesplugin.utils.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class VirtualCrate extends AbstractCrate {

    private final ItemStack item;
    public VirtualCrate(CrateData data) {
        super(data);
        this.item = ItemBuilder.fromSection(data.getSection().getConfigurationSection("item"));
    }

    @Override
    public void spawn() {
        // do nothing
    }

    @Override
    public void despawn() {

    }
}
