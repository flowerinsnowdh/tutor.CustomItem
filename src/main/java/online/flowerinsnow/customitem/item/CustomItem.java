package online.flowerinsnow.customitem.item;

import cc.carm.lib.easyplugin.utils.ItemStackFactory;
import online.flowerinsnow.customitem.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public enum CustomItem {
    ASPECT_OF_THE_END(0, new ItemStackFactory(Material.DIAMOND_SWORD)
            .addEnchant(Enchantment.DURABILITY, 1, true)
            .setUnbreakable(true)
            .setDisplayName("&bAspect of The End")
            .addFlag(ItemFlag.HIDE_ENCHANTS)
            .addFlag(ItemFlag.HIDE_UNBREAKABLE)
            .addLore("&9右键物品向前瞬间移动一段距离")
            .toItemStack(),
            new NamespacedKey(Main.getInstance(), "custom_item"),
            "aote"
            ),
    GRENADE(1, new ItemStackFactory(Material.SNOWBALL)
            .addEnchant(Enchantment.DURABILITY, 1, true)
            .setDisplayName("&cGrenade")
            .addFlag(ItemFlag.HIDE_ENCHANTS)
            .addLore("&c消耗品！右键扔出一个TNT")
            .toItemStack(),
            new NamespacedKey(Main.getInstance(), "custom_item"),
            "grenade")
    ;
    public final int id;
    public final ItemStack default_;
    public final NamespacedKey key;
    public final String tagValue;

    CustomItem(int id, ItemStack default_, NamespacedKey key, String tagValue) {
        this.id = id;
        this.default_ = default_;
        this.key = key;
        this.tagValue = tagValue;
    }

    /**
     * 以默认的物品数据 新建一个物品
     *
     * @param type 自定义物品类型
     * @return 新建的物品堆
     */
    public static ItemStack newItem(CustomItem type) {
        ItemStack item = type.default_.clone();
        ItemMeta meta = item.getItemMeta();
        @SuppressWarnings("ConstantConditions")
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(type.key, PersistentDataType.STRING, type.tagValue);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 获取一个物品在本枚举类里的类型
     *
     * @param item 一个物品堆
     * @return 自定义物品类型，如果没有匹配则返回null
     */
    public static CustomItem getTypeOfItem(ItemStack item) {
        if (item == null) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return null;
        }

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        for (CustomItem value : values()) {
            if (value.tagValue.equals(pdc.get(value.key, PersistentDataType.STRING))) {
                return value;
            }
        }
        return null;
    }
}
