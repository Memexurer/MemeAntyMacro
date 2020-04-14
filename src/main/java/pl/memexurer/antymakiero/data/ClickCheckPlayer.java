package pl.memexurer.antymakiero.data;

public class ClickCheckPlayer {
    private int clicks;
    private long lastBlock;

    ClickCheckPlayer() {
        this.clicks = 0;
        this.lastBlock = 0;
    }

    public void addClick() {
        this.clicks++;
    }

    void clear() {
        this.clicks = 0;
    }

    public void blockClicks() {
        this.lastBlock = System.currentTimeMillis();
    }

    public boolean isLimited(int i) {
        return clicks > i;
    }

    public boolean isBlocked() {
        return lastBlock != 0 && (System.currentTimeMillis() - lastBlock) < 1000 * 5;
    }
}
