package fr.blooditems.managers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class HammerManager {


    public void breakBlocks(Player player, Block center, ItemStack hammer) {

        List<Block> blocks = getBlocks3x3(center);


        for (Block block : blocks) {

            if (block.getType().isAir()) {
                continue;
            }


            if (block.getType() == Material.BEDROCK) {
                continue;
            }


            if (player.getGameMode() == GameMode.CREATIVE) {

                block.setType(Material.AIR);

            } else {

                block.breakNaturally(hammer);

            }

        }


        damageHammer(hammer);

    }



    private List<Block> getBlocks3x3(Block center) {

        List<Block> blocks = new ArrayList<>();

        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();


        for (int xx = -1; xx <= 1; xx++) {

            for (int yy = -1; yy <= 1; yy++) {

                for (int zz = -1; zz <= 1; zz++) {


                    if (xx == 0 && yy == 0 && zz == 0) {
                        continue;
                    }


                    Block block = center.getWorld()
                            .getBlockAt(
                                    x + xx,
                                    y + yy,
                                    z + zz
                            );


                    blocks.add(block);

                }

            }

        }


        return blocks;

    }



    private void damageHammer(ItemStack hammer) {

        if (hammer == null) {
            return;
        }


        if (!(hammer.getItemMeta() instanceof Damageable damageable)) {
            return;
        }


        int currentDamage = damageable.getDamage();

        int maxDamage = hammer.getType().getMaxDurability();


        if (currentDamage + 1 >= maxDamage) {

            hammer.setAmount(0);

            return;

        }


        damageable.setDamage(currentDamage + 1);

        hammer.setItemMeta(damageable);

    }

}
