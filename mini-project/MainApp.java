import java.util.Scanner;

public class MainApp {
    private OfficeManager officeManager;
    private Scanner scanner;
    private boolean exit;

    public MainApp() {
        officeManager = OfficeManager.getInstance();
        scanner = new Scanner(System.in);
        exit = false;
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
            Logger.log("Application shutdown gracefully.");
        }));

        while (!exit) {
            showMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("\nSmart Office Facility Management");
        System.out.println("1. Configure Rooms");
        System.out.println("2. Set Room Capacity");
        System.out.println("3. Book Room");
        System.out.println("4. Cancel Room Booking");
        System.out.println("5. Update Occupancy");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void handleUserChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    handleConfigureRooms();
                    break;
                case 2:
                    handleSetRoomCapacity();
                    break;
                case 3:
                    handleBookRoom();
                    break;
                case 4:
                    handleCancelRoomBooking();
                    break;
                case 5:
                    handleUpdateOccupancy();
                    break;
                case 0:
                    exit = true;
                    Logger.log("User initiated exit.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.logError("Error handling user choice: " + e.getMessage());
        }
    }

    private void handleConfigureRooms() {
        System.out.print("Enter the number of meeting rooms: ");
        int count = Integer.parseInt(scanner.nextLine());
        officeManager.configureRooms(count);
    }

    private void handleSetRoomCapacity() {
        System.out.print("Enter room number: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());
        try {
            Room room = officeManager.getRoom(roomId);
            if (room == null) {
                throw new IllegalArgumentException("Invalid room number. Please enter a valid room number.");
            }
            room.setMaxCapacity(capacity);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.logError("Error setting room capacity: " + e.getMessage());
        }
    }

    private void handleBookRoom() {
        System.out.print("Enter room number: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());
        try {
            Command command = new BookRoomCommand(roomId, startTime, duration);
            officeManager.executeCommand(command);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.logError("Error booking room: " + e.getMessage());
        }
    }

    private void handleCancelRoomBooking() {
        System.out.print("Enter room number: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        try {
            Command command = new CancelRoomCommand(roomId);
            officeManager.executeCommand(command);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.logError("Error canceling room booking: " + e.getMessage());
        }
    }

    private void handleUpdateOccupancy() {
        System.out.print("Enter room number: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of occupants: ");
        int occupants = Integer.parseInt(scanner.nextLine());
        try {
            Command command = new UpdateOccupancyCommand(roomId, occupants);
            officeManager.executeCommand(command);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.logError("Error updating occupancy: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run();
    }
}
