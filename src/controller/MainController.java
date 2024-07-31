package controller;

import model.Facade;
import model.IDevice;
import model.devices.Light;
import model.devices.SecurityCamera;
import model.devices.SmartLock;
import model.devices.Thermostat;
import view.IView;
import view.PanelView;

import java.util.List;

public class MainController {

    private final Facade facade;
    private final IView view;

    public MainController() {
        facade = new Facade();
        view = new PanelView();
    }

    public void start() {

        boolean stop = false;

        while (!stop){
            int option = view.showHomeMenu();

            switch (option) {
                case 0:
                    interectWithDevices();
                    break;
                case 1:
                    newDevice();
                    break;
                case 2:
                    newGroup();
                    break;
                case 3:
                    addDeviceToGroup();
                    break;
                case 4:
                    showDevices(facade.getDevices(), "Dispositivos");
                    break;
                case 5:
                    showDevices(facade.getDeviceGroups(), "Grupos");
                    break;
                default:
                    stop = true;
                    break;
            }
        }

    }

    public void interectWithDevices() {

        if (facade.getDevices().isEmpty()){
            view.showMessage("Nenhum dispositivo encontrado", "Erro", false);
            return;
        }

        boolean stop = false;

        while (!stop){
            int option = view.showDevicesMenu();

            switch (option) {
                case 0:
                    showDevice();
                    break;
                case 1:
                    showGroups();
                    break;
                case 2:
                    turnOnDevice();
                    break;
                case 3:
                    turnOffDevice();
                    break;
                case 4:
                    turnOnGruops();
                    break;
                case 5:
                    turnOffGruops();
                    break;
                case 6:
                    turnOnAllDevices();
                    break;
                case 7:
                    turnOffAllDevices();
                    break;
                default:
                    stop = true;
                    break;
            }
        }
    }

    public void showDevice() {
        IDevice device = view.selectDevice(facade.getDevices());

        switch (device.getType()) {
            case "Luz" -> view.showLight((Light) device);
            case "Câmera de segurança" -> view.showSecurityCamera((SecurityCamera) device);
            case "Termostato" -> view.showThermostat((Thermostat) device);
            case "Fechadura inteligente" -> view.showSmartLock((SmartLock) device);
        }
    }

    public void showGroups() {
        if (facade.getDeviceGroups().isEmpty()){
            view.showMessage("Nenhum grupo encontrado", "Erro", false);
            return;
        }

        IDevice group = view.selectDevice(facade.getDeviceGroups());
        view.showMessage(group.toString(), "Grupo", true);
    }

    public void turnOffDevice() {
        if (facade.getDevices().isEmpty()){
            view.showMessage("Nenhum dispositivo encontrado", "Erro", false);
            return;
        }

        facade.turnOffDevice(view.selectDevice(facade.getDevices()));
        view.showMessage("Dispositivo desligado", "Sucesso", true);
    }

    public void turnOffGruops() {
        if (facade.getDeviceGroups().isEmpty()){
            view.showMessage("Nenhum grupo encontrado", "Erro", false);
            return;
        }

        facade.turnOffDevice(view.selectDevice(facade.getDeviceGroups()));
        view.showMessage("Grupo desligado", "Sucesso", true);
    }

    public void turnOffAllDevices() {
        if (facade.getDevices().isEmpty()){
            view.showMessage("Nenhum dispositivo encontrado", "Erro", false);
            return;
        }

        facade.getDevices().forEach(facade::turnOffDevice);
        view.showMessage("Todos os dispositivos desligados", "Sucesso", true);
    }

    public void showDevices(List<IDevice> devices, String title) {
        if (!devices.isEmpty()){
            view.showDevices(devices, title);
        } else {
            view.showMessage("Nenhum dispositivo/grupo encontrado", "Erro", false);
        }
    }

    public void addDeviceToGroup(){
        if (facade.getDevices().isEmpty() || facade.getDeviceGroups().isEmpty()){
            view.showMessage("Nenhum dispositivo/grupo encontrado", "Erro", false);
            return;
        }

        IDevice device = view.selectDevice(facade.getDevices());
        IDevice group = view.selectDevice(facade.getDeviceGroups());

        facade.addDeviceToGroup(device.getId(), group.getId());

        view.showMessage("Dispositivo adicionado ao grupo", "Sucesso", true);
    }

    public void turnOnDevice() {
        if (facade.getDevices().isEmpty()){
                view.showMessage("Nenhum dispositivo encontrado", "Erro", false);
                return;
            }

        facade.turnOnDevice(view.selectDevice(facade.getDevices()));
        view.showMessage("Dispositivo ligado", "Sucesso", true);
    }

    public void turnOnGruops() {
        if (facade.getDeviceGroups().isEmpty()){
            view.showMessage("Nenhum grupo encontrado", "Erro", false);
            return;
        }

        facade.turnOnDevice(view.selectDevice(facade.getDeviceGroups()));
        view.showMessage("Grupo ligado", "Sucesso", true);
    }

    public void turnOnAllDevices() {
        if (facade.getDevices().isEmpty()){
            view.showMessage("Nenhum dispositivo encontrado", "Erro", false);
            return;
        }

        facade.getDevices().forEach(facade::turnOnDevice);
        view.showMessage("Todos os dispositivos ligados", "Sucesso", true);
    }

    public void newGroup() {
        IDevice group = view.newGroup();

        String id = ("G-" + (facade.getDeviceGroups().size() + 1));
        group.setId(id);

        facade.addDeviceGroup(group);
    }

    public void newDevice() {
        IDevice device = view.newDevice();

        String id = ("D-" + (facade.getDevices().size() + 1));
        device.setId(id);
        String type = device.getType();

        device = switch (device.getType()) {
            case "Luz" -> new Light(id, device.getName(), device.getStatus());
            case "Câmera de segurança" -> new SecurityCamera(id, device.getName(), device.getStatus());
            case "Termostato" -> new Thermostat(id, device.getName(), device.getStatus(), 0, 100);
            case "Fechadura inteligente" -> new SmartLock(id, device.getName(), device.getStatus());
            default -> device;
        };

        device.setType(type);
        facade.addDevice(device);
    }

}
