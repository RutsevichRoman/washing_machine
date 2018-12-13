package com.electrolux.washmachine;

import com.electrolux.washmachine.modes.Mode;
import com.electrolux.washmachine.modes.ModeState;
import org.springframework.stereotype.Component;

@Component
public class ElectroluxWashingMachine {

    private Mode mode = Mode.INITIAL;
    private ModeState state = ModeState.READY;
    private boolean doorOpen = false;
    private boolean powerOn = false;

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(boolean doorOpen) {
        this.doorOpen = doorOpen;
    }

    public boolean isPowerOn() {
        return powerOn;
    }

    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public ModeState getState() {
        return state;
    }

    public void setState(ModeState state) {
        this.state = state;
    }
}