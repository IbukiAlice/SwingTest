package data.talenttree;

import data.PlayerData;
import tool.Logger;

import java.util.TreeMap;

/**
 * 天赋树
 */
public class TalentTree {

	private final Logger logger = Logger.getInstance();

	// 玩家数据, 用于回调, 以及获取玩家根骨
	private final PlayerData playerData;

	// 天赋树存储
	private TreeMap<Integer, TalentNode> tree;

	// 天赋树深度
	private int treeLevel;

	// 天赋树节点数
	private int treeNodeNum;

	// 最高的可用节点编号
	private int highestAvailableNodeId;

	// 是否有新的可选分支(分支是否未激活)
	private boolean newBranch;

	/**
	 * 根据根骨初始化天赋树，创建初始节点
	 * @param playerData 玩家数据, 用于回调
	 */
	public TalentTree(final PlayerData playerData){
		newBranch = false;
		treeLevel = 1;
		tree = new TreeMap<>();
		this.playerData = playerData;

		// 天赋树初始生成一个根节点
		tree.put(1, new TalentNode(this.playerData.getTalent(), treeLevel));
		treeNodeNum = 1;
		highestAvailableNodeId = 1;
		tree.get(1).setAvailable(true);
		tree.get(1).setHasBranch(true);
	}

	/**
	 * 根据根骨生成新一层天赋树
	 */
	public void generateTreeLevel(){
		++treeLevel;

		tree.put(++treeNodeNum, new TalentNode(playerData.getTalent(), treeLevel));
		tree.put(++treeNodeNum, new TalentNode(playerData.getTalent(), treeLevel));
	}

	/**
	 * 给指定节点添加经验值
	 * @param nodeId 节点编号
	 * @param exp 经验值
	 * @return 溢出经验值
	 */
	public int addExpToNode(int exp, int nodeId){

		TalentNode node = tree.get(nodeId);

		if(node == null){
			logger.warn("节点不存在!");
			return exp;
		}else if(!node.isAvailable()){
			logger.warn("该节点未激活!");
			return exp;
		}else if(node.isFullExp()){
			logger.warn("该节点已满级!");
			return exp;
		}

		int remainExp = node.addExp(exp);

		if(node.isFullExp()){

			// 回调玩家数据, 将节点效果加给玩家
			playerData.addEffects(node.getEffects());

			if(nodeId == highestAvailableNodeId) {
				// 生成新的天赋树
				generateTreeLevel();

				newBranch = true;
			}
		}

		return remainExp;
	}

	/**
	 * 激活指定节点
	 * @param nodeId 节点编号
	 * @param isExtraActive 是否为额外激活动作(非正常升级激活)
	 * @return 成功激活返回true
	 */
	public boolean activeNode(int nodeId, boolean isExtraActive){
		TalentNode node = tree.get(nodeId);

		if(node == null){
			logger.warn("节点不存在!");
			return false;
		}
		if(node.isAvailable()){
			logger.warn("节点已激活!");
			return false;
		}

		// 判断是否为额外激活
		if(isExtraActive){
			node.setAvailable(true);
		}
		// 判断是否为激活新分支节点(未激活的顶部两个新的分支节点之一)
		else if(treeNodeNum < 2 + nodeId && newBranch){
			highestAvailableNodeId = nodeId;
			newBranch = false;
			node.setAvailable(true);
			node.setHasBranch(true);
		}
		else{
			logger.warn("不允许激活该节点!");
			return false;
		}

		return true;
	}
}
