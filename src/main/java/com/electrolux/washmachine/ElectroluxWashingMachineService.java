package com.electrolux.washmachine;

import com.electrolux.washmachine.com.electrolux.washmachine.modes.Modes;
import org.springframework.stereotype.Service;

@Service
public class ElectroluxWashingMachineService {

    private final ElectroluxWashingMachine washingMachine;

    public ElectroluxWashingMachineService(ElectroluxWashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public boolean powerOn() {
        washingMachine.setPowerOn(true);
        return washingMachine.isPowerOn();
    }

    public boolean powerOff() {
        washingMachine.setPowerOn(false);
        return !washingMachine.isPowerOn();
    }

    public boolean doorOpened() {
        washingMachine.setDoorClosed(false);
        return !washingMachine.isDoorClosed();
    }

    public boolean doorClosed() {
        washingMachine.setDoorClosed(true);
        return washingMachine.isDoorClosed();
    }

    public boolean setMode(String modeName) {
        Modes mode;
        try {
            mode = Modes.valueOf(modeName);
        } catch (IllegalArgumentException e) {
            return false;
        }
        washingMachine.setMode(mode);
        return washingMachine.getMode().equals(mode);
    }

    public ElectroluxWashingMachine getWashingMachine() {
        return washingMachine;
    }

    public int getWashingCapsules() {
        return washingMachine.getCountWashingCapsules();
    }

    public boolean setWashingCapsules(int countWashingCapsules) {
        int oldCountWashingCapsules = washingMachine.getCountWashingCapsules();
        washingMachine.setCountWashingCapsules(countWashingCapsules);
        if (washingMachine.getCountWashingCapsules() > oldCountWashingCapsules) {
            return true;
        } else {
            return false;
        }
    }

    public String runWashing() {
        return washingMachine.run();
    }
}
