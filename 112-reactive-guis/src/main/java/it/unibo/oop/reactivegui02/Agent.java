package it.unibo.oop.reactivegui02;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Agent implements Runnable {

    private volatile boolean stop = false;
    private volatile boolean downDirection = false;
    private final JLabel display;
    private int counter = 0;
    private Logger logger;

    public Agent(JLabel display, Logger logger) {
        this.display = display;
        this.logger = logger;
    }

    @Override
    public void run() {
        while (!this.stop) {
            try {
                // The EDT doesn't access `counter` anymore, it doesn't need to be volatile
                final var nextText = Integer.toString(this.counter);
                SwingUtilities.invokeAndWait(() -> display.setText(nextText));
                if (this.downDirection == true) {
                    this.counter--;
                } else {
                    this.counter++;
                }
                Thread.sleep(100);
            } catch (InvocationTargetException | InterruptedException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * External command to stop counting.
     */
    public void stopCounting() {
        stop = true;
    }

    public void setUpDirection(){
        this.downDirection = false;
    }

    
    public void setDownDirection(){
        this.downDirection = true;
    }
}
