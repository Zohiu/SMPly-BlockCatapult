package io.github.zohiu.smplyblockcatapult;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(SMPlyBlockCatapult.getInstance().plugin_prefix + "Usage: /smplyblockcatapult reload");
        } else {
            String output = SMPlyBlockCatapult.getInstance().loadConfig();
            sender.sendMessage(SMPlyBlockCatapult.getInstance().plugin_prefix + output);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("smplyblockcatapult") && args.length >= 0){
            if(sender instanceof Player){
                List<String> list = new ArrayList<>();

                list.add("reload");

                return list;
            }
        }
        return null;
    }
}
