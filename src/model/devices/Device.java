package model.devices;

import model.IDevice;

public class Device implements IDevice {

    private String id;
    private final String name;
    private boolean status;
    private String type;


    public Device(String id, String name, boolean status, String type) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public Device(String id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Device(String name, boolean status, String type) {
        this.name = name;
        this.status = status;
        this.type = type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void turnOn() {
        this.status = true;
    }

    @Override
    public void turnOff() {
        this.status = false;
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
