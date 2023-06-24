package me.gethertv.luckyblock.listeners;

import me.gethertv.luckyblock.LuckyBlock;
import me.gethertv.luckyblock.data.EntityData;
import me.gethertv.luckyblock.data.LuckyData;
import me.gethertv.luckyblock.data.RandomEffect;
import me.gethertv.luckyblock.data.TypeRewards;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BreakBlock implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        if(LuckyBlock.getInstance().getListLuckyBlock().get(event.getBlock().getLocation())!=null)
        {
            LuckyData luckyData = LuckyBlock.getInstance().getListLuckyBlock().get(event.getBlock().getLocation());
            if(luckyData.getLocation().equals(event.getBlock().getLocation()))
            {
                event.getBlock().setType(Material.AIR);
                event.setCancelled(true);
                luckyData.getArmorStand().remove();
                LuckyBlock.getInstance().getListLuckyBlock().remove(event.getBlock().getLocation());
                giveRandom(player, event.getBlock().getLocation());
            }

        }
    }

    public static void giveRandom(Location location) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, LuckyBlock.getInstance().getRandomEffects().size());
        RandomEffect randomEffect = LuckyBlock.getInstance().getRandomEffects().get(randomNum);
        if(randomEffect.getTypeRewards()== TypeRewards.DROP_ITEM)
        {
            for(ItemStack itemStack : randomEffect.getItems())
                location.getWorld().dropItemNaturally(location, itemStack);

            return;
        }
        if(randomEffect.getTypeRewards()== TypeRewards.SPAWN_ENTITY)
        {
            for(EntityData entityData : randomEffect.getEntityData())
            {
                for(int i = 0; i < entityData.getAmount(); i++)
                {
                    location.getWorld().spawnEntity(location.clone().add(randInt(-3, 3), 2, randInt(-3, 3)), entityData.getEntity());
                }
            }
            return;
        }
        if(randomEffect.getTypeRewards()== TypeRewards.TRAP)
        {
            if(location.getBlockY()>=250) {
                location.getWorld().dropItemNaturally(location, LuckyBlock.getInstance().getLuckyBlock());
                return;
            }
            Location loc = location.clone();

            loc.clone().add(0,1,0).getBlock().setType(Material.WATER);

            loc.clone().add(0,-1,0).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(0,2,0).getBlock().setType(Material.OBSIDIAN);

            loc.clone().add(1,0,0).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(1,1,0).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(-1,1,-1).getBlock().setType(Material.OBSIDIAN);

            loc.clone().add(-1,0,0).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(-1,1,0).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(1,1,-1).getBlock().setType(Material.OBSIDIAN);


            loc.clone().add(0,0,-1).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(0,1,-1).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(1,1,1).getBlock().setType(Material.OBSIDIAN);

            loc.clone().add(0,0,1).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(0,1,1).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(-1,1,1).getBlock().setType(Material.OBSIDIAN);


            return;
        }
        if(randomEffect.getTypeRewards()== TypeRewards.SPAWN_TNT)
        {
            Location loc = location.clone();

            Entity tnt = loc.getWorld().spawn(loc.clone().add(0, 3, 0), TNTPrimed.class);
            ((TNTPrimed)tnt).setFuseTicks(20*5);
            return;
        }
        if(randomEffect.getTypeRewards()==TypeRewards.LIGHTNING)
        {
            location.getWorld().strikeLightning(location);
            return;
        }
    }

    public static void giveRandom(Player player, Location location) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, LuckyBlock.getInstance().getRandomEffects().size());
        RandomEffect randomEffect = LuckyBlock.getInstance().getRandomEffects().get(randomNum);
        if(randomEffect.getTypeRewards()== TypeRewards.DROP_ITEM)
        {
            for(ItemStack itemStack : randomEffect.getItems())
                location.getWorld().dropItemNaturally(location, itemStack);

            return;
        }
        if(randomEffect.getTypeRewards()== TypeRewards.SPAWN_ENTITY)
        {
            for(EntityData entityData : randomEffect.getEntityData())
            {
                for(int i = 0; i < entityData.getAmount(); i++)
                {
                    location.getWorld().spawnEntity(player.getLocation().clone().add(randInt(-3, 3), 2, randInt(-3, 3)), entityData.getEntity());
                }
            }
            return;
        }
        if(randomEffect.getTypeRewards()== TypeRewards.TRAP)
        {
            if(location.getBlockY()>=250) {
                location.getWorld().dropItemNaturally(location, LuckyBlock.getInstance().getLuckyBlock());
                return;
            }

            Location loc = player.getLocation().clone();

            loc.clone().add(0,1,0).getBlock().setType(Material.WATER);

            loc.clone().add(0,-1,0).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(0,2,0).getBlock().setType(Material.OBSIDIAN);

            loc.clone().add(1,0,0).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(1,1,0).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(-1,1,-1).getBlock().setType(Material.OBSIDIAN);

            loc.clone().add(-1,0,0).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(-1,1,0).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(1,1,-1).getBlock().setType(Material.OBSIDIAN);


            loc.clone().add(0,0,-1).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(0,1,-1).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(1,1,1).getBlock().setType(Material.OBSIDIAN);

            loc.clone().add(0,0,1).getBlock().setType(Material.OBSIDIAN);
            loc.clone().add(0,1,1).getBlock().setType(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
            loc.clone().add(-1,1,1).getBlock().setType(Material.OBSIDIAN);


            return;
        }
        if(randomEffect.getTypeRewards()== TypeRewards.SPAWN_TNT)
        {
            Location loc = player.getLocation().clone();

            Entity tnt = loc.getWorld().spawn(loc.clone().add(0, 3, 0), TNTPrimed.class);
            ((TNTPrimed)tnt).setFuseTicks(20*5);
            return;
        }
        if(randomEffect.getTypeRewards()==TypeRewards.LIGHTNING)
        {
            location.getWorld().strikeLightning(player.getLocation());
            return;
        }
    }

    private static double randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
