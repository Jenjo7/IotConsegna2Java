package seiot.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seiot.modulo_lab_2_2.msg.CommChannel;

public class CarSystemModel implements CarSystem {
    
    private final CommChannel channel;
    private final Recived recived;
    
    

    public CarSystemModel(final CommChannel channel) {
        this.channel = channel;
        this.recived = new RecivedImpl(channel);
        this.recived.start();
    }

    private Stream<CommandImpl> handleSteam() {
        return Arrays.asList(CommandImpl.values()).stream();
    }
    
    @Override
    public String getInfo() {
        return recived.getInfo();
    }

    @Override
    public void sendMsg() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String> getAviableCommands() {
        return new ArrayList<>(this.getCommands().keySet());
    }

    @Override
    public Map<String, Consumer<CarSystem>> getCommands() {
        return this.handleSteam().collect(Collectors.toMap(CommandImpl::name, CommandImpl::getOperation));
    }

    @Override
    public CommChannel getChannel() {
        return this.channel;
    }

}
