package com.scrappymc.toast;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Toast extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        ItemStack toast = new ItemStack(Material.BREAD);
        ItemMeta toastMeta = toast.getItemMeta();
        toastMeta.setDisplayName(ChatColor.RESET + "Toast");
        toastMeta.addEnchant(Enchantment.LUCK, 1, true);
        toastMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        toast.setItemMeta(toastMeta);
        NamespacedKey toastKey = new NamespacedKey(this, "toast");
        FurnaceRecipe toastFurnaceRecipe = new FurnaceRecipe(toastKey, toast, Material.BREAD, 0.35F, 200);
        SmokingRecipe toastSmokerRecipe = new SmokingRecipe(toastKey, toast, Material.BREAD, 0.35F, 100);
        getServer().addRecipe(toastFurnaceRecipe);
        getServer().addRecipe(toastSmokerRecipe);
    }

}
