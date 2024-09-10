package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player {
    private final String name;
    private final List<Territory> territories;
    private int armyCount;
    private List<Card> cards;
    //dazugefügt
    private Color color;

    public Player(String name, Color color, int army) {
        this.name = name;
        this.territories = new ArrayList<>();
        this.armyCount = army;
        this.cards = new ArrayList<>();
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        this.territories.add(territory);
    }

    public void addArmies(int count) {
        this.armyCount += count;
    }

    public void removeArmies(int count) {
        this.armyCount -= count;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void useCard() {
        if (!this.cards.isEmpty()) {
            this.cards.remove(0);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean controlsContinent(Continent continent) {
        return new HashSet<>(territories).containsAll(continent.getTerritories());
    }

    public Color getColor(){
        return this.color;
    }
}
