package doom.pickup;

import doom.player.DoomPlayer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.EntityTracker;
import net.minestom.server.instance.Instance;

@SuppressWarnings("UnstableApiUsage")
public class PickupEntity extends Entity {
    private static final float PICKUP_RANGE = 0.5f;

    public PickupEntity() {
        super(EntityType.ARMOR_STAND);
    }

    @Override
    public void update(long time) {
        Instance instance = getInstance();
        if (instance == null) return;

        instance.getEntityTracker().nearbyEntities(
                getPosition(),
                PICKUP_RANGE,
                EntityTracker.Target.PLAYERS,
                this::pickup
        );
    }

    private void pickup(Player msPlayer) {
        if (!(msPlayer instanceof DoomPlayer player)) return;

        player.sendMessage("Picked up item!");
        remove();
    }
}
