package model.devices;

public class Thermostat extends Device {
        private final int min;
        private final int max;
        private int temperature;

        public Thermostat(String id, String name, boolean status, int min, int max) {
            super(id, name, status);
            this.min = min;
            this.max = max;
            temperature = 20;
        }

        public boolean ajustTemperature(int level) {
            if (level >= min && level <= max) {
                temperature = level;
                return true;
            }

            return false;
        }

        public int getMinTemperature(){
            return min;
        }

        public int getMaxTemperature(){
            return max;
        }


        public int getTemperature() {
            return temperature;
        }
}
