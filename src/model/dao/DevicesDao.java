package model.dao;

import model.IDevice;

import java.util.ArrayList;
import java.util.List;

public class DevicesDao implements IDevicesDao{

        private static DevicesDao instance;
        private final List<IDevice> devices;

        private DevicesDao() {
            devices = new ArrayList<>();
        }

        public static IDevicesDao getInstance() {
            if (instance == null) {
                instance = new DevicesDao();
            }
            return instance;
        }

        @Override
        public boolean addDevice(IDevice device) {
            return devices.add(device);
        }

        @Override
        public boolean removeDevice(IDevice device) {
            return devices.remove(device);
        }

        @Override
        public IDevice getDevice(String id) {
            for (IDevice device : devices) {
                if (device.getId().equals(id)) {
                    return device;
                }
            }

            throw new IllegalArgumentException("Device not found");
        }

        @Override
        public List<IDevice> getAllDevices() {
            return new ArrayList<>(devices);
        }

        @Override
        public boolean updateDevice(IDevice device) {
            if (device == null) {
                return false;
            }

            for (int i = 0; i < devices.size(); i++) {
                if (devices.get(i).getId().equals(device.getId())) {
                    devices.set(i, device);
                    return true;
                }
            }

            return false;
        }
}
