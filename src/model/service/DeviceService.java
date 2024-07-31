package model.service;

import model.DeviceGroup;
import model.IDevice;
import model.dao.DevicesDao;
import model.dao.DevicesGroupDao;
import model.dao.IDevicesDao;

import java.util.ArrayList;
import java.util.List;

public class DeviceService {

    private final IDevicesDao devicesDao;
    private final IDevicesDao devicesGrupsDao;

    public DeviceService() {
        devicesDao = DevicesDao.getInstance();
        devicesGrupsDao = DevicesGroupDao.getInstance();
    }

    public List<IDevice> getDevices() {
        return new ArrayList<>(devicesDao.getAllDevices());
    }

    public List<IDevice> getDeviceGroups() {
        return new ArrayList<>(devicesGrupsDao.getAllDevices());
    }

    public void addDeviceToGroup(String deviceId, String groupId) {
       if(devicesGrupsDao instanceof DevicesGroupDao devicesGroupDao){
           devicesGroupDao.addDeviceToGroup(devicesDao.getDevice(deviceId), groupId);
       }
    }

    public void removeDeviceFromGroup(String deviceId, String groupId) {
        devicesGrupsDao.removeDevice(devicesDao.getDevice(deviceId));
    }

    public void updateDevice(IDevice device) {
        devicesDao.updateDevice(device);
    }

    public void updateDeviceGroup(IDevice device) {
        devicesGrupsDao.updateDevice(device);
    }

    public void addDevice(IDevice device) {
        devicesDao.addDevice(device);
    }

    public void addDeviceGroup(IDevice device) {
        devicesGrupsDao.addDevice(device);
    }

    public void removeDevice(IDevice device) {
        devicesDao.removeDevice(device);
    }

    public void removeDeviceGroup(IDevice device) {
        devicesGrupsDao.removeDevice(device);
    }

    public IDevice getDevice(String id) {
        IDevice device;

        try {
            device = devicesDao.getDevice(id);
        } catch (IllegalArgumentException e1) {
            try {
                device = devicesGrupsDao.getDevice(id);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Device not found");
            }
        }

        return device;
    }

    public IDevice getDeviceGroup(String id) {
        return devicesGrupsDao.getDevice(id);
    }

    public void turnOnDevice(IDevice device) {
        device.turnOn();
    }

    public void turnOffDevice(IDevice device) {
        device.turnOff();
    }

    public void turnOnGroup(IDevice device) {
        device.turnOn();
    }

    public void turnOffGroup(IDevice device) {
        device.turnOff();
    }

    public void turnOnAllDevices() {
        devicesDao.getAllDevices().forEach(IDevice::turnOn);
    }

    public void turnOffAllDevices() {
        devicesDao.getAllDevices().forEach(IDevice::turnOff);
    }

    public void turnOnAllGroups() {
        devicesGrupsDao.getAllDevices().forEach(IDevice::turnOn);
    }

    public void turnOffAllGroups() {
        devicesGrupsDao.getAllDevices().forEach(IDevice::turnOff);
    }

    public boolean getStatus(IDevice device) {
        return device.getStatus();
    }

    public boolean getStatusGroup(IDevice device) {
        return device.getStatus();
    }

    public boolean getStatusAllDevices() {
        return devicesDao.getAllDevices().stream().allMatch(IDevice::getStatus);
    }

    public boolean getStatusAllGroups() {
        return devicesGrupsDao.getAllDevices().stream().allMatch(IDevice::getStatus);
    }

}
