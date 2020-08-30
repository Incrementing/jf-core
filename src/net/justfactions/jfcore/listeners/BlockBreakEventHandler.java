package net.justfactions.jfcore.listeners;

import net.justfactions.jfcore.utils.Shockwave;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakEventHandler implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!e.isCancelled()) {

            if (e.getPlayer().getItemInHand() != null) {
                if (Shockwave.isShockwave(e.getPlayer().getItemInHand())) {
                    e.setCancelled(true);
                    List<Block> blockList = new ArrayList<>();
                    for(int x = e.getBlock().getX() - 1; x <= e.getBlock().getX() + 1; x++) {
                        for(int y = e.getBlock().getY() - 1; y <= e.getBlock().getY() + 1; y++) {
                            for(int z = e.getBlock().getZ() - 1; z <= e.getBlock().getZ() + 1; z++) {
                                blockList.add(e.getBlock().getWorld().getBlockAt(x, y, z));
                            }
                        }
                    }
                    for (Block block : blockList) {
                        if (Shockwave.canBreak(block)) {
                            block.breakNaturally(e.getPlayer().getItemInHand());
                        }
                    }
                }
            }
        }
    }
}
