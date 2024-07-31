package model;

public interface IDevice {

    void turnOn();

    void turnOff();

    boolean getStatus();

    String getName();

    String getId();

    String getType();

    void setId(String id);

    void setType(String type);
}
