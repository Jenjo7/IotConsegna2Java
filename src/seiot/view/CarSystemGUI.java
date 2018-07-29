package seiot.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import seiot.controller.CarSystemViewObserver;

/**
 * 
 */
public class CarSystemGUI extends JFrame implements CarSystemView {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JLabel display;
    private final JPanel jp;
    private final List<JButton> commands;
    private CarSystemViewObserver observer;
    
    public CarSystemGUI(CarSystemViewObserver observer) {
        this.jp = new JPanel();
        JPanel south = new JPanel();
        south.setBackground(Color.WHITE);
        this.observer = observer;
        this.display = new JLabel(" ");
        commands = this.observer.getAviableCommands().stream()
                .map(JButton::new)
                .peek(b -> b.addActionListener(e ->observer.sendMsg(b.getText())))
                .collect(Collectors.toList());
        this.setSize(600, 300);
        commands.forEach(jp::add);
        south.add(display, BorderLayout.SOUTH);
        
        this.getContentPane().add(jp, BorderLayout.NORTH);
        this.getContentPane().add(south, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                System.exit(-1);
            }
        });
    }

    @Override
    public void setDisplay(String info) {
        this.display.setText(info);
    }

    @Override
    public void start() {
        this.getContentPane().setVisible(true);
    }

    @Override
    public void setObserver(CarSystemViewObserver observer) {
        this.observer = observer; 
    }

   

}