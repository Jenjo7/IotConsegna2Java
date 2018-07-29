package seiot.model;

/**
 * 
 */
public interface Recived {

    /**
     * it returns info recived by MCU.
     * @return
     */
    public String getInfo();
    
    /**
     * It starts the thread which communicates with MCU.
     */
    public void start();

}