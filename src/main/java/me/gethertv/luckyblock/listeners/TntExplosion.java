package me.gethertv.luckyblock.listeners;

import me.gethertv.luckyblock.LuckyBlock;
import me.gethertv.luckyblock.data.LuckyData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class TntExplosion implements Listener {

    @EventHandler
    public void onTntExplosion(EntityExplodeEvent event)
    {
        List<Block> blocks = event.blockList();
        for(Block block : blocks)
        {
            if(LuckyBlock.getInstance().getListLuckyBlock().get(block.getLocation())!=null)
            {
                LuckyData luckyData = LuckyBlock.getInstance().getListLuckyBlock().get(block.getLocation());
                if(luckyData.getLocation().equals(block.getLocation()))
                {
                    block.setType(Material.AIR);
                    event.setCancelled(true);
                    luckyData.getArmorStand().remove();
                    LuckyBlock.getInstance().getListLuckyBlock().remove(block.getLocation());
                    BreakBlock.giveRandom(block.getLocation());
                }


            }
        }
    }

}
