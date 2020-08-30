package net.justfactions.jfcore.manager;

import net.justfactions.jfcore.Core;
import org.bukkit.ChatColor;

import java.util.List;

public class AlertsManager {

    private Core core;
    List<String> messages;
    int delay;
    int index = 0;

    public AlertsManager(Core core) {
        this.core = core;
        loadMessages();
        loadDelay();
    }

    public void loadMessages() {
        messages = core.getConfig().getStringList("alerts.messages");
    }

    public void loadDelay() {
        delay = core.getConfig().getInt("alerts.wait");
    }

    public String getCurrentMessage() {
        return ChatColor.translateAlternateColorCodes('&', messages.get(index));
    }

    public void addMessage(String s) {
        List<String> temp = core.getConfig().getStringList("alerts.messages");
        temp.add(s);
        core.getConfig().set("alerts.messages", temp);
        core.saveConfig();
        loadMessages();
    }

    public void removeMessage(int index) {
        List<String> temp = core.getConfig().getStringList("alerts.messages");
        temp.remove(index);
        core.getConfig().set("alerts.messages", temp);
        core.saveConfig();
        loadMessages();
    }

    public void setDelay(int i) {
        core.getConfig().set("alerts.wait", i);
        core.saveConfig();
        loadDelay();
    }

    public int getDelay() {
        return delay;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotalMessages() {
        return messages.size();
    }
}
