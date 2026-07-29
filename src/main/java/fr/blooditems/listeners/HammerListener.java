package fr.blooditems.listeners;

import fr.blooditems.BloodItems;
import fr.blooditems.managers.HammerManager;
import fr.blooditems.managers.ItemManager;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class HammerListener implements Listener {


    private final ItemManager itemManager;
    private final HammerManager hammerManager;



    public HammerListener(BloodItems plugin) {

        this.itemManager = new ItemManager(plugin);
        this.hammerManager = new HammerManager();

    }




    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {


        Player player = event.getPlayer();


        ItemStack item = player.getInventory().getItemInMainHand();



        if (!itemManager.isHammer(item)) {
            return;
        }



        Block block = event.getBlock();



        // Empêche la casse normale
        event.setCancelled(true);



        // Trouve la face frappée
        BlockFace face = getBlockFace(player);



        hammerManager.breakBlocks(
                player,
                block,
                face,
                item
        );

    }





    private BlockFace getBlockFace(Player player) {


        float pitch = player.getLocation().getPitch();



        // Regarde vers le haut
        if (pitch < -45) {

            return BlockFace.DOWN;

        }



        // Regarde vers le bas
        if (pitch > 45) {

            return BlockFace.UP;

        }



        float yaw = player.getLocation().getYaw();

        yaw = (yaw % 360 + 360) % 360;



        if (yaw >= 45 && yaw < 135) {

            return BlockFace.WEST;

        }


        if (yaw >= 135 && yaw < 225) {

            return BlockFace.NORTH;

        }


        if (yaw >= 225 && yaw < 315) {

            return BlockFace.EAST;

        }



        return BlockFace.SOUTH;

    }





    @EventHandler
    public void onInteract(org.bukkit.event.player.PlayerInteractEvent event) {


        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();



        if (!itemManager.isHammer(item)) {
            return;
        }



        if (event.getAction().isRightClick()) {

            event.setCancelled(true);

        }

    }





    @EventHandler
    public void onDrop(org.bukkit.event.player.PlayerDropItemEvent event) {


        ItemStack item = event.getItemDrop().getItemStack();



        if (itemManager.isHammer(item)) {

            // réservé pour plus tard

        }

    }

}
