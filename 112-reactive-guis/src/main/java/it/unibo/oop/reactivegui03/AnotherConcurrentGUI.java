package it.unibo.oop.reactivegui03;

import it.unibo.oop.JFrameUtil;
import it.unibo.oop.reactivegui02.Agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Third experiment with reactive gui.
 */
public final class AnotherConcurrentGUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private final JButton up = new JButton("up");
    private final JButton down = new JButton("down");
    private final JButton stop = new JButton("stop");

    private static final Logger LOGGER = LoggerFactory.getLogger(AnotherConcurrentGUI.class);
    private final JLabel display = new JLabel();

    private final Agent agent = new Agent(display, LOGGER);
    private final TimeoutStopperAgent stopperAgent = new TimeoutStopperAgent(agent, LOGGER, getButtons());
    private final int threadPoolSize = Runtime.getRuntime().availableProcessors();
    private final ExecutorService exec = Executors.newFixedThreadPool(threadPoolSize);
    


    /**
     * Builds a new CGUI.
     */
    public AnotherConcurrentGUI() {
        super();
        JFrameUtil.dimensionJFrame(this);
        final JPanel panel = new JPanel();
        panel.add(display);
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        this.getContentPane().add(panel);
        this.setVisible(true);

        /*
         * thread management should be left to
         * java.util.concurrent.ExecutorService
         */
        exec.execute(agent);
        exec.execute(stopperAgent);
        /*
         * Register a listener that stops it
         */
        up.addActionListener(e -> agent.setUpDirection());
        down.addActionListener(e -> agent.setDownDirection());
        stop.addActionListener(e -> {agent.stopCounting();
                                    stopperAgent.disableButtons();
                                    });
    }

    private List<JButton> getButtons(){
        return List.of(up,down,stop);
    }

    

}
