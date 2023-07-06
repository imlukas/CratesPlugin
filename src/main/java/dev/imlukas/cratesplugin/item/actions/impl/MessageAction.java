package dev.imlukas.cratesplugin.item.actions.impl;

import dev.imlukas.cratesplugin.item.actions.ItemAction;
import dev.imlukas.cratesplugin.utils.text.TextUtils;
import org.bukkit.entity.Player;

public class MessageAction implements ItemAction {

    private final String message;

    public MessageAction(String message) {
        this.message = message;
    }

    @Override
    public void handle(Player player) {
        player.sendMessage(TextUtils.color(this.message));
    }
}
