package doom.command;

import doom.pickup.Pickup;
import doom.pickup.PickupEntity;
import doom.player.DoomPlayer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.ArgumentWord;
import net.minestom.server.command.builder.arguments.relative.ArgumentRelativeVec3;
import net.minestom.server.coordinate.Vec;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SpawnCommand extends Command {
    private final ArgumentRelativeVec3 position = ArgumentType.RelativeVec3("position");

    public SpawnCommand() {
        super("spawn");

        addSubcommand(new PickupCommand());
    }

    public class PickupCommand extends Command {
        private static final Map<String, Pickup> PICKUP_MAP = new HashMap<>(){{
            try {
                for (Field field : Pickup.class.getDeclaredFields()) {
                    put(field.getName(), (Pickup) field.get(null));
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }};

        private final ArgumentWord pickups = ArgumentType.Word("pickups")
                .from(PICKUP_MAP.keySet().toArray(new String[0]));

        public PickupCommand() {
            super("pickup");

            addSyntax(this::onSpawnNamed, pickups, position);
        }

        private void onSpawnNamed(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;

            Pickup pickup = PICKUP_MAP.get(context.get(pickups));
            Vec position = context.get(SpawnCommand.this.position).from(player);

            new PickupEntity(pickup, player.getInstance(), position.asPosition());
        }

    }


}
