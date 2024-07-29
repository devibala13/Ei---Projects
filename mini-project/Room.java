import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private int maxCapacity;
    private int currentOccupancy;
    private boolean booked;
    private List<Observer> observers;

    public Room(int id) {
        this.id = id;
        this.maxCapacity = 0;
        this.currentOccupancy = 0;
        this.booked = false;
        this.observers = new ArrayList<>();
        Logger.log("Room " + id + " created.");
        addObserver(new ControlSystem()); // Adding ControlSystem as an Observer
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity. Please enter a valid positive number.");
        }
        this.maxCapacity = maxCapacity;
        System.out.println("Room " + id + " maximum capacity set to " + maxCapacity);
        Logger.log("Room " + id + " maximum capacity set to " + maxCapacity);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void bookRoom(String startTime, int duration) {
        if (booked) {
            throw new IllegalStateException("Room " + id + " is already booked during this time. Cannot book.");
        }
        booked = true;
        notifyObservers("Room " + id + " booked from " + startTime + " for " + duration + " minutes.");
        Logger.log("Room " + id + " booked from " + startTime + " for " + duration + " minutes.");
    }

    public void cancelBooking() {
        if (!booked) {
            throw new IllegalStateException("Room " + id + " is not booked. Cannot cancel booking.");
        }
        booked = false;
        notifyObservers("Booking for Room " + id + " cancelled successfully.");
        Logger.log("Booking for Room " + id + " cancelled successfully.");
    }

    public void updateOccupancy(int occupants) {
        if (occupants < 0) {
            throw new IllegalArgumentException("Occupancy cannot be negative.");
        }
        if (occupants == 0) {
            currentOccupancy = 0;
            booked = false;
            notifyObservers("Room " + id + " is now unoccupied. AC and lights turned off.");
            Logger.log("Room " + id + " is now unoccupied. AC and lights turned off.");
        } else if (occupants > maxCapacity) {
            throw new IllegalStateException("Occupancy exceeds room capacity.");
        } else {
            currentOccupancy = occupants;
            if (occupants >= 2) {
                notifyObservers("Room " + id + " is now occupied by " + occupants + " persons. AC and lights turned on.");
                Logger.log("Room " + id + " is now occupied by " + occupants + " persons. AC and lights turned on.");
            } else {
                notifyObservers("Room " + id + " occupancy insufficient to mark as occupied.");
                Logger.log("Room " + id + " occupancy insufficient to mark as occupied.");
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public boolean isBooked() {
        return booked;
    }
}
