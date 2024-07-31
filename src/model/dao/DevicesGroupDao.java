package model.dao;

import model.DeviceGroup;
import model.IDevice;

import java.util.ArrayList;
import java.util.List;

public class DevicesGroupDao implements IDevicesDao{

    private static DevicesGroupDao instance;
    private final List<IDevice> devices;

    private DevicesGroupDao() {
        devices = new ArrayList<>();
    }

    public static IDevicesDao getInstance() {
        if (instance == null) {
            instance = new DevicesGroupDao();
        }
        return instance;
    }

    @Override
    public boolean addDevice(IDevice device) {
        return devices.add(device);
    }

    public boolean addDeviceToGroup(IDevice device, String groupId) {
        for (IDevice group : devices) {
            if (group.getId().equals(groupId)) {
                if (group instanceof DeviceGroup deviceGroup) {
                    return deviceGroup.addDevice(device);
                }
            }
        }

        return false;
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
