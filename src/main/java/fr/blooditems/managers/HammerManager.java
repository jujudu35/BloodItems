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


        // Récupère les blocs autour avant de casser
        List<Block> blocks = getBlocks3x3(center);



        // Casse le bloc ciblé
        breakBlock(player, center, hammer);



        // Casse les 8 blocs autour
        for (Block block : blocks) {

            breakBlock(player, block, hammer);

        }



        // Une seule perte de durabilité comme une pioche normale
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
     * Zone 3x3x1
     *
     * XXX
     * XXX
     * XXX
     */
    private List<Block> getBlocks3x3(Block center) {


        List<Block> blocks = new ArrayList<>();


        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();



        for (int xx = -1; xx <= 1; xx++) {


            for (int yy = -1; yy <= 1; yy++) {



                if (xx == 0 && yy == 0) {
                    continue;
                }



                Block block = center.getWorld()
                        .getBlockAt(
                                x + xx,
                                y + yy,
                                z
                        );



                blocks.add(block);

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



        // Fonctionnement vanilla de Solidité
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
