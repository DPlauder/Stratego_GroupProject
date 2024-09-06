package Model;

import Model.Settings;
import Config.MapType;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // HashMap in Liste umgebaut
    private List<Territory> territories;
    private Continent[] continents;
    private MapType mapType;

    public Board() {
        territories = new ArrayList<>();
        continents = new Continent[4];
        mapType = Settings.CURRENT_MAP;

        setTerritories();
        setAdjacentTerritories();

        for (int i = 0; i < 4; i++) {
            continents[i] = new Continent("Continent " + (i + 1));
            for (int j = 0; j < 6; j++) {
                String name = "Territory " + (i * 6 + j + 1);
                int index = findTerritoryIndexByName(name);
                if (index != -1) {
                    Territory territory = territories.get(index);
                    continents[i].addTerritory(territory);
                }
            }
        }
    }
    // dazu um Territory zu finden
    private int findTerritoryIndexByName(String name) {
        for (int i = 0; i < territories.size(); i++) {
            if (territories.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
    // neu
    private void setTerritories(){
        for (int i = 0; i < mapType.getTerritoryCount(); i++) {
            territories.add( new Territory("territory" + (i + 1)));
        }
    }

    // Umgebaut für dynamische Karten
    private void setAdjacentTerritories() {
        for(Territory territory : territories){
            List<String> neighbourNames = mapType.getNeighbors(territory.getName());
            List<Territory> neighbours = new ArrayList<>();
            for (String neighborName : neighbourNames) {
                Territory neighbour = getTerritory(neighborName);
                if (neighbour != null) {
                    neighbours.add(neighbour);
                }
            }
            territory.setNeighbours(neighbours);
        }
    }
    //umgebaut mit index für List anstelle von Map
    public Territory getTerritory(String name) {
        int territoryIndex = findTerritoryIndexByName(name);
        return territories.get(territoryIndex);
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public Continent[] getContinents() {
        return continents;
    }


}
