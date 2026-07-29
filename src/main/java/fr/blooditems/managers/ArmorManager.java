package fr.blooditems.managers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmorManager {


    public void applyHelmetEffect(Player player, String id) {

        removeHelmetEffects(player);

        if (id == null) {
            return;
        }


        switch (id) {

            case "farm_helmet":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.NIGHT_VISION,
                                40,
                                0,
                                true,
                                false
                        )
                );

                break;


            case "blood_helmet":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.DOLPHINS_GRACE,
                                40,
                                0,
                                true,
                                false
                        )
                );

                break;
        }

    }



    public void applyChestplateEffect(Player player, String id) {

        removeChestplateEffects(player);

        if (id == null) {
            return;
        }


        switch (id) {

            case "farm_chestplate":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.FIRE_RESISTANCE,
                                40,
                                0,
                                true,
                                false
                        )
                );

                break;


            case "blood_chestplate":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.STRENGTH,
                                40,
                                1,
                                true,
                                false
                        )
                );

                break;
        }

    }



    public void applyLeggingsEffect(Player player, String id) {

        removeLeggingsEffects(player);

        if (id == null) {
            return;
        }


        switch (id) {

            case "farm_leggings":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.LUCK,
                                40,
                                0,
                                true,
                                false
                        )
                );

                break;


            case "blood_leggings":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.HASTE,
                                40,
                                1,
                                true,
                                false
                        )
                );

                break;
        }

    }



    public void applyBootsEffect(Player player, String id) {

        removeBootsEffects(player);

        if (id == null) {
            return;
        }


        switch (id) {

            case "farm_boots":
            case "blood_boots":

                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.SPEED,
                                40,
                                1,
                                true,
                                false
                        )
                );

                break;
        }

    }



    private void removeHelmetEffects(Player player) {

        player.removePotionEffect(
                PotionEffectType.NIGHT_VISION
        );

        player.removePotionEffect(
                PotionEffectType.DOLPHINS_GRACE
        );

    }



    private void removeChestplateEffects(Player player) {

        player.removePotionEffect(
                PotionEffectType.FIRE_RESISTANCE
        );

        player.removePotionEffect(
                PotionEffectType.STRENGTH
        );

    }



    private void removeLeggingsEffects(Player player) {

        player.removePotionEffect(
                PotionEffectType.LUCK
        );

        player.removePotionEffect(
                PotionEffectType.HASTE
        );

    }



    private void removeBootsEffects(Player player) {

        player.removePotionEffect(
                PotionEffectType.SPEED
        );

    }

}
