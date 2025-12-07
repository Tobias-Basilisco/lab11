package it.unibo.oop.reactivegui02;

import it.unibo.oop.JFrameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Second example of reactive GUI.
 */
public final class ConcurrentGUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentGUI.class);
    private final JLabel display = new JLabel();

    private final Agent agent = new Agent(display);
    private final int threadPoolSize = Runtime.getRuntime().availableProcessors();
    private final ExecutorService exec = Executors.newFixedThreadPool(threadPoolSize);		


    /**
     * Builds a new CGUI.
     */
    public ConcurrentGUI() {
        super();
        JFrameUtil.dimensionJFrame(this);
        final JPanel panel = new JPanel();
        panel.add(display);
        final JButton stop = new JButton("stop");
        panel.add(stop);
        this.getContentPane().add(panel);
        this.setVisible(true);

        /*
         * thread management should be left to
         * java.util.concurrent.ExecutorService
         */
        exec.execute(agent);
        /*
         * Register a listener that stops it
         */
        stop.addActionListener(e -> agent.stopCounting());
    }

}
