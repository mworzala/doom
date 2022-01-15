package doom.font;

public class FontBuilder {
    private final StringBuilder content = new StringBuilder();
    private int offset = 0;

    public FontBuilder appendUnsafe(FontCharacter character) {
        content.append(character.character());
        return this;
    }

    public FontBuilder append(FontCharacter character) {
        content.append(character.character());
        offset += character.width() + 1;
        return this;
    }

    public FontBuilder offsetUnsafe(int amount) {
        appendOffset(amount);
        return this;
    }

    public FontBuilder offset(int amount) {
        appendOffset(amount);
        this.offset += amount;
        return this;
    }

    private void appendOffset(int offset) {
        if (offset > 0) {
            while (offset > 0) {
                if (offset >= 1024) {
                    content.append('\uF82E');
                    offset -= 1024;
                } else if (offset >= 512) {
                    content.append('\uF82D');
                    offset -= 512;
                } else if (offset >= 128) {
                    content.append('\uF82C');
                    offset -= 128;
                } else if (offset >= 64) {
                    content.append('\uF82B');
                    offset -= 64;
                } else if (offset >= 32) {
                    content.append('\uF82A');
                    offset -= 32;
                } else if (offset >= 16) {
                    content.append('\uF829');
                    offset -= 16;
                } else if (offset >= 8) {
                    content.append('\uF828');
                    offset -= 8;
                } else if (offset >= 7) {
                    content.append('\uF827');
                    offset -= 7;
                } else if (offset >= 6) {
                    content.append('\uF826');
                    offset -= 6;
                } else if (offset >= 5) {
                    content.append('\uF825');
                    offset -= 5;
                } else if (offset >= 4) {
                    content.append('\uF824');
                    offset -= 4;
                } else if (offset >= 3) {
                    content.append('\uF823');
                    offset -= 3;
                } else if (offset >= 2) {
                    content.append('\uF822');
                    offset -= 2;
                } else {
                    content.append('\uF821');
                    offset -= 1;
                }
            }
        } else {
            while (offset < 0) {
                if (offset <= -1024) {
                    content.append('\uF80E');
                    offset += 1024;
                } else if (offset <= -512) {
                    content.append('\uF80D');
                    offset += 512;
                } else if (offset <= -128) {
                    content.append('\uF80C');
                    offset += 128;
                } else if (offset <= -64) {
                    content.append('\uF80B');
                    offset += 64;
                } else if (offset <= -32) {
                    content.append('\uF80A');
                    offset += 32;
                } else if (offset <= -16) {
                    content.append('\uF809');
                    offset += 16;
                } else if (offset <= -8) {
                    content.append('\uF808');
                    offset += 8;
                } else if (offset <= -7) {
                    content.append('\uF807');
                    offset += 7;
                } else if (offset <= -6) {
                    content.append('\uF806');
                    offset += 6;
                } else if (offset <= -5) {
                    content.append('\uF805');
                    offset += 5;
                } else if (offset <= -4) {
                    content.append('\uF804');
                    offset += 4;
                } else if (offset <= -3) {
                    content.append('\uF803');
                    offset += 3;
                } else if (offset <= -2) {
                    content.append('\uF802');
                    offset += 2;
                } else {
                    content.append('\uF801');
                    offset += 1;
                }
            }
        }
    }

    @Override
    public String toString() {
//        System.out.println("Building with offset: " + offset);
        appendOffset(-offset);
        return content.toString();
    }
}
