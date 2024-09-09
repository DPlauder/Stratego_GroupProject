package Model;

import java.util.ArrayList;
import java.util.List;
import Config.*;
import Utils.Utils;

import java.awt.Color;

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
    public static List<Color> PLAYERCOLORS;
    public static GameMapType MAPTYPE;
    public static MapType CURRENT_MAP;
    public static int STARTARMY;


    public Settings(List<String> playernames, List<String> playerColors, String mapType) {
        PLAYERNAMES = playernames;
        PLAYERCOLORS = setColors(playerColors);
        PLAYERCOUNT = playernames.size();

        MAPTYPE = GameMapType.fromString(mapType);
        CURRENT_MAP = MAPTYPE.getMapTypeInstance();
        CURRENT_MAP.initializeMap();
        setStartArmy();;
    }

    public List<Color> setColors(List<String> playerColors){
        List<Color> tempList = new ArrayList<>();
        for(String color: playerColors){
            Color tempColor = Utils.stringToColor(color);
            tempList.add(tempColor);
        }
        return tempList;
    }
    private void setStartArmy(){
        switch (PLAYERCOUNT){
            case 2:
                STARTARMY = 20;
                break;
            case 3:
                STARTARMY = 25;
                break;
            case 4:
                STARTARMY = 30;
                break;

            default:
                System.out.println("Error in setStartArmies");
        }
    }
}