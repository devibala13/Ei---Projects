import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(String status);
}

// Concrete Observer
class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("Customer " + name + " is notified. Order Status: " + status);
    }
}

// Subject Interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class Order implements Subject {
    private List<Observer> observers;
    private String status;

    public Order() {
        observers = new ArrayList<>();
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
}

// Client
public class RestaurantManagement {
    public static void main(String[] args) {
        Order order = new Order();

        Customer customer1 = new Customer("John");
        Customer customer2 = new Customer("Jane");

        order.registerObserver(customer1);
        order.registerObserver(customer2);

        order.setStatus("Cooking");
        order.setStatus("Ready for Pickup");
    }
}



