package doom.pickup;

import doom.player.DoomPlayer;
import net.minestom.server.utils.MathUtils;
import org.jetbrains.annotations.NotNull;

public record ArmorPickup(
        String name,
        boolean alwaysPickup,
        int amount,
        int maximum
) implements Pickup {
    @Override
    public boolean shouldPickUp(@NotNull DoomPlayer player) {
        if (alwaysPickup) return true;
        return player.getArmor() < maximum;
    }

    @Override
    public void pickUp(@NotNull DoomPlayer player) {
        int newHealth = MathUtils.clamp(player.getArmor() + amount, 0, maximum);
        player.setArmor(newHealth);
    }
}
