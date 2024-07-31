package model.devices;

public class Light extends Device {

    private int brightness;

    public Light(String id, String name, boolean status) {
        super(id, name, status);
        brightness = 100;
    }

    public boolean ajustBrightness(int level) {
        if (level > 0 && level <= 100) {
            brightness = level;
            return true;
        }

        if (level == 0) {
            brightness = 0;
            this.turnOff();
            return true;
        }

        return false;
    }

    public int getBrightness() {
        return brightness;
    }
}
