package fr.blooditems.managers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

public class HammerManager {


    public void breakBlocks(Player player, Block center, ItemStack hammer) {


        // Récupère les blocs selon la direction du joueur
        List<Block> blocks = getBlocks3x3(player, center);



        // Casse le bloc ciblé
        breakBlock(player, center, hammer);



        // Casse les blocs autour
        for (Block block : blocks) {

            breakBlock(player, block, hammer);

        }



        // Durabilité comme une vraie pioche
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
     * Hammer 3x3 selon la direction
     */
    private List<Block> getBlocks3x3(Player player, Block center) {


        List<Block> blocks = new ArrayList<>();


        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();



        float yaw = player.getLocation().getYaw();

        yaw = (yaw % 360 + 360) % 360;



        // Nord / Sud
        if ((yaw >= 45 && yaw < 135) ||
            (yaw >= 225 && yaw < 315)) {



            for (int xx = -1; xx <= 1; xx++) {


                for (int yy = -1; yy <= 1; yy++) {



                    if (xx == 0 && yy == 0) {
                        continue;
                    }



                    blocks.add(
                            center.getWorld()
                                    .getBlockAt(
                                            x + xx,
                                            y + yy,
                                            z
                                    )
                    );

                }

            }



        } else {



            // Est / Ouest

            for (int zz = -1; zz <= 1; zz++) {


                for (int yy = -1; yy <= 1; yy++) {



                    if (zz == 0 && yy == 0) {
                        continue;
                    }



                    blocks.add(
                            center.getWorld()
                                    .getBlockAt(
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



        int unbreakingLevel = hammer.getEnchantmentLevel(
                Enchantment.UNBREAKING
        );



        // Solidité comme Minecraft vanilla
        if (unbreakingLevel > 0) {


            double chance = 1.0 / (unbreakingLevel + 1);



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
