package fr.blooditems.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EffectTask {


    private final ArmorManager armorManager;
    private final ItemManager itemManager;


    public EffectTask(ArmorManager armorManager, ItemManager itemManager) {

        this.armorManager = armorManager;
        this.itemManager = itemManager;

    }



    public void start() {

        Bukkit.getScheduler().runTaskTimer(
                Bukkit.getPluginManager()
                        .getPlugin("BloodItems"),

                () -> {

                    for (Player player : Bukkit.getOnlinePlayers()) {

                        armorManager.applyHelmetEffect(
                                player,
                                itemManager.getItemId(
                                        player.getInventory().getHelmet()
                                )
                        );


                        armorManager.applyChestplateEffect(
                                player,
                                itemManager.getItemId(
                                        player.getInventory().getChestplate()
                                )
                        );


                        armorManager.applyLeggingsEffect(
                                player,
                                itemManager.getItemId(
                                        player.getInventory().getLeggings()
                                )
                        );


                        armorManager.applyBootsEffect(
                                player,
                                itemManager.getItemId(
                                        player.getInventory().getBoots()
                                )
                        );

                    }


                },

                0L,

                20L
        );

    }

}
