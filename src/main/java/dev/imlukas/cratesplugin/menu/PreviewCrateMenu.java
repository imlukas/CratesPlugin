package dev.imlukas.cratesplugin.menu;

import dev.imlukas.cratesplugin.crate.impl.AbstractCrate;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PreviewCrateMenu {

    private final UUID playerId;

    public PreviewCrateMenu(Player player, AbstractCrate crate) {
        this.playerId = player.getUniqueId();
    }
}
