package org.usfirst.frc.team5427.robot.network;

import org.usfirst.frc.team5427.robot.network.ByteDictionary;
import org.usfirst.frc.team5427.robot.network.Interpreter;
import org.usfirst.frc.team5427.robot.util.Log;

import java.util.ArrayList;


public class SteamworkInterpreter extends Interpreter {

    public volatile ArrayList<Object> recievedObjects = new ArrayList<>();

    @Override
    public void interpret(byte[] buff, int numFromStream) {
        Log.debug("buff: " + Interpreter.toStringByteArray(buff));

        if (buff.length < 1) {
            return;
        }

        switch (buff[0]) {
            case ByteDictionary.MESSAGE:
                String message = (String)(deserialize(buff, 1, buff.length - 1));
                Log.pl("Message from RoboRIO: " + message);
                break;
            case ByteDictionary.OBJECT:
                recievedObjects.add( deserialize(buff, 1, buff.length - 1) );
                break;
            default:
                Log.debug("Invalid dictionary: " + toStringByteArray(buff));
                break;
        }
    }
}