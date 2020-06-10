package swingtest.data;

/**
 * 天赋树节点
 */
public class TalentNode {

	private boolean isAvailable;	// 指示当前节点是否被点亮

	private long currentExp;		// 当前经验值(显示时比上总经验值再换算成百分比)

	private long totalExp;			// 总经验值

	private int lvUpSpeed;			// 升级速度(100为基础值, 获得经验值先*升级速度/100再加到当前经验值上)
									// 升级速度的数值由根骨决定

	// 节点具体效果
	private int[] effects;			// 对属性的影响(共8项, 分别对应玩家数据中的8个可变属性)

	private String intro;			// 文字说明

	/**
	 * 根据根骨及天赋树深度生成一个新节点
	 * @param talent	根骨(来自玩家数据)
	 * @param treeLevel	天赋树深度
	 */
	public TalentNode(int[] talent, int treeLevel){
		isAvailable = false;


	}
}
