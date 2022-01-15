package doom.pickup;

import doom.player.DoomPlayer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.EntityTracker;
import net.minestom.server.instance.Instance;

@SuppressWarnings("UnstableApiUsage")
public class PickupEntity extends Entity {
    private static final float PICKUP_RANGE = 0.5f;

    private final Pickup pickup;

    public PickupEntity(Pickup pickup, Instance instance, Pos position) {
        super(EntityType.ARMOR_STAND);

        this.pickup = pickup;

        setInstance(instance, position);
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

        if (!pickup.shouldPickUp(player)) return;

        player.sendMessage("Picked up " + pickup.name() + "!"); //todo send pickup message
        pickup.pickUp(player);
        remove();
    }
}
