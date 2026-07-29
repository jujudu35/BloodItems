package fr.blooditems.managers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HammerManager {


    public List<Block> getBlocks3x3(Block center, Player player) {

        List<Block> blocks = new ArrayList<>();

        Location loc = center.getLocation();


        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();


        for (int offsetX = -1; offsetX <= 1; offsetX++) {

            for (int offsetY = -1; offsetY <= 1; offsetY++) {

                for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {


                    if (offsetX == 0 &&
                        offsetY == 0 &&
                        offsetZ == 0) {

                        continue;
                    }


                    Block block = center.getWorld()
                            .getBlockAt(
                                    x + offsetX,
                                    y + offsetY,
                                    z + offsetZ
                            );


                    if (isBreakable(block)) {

                        blocks.add(block);

                    }

                }
            }
        }


        return blocks;

    }



    private boolean isBreakable(Block block) {

        Material material = block.getType();


        return material != Material.AIR
                && material != Material.BEDROCK
                && material != Material.BARRIER;

    }
}



package fr.blooditems.managers;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HammerManager {


    public void breakBlocks(Player player, Block center, ItemStack hammer) {

        List<Block> blocks = getBlocks3x3(center, player);


        for (Block block : blocks) {

            if (player.getGameMode() != GameMode.CREATIVE) {

                block.breakNaturally(hammer);

                damageHammer(hammer);

            } else {

                block.setType(
                        org.bukkit.Material.AIR
                );

            }

        }

    }



    private void damageHammer(ItemStack hammer) {

        if (hammer == null) {
            return;
        }


        if (!hammer.hasItemMeta()) {
            return;
        }


        if (hammer.getType().getMaxDurability() <= 0) {
            return;
        }


        short durability = hammer.getDurability();


        durability++;


        if (durability >= hammer.getType().getMaxDurability()) {

            hammer.setAmount(0);

        } else {

            hammer.setDurability(durability);

        }

    }


    public List<Block> getBlocks3x3(Block center, Player player) {

        return null;
    }

}
