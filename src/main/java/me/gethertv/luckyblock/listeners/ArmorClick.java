package me.gethertv.luckyblock.listeners;

import me.gethertv.luckyblock.LuckyBlock;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorClick implements Listener {

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event)
    {
        if(!event.getRightClicked().isVisible())
        {
           event.setCancelled(true);
        }
    }

}
