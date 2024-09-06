package Model;

import java.util.List;
import Config.*;

enum GameMapType {
    BASIC(1, new MapTypeOne());
    //CLASSIC(2);

    private final int value;
    private final MapType mapTypeInstance;

    GameMapType(int value, MapType mapTypeInstance) {
        this.value = value;
        this.mapTypeInstance = mapTypeInstance;
    }

    public int getValue() {
        return value;
    }
    public MapType getMapTypeInstance() {
        return mapTypeInstance;
    }

    public static GameMapType fromString(String name) {
        for (GameMapType mapType : GameMapType.values()) {
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
    public static GameMapType MAPTYPE;
    public static MapType CURRENT_MAP;


    public Settings(List<String> playernames, List<String> playerColors, String mapType) {
        PLAYERNAMES = playernames;
        PLAYERCOLORS = playerColors;
        PLAYERCOUNT = playernames.size();

        MAPTYPE = GameMapType.fromString(mapType);
        CURRENT_MAP = MAPTYPE.getMapTypeInstance();
        CURRENT_MAP.initializeMap();
    }
}