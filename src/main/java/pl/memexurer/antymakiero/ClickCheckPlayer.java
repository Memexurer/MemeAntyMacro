package pl.memexurer.antymakiero;

class ClickCheckPlayer {
    private int clicks;
    private long lastBlock;

    ClickCheckPlayer() {
        this.clicks = 0;
        this.lastBlock = 0;
    }

    void addClick() {
        this.clicks++;
    }

    void clear() {
        this.clicks = 0;
    }

    void blockClicks() {
        this.lastBlock = System.currentTimeMillis();
    }

    boolean isLimited() {
        return clicks > 12;
    }

    boolean isBlocked() {
        return lastBlock != 0 && (System.currentTimeMillis() - lastBlock) < 1000 * 5;
    }
}
