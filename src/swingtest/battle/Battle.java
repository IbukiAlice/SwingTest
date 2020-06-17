package swingtest.battle;

import swingtest.data.Player;
import swingtest.data.PlayerData;

public class Battle {

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
