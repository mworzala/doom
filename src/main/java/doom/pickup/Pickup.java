package doom.pickup;

import doom.player.DoomPlayer;
import org.jetbrains.annotations.NotNull;

import static doom.player.DoomPlayer.*;

// https://doomwiki.org/wiki/Item#Doom_games
public interface Pickup {

    // HEALTH
    Pickup HEALTH_BONUS = new HealthPickup("Health bonus", true, 1, 200);
    Pickup STIMPACK = new HealthPickup("Stimpack", false, 10, 100);
    Pickup MEDKIT = new HealthPickup("Medikit", false, 25, 100);
    Pickup SUPERCHARGE = new HealthPickup("Soul sphere", true, 100, 200);

    // ARMOR
    Pickup ARMOR_BONUS = new ArmorPickup("Spiritual armor", true, 1, 200);
    Pickup ARMOR = new ArmorPickup("Security armor", false, 100, 100);
    Pickup MEGA_ARMOR = new ArmorPickup("Combat armor", false, 200, 200);

    // AMMO
    Pickup BULLETS_SMALL = new AmmoPickup("clip", AMMO_BULLETS, 10);
    Pickup BULLETS_LARGE = new AmmoPickup("Box of bullets", AMMO_BULLETS, 50);
    Pickup SHELLS_SMALL = new AmmoPickup("clip", AMMO_SHELLS, 4);
    Pickup SHELLS_LARGE = new AmmoPickup("clip", AMMO_SHELLS, 20);
    Pickup ROCKETS_SMALL = new AmmoPickup("clip", AMMO_ROCKETS, 1);
    Pickup ROCKETS_LARGE = new AmmoPickup("clip", AMMO_ROCKETS, 5);
    Pickup CELL_SMALL = new AmmoPickup("clip", AMMO_CELLS, 20);
    Pickup CELL_LARGE = new AmmoPickup("clip", AMMO_CELLS, 100);

    // POWERUPS
    //todo

    @NotNull
    String name();

    boolean shouldPickUp(@NotNull DoomPlayer player);

    void pickUp(@NotNull DoomPlayer player);
}
