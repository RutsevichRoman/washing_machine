package com.electrolux.washmachine;

import com.electrolux.washmachine.com.electrolux.washmachine.modes.Modes;
import com.electrolux.washmachine.request.RequestMode;
import com.electrolux.washmachine.response.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/washing_machine")
public class WashMachineController {

    private final ElectroluxWashingMachineService service;
    private final ResponseHelper responseHelper;

    public WashMachineController(ElectroluxWashingMachineService service, ResponseHelper responseHelper) {
        this.service = service;
        this.responseHelper = responseHelper;
    }

    @GetMapping("/modes")
    public List<Modes> getModes() {
        return Arrays.asList(Modes.values());
    }

    @GetMapping("/power")
    public boolean power(@RequestParam(value = "value", defaultValue = "off") String value) {
        if (value.equals("on")) {
            return service.powerOn();
        } else {
            return service.powerOff();
        }
    }

    @GetMapping("/door")
    public boolean door(@RequestParam(value = "value", defaultValue = "opened") String value) {
        if (value.equals("opened")) {
            return service.doorOpened();
        } else {
            return service.doorClosed();
        }
    }

    @PostMapping("/mode")
    public ResponseEntity selectMode(@RequestBody RequestMode mode) {
        if (mode == null) {
            return responseHelper.badStatus("Json body is not correct.");
        }
        final String modeName = mode.getModeName();
        return service.setMode(modeName) ? responseHelper.okStatus(modeName + " mode is set.") : responseHelper.notFound("Mode Not Found.");
    }

    @GetMapping("")
    public ResponseEntity getWashingMachineState() {
        return responseHelper.ok(service.getWashingMachine());
    }

    @GetMapping("/washing_capsules")
    public ResponseEntity getWashingPowder() {
        int countWashingCapsules = service.getWashingCapsules();
        if (countWashingCapsules < 1) {
            return responseHelper.notFound("There are no washing capsules in machine.");
        }
        return responseHelper.ok("Current amount of washing capsules =" + countWashingCapsules);
    }

    @GetMapping("/add_washing_capsules")
    public ResponseEntity addWashingCapsules(@RequestParam(value = "value", defaultValue = "0") String value) {
      Integer integer;
        try {
            integer = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return responseHelper.badStatus("Amount of washing capsules must be integer value.");
        }
        service.setWashingCapsules(integer);
        return responseHelper.okStatus("Total amount of capsules=" + service.getWashingCapsules());
    }

    @GetMapping("/run")
    public ResponseEntity runWashingMachine() {
        String message = service.runWashing();
        if (message.equals("Success")) {
            return responseHelper.ok(message);
        }
        return responseHelper.notFound(message);
    }

    @GetMapping("/clothes_weight")
    public ResponseEntity getClothesWeight() {
        // Stub
        return responseHelper.ok("ok");
    }

    @GetMapping("/add_clothes_weight")
    public ResponseEntity loadClothesWeight(@RequestParam(value = "value", defaultValue = "0") String value) {
        // Stub
        return responseHelper.ok("ok");
    }
}
