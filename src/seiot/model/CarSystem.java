package seiot.model;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import seiot.modulo_lab_2_2.msg.CommChannel;

/**
 * 
 */
public interface CarSystem {

    /**
     * Return elaborate info reviced by MCU.
     * @return
     */
    public String getInfo();

    /**
     * It send a messageto MCU by a channel.
     */
    public void sendMsg();

    /**
     * Return a list of names of all commands.
     * @return
     */
    public List<String> getAviableCommands();

    /**
     * Return a Map which has name of command as key, and operation as value.
     * @return
     */
    public Map<String, Consumer<CarSystem>> getCommands();

    /**
     * Return the communication channel
     * @return
     */
    public CommChannel getChannel();

}