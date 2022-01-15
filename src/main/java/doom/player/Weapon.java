package doom.player;

import static doom.player.DoomPlayer.AMMO_BULLETS;

public enum Weapon {
    PISTOL(AMMO_BULLETS);

    private final int ammoType;

    Weapon(int ammoType) {
        this.ammoType = ammoType;
    }

    public int ammoType() {
        return ammoType;
    }
}
