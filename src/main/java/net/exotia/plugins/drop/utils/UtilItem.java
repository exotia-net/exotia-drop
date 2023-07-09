package net.exotia.plugins.drop.utils;

import io.th0rgal.oraxen.api.OraxenItems;
import net.exotia.plugins.drop.drop.rewards.Drop;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class UtilItem {
    public static ItemStack getItem(String id, String type, String displayName, List<String> lore, int amount) {
        ItemStack item = (type.equalsIgnoreCase("minecraft")) ? new ItemStack(Material.valueOf(id.toUpperCase())) : OraxenItems.getItemById(id).build();
        if (item == null) return new ItemStack(Material.STONE);
        ItemMeta meta = item.getItemMeta();
        if (amount > 0) item.setAmount(amount);
        if (meta == null || displayName == null || lore == null) return item;
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getItem(Drop drop, float multiplier) {
        return getItem(drop.getId(), drop.getItemType(), drop.getDisplayName(), drop.getLore(), (int) (drop.getDropAmount() * multiplier));
    }
}
