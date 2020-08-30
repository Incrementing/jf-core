package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameCommand implements CommandExecutor {

    Core core;

    public NicknameCommand(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            if (p.hasPermission("jf.nickname")) {
                if (args.length == 1) {
                    String name = args[0];
                    if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                        if (name.length() >= 3) {
                            core.getNicknameManager().setNickname(p.getUniqueId(), name);
                            p.sendMessage("§2§l» §aNickname set!");
                        } else {
                            p.sendMessage("§4§l» §cNicknames must be atleast 3 chars long!");
                        }
                    } else {
                        p.sendMessage("§4§l» §cNicknames must be similar to your username!");
                    }
                } else if (args.length == 2) {
                    if (p.hasPermission("jf.mod")) {
                        OfflinePlayer raw = Bukkit.getOfflinePlayer(args[0]);
                        if (raw != null) {
                            core.getNicknameManager().setNickname(raw.getUniqueId(), args[1]);
                            p.sendMessage("§2§l» §aNickname set!");
                        } else {
                            p.sendMessage("§4§l» §cUnknown player!");
                        }
                    } else {
                        p.sendMessage("§4§l» §cYou cannot set other players nicknames!");
                    }
                } else {
                    p.sendMessage("§4§l» §cSyntax: /nick <name>!");
                }
            } else {
                p.sendMessage("§4§l» §cYou need a premium rank to use this command (/buy)!");
            }
        }
        return true;
    }
}
