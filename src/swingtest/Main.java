package swingtest;

import swingtest.battle.Battle;
import swingtest.data.PlayerData;
import swingtest.frame.Page;

public class Main {

    public static void main(String[] args) {
        //new Page().setVisible(true);

        PlayerData playerData = new PlayerData();
        playerData.setStrength(42);
        playerData.setAgile(68);
        playerData.setEndurance(20);

        PlayerData enemyData = new PlayerData();
        enemyData.setStrength(40);
        enemyData.setAgile(37);
        enemyData.setEndurance(53);

        Battle battle = new Battle(playerData, enemyData);
        System.out.println(battle.startBattle());
    }
}
