package com.electrolux.washmachine;

import com.electrolux.washmachine.localization.Localization;
import com.electrolux.washmachine.modes.Mode;
import com.electrolux.washmachine.response.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class WashMachineController {

    private final ElectroluxWashingMachineService service;
    private final ResponseHelper responseHelper;
    private final Localization localization;

    public WashMachineController(ElectroluxWashingMachineService service, ResponseHelper responseHelper, Localization localization) {
        this.service = service;
        this.responseHelper = responseHelper;
        this.localization = localization;
    }

    @GetMapping("/modes")
    public ResponseEntity getModes() {
        return responseHelper.okWithBody(Mode.values());
    }

    @PostMapping("/power")
    public ResponseEntity power(@RequestBody boolean value) {
        boolean result = service.setPowerOn(value);
        if (result) {
            if (value) {
                return responseHelper.ok("power_is_on");
            } else {
                return responseHelper.ok("power_is_off");
            }
        } else {
            if (value) {
                return responseHelper.badRequest("power_can_not_be_switched_on");
            } else {
                return responseHelper.badRequest("power_can_not_be_switched_off");
            }
        }
    }

    @GetMapping("/power/state")
    public ResponseEntity powerState() {
        if (service.isPowerOn()) {
            return responseHelper.ok("power_is_on");
        } else {
            return responseHelper.ok("power_is_off");
        }
    }

    @PostMapping("/door")
    public ResponseEntity door(@RequestBody boolean value) {
        boolean result = service.setDoorOpen(value);
        if (result) {
            if (value) {
                return responseHelper.ok("door_is_opened");
            } else {
                return responseHelper.ok("door_is_closed");
            }
        } else {
            if (value) {
                return responseHelper.badRequest("door_can_not_be_opened");
            } else {
                return responseHelper.badRequest("door_can_not_be_closed");
            }
        }
    }

    @GetMapping("/door/state")
    public ResponseEntity doorState() {
        if (service.isDoorOpen()) {
            return responseHelper.ok("door_is_opened");
        } else {
            return responseHelper.ok("door_is_closed");
        }
    }

    @PostMapping("/mode")
    public ResponseEntity mode(@RequestBody String mode) {
        boolean result = service.setMode(mode);
        if (result) {
            return responseHelper.okWithBody(String.format(localization.getString("mode_is_set"), mode));
        } else {
            return responseHelper.badRequestWithBody(String.format(localization.getString("mode_can_not_be_set"), mode));
        }
    }

    @GetMapping("/mode/current")
    public ResponseEntity currentMode() {
        return responseHelper.okWithBody(service.getCurrentMode().modeName);
    }

    @GetMapping("/mode/state")
    public ResponseEntity currentState() {
        return responseHelper.okWithBody(service.getCurrentState().name());
    }

    @PostMapping("/run")
    public ResponseEntity runWashingMachine() {
        boolean result = service.runWashingMode();
        if (result) {
            return responseHelper.okWithBody(String.format(localization.getString("mode_is_run"), service.getCurrentMode()));
        } else {
            return responseHelper.badRequestWithBody(String.format(localization.getString("mode_can_not_be_run"), service.getCurrentMode()));
        }
    }
}
