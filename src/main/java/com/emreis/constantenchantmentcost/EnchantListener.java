package com.emreis.constantenchantmentcost;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantListener implements Listener  {
    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        Player player = event.getEnchanter();
        int cost = event.whichButton() + 1;
        int expRequiredToGetCost = getTotalExpForLevel(cost);
        int currentTotalExp = getTotalExpOfPlayer(player);
        int targetExp = currentTotalExp - expRequiredToGetCost;
        int targetLevel = getLevelFromTotalExp(targetExp);
        float targetPercentage = (float)(targetExp - getTotalExpForLevel(targetLevel)) / (float)getExpRequiredToLevelUp(targetLevel);

        player.setLevel(getLevelFromTotalExp(targetExp) + cost);
        player.setExp(targetPercentage);

        // After we are done here, `cost` levels will be removed by the server.
    }

    public int getTotalExpForLevel(int level) {
        int level2 = level * level;
        if (level <= 16) {
            return level2 + 6 * level;
        } else if (level <= 31) {
            return (int) (2.5 * level2 - 40.5 * level + 360);
        }
        return (int) (4.5 * level2 - 162.5 * level + 2220);
    }

    public int getExpRequiredToLevelUp(int currentLevel) {
        if (currentLevel <= 15) {
            return 2 * currentLevel + 7;
        } else if (currentLevel <= 30) {
            return 5 * currentLevel - 38;
        } else {
            return 9 * currentLevel - 158;
        }
    }

    public int getTotalExpOfPlayer(Player player) {
        return (int) (getTotalExpForLevel(player.getLevel()) + player.getExpToLevel() * player.getExp());
    }

    public int getLevelFromTotalExp(int totalExp) {
        if (totalExp <= 352) {
            return (int) (Math.sqrt(totalExp + 9) - 3);
        } else if (totalExp <= 1507) {
            return (int) (81.0 / 10.0 + Math.sqrt(2.0 / 5.0 * (totalExp - 7839.0 / 40.0)));
        } else {
            return (int) (325.0 / 18.0 + Math.sqrt(2.0 / 9.0 * (totalExp - 54215.0 / 72.0)));
        }
    }
}
