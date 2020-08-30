package net.justfactions.jfcore;

import net.justfactions.jfcore.commands.*;
import net.justfactions.jfcore.listeners.*;
import net.justfactions.jfcore.manager.AlertsManager;
import net.justfactions.jfcore.manager.AltManager;
import net.justfactions.jfcore.utils.NicknameManager;
import net.justfactions.jfcore.utils.Reclaim;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Core extends JavaPlugin {

    AlertsManager alertsManager;
    AltManager altManager;
    Reclaim reclaim;
    NicknameManager nicknameManager;

    @Override
    public void onEnable() {
        reclaim = new Reclaim(this);
        nicknameManager = new NicknameManager(this);
        registerCommands();
        registerListeners();

        if (!getConfig().contains("alerts")) {
            getConfig().set("alerts.wait", 60 * 5);
            getConfig().set("alerts.messages", Arrays.asList("This is an example message! Incrementing is cool!", "This is also an example message"));
            saveConfig();
        }
        alertsManager = new AlertsManager(this);
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (alertsManager.getIndex() == alertsManager.getTotalMessages()) {
                alertsManager.setIndex(0);
            } else {
                Bukkit.broadcastMessage(alertsManager.getCurrentMessage());
                alertsManager.setIndex(alertsManager.getIndex() + 1);
            }

    }, 0L, alertsManager.getDelay() * 20);
        altManager = new AltManager();
        altManager.handleReload();

       // String header = "§6Just§eFactions\n§a" + (Bukkit.getOnlinePlayers().size()-1) + "§7/§a500";
        //String footer = "§eRanks, keys and MORE at §astore.justfactions.net";
        //for (Player player : Bukkit.getOnlinePlayers()) {
         //   Tab.setPlayerListHeader(player, header);
          //  Tab.setPlayerListFooter(player, footer);
       // }
    }

    @Override
    public void onDisable() {
        altManager.cleanUp();
    }

    public AlertsManager getAlertsManager() {
        return alertsManager;
    }

    public AltManager getAltManager() {
        return altManager;
    }

    public NicknameManager getNicknameManager() {
        return nicknameManager;
    }

    private void registerCommands() {
        getCommand("vote").setExecutor(new VoteCommand());
        getCommand("apply").setExecutor(new ApplyCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("shockwave").setExecutor(new ShockwaveCommand());
        getCommand("sellwand").setExecutor(new SellWandCommand());
        getCommand("banalts").setExecutor(new BanAltsCommand());
        getCommand("wild").setExecutor(new WildCommand());
        getCommand("rocketcreeper").setExecutor(new RocketCreeperCommand());
        getCommand("reclaim").setExecutor(new ReclaimCommand(this));
        getCommand("nickname").setExecutor(new NicknameCommand(this));
        getCommand("tnt").setExecutor(new TntCommand());
        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("toggleconnectionmessages").setExecutor(new ToggleConnectionMessages(this));
        getCommand("nv").setExecutor(new NightVision());
        getCommand("genbuckets").setExecutor(new GenBuckets());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AsyncPlayerChatEventHandler(this), this);
        pm.registerEvents(new VoteEventListener(this), this);
        pm.registerEvents(new PlayerJoinEventHandler(this), this);
        pm.registerEvents(new PlayerLoginEventHandler(this), this);
        pm.registerEvents(new PlayerQuitEventHandler(this), this);
        pm.registerEvents(new PlayerKickEventHandler(this), this);
        pm.registerEvents(new PlayerCommandPreprocessEventHandler(), this);
        pm.registerEvents(new BlockBreakEventHandler(), this);
        pm.registerEvents(new ServerListPingEventHandler(this), this);
        pm.registerEvents(new EntityExplodeEventHandler(), this);
        pm.registerEvents(new PlayerInteractEventHandler(this), this);
        pm.registerEvents(new BlockFromToEventHandler(), this);
        pm.registerEvents(new BlockPlaceEventHandler(), this);
        pm.registerEvents(new PrepareItemCraftEventHandler(), this);
        pm.registerEvents(new PlayerTeleportEventHandler(), this);
        pm.registerEvents(new CreatureSpawnEventHandler(), this);
        pm.registerEvents(new EntityDeathEventHandler(), this);
        pm.registerEvents(new RankChangeEventHandler(), this);
        pm.registerEvents(new OverlayRankChangeEventHandler(), this);
        pm.registerEvents(new EnchantItemEventHandler(), this);
        pm.registerEvents(new EntityTargetLivingEntityEventHandler(), this);
        pm.registerEvents(new InventoryClickEventHandler(), this);
        pm.registerEvents(new PlayerDropItemEventHandler(), this);
        pm.registerEvents(new PlayerBucketEmptyEventHandler(), this);
    }

    public Reclaim getReclaim() {
        return reclaim;
    }
}
