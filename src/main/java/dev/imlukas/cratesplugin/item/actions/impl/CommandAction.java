package dev.imlukas.cratesplugin.item.actions.impl;

import dev.imlukas.cratesplugin.item.actions.ItemAction;
import org.bukkit.entity.Player;

public class CommandAction implements ItemAction {

    private final String command;

    public CommandAction(String command) {
        this.command = command;
    }

    @Override
    public void handle(Player player) {
        player.performCommand(this.command);
    }
}
