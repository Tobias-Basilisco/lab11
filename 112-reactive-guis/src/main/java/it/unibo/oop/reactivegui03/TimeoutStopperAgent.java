package it.unibo.oop.reactivegui03;

import java.util.List;

import javax.swing.JButton;

import it.unibo.oop.reactivegui02.Agent;
import org.slf4j.Logger;


public class TimeoutStopperAgent implements Runnable{
    private Agent counterAgent;
    private Logger logger;
    private List<JButton> buttons;

    public TimeoutStopperAgent(final Agent counterAgent, final Logger logger, final List<JButton> buttons){
        this.counterAgent = counterAgent;
        this.logger = logger;
        this.buttons = buttons;
    }
     @Override
    public void run() {
        try {
            Thread.sleep(10_000);
            
            counterAgent.stopCounting();
            disableButtons();
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }
    }

    public void disableButtons(){
        for (JButton jButton : buttons) {
            jButton.setEnabled(false);
        }
    }
}
