package dev.imlukas.cratesplugin.item.actions.impl;

import dev.imlukas.cratesplugin.item.actions.ItemAction;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveAction implements ItemAction {

    private final String input;

    public GiveAction(String input) {
        this.input = input;
    }
    @Override
    public void handle(Player player) {
        String[] split = input.split(" ");
        ItemStack item = new ItemStack(Material.valueOf(split[0]));

        if (split.length == 2) {
            item.setAmount(Integer.parseInt(split[1]));
        }

        player.getInventory().addItem(item);
    }
}
