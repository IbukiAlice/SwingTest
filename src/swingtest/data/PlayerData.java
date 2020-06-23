package swingtest.data;


import swingtest.data.talenttree.TalentTree;

import java.util.Random;

/**
 * 玩家数据(非战斗状态下的数据)
 */
public class PlayerData {

	// 根骨(除非特殊情况，否则不会发生变化)
	private int[] talent;	// 根骨(天赋), 数值代表百分比, 表示三维成长速度(三者之和为300)

	// 三维属性
	private int strength;	// 力量(1力1血1伤)

	private int agile;		// 身法(1敏1速1闪)

	private int endurance;	// 耐力(1体5血1防)

	// 其它额外增减属性
	private int additionDamage;	// 额外永久增加/减少伤害

	private int additionSpeed;	// 额外永久增加/减少速度

	private int additionDodge;	// 额外永久增加/减少闪避

	private int additionHealth;	// 额外永久增加/减少生命

	private int additionDefence;// 额外永久增加/减少防御

	// 经验值
	private int exp;

	// 天赋树
	private TalentTree talentTree;

	public PlayerData(){

		// 初始化根骨
		talent = new int[3];
		Random r = new Random();

		int totalTalent = 300;	// 根骨总和为300

		talent[0] = r.nextInt(totalTalent - 2) + 1;	// 限定范围1~298
		totalTalent -= talent[0];
		talent[1] = r.nextInt(totalTalent - 1) + 1;
		totalTalent -= talent[1];
		talent[2] = r.nextInt(totalTalent) + 1;

		talentTree = new TalentTree(this);

		// 初始化属性(根据根骨)
		strength = 1 + talent[0] / 10;
		agile = 1 + talent[1] / 10;
		endurance = 1 + talent[2] / 10;

		additionDamage = 0;
		additionSpeed = 0;
		additionDodge = 0;
		additionHealth = 0;
		additionDefence = 0;

		exp = 0;
	}

	//----- Method -----

	public int getDamage(){
		return additionDamage + strength;
	}

	public int getSpeed(){
		return additionSpeed + agile;
	}

	public int getDodge(){
		return additionDodge + agile;
	}

	public int getHealth(){
		return additionHealth + endurance * 5 + strength;
	}

	public int getDefence(){
		return additionDefence + endurance;
	}

	//-----Getter & Setter -----
	public int[] getTalent(){ return talent; }

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgile() {
		return agile;
	}

	public void setAgile(int agile) {
		this.agile = agile;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getAdditionDamage() {
		return additionDamage;
	}

	public void setAdditionDamage(int additionDamage) {
		this.additionDamage = additionDamage;
	}

	public int getAdditionSpeed() {
		return additionSpeed;
	}

	public void setAdditionSpeed(int additionSpeed) {
		this.additionSpeed = additionSpeed;
	}

	public int getAdditionDodge() {
		return additionDodge;
	}

	public void setAdditionDodge(int additionDodge) {
		this.additionDodge = additionDodge;
	}

	public int getAdditionHealth() {
		return additionHealth;
	}

	public void setAdditionHealth(int additionHealth) {
		this.additionHealth = additionHealth;
	}

	public int getAdditionDefence() {
		return additionDefence;
	}

	public void setAdditionDefence(int additionDefence) {
		this.additionDefence = additionDefence;
	}
}
