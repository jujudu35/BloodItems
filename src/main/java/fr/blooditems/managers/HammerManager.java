package fr.blooditems.managers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class HammerManager {


    public void breakBlocks(Player player, Block center, BlockFace face, ItemStack hammer) {


        List<Block> blocks = getBlocks3x3(center, face);



        // Casse le bloc ciblé
        breakBlock(player, center, hammer);



        // Casse les 8 blocs autour
        for (Block block : blocks) {

            breakBlock(player, block, hammer);

        }



        // Durabilité comme une pioche normale
        if (player.getGameMode() != GameMode.CREATIVE) {

            damageHammer(hammer);

        }

    }





    private void breakBlock(Player player, Block block, ItemStack hammer) {


        if (block.getType().isAir()) {
            return;
        }


        if (block.getType() == Material.BEDROCK) {
            return;
        }



        if (player.getGameMode() == GameMode.CREATIVE) {

            block.setType(Material.AIR);

        } else {

            block.breakNaturally(hammer);

        }

    }





    /**
     * Crée une zone 3x3x1 selon la face cassée
     */
    private List<Block> getBlocks3x3(Block center, BlockFace face) {


        List<Block> blocks = new ArrayList<>();


        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();



        // Dessus / dessous = horizontal
        if (face == BlockFace.UP || face == BlockFace.DOWN) {


            for (int xx = -1; xx <= 1; xx++) {

                for (int zz = -1; zz <= 1; zz++) {


                    if (xx == 0 && zz == 0) {
                        continue;
                    }


                    blocks.add(
                            center.getWorld().getBlockAt(
                                    x + xx,
                                    y,
                                    z + zz
                            )
                    );

                }

            }

        }



        // Nord / Sud = vertical
        else if (face == BlockFace.NORTH || face == BlockFace.SOUTH) {


            for (int xx = -1; xx <= 1; xx++) {

                for (int yy = -1; yy <= 1; yy++) {


                    if (xx == 0 && yy == 0) {
                        continue;
                    }


                    blocks.add(
                            center.getWorld().getBlockAt(
                                    x + xx,
                                    y + yy,
                                    z
                            )
                    );

                }

            }

        }



        // Est / Ouest = vertical
        else {


            for (int zz = -1; zz <= 1; zz++) {

                for (int yy = -1; yy <= 1; yy++) {


                    if (zz == 0 && yy == 0) {
                        continue;
                    }


                    blocks.add(
                            center.getWorld().getBlockAt(
                                    x,
                                    y + yy,
                                    z + zz
                            )
                    );

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



        int unbreaking = hammer.getEnchantmentLevel(
                Enchantment.UNBREAKING
        );



        // Solidité comme Minecraft vanilla
        if (unbreaking > 0) {


            double chance = 1.0 / (unbreaking + 1);



            if (Math.random() > chance) {

                return;

            }

        }



        int damage = damageable.getDamage();

        int max = hammer.getType().getMaxDurability();



        if (damage + 1 >= max) {

            hammer.setAmount(0);
            return;

        }



        damageable.setDamage(damage + 1);

        hammer.setItemMeta(damageable);

    }

}
