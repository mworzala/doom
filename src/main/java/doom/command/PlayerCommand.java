package doom.command;

import doom.player.DoomPlayer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import org.jetbrains.annotations.NotNull;

public class PlayerCommand extends Command {

    public PlayerCommand() {
        super("player");

        addSubcommand(new Health());
        addSubcommand(new Armor());
        addSubcommand(new Ammo());

        addSyntax(this::onReset, ArgumentType.Literal("reset"));
        addSyntax(this::onSetLocked, ArgumentType.Literal("locked"), ArgumentType.Boolean("locked"));
        addSyntax(this::onSave, ArgumentType.Literal("save"));
    }

    private void onReset(@NotNull CommandSender sender, @NotNull CommandContext context) {
        DoomPlayer player = (DoomPlayer) sender;
        player.reset();
    }

    private void onSetLocked(@NotNull CommandSender sender, @NotNull CommandContext context) {
        DoomPlayer player = (DoomPlayer) sender;
        player.setPitchLocked(context.get("locked"));
    }

    private void onSave(@NotNull CommandSender sender, @NotNull CommandContext context) {
        DoomPlayer player = (DoomPlayer) sender;
        player.sendMessage("Saving...");
        player.getInstance().saveChunksToStorage().join();
        player.getInstance().saveInstance().join();
        player.sendMessage("Done!");
    }

    public static class Health extends Command {
        private final ArgumentInteger amount = ArgumentType.Integer("amount");

        public Health() {
            super("health");

            setDefaultExecutor(this::onGet);
            addSyntax(this::onSet, ArgumentType.Literal("set"), amount);
        }

        private void onSet(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;
            player.setDoomHealth(context.get(amount));
        }

        private void onGet(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;
            sender.sendMessage("Health: " + player.getDoomHealth());
        }
    }

    public static class Armor extends Command {
        private final ArgumentInteger amount = ArgumentType.Integer("amount");

        public Armor() {
            super("armor");

            setDefaultExecutor(this::onGet);
            addSyntax(this::onSet, ArgumentType.Literal("set"), amount);
        }

        private void onSet(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;
            player.setArmor(context.get(amount));
        }

        private void onGet(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;
            sender.sendMessage("Armor: " + player.getArmor());
        }
    }

    public static class Ammo extends Command {
        private final ArgumentInteger amount = ArgumentType.Integer("amount");

        public Ammo() {
            super("ammo");

            setDefaultExecutor(this::onGet);
            addSyntax(this::onSet, ArgumentType.Literal("set"), amount);
        }

        private void onSet(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;
            player.setAmmo(0, context.get(amount));
        }

        private void onGet(@NotNull CommandSender sender, @NotNull CommandContext context) {
            DoomPlayer player = (DoomPlayer) sender;
            sender.sendMessage("Ammo: " + player.getAmmo());
        }
    }


}
