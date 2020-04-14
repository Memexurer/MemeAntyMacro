package pl.memexurer.antymakiero.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.plugin.java.JavaPlugin;
import pl.memexurer.antymakiero.data.ClickCheckData;

public class InteractPacketAdapter extends PacketAdapter {
    private ClickCheckData clickData;

    public InteractPacketAdapter(JavaPlugin owner, ClickCheckData clickData) {
        super(owner, PacketType.Play.Client.USE_ENTITY);
        this.clickData = clickData;
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.getPlayer().hasPermission("antymacro.bypass") || event.getPacket().getEntityUseActions().read(0) != EnumWrappers.EntityUseAction.ATTACK)
            return;
        clickData.getPlayer(event.getPlayer().getUniqueId()).addClick();
    }
}
