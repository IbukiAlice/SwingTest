package swingtest.data;

import java.util.Random;

/**
 * 玩家(战斗状态下的数据)
 */
public class Player {
	// 三项基础属性(战斗中不会发生变化)
	private int strength;	// 力量

	private int agile;		// 身法

	private int endurance;	// 耐力

	// 五项战斗属性(战斗中可能发生变化)
	private Triple damage;	// 伤害

	private Triple speed;	// 速度

	private Triple dodge;	// 闪避

	private Triple health;	// 生命(此处是指生命上限)

	private Triple defence;// 防御

	public boolean judgeDeath() {
		return currentHealth <= 0;
	}

	public void reduceCurrentHealth(int realDamage){
		currentHealth -= realDamage;
	}

	public int getCurrentHealth(){
		return currentHealth;
	}

	public int getHealth(){
		return health.getValue();
	}

	// 当前生命
	private int currentHealth;

	// 玩家非战斗数据
	private final PlayerData playerData;

	// 初始化玩家
	public Player(final PlayerData playerData){
		this.playerData = playerData;
		initData();
	}

	// 从玩家数据中获取数据
	private void initData(){
		strength = playerData.getStrength();
		agile = playerData.getAgile();
		endurance = playerData.getEndurance();

		damage = new Triple(playerData.getDamage());
		speed = new Triple(playerData.getSpeed());
		dodge = new Triple(playerData.getDodge());
		health = new Triple(playerData.getHealth());
		defence = new Triple(playerData.getDefence());

		currentHealth = health.getValue();
	}

	/**
	 * 修改属性
	 * @param attributeType 修改的属性类型, 1伤害,2速度,3闪避,4生命,5防御
	 * @param alterNumber	修改的数值(可正可负), 若为0则表示恢复之前的变化
	 * @param alterPercent	修改的百分比(可正可负), 若为0则表示恢复之前的变化
	 *                      --(该参数的意思为:希望新的数值是原先数值的百分之x,如参数为75,则效果为当前数值变成原来的75%)
	 */
	public void alterAttribute(int attributeType, int alterNumber, int alterPercent) throws Exception{
		Triple[] triples = {damage, speed, dodge, health, defence};
		Triple triple;

		try {
			triple = triples[attributeType - 1];
		}catch(Exception e){
			throw new Exception("Wrong attributeType!");
		}

		if(alterNumber == 0){
			triple.setAlterNumber(0);
		}else{
			triple.setAlterNumber(triple.getAlterNumber() + alterNumber);
		}

		if(alterPercent == 0){
			triple.setAlterPercent(0);
		}else{
			triple.setAlterPercent(triple.getAlterPercent() * alterPercent / 100);
		}
	}

	/**
	 * 受击判定(计算受到敌人攻击的结果)
	 * @param attackDamage	攻击力
	 * @param isIgnoreDefense	是否无视防御
	 * @return	受伤数值(命中则最低为1,未命中返回0)
	 */
	public int hitJudge(int attackDamage, boolean isIgnoreDefense){
		/*
			闪避率计算公式:
			闪避率 = 1 - 1 / (1 + 闪避值 / 900)
			保证100时闪避率10%, 1000时闪避率52.6%
		 */
		double realDodge = 1 - 1 / (1 + dodge.getValue() / 900.0);

		// 闪避判定
		if(new Random().nextInt(10000) < realDodge * 10000){
			return 0;
		}

		/*
			防御计算公式:
			格挡率 = 1 - 0.15 / (0.15 + 防御值) - 1 / ( 35 / 27 - 防御值 / 270)
			保证1时格挡率10%, 100时39.8%, 1000时79.98%
		 */
		double realDamage = attackDamage;

		if(isIgnoreDefense){
			realDamage -= realDamage * (1 - 0.15 / (0.15 + defence.getValue()) - 1 / ( 35 / 27 - defence.getValue() / 270));
		}

		return (int) Math.ceil(realDamage);
	}

	public int getSpeed() {
		return speed.getValue();
	}

	public int getDamage(){
		return damage.getValue();
	}
}

// 三元组, 用于记录5项战斗属性
class Triple{
	private int value;	// 基础数值

	private int alterNumber;	// 加法数值变换

	private int alterPercent;	// 百分比数值变换

	public Triple(){
		value = 0;
		alterNumber = 0;
		alterPercent = 100;
	}

	public Triple(int value){
		this.value = value;
		alterNumber = 0;
		alterPercent = 100;
	}

	// 获取数值, 先算加法后算乘法(值最小为1)
	public int getValue(){
		int result = (value + alterNumber) * alterPercent / 100;
		return result > 0 ? result : 1;
	}

	public int getAlterNumber() {
		return alterNumber;
	}

	public int getAlterPercent() {
		return alterPercent;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setAlterNumber(int alterNumber) {
		this.alterNumber = alterNumber;
	}

	public void setAlterPercent(int alterPercent) {
		this.alterPercent = alterPercent;
	}
}
