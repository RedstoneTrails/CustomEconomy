package com.reddevtrails.customeconomy;

import com.reddevtrails.customeconomy.commands.EconomyCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEconomy extends JavaPlugin {
    private EconomyCommands economyCommands;

    @Override
    public void onEnable() {
        registerCommands();
        saveDefaultConfig();
        Configuration.load();
    }

    @Override
    public void onDisable() {}

    public void reload() {
        reloadConfig();
        Configuration.load();
    }

    public void registerCommands() {
        economyCommands = new EconomyCommands(this);
        getCommand("deposit").setExecutor(economyCommands);
        getCommand("deposit").setTabCompleter(economyCommands);
        getCommand("withdraw").setExecutor(economyCommands);
        getCommand("withdraw").setTabCompleter(economyCommands);
        getCommand("customeconomy").setExecutor(economyCommands);
        getCommand("customeconomy").setTabCompleter(economyCommands);
    }
}
