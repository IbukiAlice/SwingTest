package battle;

import data.Player;
import data.PlayerData;
import tool.Logger;

public class Battle {

	private final Logger logger = Logger.getInstance();

	private Player player;

	private Player enemy;

	public Battle(PlayerData playerData, PlayerData enemyData){
		player = new Player(playerData);
		enemy = new Player(enemyData);
	}

	public boolean startBattle(){
		int currentTime = 0;
		int playerNext = enemy.getSpeed();
		int enemyNext = player.getSpeed();

		while(true){

			// 敌人出手
			if(playerNext > enemyNext){
				int damage = player.hitJudge(enemy.getDamage(), false);
				player.reduceCurrentHealth(damage);

				logger.log("Enemy damage:" + damage +
					"---Player:" + player.getCurrentHealth() + "/" + player.getHealth());

				if(player.judgeDeath()){
					// 玩家失败
					return false;
				}

				// 计算敌人下一次攻击
				currentTime = enemyNext;
				enemyNext += player.getSpeed();

			}else{
				int damage = enemy.hitJudge(player.getDamage(), false);
				enemy.reduceCurrentHealth(damage);

				logger.log("Player damage:" + damage +
					"---Enemy:" + enemy.getCurrentHealth() + "/" + enemy.getHealth());

				if(enemy.judgeDeath()){
					// 玩家胜利
					return true;
				}

				// 计算自己下一次攻击
				currentTime = playerNext;
				playerNext += enemy.getSpeed();
			}
		}
	}
}
