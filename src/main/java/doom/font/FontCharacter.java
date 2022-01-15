package doom.font;

public record FontCharacter(int width, char character) {
    public static final float GLOBAL_SCALE = 1.5f; //todo must match scale used in font files (see chars.txt)

    public FontCharacter {
        width = (int) (width * GLOBAL_SCALE);
    }

    // SECTION : HUD
    // HUD elements with y positions explicitly to match the hud positioning

    // Background
    public static final FontCharacter HUD_BG_LEFT = new FontCharacter(160, '\uEff2');
    public static final FontCharacter HUD_BG_RIGHT = new FontCharacter(160, '\uEff3');

    public static final FontCharacter HUD_NUM_0 = new FontCharacter(14, '\uE000');
    public static final FontCharacter HUD_NUM_1 = new FontCharacter(10, '\uE001');
    public static final FontCharacter HUD_NUM_2 = new FontCharacter(14, '\uE002');
    public static final FontCharacter HUD_NUM_3 = new FontCharacter(14, '\uE003');
    public static final FontCharacter HUD_NUM_4 = new FontCharacter(14, '\uE004');
    public static final FontCharacter HUD_NUM_5 = new FontCharacter(14, '\uE005');
    public static final FontCharacter HUD_NUM_6 = new FontCharacter(14, '\uE006');
    public static final FontCharacter HUD_NUM_7 = new FontCharacter(14, '\uE007');
    public static final FontCharacter HUD_NUM_8 = new FontCharacter(14, '\uE008');
    public static final FontCharacter HUD_NUM_9 = new FontCharacter(14, '\uE009');
    public static final FontCharacter HUD_NUM_PERCENT = new FontCharacter(14, '\uE00A');

    public static final FontCharacter[] HUD_NUM = new FontCharacter[] {
            HUD_NUM_0, HUD_NUM_1, HUD_NUM_2, HUD_NUM_3, HUD_NUM_4, HUD_NUM_5, HUD_NUM_6, HUD_NUM_7, HUD_NUM_8, HUD_NUM_9
    };

    public static final FontCharacter FACE_S0_C = new FontCharacter(24, '\uE100');

}
