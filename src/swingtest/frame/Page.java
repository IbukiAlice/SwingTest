package swingtest.frame;

import swingtest.tool.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 主窗口
 */
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
        super("Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 窗口布局
        JScrollPane messageBox = new JScrollPane();
        JTextArea message = new JTextArea(50, 50);
        message.setEditable(false);
        message.setLineWrap(true);
        messageBox.setViewportView(message);
        messageBox.getVerticalScrollBar().setToolTipText("hint");
        ToolTipManager.sharedInstance().setInitialDelay(0);

        getContentPane().add(messageBox);

        Logger.setLogArea(message);

        pack();
        setLocation(SCREEN_WIDTH / 2 - getPreferredSize().width / 2,
            SCREEN_HEIGHT / 2 - getPreferredSize().height / 2);
    }
}
