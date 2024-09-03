package Model;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, Territory> territories;
    private Continent[] continents;

    public Board() {
        territories = new HashMap<>();
        continents = new Continent[4];

        for (int i = 0; i < 24; i++) {
            territories.put("Model.Territory " + (i + 1), new Territory("Model.Territory " + (i + 1)));
        }

        for (int i = 0; i < 4; i++) {
            continents[i] = new Continent("Model.Continent " + (i + 1));
            for (int j = 0; j < 6; j++) {
                Territory territory = territories.get("Model.Territory " + (i * 6 + j + 1));
                continents[i].addTerritory(territory);
            }
            setAdjacentTerritories();
        }
    }

    private void setAdjacentTerritories() {
        // Model.Territory 1
        territories.get("Model.Territory 1").addAdjacentTerritory(territories.get("Model.Territory 2"));
        territories.get("Model.Territory 1").addAdjacentTerritory(territories.get("Model.Territory 7"));

        // Model.Territory 2
        territories.get("Model.Territory 2").addAdjacentTerritory(territories.get("Model.Territory 1"));
        territories.get("Model.Territory 2").addAdjacentTerritory(territories.get("Model.Territory 3"));
        territories.get("Model.Territory 2").addAdjacentTerritory(territories.get("Model.Territory 8"));

        // Model.Territory 3
        territories.get("Model.Territory 3").addAdjacentTerritory(territories.get("Model.Territory 2"));
        territories.get("Model.Territory 3").addAdjacentTerritory(territories.get("Model.Territory 4"));
        territories.get("Model.Territory 3").addAdjacentTerritory(territories.get("Model.Territory 9"));

        // Model.Territory 4
        territories.get("Model.Territory 4").addAdjacentTerritory(territories.get("Model.Territory 3"));
        territories.get("Model.Territory 4").addAdjacentTerritory(territories.get("Model.Territory 5"));
        territories.get("Model.Territory 4").addAdjacentTerritory(territories.get("Model.Territory 10"));

        // Model.Territory 5
        territories.get("Model.Territory 5").addAdjacentTerritory(territories.get("Model.Territory 4"));
        territories.get("Model.Territory 5").addAdjacentTerritory(territories.get("Model.Territory 6"));
        territories.get("Model.Territory 5").addAdjacentTerritory(territories.get("Model.Territory 11"));

        // Model.Territory 6
        territories.get("Model.Territory 6").addAdjacentTerritory(territories.get("Model.Territory 5"));
        territories.get("Model.Territory 6").addAdjacentTerritory(territories.get("Model.Territory 12"));

        // Model.Territory 7
        territories.get("Model.Territory 7").addAdjacentTerritory(territories.get("Model.Territory 1"));
        territories.get("Model.Territory 7").addAdjacentTerritory(territories.get("Model.Territory 8"));
        territories.get("Model.Territory 7").addAdjacentTerritory(territories.get("Model.Territory 13"));

        // Model.Territory 8
        territories.get("Model.Territory 8").addAdjacentTerritory(territories.get("Model.Territory 2"));
        territories.get("Model.Territory 8").addAdjacentTerritory(territories.get("Model.Territory 7"));
        territories.get("Model.Territory 8").addAdjacentTerritory(territories.get("Model.Territory 9"));
        territories.get("Model.Territory 8").addAdjacentTerritory(territories.get("Model.Territory 14"));

        // Model.Territory 9
        territories.get("Model.Territory 9").addAdjacentTerritory(territories.get("Model.Territory 3"));
        territories.get("Model.Territory 9").addAdjacentTerritory(territories.get("Model.Territory 8"));
        territories.get("Model.Territory 9").addAdjacentTerritory(territories.get("Model.Territory 10"));
        territories.get("Model.Territory 9").addAdjacentTerritory(territories.get("Model.Territory 15"));

        // Model.Territory 10
        territories.get("Model.Territory 10").addAdjacentTerritory(territories.get("Model.Territory 4"));
        territories.get("Model.Territory 10").addAdjacentTerritory(territories.get("Model.Territory 9"));
        territories.get("Model.Territory 10").addAdjacentTerritory(territories.get("Model.Territory 11"));
        territories.get("Model.Territory 10").addAdjacentTerritory(territories.get("Model.Territory 16"));

        // Model.Territory 11
        territories.get("Model.Territory 11").addAdjacentTerritory(territories.get("Model.Territory 5"));
        territories.get("Model.Territory 11").addAdjacentTerritory(territories.get("Model.Territory 10"));
        territories.get("Model.Territory 11").addAdjacentTerritory(territories.get("Model.Territory 12"));
        territories.get("Model.Territory 11").addAdjacentTerritory(territories.get("Model.Territory 17"));

        // Model.Territory 12
        territories.get("Model.Territory 12").addAdjacentTerritory(territories.get("Model.Territory 6"));
        territories.get("Model.Territory 12").addAdjacentTerritory(territories.get("Model.Territory 11"));
        territories.get("Model.Territory 12").addAdjacentTerritory(territories.get("Model.Territory 18"));

        // Model.Territory 13
        territories.get("Model.Territory 13").addAdjacentTerritory(territories.get("Model.Territory 7"));
        territories.get("Model.Territory 13").addAdjacentTerritory(territories.get("Model.Territory 14"));
        territories.get("Model.Territory 13").addAdjacentTerritory(territories.get("Model.Territory 19"));

        // Model.Territory 14
        territories.get("Model.Territory 14").addAdjacentTerritory(territories.get("Model.Territory 8"));
        territories.get("Model.Territory 14").addAdjacentTerritory(territories.get("Model.Territory 13"));
        territories.get("Model.Territory 14").addAdjacentTerritory(territories.get("Model.Territory 15"));
        territories.get("Model.Territory 14").addAdjacentTerritory(territories.get("Model.Territory 20"));

        // Model.Territory 15
        territories.get("Model.Territory 15").addAdjacentTerritory(territories.get("Model.Territory 9"));
        territories.get("Model.Territory 15").addAdjacentTerritory(territories.get("Model.Territory 14"));
        territories.get("Model.Territory 15").addAdjacentTerritory(territories.get("Model.Territory 16"));
        territories.get("Model.Territory 15").addAdjacentTerritory(territories.get("Model.Territory 21"));

        // Model.Territory 16
        territories.get("Model.Territory 16").addAdjacentTerritory(territories.get("Model.Territory 10"));
        territories.get("Model.Territory 16").addAdjacentTerritory(territories.get("Model.Territory 15"));
        territories.get("Model.Territory 16").addAdjacentTerritory(territories.get("Model.Territory 17"));
        territories.get("Model.Territory 16").addAdjacentTerritory(territories.get("Model.Territory 22"));

        // Model.Territory 17
        territories.get("Model.Territory 17").addAdjacentTerritory(territories.get("Model.Territory 11"));
        territories.get("Model.Territory 17").addAdjacentTerritory(territories.get("Model.Territory 16"));
        territories.get("Model.Territory 17").addAdjacentTerritory(territories.get("Model.Territory 18"));
        territories.get("Model.Territory 17").addAdjacentTerritory(territories.get("Model.Territory 23"));

        // Model.Territory 18
        territories.get("Model.Territory 18").addAdjacentTerritory(territories.get("Model.Territory 12"));
        territories.get("Model.Territory 18").addAdjacentTerritory(territories.get("Model.Territory 17"));
        territories.get("Model.Territory 18").addAdjacentTerritory(territories.get("Model.Territory 24"));

        // Model.Territory 19
        territories.get("Model.Territory 19").addAdjacentTerritory(territories.get("Model.Territory 13"));
        territories.get("Model.Territory 19").addAdjacentTerritory(territories.get("Model.Territory 20"));

        // Model.Territory 20
        territories.get("Model.Territory 20").addAdjacentTerritory(territories.get("Model.Territory 14"));
        territories.get("Model.Territory 20").addAdjacentTerritory(territories.get("Model.Territory 19"));
        territories.get("Model.Territory 20").addAdjacentTerritory(territories.get("Model.Territory 21"));

        // Model.Territory 21
        territories.get("Model.Territory 21").addAdjacentTerritory(territories.get("Model.Territory 15"));
        territories.get("Model.Territory 21").addAdjacentTerritory(territories.get("Model.Territory 20"));
        territories.get("Model.Territory 21").addAdjacentTerritory(territories.get("Model.Territory 22"));

        // Model.Territory 22
        territories.get("Model.Territory 22").addAdjacentTerritory(territories.get("Model.Territory 16"));
        territories.get("Model.Territory 22").addAdjacentTerritory(territories.get("Model.Territory 21"));
        territories.get("Model.Territory 22").addAdjacentTerritory(territories.get("Model.Territory 23"));

        // Model.Territory 23
        territories.get("Model.Territory 23").addAdjacentTerritory(territories.get("Model.Territory 17"));
        territories.get("Model.Territory 23").addAdjacentTerritory(territories.get("Model.Territory 22"));
        territories.get("Model.Territory 23").addAdjacentTerritory(territories.get("Model.Territory 24"));

        // Model.Territory 24
        territories.get("Model.Territory 24").addAdjacentTerritory(territories.get("Model.Territory 18"));
        territories.get("Model.Territory 24").addAdjacentTerritory(territories.get("Model.Territory 23"));
    }


    public Territory getTerritory(String name) {
        return territories.get(name);
    }

    public Map<String, Territory> getTerritories() {
        return territories;
    }

    public Continent[] getContinents() {
        return continents;
    }
}
