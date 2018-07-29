package seiot.view;

import seiot.controller.CarSystemViewObserver;

/**
 * 
 */
public interface CarSystemView {

    /**
     * Set the label with inforamations recived by MCU, like distance, can stop ecc...
     * @param String info
     */
    public void setDisplay(String info);

    /**
     * Set visible the GUI and, eventually, can start some threads.
     */
    public void start();

    /**
     * It sets the observer which should manage the gui (patterna MVC).
     * @param CarSystemViewObserver observer
     */
    public void setObserver(CarSystemViewObserver observer);

}