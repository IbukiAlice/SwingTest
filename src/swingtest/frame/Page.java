package frame;

import frame.panel.GamePanel;
import frame.panel.TalentTreeTable;
import tool.Logger;

import javax.swing.*;
import java.awt.*;

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

        // 自定义面板，使显示界面尺寸符合设定尺寸
        setContentPane(new GamePanel());

        pack();
        setLocation(SCREEN_WIDTH / 2 - getPreferredSize().width / 2,
            SCREEN_HEIGHT / 2 - getPreferredSize().height / 2);
    }
}
