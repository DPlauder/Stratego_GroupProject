package Model;

import Config.MapTypeOne;
import Model.Settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<String, Territory> territories;
    private Continent[] continents;

    public Board() {
        territories = new HashMap<>();
        continents = new Continent[4];

        for (int i = 0; i < 24; i++) {
            territories.put("Territory " + (i + 1), new Territory("Territory " + (i + 1)));
        }

        for (int i = 0; i < 4; i++) {
            continents[i] = new Continent("Continent " + (i + 1));
            for (int j = 0; j < 6; j++) {
                Territory territory = territories.get("Territory " + (i * 6 + j + 1));
                continents[i].addTerritory(territory);
            }
            setAdjacentTerritories();
        }
    }



    private void setAdjacentTerritories() {
        //FIXME Möglicher Fehler mit Thread
        Map<String, List<String>> neighborsList;
        if(Settings.MAPTYPE == 1){
            MapTypeOne.initializeMap();
            neighborsList = MapTypeOne.getTerritories();
        }


        // Territory 1
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 7"));

        // Territory 2
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 8"));

        // Territory 3
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 9"));

        // Territory 4
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 10"));

        // Territory 5
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 11"));

        // Territory 6
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 12"));

        // Territory 7
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 8
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 14"));

        // Territory 9
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 15"));

        // Territory 10
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 16"));

        // Territory 11
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 17"));

        // Territory 12
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 18"));

        // Territory 13
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 19"));

        // Territory 14
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 15
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 16
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 17
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 18
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 19
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 20
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 21
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 22
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 21"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 23
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 24
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 23"));
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
