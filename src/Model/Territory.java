package Model;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private final String name;
    private Player owner;
    private int armyCount;
    private final List<Territory> adjacentTerritories;

    public Territory(String name) {
        this.name = name;
        this.owner = null;
        this.armyCount = 0;
        this.adjacentTerritories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void addArmies(int count) {
        this.armyCount += count;
    }

    public void removeArmies(int count) {
        this.armyCount -= count;;
    }

    public List<Territory> getAdjacentTerritories() {
        return adjacentTerritories;
    }

    public void addAdjacentTerritory(Territory territory) {
        adjacentTerritories.add(territory);

    }
    public void setNeighbours(List<Territory> territories){
        for(Territory territory : territories){
            addAdjacentTerritory(territory);
        }
    }
}
