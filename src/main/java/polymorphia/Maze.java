package polymorphia;

import polymorphia.characters.Adventurer;
import polymorphia.characters.Character;
import polymorphia.characters.Creature;
import polymorphia.characters.Guard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private final Random rand;

    private final List<Room> rooms;

    public Maze(List<Room> rooms, List<Character> characters, Random rand) {
        this.rooms = rooms;
        this.rand = rand;
        addCharacters(characters);
    }

    public Maze(List<Room> rooms, List<Character> characters) {
        this(rooms, characters, new Random());
    }

    public Maze(List<Room> rooms) {
        this(rooms, List.of());
    }

    public Maze() {
        this(create2x2Rooms(), defaultCharacters(), new Random());
    }

    public static Maze create2x2() {
        return new Maze(create2x2Rooms(), defaultCharacters(), new Random());
    }

    public static Maze create2x2(List<Character> characters) {
        return new Maze(create2x2Rooms(), characters, new Random());
    }

    public static Maze create2x2(List<Character> characters, Random rand) {
        return new Maze(create2x2Rooms(), characters, rand);
    }

    public static Maze create3x3() {
        return new Maze(create3x3Rooms(), defaultCharacters(), new Random());
    }

    public static Maze create3x3(List<Character> characters) {
        return new Maze(create3x3Rooms(), characters, new Random());
    }

    public static Maze create3x3(List<Character> characters, Random rand) {
        return new Maze(create3x3Rooms(), characters, rand);
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

    public void addCharacters(List<Character> characters) {
        for (Character character : characters) {
            addToRandomRoom(character);
        }
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

    private static List<Character> defaultCharacters() {
        return List.of(
                new Adventurer("Bilbo"),
                new Creature("Ogre"),
                new Guard("Guard")
        );
    }

    private static List<Room> create2x2Rooms() {
        List<Room> rooms = new ArrayList<>();

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

        return rooms;
    }

    private static List<Room> create3x3Rooms() {
        List<Room> rooms = new ArrayList<>();

        Room northwest = new Room("Northwest");
        Room north = new Room("North");
        Room northeast = new Room("Northeast");
        Room west = new Room("West");
        Room center = new Room("Center");
        Room east = new Room("East");
        Room southwest = new Room("Southwest");
        Room south = new Room("South");
        Room southeast = new Room("Southeast");

        rooms.add(northwest);
        rooms.add(north);
        rooms.add(northeast);
        rooms.add(west);
        rooms.add(center);
        rooms.add(east);
        rooms.add(southwest);
        rooms.add(south);
        rooms.add(southeast);

        northwest.connect(north).connect(west);
        north.connect(northeast).connect(center);
        west.connect(center).connect(southwest);
        center.connect(east).connect(south);
        east.connect(northeast).connect(southeast);
        southwest.connect(south);
        south.connect(southeast);

        return rooms;
    }
}
