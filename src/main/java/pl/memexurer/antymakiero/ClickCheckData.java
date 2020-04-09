package pl.memexurer.antymakiero;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class ClickCheckData {
    private ConcurrentHashMap<UUID, ClickCheckPlayer> clicks;

    ClickCheckData() {
        this.clicks = new ConcurrentHashMap<>();
    }

    void clearClicks() {
        this.clicks.forEach((a, b) -> b.clear());
    }

    ClickCheckPlayer getPlayer(UUID uuid) {
        return clicks.computeIfAbsent(uuid, x -> new ClickCheckPlayer());
    }

}
