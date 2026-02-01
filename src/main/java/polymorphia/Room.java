package polymorphia;

import polymorphia.characters.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    static private final Random rand = new Random();

    private final String name;
    private final List<Room> neighbors = new ArrayList<>();
    private final List<Character> characters = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Character> getLivingCreatures() {
        return getLivingCharacters().stream()
                .filter(Character::isCreature)
                .toList();
    }

    public List<Character> getLivingAdventurers() {
        return getLivingCharacters().stream()
                .filter(Character::isAdventurer)
                .toList();
    }

    public List<String> getContents() {
        return getLivingCharacters().stream()
                .map(Object::toString)
                .toList();
    }

    public void addNeighbor(Room neighbor) {
        // Make sure we are never a neighbor of ourselves
        assert this != neighbor;
        this.neighbors.add(neighbor);
    }

    public Room connect(Room neighbor) {
        this.addNeighbor(neighbor);
        neighbor.addNeighbor(this);
        return this;
    }

    public String toString() {
        String representation = "\t" + name + ":\n\t\t";
        representation += String.join("\n\t\t", getContents());
        return representation;
    }

    public void add(Character character) {
        characters.add(character);
        character.enterRoom(this);
    }

    public Boolean hasLivingCreatures() {
        // Encapsulation: Room answers questions about its contents without exposing internal lists.
        return getLivingCharacters().stream().anyMatch(Character::isCreature);
    }

    public Boolean hasLivingAdventurers() {
        return getLivingCharacters().stream().anyMatch(Character::isAdventurer);
    }

    public void remove(Character character) {
        characters.remove(character);
    }

    public Character getRandomCreature() {
        List<Character> creatures = getLivingCreatures();
        return creatures.get(rand.nextInt(creatures.size()));
    }

    public Room getRandomNeighbor() {
        if (neighbors.isEmpty()) {
            return null;
        }
        return neighbors.stream().toList().get(rand.nextInt(neighbors.size()));
    }

    public void enter(Character character) {
        add(character);
    }

    public List<Character> getLivingCharacters() {
        return characters.stream()
                .filter(Character::isAlive)
                .toList();
    }

    public Character getRandomAdventurer() {
        List<Character> creatures = getLivingAdventurers();
        return creatures.get(rand.nextInt(creatures.size()));
    }
}
