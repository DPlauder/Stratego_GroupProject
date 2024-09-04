package Model;

import java.util.List;

enum MapType {
    BASIC(1),
    CLASSIC(2);


    private final int value;

    MapType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MapType fromString(String name) {
        for (MapType mapType : MapType.values()) {
            if (mapType.name().equalsIgnoreCase(name)) {
                return mapType;
            }
        }
        throw new IllegalArgumentException("Invalid map type name: " + name);
    }
}

public class Settings {
    public static int PLAYERCOUNT;
    public static List<String> PLAYERNAMES;
    public static List<String> PLAYERCOLORS;
    public static int MAPTYPE;

    public Settings(List<String> playernames, List<String> playerColors, String mapType) {
        PLAYERNAMES = playernames;
        PLAYERCOLORS = playerColors;
        PLAYERCOUNT = playernames.size();

        MapType type = MapType.fromString(mapType);

        MAPTYPE = type.getValue();
    }
}
