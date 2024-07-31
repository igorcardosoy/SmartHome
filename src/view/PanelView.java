package view;

import model.DeviceGroup;
import model.DeviceType;
import model.IDevice;
import model.devices.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class PanelView implements IView {

    @Override
    public int showHomeMenu() {
        String[] options = {"Interagir com dispositivos", "Criar novo dispositivo", "Criar novo grupo", "Adicionar dispositivo a grupo", "Dispositivos", "Grupos", "Sair"};

        JComboBox<String> comboBox = new JComboBox<>(options);

        JOptionPane.showConfirmDialog(null, comboBox, "Menu principal", JOptionPane.OK_CANCEL_OPTION);

        return comboBox.getSelectedIndex();

    }

    @Override
    public int showDevicesMenu() {
        String[] options = {"Ver dispositivo", "Ver grupo", "Ligar dispositivo", "Desligar dispositivo", "Ligar Grupo", "Desligar Grupo", "Ligar todos os dispositivos", "Desligar todos os dispositivos", "Voltar"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        JOptionPane.showConfirmDialog(null, comboBox, "Menu de dispositivos", JOptionPane.OK_CANCEL_OPTION);

        return comboBox.getSelectedIndex();
    }

    @Override
    public void showDevices(List<IDevice> devices, String title) {
        JTextArea textArea = new JTextArea();

        textArea.setEditable(false);
        textArea.setColumns(20);

        if (devices.getFirst() instanceof Device){
            devices.forEach(device -> textArea.append("Device: " + device.getName() + "\n"));
        }  else {
            for (IDevice device : devices) {
                if (device instanceof DeviceGroup deviceGroup) {
                    textArea.append("Group: " + deviceGroup.getName() + "\n");
                    deviceGroup.getDevices().forEach(device1 -> textArea.append("\tDevice: " + device1.getName() + "\n"));
                }
            }
        }




        JOptionPane.showMessageDialog(null, textArea, title, JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public IDevice newGroup() {
        Panel panel = new Panel();

        panel.setLayout(new GridLayout(4,1));

        panel.add(new Label("Nome do grupo"));
        TextField name = new TextField();
        panel.add(name);

        JOptionPane.showConfirmDialog(null, panel, "Novo grupo", JOptionPane.OK_CANCEL_OPTION);

        return new DeviceGroup(name.getText());
    }

    @Override
    public void showMessage(String message, String title, boolean type){
        JOptionPane.showMessageDialog(null, message, title, type ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public IDevice selectDevice(List<IDevice> devices) {
        Panel panel = new Panel();
        JComboBox<String> comboBox = new JComboBox<>();

        if (devices.getFirst() instanceof Device){
            devices.forEach(device -> comboBox.addItem(device.getName()));
            panel.add(comboBox);
        }  else {
            for (IDevice device : devices) {
                if (device instanceof DeviceGroup deviceGroup) {
                    comboBox.addItem(deviceGroup.getName());
                    panel.add(comboBox);
                }
            }
        }

        var result = JOptionPane.showConfirmDialog(null, panel, "Select device", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String selected = Objects.requireNonNull(comboBox.getSelectedItem()).toString();

            for (IDevice device : devices) {
                if (device.getName().equals(selected)) {
                    return device;
                }
            }
        }

        return null;
    }

    @Override
    public Panel getDevicePanel(IDevice device){
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4,1));

        panel.add(new Label("Nome do dispositivo"));
        TextField name = new TextField();
        name.setText(device.getName());
        panel.add(name);

        panel.add(new Label("Tipo do dispositivo"));
        TextField type = new TextField();
        type.setText(device.getType());
        panel.add(type);

        panel.add(new Label("Status do dispositivo"));
        TextField status = new TextField();
        status.setText(device.getStatus() ? "Ligado" : "Desligado");
        panel.add(status);

        return panel;
    }

    @Override
    public void showLight(Light light) {

        Panel panel = getDevicePanel(light);

        panel.add(new Label("Luminosidade"));
        TextField brightness = new TextField();
        brightness.setText(light.getBrightness()+ "%");
        brightness.setEditable(false);
        panel.add(brightness);

        JOptionPane.showConfirmDialog(null, panel, "Dispositivo", JOptionPane.OK_CANCEL_OPTION);
    }

    @Override
    public void showSecurityCamera(SecurityCamera securityCamera) {

        Panel panel = getDevicePanel(securityCamera);

        panel.add(new Label("Está stremando?"));
        TextField isStreaming = new TextField();
        isStreaming.setText(securityCamera.isStreaming() ? "Sim" : "Não");
        isStreaming.setEditable(false);
        panel.add(isStreaming);


        JOptionPane.showConfirmDialog(null, panel, "Dispositivo", JOptionPane.OK_CANCEL_OPTION);
    }

    @Override
    public void showSmartLock(SmartLock smartLock) {

        Panel panel = getDevicePanel(smartLock);

        panel.add(new Label("Está trancado?"));
        TextField isLocked = new TextField();
        isLocked.setText(smartLock.isLocked() ? "Sim" : "Não");
        isLocked.setEditable(false);
        panel.add(isLocked);


        JOptionPane.showConfirmDialog(null, panel, "Dispositivo", JOptionPane.OK_CANCEL_OPTION);
    }

    @Override
    public void showThermostat(Thermostat thermostat) {
        Panel panel = getDevicePanel(thermostat);

        panel.add(new Label("Temperatura"));
        TextField temperature = new TextField();
        temperature.setText(thermostat.getTemperature() + "°C");
        temperature.setEditable(false);
        panel.add(temperature);

        panel.add(new Label("Temperatura mínima"));
        TextField minTemperature = new TextField();
        minTemperature.setText(thermostat.getMinTemperature() + "°C");
        minTemperature.setEditable(false);
        panel.add(minTemperature);

        panel.add(new Label("Temperatura máxima"));
        TextField maxTemperature = new TextField();
        maxTemperature.setText(thermostat.getMaxTemperature() + "°C");
        maxTemperature.setEditable(false);
        panel.add(maxTemperature);

        JOptionPane.showConfirmDialog(null, panel, "Dispositivo", JOptionPane.OK_CANCEL_OPTION);
    }

    @Override
    public IDevice newDevice() {
        Panel panel = new Panel();

        panel.setLayout(new GridLayout(4,1));

        panel.add(new Label("Nome do dispositivo"));
        TextField name = new TextField();
        panel.add(name);

        panel.add(new Label("Tipo do dispositivo"));
        JComboBox<String> type = new JComboBox<>();
        for (DeviceType deviceType : DeviceType.values()) {
            type.addItem(deviceType.getType());
        }
        panel.add(type);

        JOptionPane.showConfirmDialog(null, panel, "Novo dispositivo", JOptionPane.OK_CANCEL_OPTION);

        boolean status = false;


        return new Device(name.getText(), status, Objects.requireNonNull(type.getSelectedItem()).toString());
    }
}
