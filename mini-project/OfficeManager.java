import java.util.*;

public class OfficeManager {
    private static OfficeManager instance;
    private Map<Integer, Room> rooms;
    private List<Command> commandHistory;

    private OfficeManager() {
        rooms = new HashMap<>();
        commandHistory = new ArrayList<>();
        Logger.log("OfficeManager instance created.");
    }

    public static OfficeManager getInstance() {
        if (instance == null) {
            instance = new OfficeManager();
        }
        return instance;
    }

    public void configureRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.put(i, new Room(i));
        }
        Logger.log("Office configured with " + count + " meeting rooms.");
        System.out.println("Office configured with " + count + " meeting rooms: " + rooms.keySet());
    }

    public Room getRoom(int roomId) {
        return rooms.get(roomId);
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    public void executeCommand(Command command) {
        try {
            command.execute();
            commandHistory.add(command);
            Logger.log("Executed command: " + command.getClass().getSimpleName());
        } catch (Exception e) {
            Logger.logError("Command execution failed: " + e.getMessage());
            throw e;
        }
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
            Logger.log("Undid command: " + lastCommand.getClass().getSimpleName());
        }
    }
}
