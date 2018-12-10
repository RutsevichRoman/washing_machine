package com.electrolux.washmachine;

import com.electrolux.washmachine.com.electrolux.washmachine.modes.Modes;
import org.springframework.stereotype.Component;

@Component
public class ElectroluxWashingMachine {

    private int countWashingCapsules;
    private boolean powerOn;
    private boolean doorClosed;
    private Modes mode;
    private int clothesWeight;

    public ElectroluxWashingMachine() {
    }

    public int getCountWashingCapsules() {
        return countWashingCapsules;
    }

    public synchronized void setCountWashingCapsules(int countWashingCapsules) {
        this.countWashingCapsules += countWashingCapsules;
    }

    public boolean isDoorClosed() {
        return doorClosed;
    }

    public synchronized void setDoorClosed(boolean doorClosed) {
        this.doorClosed = doorClosed;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }


    public int getClothesWeight() {
        return clothesWeight;
    }

    public synchronized void setClothesWeight(int clothesWeight) {
        this.clothesWeight = clothesWeight;
    }

    public boolean isPowerOn() {
        return powerOn;
    }

    public synchronized void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }

    public synchronized String run() {
        if (!powerOn) {
            return "Washing Machine is not switched on.";
        }
        if (!doorClosed) {
            return "Door is open. Please, close the door.";
        }
        if (countWashingCapsules == 0) {
            return "Not enough washing capsules. Please add some amount of capsules to start washing.";
        }
        if (mode == null) {
            return "Mode is not selected. Please, select washing mode.";
        }
        if (clothesWeight > mode.getAllowedWeight()) {
            return "Too much clothes weight for current mode. Please, take away some clothes.";
        }

        try {
            //To imitate washing process
            Thread.sleep(mode.getTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Washing process interrupted.";
        }
        countWashingCapsules = 0;
        return "Washing finished.";
    }
}