package me.gethertv.luckyblock.listeners;

import me.gethertv.luckyblock.LuckyBlock;
import me.gethertv.luckyblock.data.LuckyData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PlaceBlock implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event)
    {
        if(event.isCancelled())
            return;

        Player player = event.getPlayer();
        if(player.getInventory().getItemInHand().isSimilar(LuckyBlock.getInstance().getLuckyBlock()))
        {
            addLuckyBlock(player, event.getBlock().getLocation());
            return;
        }
        if(player.getInventory().getItemInOffHand().isSimilar(LuckyBlock.getInstance().getLuckyBlock()))
        {
            if(player.getInventory().getItemInHand().isSimilar(LuckyBlock.getInstance().getLuckyBlock())) {
                addLuckyBlock(player, event.getBlock().getLocation());
                return;
            }
            if(player.getItemInHand()==null || player.getItemInHand().getType()==Material.AIR || !player.getInventory().getItemInHand().getType().isBlock())
            {
                addLuckyBlock(player, event.getBlock().getLocation());
                return;
            }
        }
    }



    private void addLuckyBlock(Player player, Location location) {

        Location hologram = location.clone();

        LuckyData luckyData = new LuckyData(location, hologram);

        addHeadLucky(hologram, luckyData);
        location.getBlock().setType(LuckyBlock.GLASS_LUCKYBLOCK);
        LuckyBlock.getInstance().getListLuckyBlock().put(location, luckyData);

    }

    private void addHeadLucky(Location location, LuckyData luckyData) {

        Location loc = location.clone().add(0.5, -0.4, 0.5);
        ArmorStand hologram = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        hologram.setHelmet(LuckyBlock.getInstance().getLuckyBlock().clone());
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setSmall(true);
        hologram.setCanPickupItems(false);
        hologram.setInvulnerable(true);
        hologram.setBasePlate(false);
        hologram.setMarker(true);
        hologram.setCustomNameVisible(false);
        luckyData.setArmorStand(hologram);
    }

}
