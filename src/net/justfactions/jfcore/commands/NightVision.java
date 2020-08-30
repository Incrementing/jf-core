package net.justfactions.jfcore.commands;

import com.arespvp.api.API;
import com.arespvp.api.interfaces.User;
import net.justfactions.jfcore.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * **************************************************************************************
 * Copyright Molten Rock Ltd (c) 2017. All Right Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of Molten Rock Ltd. Distribution, taking snippets, or
 * claiming any contents as your own will break the terms of licence, and void any
 * agreements with you, the third party.
 * Regards Molten Rock Ltd - @MoltenRockLtd - https://moltenrock.io
 * ***************************************************************************************
 */
public class NightVision implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("jf.nv")) {
                if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    p.sendMessage("§8§l» §7Night vision has been §4disabled§7!");
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0));
                    p.sendMessage("§8§l» §7Night vision has been §aenabled§7!");
                }
            } else {
                p.sendMessage("§4§l §cYou do not have permission to use this command!");
            }
        }
        return true;
    }
}
