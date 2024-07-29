public class Sensor implements Observer {
    @Override
    public void update(String message) {
        System.out.println("SensorSystem: " + message);
        Logger.log("SensorSystem updated with message: " + message);
    }
}
