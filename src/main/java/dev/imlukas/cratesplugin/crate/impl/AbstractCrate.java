package dev.imlukas.cratesplugin.crate.impl;

import dev.imlukas.cratesplugin.crate.data.CrateData;
import dev.imlukas.cratesplugin.item.impl.CrateItem;
import dev.imlukas.cratesplugin.utils.ListUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public abstract class AbstractCrate {

    private final Map<UUID, Boolean> unboxingMap = new HashMap<>();
    private final CrateData data;

    public AbstractCrate(CrateData data) {
        this.data = data;
    }

    public CrateItem unbox(Player player) {
        setUnboxing(player, true);
        CrateItem item = getRandomByChange();
        System.out.println("Unboxing " + item.getIdentifier());
        item.runActionsFor(player);
        return item;
    }

    public abstract void spawn();

    public abstract void despawn();

    public String getIdentifier() {
        return data.getIdentifier();
    }

    public Location getLocation() {
        return data.getLocation();
    }

    public List<CrateItem> getItems() {
        return data.getItems();
    }

    /**
     * Gets a truly random item
     * @return a random item
     */
    public CrateItem getRandomItem() {
        return ListUtils.getRandom(data.getItems());
    }

    /**
     * Gets a random item by chance
     * @return a random item
     */
    public CrateItem getRandomByChange() {
        for (CrateItem item : data.getItems()) {
            if (Math.random() <= item.getChance()) {
                return item;
            }
        }

        return getRandomByChange();
    }

    public void setUnboxing(Player player, boolean unboxing) {
        unboxingMap.put(player.getUniqueId(), unboxing);
    }

    public boolean isUnboxing(Player player) {
        return unboxingMap.getOrDefault(player.getUniqueId(), false);
    }
}
