package net.justfactions.jfcore.listeners;

import com.arespvp.api.API;
import com.arespvp.api.enums.Rank;
import com.arespvp.api.interfaces.User;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import net.justfactions.jfcore.Core;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatEventHandler implements Listener {

    Core core;

    public AsyncPlayerChatEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!e.isCancelled()) {
            e.setCancelled(true);
            e.setFormat("");

            String name = core.getNicknameManager().getNickname(e.getPlayer().getUniqueId());
            String message = e.getMessage().toLowerCase();

            if (message.contains("enderraids")) {
                e.setCancelled(true);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + e.getPlayer().getName() + " Fuck off you dirty advertising cunt. -s");
                return;
            }

            if ((message.contains("a") || message.contains("advertising") || message.contains("ad")) && (message.contains("saico") || message.contains("saicopvp"))) {
                e.setMessage("omfg! I love this server so much! please take all my donation money!");
            }

            if ((message.contains("a") || message.contains("advertising") || message.contains("ad")) && message.contains("skycade")) {
                e.setMessage("omfg! I love this server so much! please take all my donation money!");
            }

            if (e.getMessage().contains("[item]")) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    sendItemTooltipMessage(p, "§6§l» §7" + name + "§e holds up their §7" + getName(e.getPlayer().getItemInHand()), e.getPlayer().getItemInHand());
                }
                return;
            }

            User user = new User(e.getPlayer().getUniqueId(), (API) Bukkit.getPluginManager().getPlugin("AresAPI"));
            
            if (user.getOverlayRank() != null) {
                sendChat(user.getOverlayRank().getPrefix() + user.getOverlayRank().getUsername() + name + user.getOverlayRank().getArrow() + " » " + user.getOverlayRank().getText() + e.getMessage(), e.getPlayer());
            } else if (user.getRank() == Rank.MEMBER) {
                sendChat(user.getRank().getUsername() + name + user.getRank().getArrow() + " » " + user.getRank().getText() + e.getMessage(), e.getPlayer());
            } else {
                sendChat(user.getRank().getPrefix() + user.getRank().getUsername() + name + user.getRank().getArrow() + " » " + user.getRank().getText() + e.getMessage(), e.getPlayer());
            }
            
        }
    }

    private String getName(org.bukkit.inventory.ItemStack item) {
        if (item.getType()== Material.AIR) {
            return "Nothing";
        }

        if (item.hasItemMeta()) {
            if (item.getItemMeta().hasDisplayName()) {
                return item.getItemMeta().getDisplayName();
            }
        }

        return item.getType().toString().replaceAll("_", " ").toLowerCase();
    }

    private void sendChat(String s, Player sender) {
        Faction senderFaction = FPlayers.getInstance().getByPlayer(sender).getFaction();
        for (Player receiver : Bukkit.getOnlinePlayers()) {
            Faction receiverFaction = FPlayers.getInstance().getByPlayer(receiver).getFaction();
            if (senderFaction.isNone()) {
                receiver.sendMessage(s);
            } else {
                if (senderFaction.getRelationWish(receiverFaction).isAlly() && receiverFaction.getRelationWish(senderFaction).isAlly()) {
                    receiver.sendMessage("§7[§d" + senderFaction.getTag() + "§7] " + s);
                } else if (senderFaction.getRelationWish(receiverFaction).isEnemy() || receiverFaction.getRelationWish(senderFaction).isEnemy()) {
                    receiver.sendMessage("§7[§c" + senderFaction.getTag() + "§7] " + s);
                } else if (senderFaction.getTag().equalsIgnoreCase(receiverFaction.getTag())) {
                    receiver.sendMessage("§7[§a" + senderFaction.getTag() + "§7] " + s);
                } else {
                    receiver.sendMessage("§7[§r" + senderFaction.getTag() + "§7] " + s);
                }
            }
        }

    }

    private String convertItemStackToJsonRegular(org.bukkit.inventory.ItemStack itemStack) {
        ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        compound = nmsItemStack.save(compound);
        return compound.toString();
    }

    private void sendItemTooltipMessage(Player player, String message, org.bukkit.inventory.ItemStack item) {
        String itemJson = convertItemStackToJsonRegular(item);
        BaseComponent[] hoverEventComponents = new BaseComponent[]{
                new TextComponent(itemJson)
        };
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);
        TextComponent component = new TextComponent(message);
        component.setHoverEvent(event);
        player.spigot().sendMessage(component);
    }

}
