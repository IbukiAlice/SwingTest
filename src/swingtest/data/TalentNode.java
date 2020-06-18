package swingtest.data;

import java.util.Random;

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

		Random r = new Random();
		effects = new int[8];

		// 设置每种属性的概率
		int[] effectPredict = new int[8];
		final int[] pos = {0, 1, 1, 0, 2};

		for(int i = 0; i < effectPredict.length; i++){
			if(i < 3){
				effectPredict[i] = talent[i] * 2;
			}else{
				effectPredict[i] = talent[pos[i - 3]];
			}
		}

		effectPredict[6] += talent[2];

		// 选择第一属性
		int target = r.nextInt(1200);
		int targetPos = 0;

		for(int i = 0; i < effectPredict.length; i++){
			target -= effectPredict[i];
			if(target < 0){
				targetPos = i;
			}
		}

		// 设置属性值
		effects[targetPos] = (targetPos == 6 ? 5 : 1) * (targetPos > 2 ? 2 : 1) * treeLevel;

		// 设置升级速度
		final int[] targetPosToLvSpeed = {0, 1, 2, 0, 1, 1, 0, 2};
		if(targetPos == 6){
			lvUpSpeed = Math.max(talent[0], talent[2]);
		}else{
			lvUpSpeed = talent[targetPosToLvSpeed[targetPos]];
		}

		// 第二属性是否开启
		if(r.nextInt(10) == 0){

			// 去掉第一属性
			target = r.nextInt(1200 - effectPredict[targetPos]);
			effectPredict[targetPos] = 0;

			targetPos = 0;
			for(int i = 0; i < effectPredict.length; i++){
				target -= effectPredict[i];
				if(target < 0){
					targetPos = i;
				}
			}

			// 设置属性值
			effects[targetPos] = (targetPos == 6 ? 5 : 1) * (targetPos > 2 ? 2 : 1) * treeLevel / 3;
		}

		// 设置经验值相关
		currentExp = 0;
		totalExp = ((int)Math.pow(treeLevel, 1.5)) * 37 + 31;
	}
}
