package swingtest.data.talenttree;

import java.util.Random;

/**
 * 天赋树节点
 */
public class TalentNode {

	private boolean available;	// 指示当前节点是否被点亮

	private boolean fullExp;		// 指示当前节点是否满级

	private int currentExp;		// 当前经验值(显示时比上总经验值再换算成百分比)

	private int totalExp;			// 总经验值

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
		available = false;
		fullExp = false;

		Random r = new Random();
		effects = new int[8];

		final String[] propertyNames = {"力量", "敏捷", "体力", "攻击", "速度", "闪避", "生命上限", "防御"};

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

		StringBuilder builder = new StringBuilder();
		builder.append(propertyNames[targetPos]).append("增加").append(effects[targetPos]);

		// 设置升级速度
		final int[] targetPosToLvSpeed = {0, 1, 2, 0, 1, 1, 0, 2};
		if(targetPos == 6){
			lvUpSpeed = Math.max(talent[0], talent[2]);
		}else{
			lvUpSpeed = talent[targetPosToLvSpeed[targetPos]];
		}

		// 第二属性是否开启(根据根骨分布均匀程度来决定)
		if(r.nextInt(100) <= (int) Math.pow(talent[0] * talent[1] * talent[2], 1.0 / 3)){

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
			effects[targetPos] = 1 + (targetPos == 6 ? 5 : 1) * (targetPos > 2 ? 2 : 1) * treeLevel / 2;

			builder.append("【第二属性:").append(propertyNames[targetPos]).append("增加")
				.append(effects[targetPos]).append("】");
		}

		// 设置经验值相关
		currentExp = 0;
		totalExp = (int)(Math.pow(treeLevel, 1.5) * 37 + 31);

		intro = builder.toString();
	}

	/**
	 * 增加经验值
	 * @param exp 经验值
	 * @return 溢出经验值
	 */
	protected int addExp(int exp){
		currentExp += (exp * lvUpSpeed / 100);

		fullExp = currentExp >= totalExp;

		return fullExp ? currentExp - totalExp : 0;
	}

	protected boolean isAvailable(){
		return available;
	}

	protected boolean isFullExp(){
		return fullExp;
	}

	protected void setAvailable(boolean available) {
		this.available = available;
	}

	protected int[] getEffects(){return effects;}
}
