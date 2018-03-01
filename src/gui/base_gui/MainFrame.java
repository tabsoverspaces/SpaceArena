package gui.base_gui;

import engine_classes.Ship;
import gui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final int width = 1280;
    public static final int height = 700;

    private GamePanel gamePanel;

    public MainFrame()
    {
        this.setTitle("Space Arena v0.1");
        this.setPreferredSize(new Dimension(width , height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.pack();

        this.setLocationRelativeTo(null);


        Ship ship1 = new Ship();
        Ship ship2 = new Ship();
        this.gamePanel = new GamePanel(ship1 , ship2);
//        this.gamePanel.setLocation(0 , 0 );
//        this.gamePanel.setPreferredSize(new Dimension(MainFrame.width, MainFrame.height));
        this.gamePanel.setBounds(0, 0, MainFrame.width-(this.getInsets().left+this.getInsets().right) , MainFrame.height - this.getInsets().top);
        System.out.println(this.getInsets().top);
        this.gamePanel.init();

        this.add(this.gamePanel);

        Thread th = new Thread(this.gamePanel);
        th.start();

        this.setVisible(true);
    }

}
