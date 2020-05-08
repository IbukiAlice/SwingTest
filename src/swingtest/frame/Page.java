package swingtest.frame;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Page extends JFrame {

    // 屏幕分辨率
    public static final int SCREEN_HEIGHT;
    public static final int SCREEN_WIDTH;

    static{
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        SCREEN_HEIGHT = toolkit.getScreenSize().height;
        SCREEN_WIDTH = toolkit.getScreenSize().width;
    }

    public Page(){
        super("GamePage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
    }
}
