package doom.pickup;

import doom.player.DoomPlayer;
import net.minestom.server.utils.MathUtils;
import org.jetbrains.annotations.NotNull;

public record AmmoPickup(
        String name,
        int ammoType,
        int amount
) implements Pickup {
    @Override
    public boolean shouldPickUp(@NotNull DoomPlayer player) {
        return true;
    }

    @Override
    public void pickUp(@NotNull DoomPlayer player) {
        player.addAmmo(ammoType, amount);
    }
}
