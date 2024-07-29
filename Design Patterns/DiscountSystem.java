// Strategy Interface
interface DiscountStrategy {
    double applyDiscount(double price);
}

// Concrete Strategy for No Discount
class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}

// Concrete Strategy for Seasonal Discount
class SeasonalDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.9; // 10% discount
    }
}

// Concrete Strategy for Member Discount
class MemberDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.85; // 15% discount
    }
}

// Context
class Bill {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateFinalPrice(double price) {
        return discountStrategy.applyDiscount(price);
    }
}

// Client
public class DiscountSystem {
    public static void main(String[] args) {
        Bill bill = new Bill();

        bill.setDiscountStrategy(new NoDiscount());
        System.out.println("Final Price with No Discount: $" + bill.calculateFinalPrice(100));

        bill.setDiscountStrategy(new SeasonalDiscount());
        System.out.println("Final Price with Seasonal Discount: $" + bill.calculateFinalPrice(100));

        bill.setDiscountStrategy(new MemberDiscount());
        System.out.println("Final Price with Member Discount: $" + bill.calculateFinalPrice(100));
    }
}
