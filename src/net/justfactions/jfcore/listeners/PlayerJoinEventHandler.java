package net.justfactions.jfcore.listeners;

import com.arespvp.api.API;
import com.arespvp.api.interfaces.User;
import net.justfactions.jfcore.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventHandler implements Listener {

    Core core;
    public PlayerJoinEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        User user = new User(e.getPlayer().getUniqueId(), (API) Bukkit.getPluginManager().getPlugin("AresAPI"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + user.getOfflinePlayer().getName() + " group set " + user.getRank().name());
        if (user.getOverlayRank() != null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + user.getOfflinePlayer().getName() + " group add " + user.getOverlayRank().name());
        }

        if (core.getConfig().getBoolean("connection-messages", true)) {
            e.setJoinMessage("§7[§a+§7] §a" + e.getPlayer().getName());
        } else {
            e.setJoinMessage("");
        }

        for (int i = 0; i < 250; i++) {
            p.sendMessage(" ");
        }


        p.sendMessage("§b§l§m---------------------------------------------");
        p.sendMessage("§3Welcome §b" + e.getPlayer().getName() + "§3, to §bAresPvP§3 Factions.");

        core.getAltManager().handleJoinEvent(e.getPlayer());
        TextComponent store = new TextComponent("    [STORE]");
        store.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.justfactions.net/"));
        store.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Click to be taken to our webstore!").create() ) );
        store.setBold(true);
        store.setColor(ChatColor.GRAY);

        TextComponent forums = new TextComponent("    [FORUMS]");
        forums.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://justfactions.net/"));
        forums.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Click to be taken to our forums!").create() ) );
        forums.setBold(true);
        forums.setColor(ChatColor.GRAY);

        TextComponent vote = new TextComponent("    [VOTE]");
        vote.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://vote.justfactions.net/"));
        vote.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Click to vote for us!").create() ) );
        vote.setBold(true);
        vote.setColor(ChatColor.GRAY);

        store.addExtra(forums);
        store.addExtra(vote);
        p.spigot().sendMessage( store );

        p.sendMessage("§7§oType §b§o/settings §7§oto customize your AresPvP experience.");
        p.sendMessage("§b§l§m---------------------------------------------");

       // String header = "§6Just§eFactions\n§a" + (Bukkit.getOnlinePlayers().size()-1) + "§7/§a500";
       // String footer = "§eRanks, keys and MORE at §astore.justfactions.net";
      //  for (Player player : Bukkit.getOnlinePlayers()) {
      //      Tab.setPlayerListHeader(player, header);
      //      Tab.setPlayerListFooter(player, footer);
      //  }
    }
}
