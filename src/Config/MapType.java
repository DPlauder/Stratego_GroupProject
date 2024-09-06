package Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MapType {
    Map<String, List<String>> territories = new HashMap<>();

    public int getTerritoryCount() {
        return territories.size();
    }
    public List<String> getNeighbors(String territory) {
        return territories.getOrDefault(territory, List.of());
    }
    public abstract void initializeMap();

    public String getMapType() {
        return "Default Map Type";
    }

    public Map<String, List<String>> getTerritories() {
        return territories;
    }
}
