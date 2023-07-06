package dev.imlukas.cratesplugin.crate.registry;

import dev.imlukas.cratesplugin.CratesPlugin;
import dev.imlukas.cratesplugin.crate.data.CrateData;
import dev.imlukas.cratesplugin.crate.impl.AbstractCrate;
import dev.imlukas.cratesplugin.crate.impl.PhysicalCrate;
import dev.imlukas.cratesplugin.crate.impl.VirtualCrate;
import dev.imlukas.cratesplugin.item.impl.CrateItem;
import dev.imlukas.cratesplugin.item.registry.CrateItemRegistry;
import dev.imlukas.cratesplugin.utils.storage.YMLBase;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CrateParser extends YMLBase {

    private final static Map<String, Function<CrateData, AbstractCrate>> DEFAULT_CRATES = Map.of(
            "physical", PhysicalCrate::new,
            "virtual", VirtualCrate::new);

    private final CrateRegistry crateRegistry;
    private final CrateItemRegistry crateItemRegistry;

    public CrateParser(CratesPlugin plugin) {
        super(plugin, "crates.yml");
        this.crateRegistry = plugin.getCrateRegistry();
        this.crateItemRegistry = plugin.getCrateItemRegistry();

        parse();
    }

    public void parse() {
        for (String key : getConfiguration().getKeys(false)) {
            ConfigurationSection crateSection = getConfiguration().getConfigurationSection(key);

            if (crateSection == null) {
                continue;
            }

            List<CrateItem> items = new ArrayList<>();

            for (String item : crateSection.getStringList("items")) {
                CrateItem crateItem = crateItemRegistry.getItem(item);

                if (crateItem == null) {
                    System.err.println("Failed to find item " + item + " for crate " + key);
                    System.err.println("Check the name of the item in the config and make sure it exists in the items.yml file");
                    continue;
                }

                items.add(crateItem);
            }
            CrateData crateData = new CrateData(crateSection, items);

            String type = crateSection.getString("type");
            AbstractCrate crate = DEFAULT_CRATES.getOrDefault(type, PhysicalCrate::new).apply(crateData);
            crate.spawn();
            crateRegistry.register(crate);
        }
    }
}
