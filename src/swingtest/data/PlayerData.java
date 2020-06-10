package swingtest.data;


/**
 * 玩家数据(非战斗状态下的数据)
 */
public class PlayerData {

	// 根骨(除非特殊情况，否则不会发生变化)
	private int[] talent;	// 根骨(天赋), 数值代表百分比, 表示三维成长速度

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

	// 天赋树
	private TalentTree talentTree;

	public PlayerData(){

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
