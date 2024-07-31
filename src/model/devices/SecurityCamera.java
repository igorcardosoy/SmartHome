package model.devices;

public class SecurityCamera extends Device {

    private boolean streaming;

    public SecurityCamera(String id, String name, boolean status) {
        super(id, name, status);
    }

    public boolean isStreaming() {
        return streaming;
    }

    public void initStreming() {
        this.streaming = true;
    }

    public void stopStreaming() {
        this.streaming = false;
    }
}
