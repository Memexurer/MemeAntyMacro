package pl.memexurer.antymakiero.data;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClickCheckData {
    private ConcurrentHashMap<UUID, ClickCheckPlayer> clicks;

    public ClickCheckData() {
        this.clicks = new ConcurrentHashMap<>();
    }

    public void clearClicks() {
        this.clicks.forEach((a, b) -> b.clear());
    }

    public ClickCheckPlayer getPlayer(UUID uuid) {
        return clicks.computeIfAbsent(uuid, x -> new ClickCheckPlayer());
    }

}
