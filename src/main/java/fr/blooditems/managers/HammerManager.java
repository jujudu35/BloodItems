package fr.blooditems.managers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HammerManager {


    public void breakBlocks(Player player, Block center, ItemStack hammer) {


        // Récupère les blocs autour avant de casser le centre
        List<Block> blocks = getBlocks3x3(center);



        // Casse le bloc ciblé
        breakBlock(player, center, hammer);



        // Casse les 8 blocs autour
        for (Block block : blocks) {

            breakBlock(player, block, hammer);

        }

        // Pas de dégâts manuels :
        // le Hammer utilise la durabilité normale de la pioche en netherite

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
     * Zone de minage 3x3x1
     *
     * XXX
     * XXX
     * XXX
     *
     */
    private List<Block> getBlocks3x3(Block center) {


        List<Block> blocks = new ArrayList<>();


        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();



        for (int xx = -1; xx <= 1; xx++) {


            for (int yy = -1; yy <= 1; yy++) {



                // Ignore le bloc central
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

}
