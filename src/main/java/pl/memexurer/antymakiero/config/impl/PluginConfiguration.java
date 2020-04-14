package pl.memexurer.antymakiero.config.impl;

import org.bukkit.plugin.java.JavaPlugin;
import pl.memexurer.antymakiero.config.ConfigurationSource;
import pl.memexurer.antymakiero.config.CustomConfiguration;

public class PluginConfiguration extends CustomConfiguration {
    @ConfigurationSource(path = "click_limit")
    public int CPS_LIMIT;
    @ConfigurationSource(path = "admin_broadcast_message")
    public String ADMIN_BROADCAST_MESSAGE;
    @ConfigurationSource(path = "blindness_effect")
    public boolean BLINDNESS_EFFECT;
    @ConfigurationSource(path = "title.title")
    public String TITLE_TITLE;
    @ConfigurationSource(path = "title.subtitle")
    public String TITLE_SUBTITLE;

    public PluginConfiguration(JavaPlugin plugin) {
        super(plugin);
    }
}
