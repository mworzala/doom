package doom.font;

import java.util.ArrayList;
import java.util.List;

import static doom.font.FontCharacter.*;

public final class FontUtil {
    private FontUtil() {}

    public static void writeHudNumber(FontBuilder builder, int number, boolean hasPercent, int targetSize) {

        // Compute characters
        List<FontCharacter> characters = new ArrayList<>();
        for (String s : String.valueOf(number).split("")) {
            int num = Integer.parseInt(s);
            characters.add(HUD_NUM[num]);
        }
        if (hasPercent) characters.add(HUD_NUM_PERCENT);

        // Front padding
        int totalSize = 0;
        for (FontCharacter character : characters)
            totalSize += character.width();
        int padding = targetSize - totalSize - characters.size();

        // Write characters
        builder.offset(padding);
        for (FontCharacter character : characters)
            builder.append(character);
    }
}
