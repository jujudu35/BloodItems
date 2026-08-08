package fr.blooditems.managers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ArmorManager {

    /*
     * Effets actuellement donnés par l'armure.
     */
    private final Map<UUID, Set<PotionEffectType>> armorEffects =
            new HashMap<>();


    // =========================================================
    // HELMET
    // =========================================================

    public void applyHelmetEffect(Player player, String id) {

        removeArmorEffect(player, PotionEffectType.NIGHT_VISION);
        removeArmorEffect(player, PotionEffectType.DOLPHINS_GRACE);

        if (id == null) {
            return;
        }

        switch (id) {

            case "farm_helmet":

                giveArmorEffect(
                        player,
                        PotionEffectType.NIGHT_VISION,
                        0
                );

                break;


            case "blood_helmet":

                giveArmorEffect(
                        player,
                        PotionEffectType.DOLPHINS_GRACE,
                        0
                );

                break;
        }
    }


    // =========================================================
    // CHESTPLATE
    // =========================================================

    public void applyChestplateEffect(Player player, String id) {

        removeArmorEffect(player, PotionEffectType.FIRE_RESISTANCE);
        removeArmorEffect(player, PotionEffectType.STRENGTH);

        if (id == null) {
            return;
        }

        switch (id) {

            case "farm_chestplate":

                giveArmorEffect(
                        player,
                        PotionEffectType.FIRE_RESISTANCE,
                        0
                );

                break;


            case "blood_chestplate":

                giveArmorEffect(
                        player,
                        PotionEffectType.STRENGTH,
                        1
                );

                break;
        }
    }


    // =========================================================
    // LEGGINGS
    // =========================================================

    public void applyLeggingsEffect(Player player, String id) {

        removeArmorEffect(player, PotionEffectType.LUCK);
        removeArmorEffect(player, PotionEffectType.HASTE);

        if (id == null) {
            return;
        }

        switch (id) {

            case "farm_leggings":

                giveArmorEffect(
                        player,
                        PotionEffectType.LUCK,
                        0
                );

                break;


            case "blood_leggings":

                giveArmorEffect(
                        player,
                        PotionEffectType.HASTE,
                        1
                );

                break;
        }
    }


    // =========================================================
    // BOOTS
    // =========================================================

    public void applyBootsEffect(Player player, String id) {

        removeArmorEffect(player, PotionEffectType.SPEED);

        if (id == null) {
            return;
        }

        switch (id) {

            case "farm_boots":
            case "blood_boots":

                giveArmorEffect(
                        player,
                        PotionEffectType.SPEED,
                        1
                );

                break;
        }
    }


    // =========================================================
    // DONNER UN EFFET PAR L'ARMURE
    // =========================================================

    private void giveArmorEffect(
            Player player,
            PotionEffectType type,
            int amplifier
    ) {

        UUID uuid = player.getUniqueId();

        armorEffects
                .computeIfAbsent(
                        uuid,
                        k -> new HashSet<>()
                )
                .add(type);


        player.addPotionEffect(
                new PotionEffect(
                        type,
                        40,
                        amplifier,
                        true,
                        false
                )
        );
    }


    // =========================================================
    // RETIRER UNIQUEMENT L'EFFET DE L'ARMURE
    // =========================================================

    private void removeArmorEffect(
            Player player,
            PotionEffectType type
    ) {

        UUID uuid = player.getUniqueId();

        Set<PotionEffectType> effects =
                armorEffects.get(uuid);


        if (effects == null) {
            return;
        }


        if (!effects.contains(type)) {
            return;
        }


        player.removePotionEffect(type);

        effects.remove(type);


        if (effects.isEmpty()) {
            armorEffects.remove(uuid);
        }
    }


    // =========================================================
    // NETTOYAGE DU JOUEUR
    // =========================================================

    public void clearPlayer(Player player) {

        UUID uuid = player.getUniqueId();

        Set<PotionEffectType> effects =
                armorEffects.remove(uuid);


        if (effects == null) {
            return;
        }


        for (PotionEffectType type : effects) {

            player.removePotionEffect(type);
        }
    }
}
