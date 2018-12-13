package com.electrolux.washmachine;

import com.electrolux.washmachine.modes.Mode;
import com.electrolux.washmachine.modes.ModeState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectroluxWashingMachineService {

    private static final String WASHING_MODE_STARTED = "Washing mode started state - %s";
    private final Logger logger = LoggerFactory.getLogger(ElectroluxWashingMachineService.class);
    private final ElectroluxWashingMachine washingMachine;

    @Autowired
    public ElectroluxWashingMachineService(ElectroluxWashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public boolean isDoorOpen() {
        return washingMachine.isDoorOpen();
    }

    public boolean setDoorOpen(boolean doorOpen) {
        if (doorOpen == washingMachine.isDoorOpen()) {
            return false;
        }
        washingMachine.setDoorOpen(doorOpen);
        return true;
    }

    public boolean isPowerOn() {
        return washingMachine.isPowerOn();
    }

    public boolean setPowerOn(boolean powerOn) {
        if (powerOn == washingMachine.isPowerOn()) {
            return false;
        }
        washingMachine.setPowerOn(powerOn);
        return true;
    }

    public Mode getCurrentMode() {
        return washingMachine.getMode();
    }

    public ModeState getCurrentState() {
        return washingMachine.getState();
    }

    public boolean setMode(String modeName) {
        Mode mode = Mode.fromName(modeName);
        if (mode == null) {
            return false;
        }
        if (washingMachine.getMode() == mode) {
            return false;
        }
        if (washingMachine.getMode() == Mode.INITIAL || mode == Mode.INITIAL) {
            washingMachine.setMode(mode);
            washingMachine.setState(ModeState.READY);
            return true;
        }
        return false;
    }

    public boolean runWashingMode() {
        if (washingMachine.getMode() == Mode.INITIAL) {
            //Initial state can not be run
            return false;
        }
        for (ModeState state : ModeState.values()) {
            //Executing states
            washingMachine.setState(state);
            executingModeState(state);
        }
        // Set beginning state
        washingMachine.setState(ModeState.READY);
        return true;
    }

    private void executingModeState(ModeState modeState) {
        logger.info(String.format(WASHING_MODE_STARTED, modeState.name()));
    }
}
