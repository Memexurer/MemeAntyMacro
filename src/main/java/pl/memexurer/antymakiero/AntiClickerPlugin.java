package pl.memexurer.antymakiero;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.memexurer.antymakiero.config.impl.PluginConfiguration;
import pl.memexurer.antymakiero.data.ClickCheckData;
import pl.memexurer.antymakiero.listener.InteractPacketAdapter;
import pl.memexurer.antymakiero.listener.PlayerDamageListener;

public final class AntiClickerPlugin extends JavaPlugin {
    private final ClickCheckData clickData = new ClickCheckData();
    private final PluginConfiguration pluginConfiguration = new PluginConfiguration(this);

    @Override
    public void onEnable() {
        new MetricsLite(this);


        pluginConfiguration.load();

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new InteractPacketAdapter(this, clickData));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, clickData::clearClicks, 0L, 25L); // głupi sposób
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(this), this);
    }

    public ClickCheckData getClickData() {
        return clickData;
    }

    public PluginConfiguration getPluginConfiguration() {
        return pluginConfiguration;
    }
}
