package dev.imlukas.cratesplugin.item.registry;

import dev.imlukas.cratesplugin.CratesPlugin;
import dev.imlukas.cratesplugin.item.actions.ActionRegistry;
import dev.imlukas.cratesplugin.item.actions.ItemAction;
import dev.imlukas.cratesplugin.item.impl.CrateItem;
import dev.imlukas.cratesplugin.utils.item.ItemBuilder;
import dev.imlukas.cratesplugin.utils.storage.YMLBase;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemParser extends YMLBase {

    private final CrateItemRegistry crateItemRegistry;

    public ItemParser(CratesPlugin plugin) {
        super(plugin, "items.yml");
        this.crateItemRegistry = plugin.getCrateItemRegistry();
        parse();
    }

    public void parse() {
        for (String key : getConfiguration().getKeys(false)) {
            ConfigurationSection itemSection = getConfiguration().getConfigurationSection(key);
            List<ItemAction> actions = new ArrayList<>();
            for (String action : itemSection.getStringList("actions")) {
                actions.add(ActionRegistry.getAction(action));
            }

            ItemStack displayItem = ItemBuilder.fromSection(getConfiguration().getConfigurationSection(key));
            double chance = itemSection.getDouble("chance");
            crateItemRegistry.register(new CrateItem(actions, key, displayItem, chance));

        }
    }
}
