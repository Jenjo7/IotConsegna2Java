package seiot.model;

import java.util.function.Consumer;

/**
 * 
 */
public interface Command {

    /**
     * It applies the command to the specified model.
     * @param CarSystem m
     */
    public void exec(CarSystem m);

    /**
     * Return the operation of command.
     * @return
     */
    public Consumer<CarSystem> getOperation();

}