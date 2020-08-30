package net.justfactions.jfcore.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromToEventHandler implements Listener {

    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent e) {
        Block block = e.getBlock();
        if (block.getType() == Material.STATIONARY_WATER || block.getType() == Material.WATER) {
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
    }
}
