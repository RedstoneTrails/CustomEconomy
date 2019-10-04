package com.reddevtrails.customeconomy.commands;

import com.reddevtrails.customeconomy.CustomEconomy;
import com.reddevtrails.customeconomy.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EconomyCommands implements CommandExecutor, TabCompleter {
    private CustomEconomy plugin;

    public EconomyCommands(CustomEconomy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console commands currently are not enabled for this command. Please use commands in-game.");
            return true;
        }

        // Get basic info
        Player player = (Player) sender;
        String cmd = command.getName().toLowerCase();

        // Using /deposit
        if (cmd.equalsIgnoreCase("deposit")) {
            if (args.length == 0) {
                // Try to deposit one of current item in hand
                Deposit.amount(player, Settings.DefaultAmount);
            } else if (args.length == 1) {
                String arg = args[0];
                if (arg.equalsIgnoreCase("all")) {
                    // Deposit all in inventory
                    Deposit.all(player);
                } else if (arg.equalsIgnoreCase("hand")) {
                    // Deposit all in hand
                    Deposit.hand(player);
                } else {
                    if (isInt(arg)) {
                        // Deposit specific number
                        Deposit.amount(player, Integer.parseInt(arg));
                    } else {
                        // Unrecognized argument
                    }
                }
            } else {
                // Too many arguments, send usage
            }
        }
        // Using /withdraw
        else if (cmd.equalsIgnoreCase("withdraw")) {
            if (args.length == 0) {
                // Try to withdraw one unit
                Withdraw.amount(player, Settings.DefaultAmount);
            } else if (args.length == 1) {
                String arg = args[0];
                if (arg.equalsIgnoreCase("all")) {
                    // Withdraw all user can hold
                    Withdraw.all(player);
                } else {
                    if (isInt(arg)) {
                        // Withdraw specific number
                        Withdraw.amount(player, Integer.parseInt(arg));
                    } else {
                        // Unrecognized argument
                    }
                }
            } else {
                // Too many arguments, send usage
            }
        }
        // Using /customeconomy
        else if (cmd.equalsIgnoreCase("customeconomy")) {

        }

        return true;
    }

    public static boolean isInt(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<String>();
        return options;
    }
}
