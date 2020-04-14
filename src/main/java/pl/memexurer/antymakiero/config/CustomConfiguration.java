package pl.memexurer.antymakiero.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class CustomConfiguration {
    private File file;
    private FileConfiguration configuration;

    public CustomConfiguration(JavaPlugin plugin, String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            if (plugin.getResource(fileName) == null) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else plugin.saveResource(fileName, false);
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public CustomConfiguration(JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists())
            plugin.saveResource("config.yml", false);
        this.configuration = plugin.getConfig();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public File getFile() {
        return file;
    }

    public void load() {
        for (Field f : getClass().getDeclaredFields()) {
            if (!f.isAnnotationPresent(ConfigurationSource.class)) continue;

            try {
                ConfigurationSource source = f.getAnnotation(ConfigurationSource.class);
                f.set(this, configuration.get(source.path()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
