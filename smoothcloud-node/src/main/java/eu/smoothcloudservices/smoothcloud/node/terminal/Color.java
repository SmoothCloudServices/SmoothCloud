package eu.smoothcloudservices.smoothcloud.node.terminal;

import org.jline.jansi.Ansi;

public enum Color {

    HIGHLIGHTER(28, 119, 255),
    DEFAULT(181, 181, 181),
    DARK(60, 60, 60),
    RED(255, 50, 50);

    String ansi;

    private static final Color[] VALUES = values();

    Color(int red, int green, int blue) {
        ansi = Ansi.ansi().a(Ansi.Attribute.RESET).fgRgb(red, green, blue).toString();
    }

    public static String translate(String output) {
        for (Color color : VALUES) {
            output = output.replace(STR."&\{color.ordinal()}", color.ansi);
        }
        return output;
    }
}
