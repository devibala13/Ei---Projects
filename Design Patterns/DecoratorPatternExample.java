// Component
interface MenuItem {
    String getDescription();
    double getCost();
}

// Concrete Component
class BasicPizza implements MenuItem {
    @Override
    public String getDescription() {
        return "Basic Pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Decorator
abstract class MenuItemDecorator implements MenuItem {
    protected MenuItem decoratedMenuItem;

    public MenuItemDecorator(MenuItem menuItem) {
        this.decoratedMenuItem = menuItem;
    }

    @Override
    public String getDescription() {
        return decoratedMenuItem.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedMenuItem.getCost();
    }
}

// Concrete Decorators
class CheeseDecorator extends MenuItemDecorator {
    public CheeseDecorator(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public String getDescription() {
        return decoratedMenuItem.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return decoratedMenuItem.getCost() + 1.0;
    }
}

class PepperoniDecorator extends MenuItemDecorator {
    public PepperoniDecorator(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public String getDescription() {
        return decoratedMenuItem.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return decoratedMenuItem.getCost() + 1.5;
    }
}

// Client
public class DecoratorPatternExample {
    public static void main(String[] args) {
        MenuItem pizza = new BasicPizza();
        System.out.println(pizza.getDescription() + " $" + pizza.getCost());

        pizza = new CheeseDecorator(pizza);
        System.out.println(pizza.getDescription() + " $" + pizza.getCost());

        pizza = new PepperoniDecorator(pizza);
        System.out.println(pizza.getDescription() + " $" + pizza.getCost());
    }
}
