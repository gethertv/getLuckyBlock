package me.gethertv.luckyblock.file;

import me.gethertv.luckyblock.LuckyBlock;
import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LuckyblockFile {

    private static File file;
    private static FileConfiguration config;

    public static void setup() {
        file = new File(LuckyBlock.getInstance().getDataFolder(), "luckyblock.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            LuckyBlock.getInstance().saveResource("luckyblock.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            config.load(file);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("Nie mozna zapisac pliku!");
        }
    }


    public static void loadFile() {
        setup();
        save();
    }

}
