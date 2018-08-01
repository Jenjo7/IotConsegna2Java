package seiot.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import seiot.modulo_lab_2_2.msg.CommChannel;

public class RecivedImpl extends Thread implements Recived {

    enum Words{
        WELCOME("W", "Welcome home!"), 
        DISTANCE("D", "Distance: "), 
        OK("O", "Ok can stop"), 
        TOUCH("T", "Touching"), 
        FAR("F", "Too far"), 
        FINISH("K", "Ok");
        
        private String key;
        private String value;
        
        private Words(final String key, final String value){
            this.key = key;
            this.value = value;
        }
        
        public String getKey() {
            return key;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    private volatile String info;
    private CommChannel channel;
    private final static Map<String, String> MAP = Arrays.asList(Words.values())        //collezione tutti i valori di Words in un Array,
                                                         .stream()                      //ne genero lo stream,
                                                         .collect(Collectors.toMap(     //colleziono tutte le parole utili in una mappa,
                                                                     Words::getKey,     //nel quale uso come chiave il valore key,
                                                                     Words::getValue)); //e value come valore.
    
    public RecivedImpl(final CommChannel channel) {
        this.channel = channel;
    }

    @Override
    public String getInfo() {
        return info;
    }

    public void run() {
        while (true) {
            if (channel.isMsgAvailable()) {
                try {
                    String str = channel.receiveMsg();
                    String[] msgs = str.split(" ");
                    info = MAP.get(msgs[0]) + (msgs[0].equals("D") ? msgs[1] : "");
                } catch (InterruptedException e) {
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

}
