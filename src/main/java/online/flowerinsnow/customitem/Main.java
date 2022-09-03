package online.flowerinsnow.customitem;

import online.flowerinsnow.customitem.command.CommandCustomItem;
import online.flowerinsnow.customitem.listener.CustomItemListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new CustomItemListener(), this);
        registerCommand("customitem", new CommandCustomItem());
    }

    public static Main getInstance() {
        return instance;
    }

    private void registerCommand(String name, TabExecutor executor) {
        PluginCommand command = getCommand(name);
        if (command != null) {
            command.setExecutor(executor);
            command.setTabCompleter(executor);
        }
    }
}
