package online.flowerinsnow.customitem.command;

import online.flowerinsnow.customitem.item.CustomItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class CommandCustomItem implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Not a player.");
            return true;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "aote" -> player.getInventory().addItem(CustomItem.newItem(CustomItem.ASPECT_OF_THE_END));
                case "grenade" -> player.getInventory().addItem(CustomItem.newItem(CustomItem.GRENADE));
                default -> player.sendMessage("No item named " + args[0]);
            }
        } else {
            sender.sendMessage("Usage: /customitem <itemtype>");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList("aote", "grenade"));
            list.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            return list;
        }
        return new ArrayList<>();
    }
}
