package net.justfactions.jfcore.listeners;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import net.ess3.api.Economy;
import net.justfactions.jfcore.Core;
import net.justfactions.jfcore.manager.GenBucketsManager;
import net.justfactions.jfcore.utils.RocketCreepers;
import net.justfactions.jfcore.utils.SellWand;
import net.minecraft.server.v1_8_R3.EntityCreeper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreeper;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PlayerInteractEventHandler implements Listener {

    private Core core;
    private HashMap<Long, Integer> ids = new HashMap<>();

    public PlayerInteractEventHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getPlayer().getItemInHand() != null) {
                if (SellWand.isSellWand(e.getPlayer().getItemInHand())) {
                    e.setCancelled(true);
                }
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getItemInHand() != null) {
                if (e.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET) {
                    Block block = e.getClickedBlock();
                        Material north = block.getRelative(BlockFace.NORTH).getType();
                        Material east = block.getRelative(BlockFace.EAST).getType();
                        Material south = block.getRelative(BlockFace.SOUTH).getType();
                        Material west = block.getRelative(BlockFace.WEST).getType();
                        Material up = block.getRelative(BlockFace.UP).getType();
                        Material down = block.getRelative(BlockFace.DOWN).getType();

                        if (north == Material.MOB_SPAWNER ||
                                east == Material.MOB_SPAWNER ||
                                south == Material.MOB_SPAWNER ||
                                west == Material.MOB_SPAWNER ||
                                up == Material.MOB_SPAWNER ||
                                down == Material.MOB_SPAWNER) {
                            e.setCancelled(true);
                        }
                }

                if (SellWand.isSellWand(e.getPlayer().getItemInHand())) {
                    e.setCancelled(true);
                    Block block = e.getClickedBlock();
                    if (block.getType() == Material.CHEST ||
                    block.getType() == Material.TRAPPED_CHEST ||
                    block.getType() == Material.DISPENSER ||
                    block.getType() == Material.HOPPER ||
                    block.getType() == Material.DROPPER ||
                    block.getType() == Material.FURNACE ||
                    block.getType() == Material.BURNING_FURNACE) {
                        InventoryHolder inventoryHolder = (InventoryHolder) block.getState();
                        Inventory inventory = inventoryHolder.getInventory();
                        HashMap<Material, Integer> contents = new HashMap<>();
                        for (ItemStack item : inventory.getContents()) {
                            if (item != null && item.getType() != Material.AIR) {
                                int amount = item.getAmount();
                                if (contents.containsKey(item.getType())) {
                                    amount += contents.get(item.getType());
                                }
                                contents.put(item.getType(), amount);
                            }
                        }

                        HashMap<Material, Double> prices = SellWand.getPrices();
                        int sold = 0;
                        e.getPlayer().sendMessage("§6§l» §eTrying to sell all items inside container...");
                        for (Map.Entry<Material, Integer> entry : contents.entrySet()) {
                            if (contents.containsKey(entry.getKey())) {
                                if (prices.containsKey(entry.getKey())) {
                                    double price = (prices.get(entry.getKey()) * entry.getValue());
                                    sold += price;
                                    e.getPlayer().sendMessage("§6§l> §eSold §7" + entry.getValue() + " x " + entry.getKey().name() + "§e for §7$" + price + "§e.");
                                    inventory.remove(entry.getKey());
                                }
                            }
                        }
                        if (sold == 0) {
                            e.getPlayer().sendMessage("§6§l> §cNo items found to sell!");
                        } else {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + e.getPlayer().getName() + " " + sold);
                        }

                        for (HumanEntity he : inventory.getViewers()) {
                            if (he instanceof Player) {
                                ((Player) he).updateInventory();
                            }
                        }
                    } else {
                        e.getPlayer().sendMessage("§4§l» §cYou can only use sell wands on containers!");
                    }
                    return;
                }

                if (e.getPlayer().getItemInHand().getType() == Material.SPONGE) {
                    if (e.getClickedBlock() != null) {
                        Faction playerFaction = FPlayers.getInstance().getByPlayer(e.getPlayer()).getFaction();
                        Faction blockFaction = Board.getInstance().getFactionAt(new FLocation(e.getClickedBlock().getLocation()));

                        if (blockFaction.isWarZone() || blockFaction.isWilderness()) {
                            return;
                        }

                        if (!playerFaction.getId().equalsIgnoreCase(blockFaction.getId())) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage("§4§l» §cYou cannot place sponges here!");
                            e.getPlayer().updateInventory();
                        }
                    }
                    return;
                }


                if (RocketCreepers.isRocketCreeper(e.getPlayer().getItemInHand())) {
                    e.setCancelled(true);

                    if (e.getPlayer().getItemInHand().getAmount() > 1) {
                        e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
                    } else {
                        e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                    }
                    e.getPlayer().updateInventory();

                    Creeper creeper = (Creeper) e.getClickedBlock().getLocation().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, 1.5, 0.5), EntityType.CREEPER);
                    creeper.setPowered(true);
                    creeper.setCustomName("§3Rocket Creeper");
                    creeper.setCustomNameVisible(true);
                    creeper.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 0));
                    final long time = System.currentTimeMillis();
                    BukkitTask bt = core.getServer().getScheduler().runTaskTimer(core, () -> {
                        try {
                            if (creeper.isDead()) {
                                cancel(time);
                            }

                            creeper.setVelocity(new Vector(0, 1, 0));

                            if (creeper.getLocation().getY() >= 256) {
                                creeper.remove();
                                cancel(time);
                            }

                            if (creeper.getEyeLocation().add(0,0,0).getBlock().getType() != Material.AIR && !creeper.getEyeLocation().add(0,0,0).getBlock().isLiquid() ||
                            creeper.getEyeLocation().add(0,1,0).getBlock().getType() != Material.AIR && !creeper.getEyeLocation().add(0,1,0).getBlock().isLiquid() ||
                            creeper.getEyeLocation().add(0,2,0).getBlock().getType() != Material.AIR && !creeper.getEyeLocation().add(0,2,0).getBlock().isLiquid()) {
                                customizeCreeper(creeper, 0, 2);
                                cancel(time);
                            }
                        } catch (Exception ex) {
                            cancel(time);
                        }
                    }, 0L, 5L);
                    ids.put(time, bt.getTaskId());
                }
            }
        }
    }

    private void cancel(Long time) {
        if (ids.containsKey(time)) {
            core.getServer().getScheduler().cancelTask(ids.get(time));
            ids.remove(time);
        }
    }

    public void customizeCreeper(Creeper creeper, int fuse, int radius){
        EntityCreeper entCreeper = ((CraftCreeper)creeper).getHandle();
        Field fuseF = null;
        Field radiusF = null;
        try {
            fuseF = EntityCreeper.class.getDeclaredField("maxFuseTicks");
            radiusF = EntityCreeper.class.getDeclaredField("explosionRadius");
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        fuseF.setAccessible(true);
        radiusF.setAccessible(true);
        try {
            fuseF.setInt(entCreeper, fuse);
            radiusF.setInt(entCreeper, radius);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
