package net.justfactions.jfcore.commands;

import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReclaimCommand implements CommandExecutor {

    Core core;

    public ReclaimCommand(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            List<String> raw;
            try {
                raw = core.getReclaim().get().getStringList("uuids");
            } catch (Exception ex) {
                raw = new ArrayList<>();
            }

            if (raw.contains(p.getUniqueId().toString())) {
                p.sendMessage("§4§l» §cYou've already reclaimed your reward for this map!");
            } else {
                if (p.hasPermission("jf.overlord")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p legendary 3 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                } else if (p.hasPermission("jf.titan")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p legendary 2 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                } else if (p.hasPermission("jf.god")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p rare 1 " + p.getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p legendary 1 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.demigod")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p rare 2 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.legend")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p rare 1 " + p.getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p spawner 1 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.hero")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p spawner 5 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.eliteplus")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p spawner 4 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.elite")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p spawner 3 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.vipplus")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p spawner 2 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                }else if (p.hasPermission("jf.vip")) {
                    raw.add(p.getUniqueId().toString());
                    core.getReclaim().get().set("uuids", raw);
                    core.getReclaim().save();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p spawner 2 " + p.getName());
                    p.sendMessage("§2§l» §aYou've successfully reclaimed your keys!");
                } else {
                    p.sendMessage("§4§l» §cYou must have a premium rank to reclaim keys (/buy)!");
                }
            }

        } else {
            sender.sendMessage("This command can only be run by a player!");
        }

        return false;
    }
}
