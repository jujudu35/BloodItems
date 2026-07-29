package fr.blooditems.commands;

import fr.blooditems.BloodItems;
import fr.blooditems.managers.ItemManager;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BloodItemsCommand implements CommandExecutor, TabCompleter {

    private final BloodItems plugin;
    private final ItemManager itemManager;


    public BloodItemsCommand(BloodItems plugin) {

        this.plugin = plugin;
        this.itemManager = new ItemManager(plugin);

    }


    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {


        if (args.length == 0) {

            sender.sendMessage("§c§lBloodItems");
            sender.sendMessage("§7/blooditems give <joueur> <item>");
            sender.sendMessage("§7/blooditems reload");

            return true;

        }


        // Reload
        if (args[0].equalsIgnoreCase("reload")) {


            if (!sender.hasPermission("blooditems.reload")) {

                sender.sendMessage("§cTu n'as pas la permission.");

                return true;
            }


            plugin.reloadPlugin();

            sender.sendMessage("§aConfiguration rechargée.");

            return true;

        }



        // Give
        if (args[0].equalsIgnoreCase("give")) {


            if (!sender.hasPermission("blooditems.give")) {

                sender.sendMessage("§cTu n'as pas la permission.");

                return true;

            }


            if (args.length < 3) {

                sender.sendMessage(
                        "§c/blooditems give <joueur> <item>"
                );

                return true;

            }


            Player target = Bukkit.getPlayer(args[1]);


            if (target == null) {

                sender.sendMessage("§cJoueur introuvable.");

                return true;

            }


            ItemStack item =
                    itemManager.createItem(args[2]);


            target.getInventory().addItem(item);


            sender.sendMessage(
                    "§aObjet donné à §e" + target.getName()
            );


            return true;

        }


        return true;
    }



    @Override
    public List<String> onTabComplete(CommandSender sender,
                                      Command command,
                                      String alias,
                                      String[] args) {


        List<String> list = new ArrayList<>();


        if (args.length == 1) {

            list.add("give");
            list.add("reload");

        }


        if (args.length == 3) {

            list.add("farm_helmet");
            list.add("farm_chestplate");
            list.add("farm_leggings");
            list.add("farm_boots");

            list.add("blood_helmet");
            list.add("blood_chestplate");
            list.add("blood_leggings");
            list.add("blood_boots");

            list.add("hammer");

        }


        return list;

    }

}
