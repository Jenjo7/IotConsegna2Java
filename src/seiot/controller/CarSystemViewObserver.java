package seiot.controller;

import java.util.*;

/**
 * 
 */
public interface CarSystemViewObserver {

    /**
     * It returns a list of all commands name.
     * @return
     */
    public List<String> getAviableCommands();

    /**
     * It sends the specified command.
     * @param String command
     */
    public void sendMsg(String command);

    /**
     * return info recived by MCU.
     * @return
     */
    public String getInfo();

}