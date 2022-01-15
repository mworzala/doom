package doom.player;

import doom.font.FontBuilder;
import doom.font.FontUtil;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.utils.MathUtils;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static doom.font.FontCharacter.*;

public class DoomPlayer extends Player {
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

    @Override
    public void update(long time) {

        String message = buildHUD();

        sendActionBar(Component.text(message));

        super.update(time);
    }

    public void reset() {
        health = 100;
        armor = 100;
        ammo = new int[] { 200, 0, 0, 0 };
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
}
