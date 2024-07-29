public class ControlSystem implements Observer {
    @Override
    public void update(String message) {
        System.out.println("ControlSystem: " + message);
        Logger.log("ControlSystem updated with message: " + message);
    }
}
