package Config;

import Model.Territory;

import java.util.List;

public class MapTypeOne extends MapType {
    @Override
    public void initializeMap() {
        // Continent 1
        territories.put("territory1", List.of("territory2", "territory5", "territory6", "territory8"));
        territories.put("territory2", List.of("territory1", "territory3", "territory5", "territory6"));
        territories.put("territory3", List.of("territory2", "territory4", "territory5"));
        territories.put("territory4", List.of("territory3", "territory5", "territory7"));
        territories.put("territory5", List.of("territory1", "territory2", "territory3", "territory4", "territory6"));
        territories.put("territory6", List.of("territory1", "territory5", "territory23"));
        // Continent 2
        territories.put("territory7", List.of("territory4", "territory8", "territory9"));
        territories.put("territory8", List.of("territory7", "territory9", "territory11"));
        territories.put("territory9", List.of("territory7", "territory8", "territory10", "territory11"));
        territories.put("territory10", List.of("territory9", "territory11", "territory12", "territory14", "territory15"));
        territories.put("territory11", List.of("territory8", "territory9", "territory10", "territory12"));
        territories.put("territory12", List.of("territory10", "territory11", "territory13", "territory14", "territory15"));
        // Continent 3
        territories.put("territory13", List.of("territory12", "territory14", "territory17"));
        territories.put("territory14", List.of("territory12", "territory13", "territory15", "territory16", "territory17"));
        territories.put("territory15", List.of("territory10", "territory12", "territory14", "territory16", "territory24"));
        territories.put("territory16", List.of("territory14", "territory15", "territory17", "territory18"));
        territories.put("territory17", List.of("territory13", "territory14", "territory16", "territory18"));
        // Continent 4
        territories.put("territory18", List.of("territory17", "territory19", "territory20"));
        territories.put("territory19", List.of("territory18", "territory20", "territory21"));
        territories.put("territory20", List.of("territory18", "territory19", "territory21", "territory22"));
        territories.put("territory21", List.of("territory19", "territory20", "territory22", "territory23", "territory24"));
        territories.put("territory22", List.of("territory20", "territory21", "territory23"));
        territories.put("territory23", List.of("territory6", "territory21", "territory22", "territory24"));
        territories.put("territory24", List.of("territory15", "territory21", "territory23"));
    }
}
