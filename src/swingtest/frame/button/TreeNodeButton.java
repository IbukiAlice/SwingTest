package frame.button;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 天赋树节点按钮
 */
public class TreeNodeButton extends JButton {

	private static final ImageIcon treeNodeBackground = new ImageIcon(Objects.requireNonNull(
		TreeNodeButton.class.getClassLoader().getResource("pic/tree_node.png")));

	public static int WIDTH = 100;
	public static int HEIGHT = 100;

	public TreeNodeButton(String text){
		super(text, treeNodeBackground);
		setHorizontalTextPosition(CENTER);
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
}
