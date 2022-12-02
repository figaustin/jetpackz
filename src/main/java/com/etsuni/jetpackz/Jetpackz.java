package com.etsuni.jetpackz;

import org.bukkit.plugin.java.JavaPlugin;



public final class Jetpackz extends JavaPlugin {

    protected static Jetpackz plugin;
    @Override
    public void onEnable() {
        plugin = this;
        this.getServer().getPluginManager().registerEvents(new Events(), this);
        CustomEnchant.register();
    }

    @Override
    public void onDisable() {
    }

}
