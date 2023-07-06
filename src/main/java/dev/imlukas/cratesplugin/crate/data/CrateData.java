package dev.imlukas.cratesplugin.crate.data;

import dev.imlukas.cratesplugin.item.impl.CrateItem;
import dev.imlukas.cratesplugin.utils.ConfigUtil;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrateData {

    private final List<CrateItem> items = new ArrayList<>();
    private final Location location;
    private final String identifier;
    private final ConfigurationSection section;

    public CrateData(ConfigurationSection section, List<CrateItem> items) {
        this.items.addAll(items);
        this.identifier = section.getName();
        this.location = ConfigUtil.parseLocation(section.getConfigurationSection("location"));
        this.section = section;
    }
}
