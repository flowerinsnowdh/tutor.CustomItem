package online.flowerinsnow.customitem.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ItemStackUtils {
    public static void decrementItem(ItemStack stack) {
        if (stack.getAmount() < 2) {
            stack.setType(Material.AIR);
        } else {
            stack.setAmount(stack.getAmount() - 1);
        }
    }

    private ItemStackUtils() {
    }
}
