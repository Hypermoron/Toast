package com.scrappymc.toast;

import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ToastListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (isToast(item)) {
            int hunger = player.getFoodLevel();
            player.setFoodLevel(hunger + 2);
        }
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        ItemStack item = furnace.getInventory().getSmelting();
        if (isToast(item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.GRINDSTONE) {
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                ItemStack item = event.getCurrentItem();
                if (isToast(item)) {
                    event.setCancelled(true);
                }
            } else {
                ItemStack item = event.getWhoClicked().getItemOnCursor();
                if (event.getSlotType() == InventoryType.SlotType.CRAFTING
                        && isToast(item)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().getType() == InventoryType.GRINDSTONE
                && (event.getInventorySlots().contains(0) || event.getInventorySlots().contains(1))) {
            for (Map.Entry<Integer,ItemStack> entry : event.getNewItems().entrySet()) {
                if (entry.getValue().getType() == Material.BREAD) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    private boolean isToast(ItemStack item) {
        return (item != null
                && item.getType() == Material.BREAD
                && item.getItemMeta().getEnchantLevel(Enchantment.LUCK) == 1);
    }

}
