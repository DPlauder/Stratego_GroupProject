package Utils;

import java.awt.Color;

public class Utils {
    public static Color stringToColor(String colorStr) {
        return switch (colorStr.toLowerCase()) {
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "black" -> Color.BLACK;
            case "yellow" -> Color.YELLOW;
            case "green" -> Color.GREEN;
            default -> Color.GRAY;
        };
    }
}
