package me.gethertv.luckyblock.command;

import me.gethertv.luckyblock.LuckyBlock;
import me.gethertv.luckyblock.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LuckyBlockCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("admin.luckyblock"))
            return false;

        if(args.length==3)
        {
            if(args[0].equalsIgnoreCase("give"))
            {
                Player player = Bukkit.getPlayer(args[1]);
                if(player==null)
                {
                    sender.sendMessage(ColorFixer.addColors("&cPodany gracz nie jest online!"));
                    return false;
                }
                if(!isInt(args[2]))
                {
                    sender.sendMessage(ColorFixer.addColors("&aMusisz podac liczbe calkowita!"));
                    return false;
                }
                int amount = Integer.parseInt(args[2]);
                ItemStack clone = LuckyBlock.getInstance().getLuckyBlock().clone();
                clone.setAmount(amount);
                player.getInventory().addItem(clone);
                sender.sendMessage(ColorFixer.addColors("&aPomyslnie nadano luckyblock!"));
                return true;
            }
        }
        return false;
    }


    public boolean isInt(String input)
    {
        try {
            int a = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ignored) {}

        return false;
    }
}


