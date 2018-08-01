package seiot.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.SwingUtilities;

import seiot.model.CarSystem;
import seiot.model.CarSystemModel;
import seiot.modulo_lab_2_2.msg.CommChannel;
import seiot.modulo_lab_2_2.msg.SerialCommChannel;
import seiot.view.CarSystemGUI;
import seiot.view.CarSystemView;

/**
 * 
 */
public class CarSystemApp implements CarSystemViewObserver {

    private CarSystem m;
    private CarSystemView gui;
    private Agent agent;
    
    public CarSystemApp(final CommChannel channel) {
        
        this.m = new CarSystemModel(channel);
        this.gui = new CarSystemGUI(this);
        this.gui.setObserver(this);
        this.gui.start();
        this.agent = new Agent();
        agent.start();
    }

    @Override
    public List<String> getAviableCommands() {
         return m.getAviableCommands();
    }

    @Override
    public void sendMsg(String command) {
        m.getCommands().get(command).accept(m);
    }

    @Override
    public String getInfo() {
        return m.getInfo();
    }

    private class Agent extends Thread {
        public void run() {
            while(true) {
                try {
                    SwingUtilities.invokeAndWait(()->gui.setDisplay(m.getInfo()));
                } catch (InvocationTargetException | InterruptedException e) {
                    e.printStackTrace();
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        CommChannel channel = new SerialCommChannel("COM5", 9600);
        new CarSystemApp(channel);
     }
}