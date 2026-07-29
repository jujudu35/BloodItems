package fr.blooditems; 

import fr.blooditems.commands.BloodItemsCommand;
import fr.blooditems.listeners.ArmorListener;
import fr.blooditems.listeners.HammerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BloodItems extends JavaPlugin {

    private static BloodItems instance;

    public static BloodItems getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        BloodItemsCommand command = new BloodItemsCommand(this);

        getCommand("blooditems").setExecutor(command);
        getCommand("blooditems").setTabCompleter(command);

        getServer().getPluginManager().registerEvents(new ArmorListener(this), this);
        getServer().getPluginManager().registerEvents(new HammerListener(this), this);

        getLogger().info("====================================");
        getLogger().info("BloodItems activé !");
        getLogger().info("Version : " + getDescription().getVersion());
        getLogger().info("====================================");
    }

    @Override
    public void onDisable() {

        getLogger().info("BloodItems désactivé.");

    }

    public void reloadPlugin() {

        reloadConfig();

    }

}
