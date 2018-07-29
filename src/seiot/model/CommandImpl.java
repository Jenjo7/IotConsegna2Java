package seiot.model;

import java.util.function.Consumer;

import seiot.model.CarSystem;

public enum CommandImpl implements Command {
    OPEN(f -> f.getChannel().sendMsg("open")), STOP(f -> f.getChannel().sendMsg("stop"));
    
    private Consumer<CarSystem> f;
    
    private CommandImpl(Consumer<CarSystem> f) {
        this.f = f;
    }
    ;

    @Override
    public void exec(CarSystem m) {
        this.f.accept(m);
    }

    @Override
    public Consumer<CarSystem> getOperation() {
        return this.f;
    }

}
