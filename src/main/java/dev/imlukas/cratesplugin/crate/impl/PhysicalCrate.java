package dev.imlukas.cratesplugin.crate.impl;

import dev.imlukas.cratesplugin.crate.data.CrateData;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Chest;
import org.bukkit.material.DirectionalContainer;
import org.bukkit.material.MaterialData;

public class PhysicalCrate extends AbstractCrate {

    private final Block block;

    public PhysicalCrate(CrateData data) {
        super(data);
        this.block = data.getLocation().getBlock();
    }

    @Override
    public void spawn() {
        Material material = Material.valueOf(getData().getSection().getString("block"));
        this.block.setType(material);

        if (block.getBlockData() instanceof Directional directional) {
            System.out.println("Directional");
            directional.setFacing(BlockFace.valueOf(getData().getSection().getString("facing", "SOUTH").toUpperCase()));
            this.block.setBlockData(directional);
        }
    }

    @Override
    public void despawn() {
        this.block.setType(Material.AIR);
    }
}
