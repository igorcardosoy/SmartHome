package model.dao;

import model.IDevice;

import java.util.List;

public interface IDevicesDao {

    boolean addDevice(IDevice device);

    boolean removeDevice(IDevice device);

    IDevice getDevice(String id);

    List<IDevice> getAllDevices();

    boolean updateDevice(IDevice device);
}
