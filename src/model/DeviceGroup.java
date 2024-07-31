package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeviceGroup implements IDevice {

    private final List<IDevice> devices;
    private String id;
    private String name;

    public DeviceGroup(String name) {
        this.name = name;
        this.devices = new LinkedList<>();
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setType(String type) {

    }

    public boolean addDevice(IDevice device) {
        if (device == null || devices.contains(device)) {
            return false;
        }

        return devices.add(device);
    }

    public boolean removeDevice(String id) {
        if (id == null) {
            return false;
        }

        return devices.removeIf(device -> {
            device.getId();
            return false;
        });
    }

    @Override
    public void turnOn() {
        devices.forEach(IDevice::turnOn);
    }

    @Override
    public void turnOff() {
        devices.forEach(IDevice::turnOff);
    }

    @Override
    public boolean getStatus() {
        return devices.stream().allMatch(IDevice::getStatus);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return "Gruop";
    }

    public List<IDevice> getDevices() {
        return new ArrayList<>(devices);
    }

    public void setName(String name) {
        this.name = name;
    }
}
