package view;

import model.IDevice;
import model.devices.*;

import java.awt.*;
import java.util.List;

public interface IView {

    int showHomeMenu();

    int showDevicesMenu();

    void showDevices(List<IDevice> devices, String title);

    IDevice newGroup();

    void showMessage(String message, String title, boolean type);

    IDevice selectDevice(List<IDevice> devices);

    Panel getDevicePanel(IDevice device);

    void showLight(Light light);

    void showSecurityCamera(SecurityCamera securityCamera);

    void showSmartLock(SmartLock smartLock);

    void showThermostat(Thermostat thermostat);

    IDevice newDevice();
}
