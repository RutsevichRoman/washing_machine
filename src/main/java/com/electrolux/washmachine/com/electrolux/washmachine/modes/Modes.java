package com.electrolux.washmachine.com.electrolux.washmachine.modes;

public enum Modes {
    // Mode name,
    QUICK_WASHING_30("QUICK_WASHING_30", 30, 4000, 600, 800, 3),
    SYNTHETICS("SYNTHETICS", 40, 4000, 1000, 800, 3),
    COTTON_30("COTTON_30", 60, 9000, 1000, 800, 6),
    COTTON_60("COTTON_60", 60, 9000, 1000, 800, 6);

    private final String modeName;
    private final int temperature;
    private final long time;
    private final int modeSpeed;
    private final int pressingSpeed;
    private final int allowedWeight;

    Modes(String modeName, int temperature, long time, int modeSpeed, int pressingSpeed, int allowedWeight) {
        this.modeName = modeName;
        this.temperature = temperature;
        this.time = time;
        this.modeSpeed = modeSpeed;
        this.pressingSpeed = pressingSpeed;
        this.allowedWeight = allowedWeight;
    }


    public String getModeName() {
        return modeName;
    }

    public int getTemperature() {
        return temperature;
    }

    public long getTime() {
        return time;
    }

    public int getModeSpeed() {
        return modeSpeed;
    }

    public int getPressingSpeed() {
        return pressingSpeed;
    }

    public int getAllowedWeight() {
        return allowedWeight;
    }
}
