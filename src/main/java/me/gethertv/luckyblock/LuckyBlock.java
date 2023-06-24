package me.gethertv.luckyblock;

import me.gethertv.luckyblock.command.LuckyBlockCmd;
import me.gethertv.luckyblock.data.EntityData;
import me.gethertv.luckyblock.data.LuckyData;
import me.gethertv.luckyblock.data.RandomEffect;
import me.gethertv.luckyblock.data.TypeRewards;
import me.gethertv.luckyblock.file.LuckyblockFile;
import me.gethertv.luckyblock.listeners.*;
import me.gethertv.luckyblock.utils.ColorFixer;
import me.gethertv.luckyblock.utils.CustomHead;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LuckyBlock extends JavaPlugin {


    private static LuckyBlock instance;
    private ItemStack luckyBlock;

    private HashMap<Location, LuckyData> listLuckyBlock;
    public static Material GLASS_LUCKYBLOCK;

    private List<RandomEffect> randomEffects;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        LuckyblockFile.loadFile();

        randomEffects = new ArrayList<>();

        listLuckyBlock = new HashMap<>();

        GLASS_LUCKYBLOCK = Material.valueOf(LuckyblockFile.getConfig().getString("luckyblock.glass").toUpperCase());
        loadLuckyBlock();

        loadRewards();

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new ArmorClick(), this);
        getServer().getPluginManager().registerEvents(new PlaceBlock(), this);
        getServer().getPluginManager().registerEvents(new TntExplosion(), this);

        getCommand("luckyblock").setExecutor(new LuckyBlockCmd());

    }

    private void loadRewards() {
        FileConfiguration config = LuckyblockFile.getConfig();
        for(String key : config.getConfigurationSection("drop").getKeys(false))
        {
            TypeRewards typeRewards = TypeRewards.valueOf(config.getString("drop."+key+".type").toUpperCase());
            List<EntityData> entityData = new ArrayList<>();
            List<ItemStack> itemStacks = new ArrayList<>();
            if(config.getConfigurationSection("drop."+key+".items")!=null) {
                for (String item : config.getConfigurationSection("drop." + key + ".items").getKeys(false)) {
                    ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("drop." + key + ".items." + item + ".material")));
                    int amount = config.getInt("drop." + key + ".items." + item + ".amount");
                    itemStack.setAmount(amount);
                    for (String enchantName : config.getStringList("drop." + key + ".items." + item + ".enchants")) {
                        String[] enchant = enchantName.split(":");
                        int level = Integer.parseInt(enchant[1]);
                        itemStack.addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), level);
                    }
                    itemStacks.add(itemStack);
                }
            }
            for(String entityName : config.getStringList("drop."+key+".entity"))
            {
                String[] entity = entityName.split(":");
                int amount = Integer.parseInt(entity[1]);
                entityData.add(new EntityData(EntityType.valueOf(entity[0].toUpperCase()), amount));
            }
            randomEffects.add(new RandomEffect(typeRewards, itemStacks, entityData));
        }
    }

    private void loadLuckyBlock() {
        FileConfiguration config = LuckyblockFile.getConfig();
        luckyBlock = new ItemStack(Material.valueOf(config.getString("luckyblock.material")));
        if(config.isSet("luckyblock.url"))
            luckyBlock = CustomHead.getCustomTextureHead(config.getString("luckyblock.url"));
        ItemMeta itemMeta = luckyBlock.getItemMeta();
        itemMeta.setDisplayName(ColorFixer.addColors(config.getString("luckyblock.displayname")));
        List<String> lore = new ArrayList<>();
        lore.addAll(config.getStringList("luckyblock.lore"));
        itemMeta.setLore(ColorFixer.addColors(lore));
        luckyBlock.setItemMeta(itemMeta);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(Map.Entry<Location, LuckyData> data : LuckyBlock.getInstance().getListLuckyBlock().entrySet())
        {
            LuckyData luckyData = data.getValue();
            luckyData.getArmorStand().remove();
            luckyData.getLocation().getBlock().setType(Material.AIR);
        }
    }

    public List<RandomEffect> getRandomEffects() {
        return randomEffects;
    }

    public HashMap<Location, LuckyData> getListLuckyBlock() {
        return listLuckyBlock;
    }

    public ItemStack getLuckyBlock() {
        return luckyBlock;
    }

    public static LuckyBlock getInstance() {
        return instance;
    }
}
