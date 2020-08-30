package net.justfactions.jfcore.listeners;

import com.vexsoftware.votifier.model.VotifierEvent;
import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoteEventListener implements Listener {

    private Core core;

    public VoteEventListener(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onVote(VotifierEvent e) {
        Logger logger = Logger.getLogger("Minecraft");
        logger.log(Level.INFO, e.getVote().getUsername() + " just voted at " + e.getVote().getServiceName());

        if (Bukkit.getPlayer(e.getVote().getUsername()) != null) {
            int random = new Random().nextInt(3);

            if (random == 1) {
                Bukkit.broadcastMessage("§5§l» §7" + Bukkit.getPlayer(e.getVote().getUsername()).getName() + "§d just voted for us on §7" + e.getVote().getServiceName() + "§d (§lAND GOT DOUBLE THE REWARDS§d)!");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc give p vote 2 " + e.getVote().getUsername());
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + e.getVote().getUsername() + " 5000");
                Bukkit.getPlayer(e.getVote().getUsername()).sendMessage("§5§l» §dThanks for voting! You've been given a vote key and $5000.");
            } else {
                Bukkit.broadcastMessage("§5§l» §7" + Bukkit.getPlayer(e.getVote().getUsername()).getName() + "§d just voted for us on §7" + e.getVote().getServiceName() + "§d!");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc give p vote 1 " + e.getVote().getUsername());
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + e.getVote().getUsername() + " 2500");
                Bukkit.getPlayer(e.getVote().getUsername()).sendMessage("§5§l» §dThanks for voting! You've been given a vote key and $2500.");
            }
        } else {
            Bukkit.broadcastMessage("§5§l» §7" + e.getVote().getUsername() + "§d just voted for us on §7" + e.getVote().getServiceName() + "§d!");
        }
    }
}
