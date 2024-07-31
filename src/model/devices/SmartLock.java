package model.devices;

public class SmartLock extends Device {
        private boolean locked;

        public SmartLock(String id, String name, boolean status) {
            super(id, name, status);
            locked = true;
        }

        public boolean isLocked() {
            return locked;
        }

        public void lock() {
            this.locked = true;
        }

        public void unlock() {
            this.locked = false;
        }
}
