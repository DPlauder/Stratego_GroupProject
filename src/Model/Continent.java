package Model;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    private final String name;
    private final List<Territory> territories;

    public Continent(String name) {
        this.name = name;
        this.territories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTerritory(Territory territory) {
        this.territories.add(territory);
    }

    public List<Territory> getTerritories() {
        return territories;
    }
}
