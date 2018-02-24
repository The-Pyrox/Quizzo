package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDownProgressBar {
	
	Timer timer;
    JProgressBar progressBar;

    public CountDownProgressBar() {
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 10);
        progressBar.setValue(10);
        progressBar.setVisible(true);
        ActionListener listener = new ActionListener() {
            int counter = 10;
			@Override
			public void actionPerformed(ActionEvent e) {
				counter--;
                progressBar.setValue(counter);
                if (counter<1) {
                    JOptionPane.showMessageDialog(null, "Kaboom!");
                    timer.stop();
                } 
				
			}
        };
        timer = new Timer(1000, listener);
        timer.start();
    

}

}
