package swingtest.data.talenttree;

import java.util.TreeMap;

/**
 * 天赋树
 */
public class TalentTree {

	// 天赋树存储
	private TreeMap<Integer, TalentNode> tree;

	// 天赋树深度
	private int treeLevel;

	// 天赋树节点数
	private int treeNodeNum;

	// 角色的根骨
	private int[] talent;

	// 是否有新的可选分支
	private boolean newBranch;

	// 是否有升级成功的节点
	private boolean fullExpNode;

	/**
	 * 根据根骨初始化天赋树，创建初始节点
	 * @param talent 根骨
	 */
	public TalentTree(int[] talent){
		newBranch = false;
		fullExpNode = false;
		treeLevel = 1;
		tree = new TreeMap<>();
		this.talent = talent.clone();

		tree.put(1, new TalentNode(this.talent, treeLevel));
		treeNodeNum = 1;
		tree.get(1).setAvailable(true);
	}

	/**
	 * 根据根骨生成新一层天赋树
	 */
	public void generateTreeLevel(){
		++treeLevel;

		tree.put(++treeNodeNum, new TalentNode(talent, treeLevel));
		tree.put(++treeNodeNum, new TalentNode(talent, treeLevel));
	}

	/**
	 * 给最新的节点添加经验值
	 * @param exp 经验值
	 * @return 溢出经验值
	 */
	public int addExpToLastedNode(int exp){

		if(newBranch){
			// 还未选择新节点
			return exp;
		}

		TalentNode node = tree.get(treeNodeNum);

		if(node.isAvailable()){
			node = tree.get(treeNodeNum - 1);
		}

		int remainExp = node.addExp(exp);

		if(node.isFullExp()){
			// 生成新的天赋树
			generateTreeLevel();

			newBranch = true;
			fullExpNode = true;
		}

		return remainExp;
	}

	/**
	 * 获取满经验值节点的8项属性影响数组
	 * @return 8项属性影响数组
	 */
	public int[] getEffectOfFullExpNode(){
		if(!fullExpNode){
			return new int[8];
		}

		fullExpNode = false;

		TalentNode node = tree.get(treeNodeNum);

		if(node.isAvailable()){
			node = tree.get(treeNodeNum - 1);
		}

		return node.getEffects().clone();
	}
}
