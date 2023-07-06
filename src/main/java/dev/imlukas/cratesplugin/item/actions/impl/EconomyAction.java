package dev.imlukas.cratesplugin.item.actions.impl;

import dev.imlukas.cratesplugin.item.actions.ItemAction;
import org.bukkit.entity.Player;

public class EconomyAction implements ItemAction {

    private final String input;

    public EconomyAction(String input) {
        this.input = input;
    }

    @Override
    public void handle(Player player) {

    }
}
