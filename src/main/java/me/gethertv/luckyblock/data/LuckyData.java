package me.gethertv.luckyblock.data;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class LuckyData {

    private Location location;
    private Location hologram;
    private ArmorStand armorStand;

    public LuckyData(Location location, Location hologram) {
        this.location = location;
        this.hologram = hologram;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getHologram() {
        return hologram;
    }

    public void setHologram(Location hologram) {
        this.hologram = hologram;
    }
}
