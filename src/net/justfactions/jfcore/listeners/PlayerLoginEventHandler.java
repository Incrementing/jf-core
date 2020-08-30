package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginEventHandler implements Listener {

    Core core;

    public PlayerLoginEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (core.getAltManager().getAltsOnline(e.getAddress().getHostAddress()) >= 3) {
            String reason = "§4§lCONNECTION BLOCKED\n\n"+
                    "§cYou're only allowed 3 accounts per IP Address!" +
                    "\n§cIP Address: §7" + e.getRealAddress().getHostAddress() +
                    "\n§cAccounts Online: §7" + core.getAltManager().getAltNamesOnline(e.getRealAddress().getHostAddress()) +
                    "\n\n§cProblem? Join the discord support room!";
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, reason);
        }
    }
}
