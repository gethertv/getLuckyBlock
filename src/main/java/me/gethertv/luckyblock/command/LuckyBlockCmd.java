package me.gethertv.luckyblock.command;

import me.gethertv.luckyblock.LuckyBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LuckyBlockCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("admin.luckyblock"))
            return false;

        if(!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        player.getInventory().addItem(LuckyBlock.getInstance().getLuckyBlock());
        return false;
    }
}
