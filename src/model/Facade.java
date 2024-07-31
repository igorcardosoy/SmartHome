package model;

import model.service.DeviceService;

import java.util.List;

public class Facade {

    private final DeviceService deviceService;

    public Facade() {
        deviceService = new DeviceService();
    }

    public void addDeviceToGroup(String deviceId, String groupId) {
        deviceService.addDeviceToGroup(deviceId, groupId);
    }

    public void addDeviceGroup(IDevice device) {
        deviceService.addDeviceGroup(device);
    }

    public void addDevice(IDevice device) {
        deviceService.addDevice(device);
    }

    public List<IDevice> getDevices() {
        return deviceService.getDevices();
    }

    public List<IDevice> getDeviceGroups() {
        return deviceService.getDeviceGroups();
    }

    public void turnOnDevice(IDevice device) {
        device.turnOn();
    }

    public void turnOffDevice(IDevice device) {
        device.turnOff();
    }

}
