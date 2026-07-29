package fr.blooditems.listeners;

import fr.blooditems.BloodItems;
import fr.blooditems.managers.ArmorManager;
import fr.blooditems.managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorListener implements Listener {

    private final ArmorManager armorManager;
    private final ItemManager itemManager;


    public ArmorListener(BloodItems plugin) {

        this.armorManager = new ArmorManager();
        this.itemManager = new ItemManager(plugin);

    }


    private void updateArmor(Player player) {

        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();


        armorManager.applyHelmetEffect(
                player,
                itemManager.getItemId(helmet)
        );


        armorManager.applyChestplateEffect(
                player,
                itemManager.getItemId(chestplate)
        );


        armorManager.applyLeggingsEffect(
                player,
                itemManager.getItemId(leggings)
        );


        armorManager.applyBootsEffect(
                player,
                itemManager.getItemId(boots)
        );

    }



    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Bukkit.getScheduler().runTaskLater(
                BloodItems.getInstance(),
                () -> updateArmor(event.getPlayer()),
                20L
        );

    }



 @EventHandler
    public void onArmorChange(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }


        Bukkit.getScheduler().runTaskLater(
                BloodItems.getInstance(),
                () -> updateArmor(player),
                2L
        );

    }

}
