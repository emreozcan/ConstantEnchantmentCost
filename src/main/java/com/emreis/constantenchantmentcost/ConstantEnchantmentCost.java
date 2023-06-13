package com.emreis.constantenchantmentcost;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConstantEnchantmentCost extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
