package dev.imlukas.cratesplugin.item.impl;

import dev.imlukas.cratesplugin.item.actions.ItemAction;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CrateItem {

    private final List<ItemAction> unboxAction = new ArrayList<>();
    private final String identifier;
    private final ItemStack displayItem;
    private final double chance;

    public CrateItem(List<ItemAction> itemActions, String identifier, ItemStack displayItem, double chance) {
        this.unboxAction.addAll(itemActions);
        this.identifier = identifier;
        this.displayItem = displayItem;
        this.chance = chance;
    }

    public void runActionsFor(Player player) {
        this.unboxAction.forEach(action -> action.handle(player));
    }
}
