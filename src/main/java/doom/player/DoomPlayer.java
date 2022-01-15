package doom.player;

import net.minestom.server.MinecraftServer;
import net.minestom.server.attribute.Attribute;
import net.minestom.server.attribute.AttributeModifier;
import net.minestom.server.attribute.AttributeOperation;
import net.minestom.server.coordinate.Pos;
import doom.font.FontBuilder;
import doom.font.FontUtil;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerPacketEvent;
import net.minestom.server.event.trait.PlayerEvent;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.network.packet.client.play.ClientPlayerPositionAndRotationPacket;
import net.minestom.server.network.packet.client.play.ClientPlayerRotationPacket;
import net.minestom.server.network.packet.server.play.PlayerPositionAndLookPacket;
import net.minestom.server.network.packet.server.play.UpdateHealthPacket;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.potion.Potion;
import net.minestom.server.potion.PotionEffect;
import net.minestom.server.timer.TaskSchedule;
import net.minestom.server.utils.MathUtils;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static doom.font.FontCharacter.*;

public class DoomPlayer extends Player {

    private static final EventNode<PlayerEvent> eventNode = EventNode.type("doom-player-event-node", EventFilter.PLAYER);

    static {
        MinecraftServer.getGlobalEventHandler().addChild(eventNode);
    }

    private int waitTicks = 0;
    public static final int AMMO_BULLETS = 0;
    public static final int AMMO_SHELLS = 1;
    public static final int AMMO_ROCKETS = 2;
    public static final int AMMO_CELLS = 3;

    private final int[] maxAmmo = new int[] { 200, 50, 50, 300 };

    private int health;
    private int armor;
    private Weapon weapon = Weapon.PISTOL;
    private int[] ammo;

    public DoomPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);

//        MinecraftServer.getSchedulerManager().submitTask(() -> {
//        });
        reset();
    }

    public int getDoomHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getAmmo() {
        return ammo[weapon.ammoType()];
    }

    public int getAmmo(int type) {
        return ammo[type];
    }

    public void setDoomHealth(int integer) {
        health = MathUtils.clamp(integer, 0, 199);
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAmmo(int type, int amount) {
        ammo[type] = amount;
    }

    public void addAmmo(int type, int amount) {
        ammo[type] = MathUtils.clamp(ammo[type] + amount, 0, maxAmmo[type]);
    }

    @Override
    public void update(long time) {

        String message = buildHUD();

        sendActionBar(Component.text(message));

        super.update(time);

        Pos playerPos = getPosition();
        position = playerPos.withPitch(0.0f);
        final byte flag = 0x01 | 0x02 | 0x04 | 0x8;
        sendPacket(new PlayerPositionAndLookPacket(new Pos(0, 0, 0, 0, 0), flag, 0, false));
    }

    public void reset() {
        health = 100;
        armor = 100;
        ammo = new int[] { 200, 0, 0, 0 };

        MinecraftServer.getSchedulerManager().buildTask(() -> {
            setFood(1);
            getAttribute(Attribute.MOVEMENT_SPEED).addModifier(new AttributeModifier("test",  .4f, AttributeOperation.MULTIPLY_BASE));
            addEffect(new Potion(PotionEffect.SPEED, (byte) 2, 1_000_000));
            addEffect(new Potion(PotionEffect.JUMP_BOOST, (byte) 128, 1_000_000));
        }).delay(5, TimeUnit.SERVER_TICK).schedule();
    }

    private String buildHUD() {
        FontBuilder builder = new FontBuilder();

        // Background
        builder.appendUnsafe(HUD_BG_LEFT);
        builder.offsetUnsafe(-1);
        builder.appendUnsafe(HUD_BG_RIGHT);

        builder.offset(-HUD_BG_LEFT.width() * 2);

        // Ammo
        drawAmmo(builder);

        builder.offset(8);

        // Health - 80px
        FontUtil.writeHudNumber(builder, health, true, 80);

//        builder.offset(8);

        // Arms

        builder.offset(65);

        // Face
        builder.append(FACE_S0_C);

        builder.offset(15);

        // Armor
        FontUtil.writeHudNumber(builder, armor, true, 80);

        // Whatever those slots are

        // Ammo capacities


        return builder.toString();
    }

    // Width: 68
    private void drawAmmo(FontBuilder builder) {
        int amount = ammo[weapon.ammoType()];

        FontUtil.writeHudNumber(builder, amount, false, 68);
    }

    @Deprecated
    public float getHealth() {
        return super.getHealth();
    }

    @Deprecated
    public void setHealth(float health) {
        super.setHealth(health);
    }

//    @Override
//    public void update(long tick) {
//        super.update(tick);
//        if (waitTicks == 3) {
//            System.out.println("player pos: " + position.withPitch(20.0f));
////            facePosition();
//            sendPacket(new PlayerPositionAndLookPacket(position.withPitch(20.0f), (byte) 0, 0, false));
//            waitTicks = 0;
//        }
//        else {
//            waitTicks++;
//        }
//    }
}
