package Utils;

import java.awt.Color;

public class Utils {
    public static Color stringToColor(String colorStr) {
        switch (colorStr.toLowerCase()) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "black":
                return Color.BLACK;
            case "yellow":
                return Color.YELLOW;
            case "green":
                return Color.GREEN;
            default:
                return Color.GRAY;
        }
    }
}