package com.untitled.untitled.handlers;

import arduino.Arduino;
import com.untitled.untitled.GameInterface;
import com.untitled.untitled.Input;
import com.untitled.untitled.helpers.Log;

public class ArduinoInputHandler implements GameInterface {

    final String LOG = "ArduinoInputHandler";

    Arduino arduino;
    String comPort = "/dev/ttyUSB0";

    @Override
    public Boolean interfaceInit() {
        arduino = new Arduino(comPort,9600);
        boolean b = false;
        try {
            b = arduino.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.info(LOG, b ? String.format("Arduino on %s init", comPort) : "Arduino on " + comPort + " failed");
        return b;
    }

    @Override
    public Input interfaceGetInput() {
        String out = arduino.serialRead(0);

        out = out.replace("\n","");

        Log.info(LOG, out);

        if (Integer.parseInt(out) < 100) {
            return new Input(Input.InputTypes.INPUT_MOVE_ROCK);
        } else if(Integer.parseInt(out) > 923) {
            return new Input(Input.InputTypes.INPUT_MOVE_SCISSORS);
        } else {
            return new Input(Input.InputTypes.INPUT_MOVE_PAPER);
        }

    }
}
