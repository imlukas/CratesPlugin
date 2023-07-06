package dev.imlukas.cratesplugin.crate.registry;

import dev.imlukas.cratesplugin.crate.impl.AbstractCrate;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class CrateRegistry {

    private final Map<String, AbstractCrate> crates = new HashMap<>();

    public AbstractCrate get(String identifier) {
        return crates.get(identifier);
    }

    public Map<String, AbstractCrate> getCrates() {
        return crates;
    }

    public void register(AbstractCrate crate) {
        crates.put(crate.getIdentifier(), crate);
    }

    public void unregister(AbstractCrate crate) {
        crates.remove(crate.getIdentifier());
    }

    public AbstractCrate getCrateByLocation(Location location) {
        for (AbstractCrate crate : crates.values()) {

            Location crateLocation = crate.getLocation();
            if (crateLocation.getBlockX() == location.getBlockX() &&
                    crateLocation.getBlockY() == location.getBlockY() &&
                    crateLocation.getBlockZ() == location.getBlockZ() &&
                    crateLocation.getWorld().getName().equals(location.getWorld().getName())) {
                return crate;
            }
        }

        return null;
    }
}
