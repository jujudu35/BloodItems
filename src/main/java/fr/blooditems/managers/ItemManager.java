package fr.blooditems.managers;

import fr.blooditems.BloodItems;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemManager {

    private final NamespacedKey itemKey;


    public ItemManager(BloodItems plugin) {

        this.itemKey = new NamespacedKey(plugin, "blood_item");

    }



    public ItemStack createItem(String id) {

        return switch (id.toLowerCase()) {


            // 🧑‍🌾 ARMURE FARM (CUIR JAUNE)

            case "farm_helmet" ->
                    createLeather(
                            Material.LEATHER_HELMET,
                            "§aCasque de Farm",
                            id
                    );


            case "farm_chestplate" ->
                    createLeather(
                            Material.LEATHER_CHESTPLATE,
                            "§aPlastron de Farm",
                            id
                    );


            case "farm_leggings" ->
                    createLeather(
                            Material.LEATHER_LEGGINGS,
                            "§aPantalon de Farm",
                            id
                    );


            case "farm_boots" ->
                    createLeather(
                            Material.LEATHER_BOOTS,
                            "§aBottes de Farm",
                            id
                    );



            // 🩸 ARMURE BLOOD

            case "blood_helmet" ->
                    create(
                            Material.NETHERITE_HELMET,
                            "§cCasque Blood",
                            id
                    );


            case "blood_chestplate" ->
                    create(
                            Material.NETHERITE_CHESTPLATE,
                            "§cPlastron Blood",
                            id
                    );


            case "blood_leggings" ->
                    create(
                            Material.NETHERITE_LEGGINGS,
                            "§cPantalon Blood",
                            id
                    );


            case "blood_boots" ->
                    create(
                            Material.NETHERITE_BOOTS,
                            "§cBottes Blood",
                            id
                    );



            // 🔨 HAMMER

            case "hammer" ->
                    create(
                            Material.NETHERITE_PICKAXE,
                            "§6Hammer 3x3",
                            id
                    );


            default ->
                    new ItemStack(Material.BARRIER);

        };

    }




    private ItemStack create(Material material, String name, String id) {

        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();


        if (meta != null) {

            meta.setDisplayName(name);


            meta.setLore(List.of(
                    "§7Objet spécial Blood SMP",
                    "§8ID: " + id
            ));


            meta.getPersistentDataContainer().set(
                    itemKey,
                    PersistentDataType.STRING,
                    id
            );


            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


            item.setItemMeta(meta);

        }


        return item;

    }





    private ItemStack createLeather(Material material, String name, String id) {

        ItemStack item = new ItemStack(material);

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();


        if (meta != null) {


            meta.setDisplayName(name);


            // 🟨 Couleur jaune
            meta.setColor(Color.YELLOW);


            meta.setLore(List.of(
                    "§7Armure spéciale Blood SMP",
                    "§8ID: " + id
            ));


            meta.getPersistentDataContainer().set(
                    itemKey,
                    PersistentDataType.STRING,
                    id
            );


            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


            item.setItemMeta(meta);

        }


        return item;

    }





    public String getItemId(ItemStack item) {

        if (item == null) {
            return null;
        }


        if (!item.hasItemMeta()) {
            return null;
        }


        ItemMeta meta = item.getItemMeta();


        if (meta == null) {
            return null;
        }


        return meta.getPersistentDataContainer().get(
                itemKey,
                PersistentDataType.STRING
        );

    }





    public boolean isCustomItem(ItemStack item) {

        return getItemId(item) != null;

    }




    public boolean isHammer(ItemStack item) {

        String id = getItemId(item);

        return "hammer".equals(id);

    }





    public boolean isFarmArmor(ItemStack item) {

        String id = getItemId(item);

        if (id == null) {
            return false;
        }


        return id.startsWith("farm_");

    }





    public boolean isBloodArmor(ItemStack item) {

        String id = getItemId(item);

        if (id == null) {
            return false;
        }


        return id.startsWith("blood_");

    }

}
