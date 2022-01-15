package doom;

import doom.player.DoomPlayer;
import net.minestom.server.MinecraftServer;

public class DoomServer {
    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();

        MinecraftServer.getConnectionManager().setPlayerProvider(DoomPlayer::new);

        server.start("0.0.0.0", 25565);
    }
}
