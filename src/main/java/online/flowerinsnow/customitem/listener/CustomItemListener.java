package online.flowerinsnow.customitem.listener;

import online.flowerinsnow.customitem.item.CustomItem;
import online.flowerinsnow.customitem.util.ItemStackUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CustomItemListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            CustomItem type = CustomItem.getTypeOfItem(e.getItem());
            if (type == null) {
                return;
            }

            e.setCancelled(true);
            switch (type) {
                case ASPECT_OF_THE_END -> onAOTE(e.getPlayer());
                case GRENADE -> onGrenade(e.getPlayer(), e.getItem());
            }
        }
    }

    private void onAOTE(Player player) {
        Location loc = player.getLocation().clone();
        Vector vec = loc.getDirection().multiply(3);
        Location newLocation = loc.add(vec.getX(), vec.getY(), vec.getZ());
        player.teleport(newLocation);
        player.playSound(newLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }

    private void onGrenade(Player player, ItemStack stack) {
        Location loc = player.getEyeLocation().clone();
        TNTPrimed tnt = player.getWorld().spawn(loc, TNTPrimed.class);
        tnt.setVelocity(loc.getDirection().multiply(3));
        tnt.setSource(player);
        ItemStackUtils.decrementItem(stack);
    }
}
