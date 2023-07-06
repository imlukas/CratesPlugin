package dev.imlukas.cratesplugin.item.registry;

import dev.imlukas.cratesplugin.item.impl.CrateItem;

import java.util.HashMap;
import java.util.Map;

public class CrateItemRegistry {

    private final Map<String, CrateItem> items = new HashMap<>();


    public void register(CrateItem item) {
        register(item.getIdentifier(), item);
    }
    public void register(String key, CrateItem item) {
        items.put(key, item);
    }

    public CrateItem getItem(String key) {
        return items.get(key);
    }
}
