package fr.blooditems.listeners;

import fr.blooditems.BloodItems;
import fr.blooditems.managers.HammerManager;
import fr.blooditems.managers.ItemManager;

import org.bukkit.block.Block;
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


        // Vérifie si c'est le Hammer
        if (!itemManager.isHammer(item)) {
            return;
        }


        Block block = event.getBlock();


        // Empêche la casse normale
        event.setCancelled(true);


        // Lance la casse 3x3
        hammerManager.breakBlocks(
                player,
                block,
                item
        );

    }

}



  @EventHandler
    public void onInteract(org.bukkit.event.player.PlayerInteractEvent event) {

        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();


        if (!itemManager.isHammer(item)) {
            return;
        }


        // Évite les actions inutiles avec le Hammer
        if (event.getAction().isRightClick()) {

            event.setCancelled(true);

        }

    }


    @EventHandler
    public void onDrop(org.bukkit.event.player.PlayerDropItemEvent event) {

        ItemStack item = event.getItemDrop().getItemStack();


        if (itemManager.isHammer(item)) {

            // Le Hammer reste un objet spécial,
            // aucune action supplémentaire pour le moment.

        }

    }

}
