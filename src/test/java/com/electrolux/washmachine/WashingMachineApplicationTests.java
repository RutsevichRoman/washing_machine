package com.electrolux.washmachine;

import com.electrolux.washmachine.localization.Localization;
import com.electrolux.washmachine.modes.Mode;
import com.electrolux.washmachine.modes.ModeState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WashingMachineApplication.class)
public class WashingMachineApplicationTests {

    @Autowired
    private WashMachineController controller;
    @Autowired
    private Localization localization;

    @Test
    public void testDoor() {
        Assert.assertEquals(localization.getString("door_is_closed"), controller.doorState().getBody());
        Assert.assertEquals(localization.getString("door_is_opened"), controller.door(true).getBody());
        Assert.assertEquals(localization.getString("door_can_not_be_opened"), controller.door(true).getBody());
        Assert.assertEquals(localization.getString("door_is_closed"), controller.door(false).getBody());
        Assert.assertEquals(localization.getString("door_can_not_be_closed"), controller.door(false).getBody());
        Assert.assertEquals(localization.getString("door_is_closed"), controller.doorState().getBody());
    }

    @Test
    public void testPower() {
        Assert.assertEquals(localization.getString("power_is_off"), controller.powerState().getBody());
        Assert.assertEquals(localization.getString("power_is_on"), controller.power(true).getBody());
        Assert.assertEquals(localization.getString("power_can_not_be_switched_on"), controller.power(true).getBody());
        Assert.assertEquals(localization.getString("power_is_off"), controller.power(false).getBody());
        Assert.assertEquals(localization.getString("power_can_not_be_switched_off"), controller.power(false).getBody());
        Assert.assertEquals(localization.getString("power_is_off"), controller.powerState().getBody());
    }

    @Test
    public void testMode() {
        Assert.assertEquals(Mode.INITIAL.modeName, controller.currentMode().getBody());
        Assert.assertEquals(ModeState.READY.name(), controller.currentState().getBody());
        Assert.assertEquals(String.format(localization.getString("mode_is_set"), Mode.SYNTHETICS.modeName), controller.mode(Mode.SYNTHETICS.modeName).getBody());
        Assert.assertEquals(String.format(localization.getString("mode_can_not_be_set"), Mode.SYNTHETICS.modeName), controller.mode(Mode.SYNTHETICS.modeName).getBody());
        Assert.assertEquals(String.format(localization.getString("mode_is_run"), Mode.SYNTHETICS.modeName), controller.runWashingMachine().getBody());
        Assert.assertEquals(Mode.SYNTHETICS.modeName, controller.currentMode().getBody());
        Assert.assertEquals(ModeState.READY.name(), controller.currentState().getBody());

        Assert.assertEquals(String.format(localization.getString("mode_is_set"), Mode.INITIAL.modeName), controller.mode(Mode.INITIAL.modeName).getBody());
        Assert.assertEquals(Mode.INITIAL.modeName, controller.currentMode().getBody());
        Assert.assertEquals(ModeState.READY.name(), controller.currentState().getBody());
    }
}

