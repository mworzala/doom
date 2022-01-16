package doom;

import doom.command.GamemodeCommand;
import doom.command.PlayerCommand;
import doom.command.SpawnCommand;
import doom.enemy.BaseEntity;
import doom.pickup.PickupEntity;
import doom.player.DoomPlayer;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.*;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DoomServer {
    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();
        MinecraftServer.getConnectionManager().setPlayerProvider(DoomPlayer::new);

        MinecraftServer.getCommandManager().register(new PlayerCommand());
        MinecraftServer.getCommandManager().register(new SpawnCommand());
        MinecraftServer.getCommandManager().register(new GamemodeCommand());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        instanceContainer.setChunkGenerator(new GeneratorDemo());
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(11, -59, 33.5));

//            Pos entityPos = new Pos(2, 40, 0);
//            BaseEntity entity = new BaseEntity(entityPos);
//            entity.setInstance(instanceContainer, entityPos);
//            instanceContainer.setBlock(entityPos.add(new Vec(0, -1, 0)), Block.EMERALD_BLOCK);

        });

        globalEventHandler.addListener(PlayerSpawnEvent.class, event -> {
            event.getPlayer().setGameMode(GameMode.CREATIVE);
            event.getPlayer().setPermissionLevel(4);
        });

        server.start("0.0.0.0", 25565);
    }
    private static class GeneratorDemo implements ChunkGenerator {

        @Override
        public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkZ) {
            // Set chunk blocks
            for (byte x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
                for (byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                    for (byte y = 0; y < 40; y++) {
                        batch.setBlock(x, y, z, Block.STONE);
                    }
                }
            }
        }

//        @Override
//        public void fillBiomes(Biome[] biomes, int chunkX, int chunkZ) {
//            Arrays.fill(biomes, Biome.PLAINS);
//        }

        @Override
        public List<ChunkPopulator> getPopulators() {
            return null;
        }
    }
}

