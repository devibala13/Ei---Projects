// Product Interface
interface MenuItem {
    void prepare();
}

// Concrete Products
class Pizza implements MenuItem {
    @Override
    public void prepare() {
        System.out.println("Preparing Pizza");
    }
}

class Burger implements MenuItem {
    @Override
    public void prepare() {
        System.out.println("Preparing Burger");
    }
}

// Creator
abstract class MenuItemCreator {
    public abstract MenuItem createMenuItem();
}

// Concrete Creators
class PizzaCreator extends MenuItemCreator {
    @Override
    public MenuItem createMenuItem() {
        return new Pizza();
    }
}

class BurgerCreator extends MenuItemCreator {
    @Override
    public MenuItem createMenuItem() {
        return new Burger();
    }
}

// Client
public class RestaurantMenu {
    public static void main(String[] args) {
        MenuItemCreator pizzaCreator = new PizzaCreator();
        MenuItem pizza = pizzaCreator.createMenuItem();
        pizza.prepare();

        MenuItemCreator burgerCreator = new BurgerCreator();
        MenuItem burger = burgerCreator.createMenuItem();
        burger.prepare();
    }
}
