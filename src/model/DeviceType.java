package model;

public enum DeviceType {
    LIGHT("Luz"),
    SECURITY_CAMERA("Câmera de segurança"),
    THERMOSTAT("Termostato"),
    SMART_LOCK("Fechadura inteligente");

    private final String type;

    DeviceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
