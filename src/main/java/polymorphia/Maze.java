package polymorphia;

import polymorphia.characters.Adventurer;
import polymorphia.characters.Character;
import polymorphia.characters.Creature;
import polymorphia.characters.Guard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private final Random rand = new Random();

    private List<Room> rooms = new ArrayList<>();

    public Maze() {
        Room northwest = new Room("Northwest");
        rooms.add(northwest);
        Room northeast = new Room("Northeast");
        rooms.add(northeast);
        Room southwest = new Room("Southwest");
        rooms.add(southwest);
        Room southeast = new Room("Southeast");
        rooms.add(southeast);

        northwest.connect(northeast).connect(southwest);
        northeast.connect(southeast);
        southwest.connect(southeast);

        addToRandomRoom(new Adventurer("Bilbo"));
        addToRandomRoom(new Creature("Ogre"));
        addToRandomRoom(new Guard("Guard"));
    }

    public int size() {
        return rooms.size();
    }

    public String toString() {
        return String.join("\n\n", rooms.stream().map(Object::toString).toList());
    }

    public Boolean hasLivingCreatures() {
        return rooms.stream().anyMatch(Room::hasLivingCreatures);
    }

    public Boolean hasLivingAdventurers() {
        return rooms.stream().anyMatch(Room::hasLivingAdventurers);
    }

    private Room getRandomRoom() {
        return rooms.get(rand.nextInt(rooms.size()));
    }

    public List<Character> getLivingCreatures() {
        List<Character> creatures = new ArrayList<>();
        for (Room room : rooms) {
            creatures.addAll(room.getLivingCreatures());
        }
        return creatures;
    }

    public List<Character> getLivingCharacters() {
        List<Character> characters = new ArrayList<>();
        for (Room room : rooms) {
            characters.addAll(room.getLivingCharacters());
        }
        return characters;
    }

    public void addToRandomRoom(Character character) {
        getRandomRoom().add(character);
    }

    public boolean hasLivingCharacters() {
        return getLivingCharacters().stream().anyMatch(Character::isAlive);
    }
}
