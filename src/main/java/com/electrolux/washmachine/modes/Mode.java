package com.electrolux.washmachine.modes;

public enum Mode {
    INITIAL("INITIAL", 0, 0, 0, 0, 0),
    QUICK_WASHING_30("QUICK_WASHING_30", 30, 4000, 600, 800, 3),
    SYNTHETICS("SYNTHETICS", 40, 4000, 800, 1000, 3),
    COTTON_30("COTTON_30", 60, 9000, 800, 1000, 6),
    COTTON_60("COTTON_60", 60, 9000, 800, 1000, 6);

    public final String modeName;
    public final int temperature;
    public final long time;
    public final int modeSpeed;
    public final int pressingSpeed;
    public final double allowedWeight;

    Mode(String modeName, int temperature, long time, int modeSpeed, int pressingSpeed, double allowedWeight) {
        this.modeName = modeName;
        this.temperature = temperature;
        this.time = time;
        this.modeSpeed = modeSpeed;
        this.pressingSpeed = pressingSpeed;
        this.allowedWeight = allowedWeight;
    }

    public static Mode fromName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        for (Mode mode : Mode.values()) {
            if (mode.modeName.equalsIgnoreCase(name)) {
                return mode;
            }
        }
        return null;
    }
}
