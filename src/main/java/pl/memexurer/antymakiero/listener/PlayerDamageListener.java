package pl.memexurer.antymakiero.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.memexurer.antymakiero.AntiClickerPlugin;
import pl.memexurer.antymakiero.config.impl.PluginConfiguration;
import pl.memexurer.antymakiero.data.ClickCheckData;
import pl.memexurer.antymakiero.data.ClickCheckPlayer;
import pl.memexurer.antymakiero.util.ChatUtil;

public class PlayerDamageListener implements Listener {
    private ClickCheckData clickData;
    private PluginConfiguration configuration;

    public PlayerDamageListener(AntiClickerPlugin plugin) {
        this.clickData = plugin.getClickData();
        this.configuration = plugin.getPluginConfiguration();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || e.getDamager().hasPermission("antymacro.bypass")) return;

        ClickCheckPlayer player = clickData.getPlayer(e.getDamager().getUniqueId());

        if (player.isBlocked()) e.setCancelled(true);
        else if (player.isLimited(configuration.CPS_LIMIT)) {
            Player damager = (Player) e.getDamager();

            ChatUtil.broadcastPermission(configuration.ADMIN_BROADCAST_MESSAGE.replace("{PLAYERNAME}", damager.getName()), "antymacro.broadcast");
            if (configuration.BLINDNESS_EFFECT)
                damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 5));
            damager.sendTitle(ChatUtil.fixColor(configuration.TITLE_TITLE), ChatUtil.fixColor(configuration.TITLE_SUBTITLE));
            player.blockClicks();

            e.setCancelled(true);
        }
    }
}
