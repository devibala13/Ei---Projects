// Target Interface
interface PaymentGateway {
    void makePayment(String accountNumber, double amount);
}

// Adaptee
class OldPaymentSystem {
    public void processPayment(String customer, double money) {
        System.out.println("Processing payment for customer: " + customer + " Amount: $" + money);
    }
}

// Adapter
class PaymentAdapter implements PaymentGateway {
    private OldPaymentSystem oldPaymentSystem;

    public PaymentAdapter(OldPaymentSystem oldPaymentSystem) {
        this.oldPaymentSystem = oldPaymentSystem;
    }

    @Override
    public void makePayment(String accountNumber, double amount) {
        oldPaymentSystem.processPayment(accountNumber, amount);
    }
}

// Client
public class PaymentSystemAdapter {
    public static void main(String[] args) {
        OldPaymentSystem oldPaymentSystem = new OldPaymentSystem();
        PaymentGateway paymentGateway = new PaymentAdapter(oldPaymentSystem);
        paymentGateway.makePayment("123-456", 250.0);
    }
}
