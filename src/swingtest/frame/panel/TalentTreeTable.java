package frame.panel;

import frame.button.TreeNodeButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * 天赋树面板
 */
public class TalentTreeTable extends JPanel {

	// 鼠标点暂存
	private Point mousePt = new Point(GamePanel.PAGE_WIDTH >> 1, GamePanel.PAGE_HEIGHT >> 1);

	// 拖动面板背景图
	private final ImageIcon dragBg = new ImageIcon(Objects.requireNonNull(
		TalentTreeTable.class.getClassLoader().getResource("pic/drag_bg.jpg")));

	// 天赋树深度
	private int level = 3;

	private int tableHeight = 500;
	private int tableWidth = TreeNodeButton.WIDTH * ((level << 1) + 1);



	public TalentTreeTable() {
		super(null);

		// 拖动面板
		DragPanel dragTable = new DragPanel();
		dragTable.setBounds(0, 0, tableWidth, tableHeight);
		dragTable.setBackground(Color.BLACK);
		dragTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePt = e.getPoint();
			}
		});
		dragTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int dx = e.getX() - mousePt.x;
				int dy = e.getY() - mousePt.y;

				int newX = dragTable.getX() + dx;
				int newY = dragTable.getY() + dy;

				// 拖动边界判断
				if (newX > 0) newX = 0;
				if (newY > 0) newY = 0;
				if (newX < GamePanel.PAGE_WIDTH - tableWidth) newX = GamePanel.PAGE_WIDTH - tableWidth;
				if (newY < GamePanel.PAGE_HEIGHT - tableHeight) newY = GamePanel.PAGE_HEIGHT - tableHeight;

				dragTable.setLocation(newX, newY);
				dragTable.repaint();
			}
		});
		add(dragTable);
	}

	private class DragPanel extends JPanel{

		public DragPanel(){
			super(null);

			TreeNodeButton b1 = new TreeNodeButton("节点1");
			b1.setLocation(TreeNodeButton.WIDTH, TreeNodeButton.HEIGHT * 3);
			add(b1);

			TreeNodeButton b2 = new TreeNodeButton("节点2");
			b2.setLocation(TreeNodeButton.WIDTH * 3, TreeNodeButton.HEIGHT);
			add(b2);

			TreeNodeButton b3 = new TreeNodeButton("节点3");
			b3.setLocation(TreeNodeButton.WIDTH * 3, TreeNodeButton.HEIGHT * 3);
			add(b3);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// 设置背景图片
			for(int i = 0; i < level; i++) {
				g.drawImage(dragBg.getImage(), i * (TreeNodeButton.WIDTH << 1), 0,
					dragBg.getIconWidth(), dragBg.getIconHeight(), this);
			}
			g.drawImage(dragBg.getImage(), level * (TreeNodeButton.WIDTH << 1), 0,
				dragBg.getIconWidth(), dragBg.getIconHeight(), this);
		}
	}
}
