package frame.panel;

import javax.swing.*;
import java.awt.*;

/**
 * 显示界面面板（不含边框菜单等）
 */
public class GamePanel extends JPanel {

	// 界面大小
	public static final int PAGE_HEIGHT = 500;
	public static final int PAGE_WIDTH = 500;

	public GamePanel(){
		setLayout(new BorderLayout());

		// 设置界面大小
		setSize(PAGE_WIDTH, PAGE_HEIGHT);
		setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		add(new TalentTreeTable());
	}
}
