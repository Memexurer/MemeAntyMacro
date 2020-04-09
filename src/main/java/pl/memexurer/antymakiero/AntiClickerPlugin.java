package pl.memexurer.antymakiero;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class AntiClickerPlugin extends JavaPlugin implements Listener {
    private final ClickCheckData clickData = new ClickCheckData();

    @Override
    public void onEnable() {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new InteractPacketAdapter(this, clickData));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, clickData::clearClicks, 0L, 20L); // głupi sposób
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || e.getDamager().hasPermission("antymacro.bypass")) return;

        ClickCheckPlayer player = clickData.getPlayer(e.getDamager().getUniqueId());

        if (player.isBlocked()) e.setCancelled(true);
        else if (player.isLimited()) {
            Player damager = (Player) e.getDamager();
            damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 5));
            damager.sendTitle(fixColor("&c&lANTYMAKIERO"), fixColor("&7Maksymalna ilosc cpsow wynosi &c12&7!"));
            player.blockClicks();

            e.setCancelled(true);
        }
    }

    private String fixColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
