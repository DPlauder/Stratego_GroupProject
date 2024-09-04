package Config;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class MapTypeOne {
    private static final Map<String, List<String>> territoryNeighbors = new HashMap<>();

    public static Map<String, List<String>> getTerritories(){
        return territoryNeighbors;
    }

    public static void addNeighbors(String territory, List<String> neighbors) {
        territoryNeighbors.put(territory, neighbors);
    }

    public static List<String> getNeighbors(String territory) {
        return territoryNeighbors.getOrDefault(territory, new ArrayList<>());
    }
    public static void initializeMap() {
        //Continent 1
        addNeighbors("territory1", List.of("territory2", "territory5", "territory6", "territory8"));
        addNeighbors("territory2", List.of("territory1", "territory3", "territory5", "territory6"));
        addNeighbors("territory3", List.of("territory2", "territory4", "territory5"));
        addNeighbors("territory4", List.of("territory3", "territory5", "territory7"));
        addNeighbors("territory5", List.of("territory1", "territory2", "territory3","territory4","territory6"));
        addNeighbors("territory6", List.of("territory1", "territory5", "territory23"));
        //Continent 2
        addNeighbors("territory7", List.of("territory4", "territory8", "territory9"));
        addNeighbors("territory8", List.of("territory7", "territory9", "territory11"));
        addNeighbors("territory9", List.of("territory7", "territory8", "territory10", "territory11"));
        addNeighbors("territory10", List.of("territory9", "territory11", "territory12", "territory14", "territory15"));
        addNeighbors("territory11", List.of("territory8", "territory9", "territory10","territory12"));
        addNeighbors("territory12", List.of("territory10", "territory11", "territory13", "territory14", "territory15"));
        //Continent 3
        addNeighbors("territory13", List.of("territory12", "territory14", "territory17"));
        addNeighbors("territory14", List.of("territory12", "territory13", "territory15", "territory16", "territory17"));
        addNeighbors("territory15", List.of("territory10", "territory12", "territory14", "territory16", "territory24"));
        addNeighbors("territory16", List.of("territory14", "territory15", "territory17","territory18"));
        addNeighbors("territory17", List.of("territory13", "territory14", "territory16", "territory18"));
        //Continent 4
        addNeighbors("territory18", List.of("territory17", "territory19", "territory20"));
        addNeighbors("territory19", List.of("territory18", "territory20", "territory21"));
        addNeighbors("territory20", List.of("territory18", "territory19", "territory21", "territory22"));
        addNeighbors("territory21", List.of("territory19", "territory20", "territory22", "territory23", "territory24"));
        addNeighbors("territory22", List.of("territory20", "territory21", "territory23"));
        addNeighbors("territory23", List.of("territory6", "territory21", "territory22", "territory24"));
        addNeighbors("territory24", List.of("territory15", "territory21", "territory23"));
    }
}
