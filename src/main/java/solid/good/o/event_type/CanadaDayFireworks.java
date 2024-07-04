package solid.good.o.event_type;

public class CanadaDayFireworks implements EventType {
    @Override
    public void setupEventEnvironment() {
        System.out.println("Setting up environment for the event CanadaDayFireworks");
    }
}
