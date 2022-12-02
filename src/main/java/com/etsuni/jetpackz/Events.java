package com.etsuni.jetpackz;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.etsuni.jetpackz.Jetpackz.plugin;

public class Events implements Listener {


    @EventHandler
    public void onHandSwitch(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack chestPlate = player.getInventory().getChestplate() == null ? null : player.getInventory().getChestplate();

        if(chestPlate != null && Objects.requireNonNull(chestPlate.getItemMeta()).hasEnchant(CustomEnchant.JETPACK)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10, 2, false, false, false));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAnvilClick(PrepareAnvilEvent event){

        AnvilInventory anvilInventory = event.getInventory();

        List<Material> armorList = Arrays.asList(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.GOLDEN_CHESTPLATE,
                Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE);
        boolean foundArmor = false;
        boolean foundRocket = false;
        ItemStack item = null;
         for(ItemStack itemStack : anvilInventory.getContents()) {
             if(itemStack == null) {
                 continue;
             }
             if(armorList.contains(itemStack.getType())) {
                 foundArmor = true;
                 item = new ItemStack(itemStack);
             }
             if(itemStack.getType().equals(Material.FIREWORK_ROCKET)) {
                 foundRocket = true;
             }
         }

         if(foundArmor && foundRocket) {
             ItemMeta meta = item.getItemMeta();
             List<String> lore = new ArrayList<>();
             lore.add(ChatColor.GRAY + "Jetpack");
             if(meta.hasLore()) {
                 for(String l : meta.getLore()) {
                     lore.add(l);
                 }
             }
             meta.setLore(lore);
             meta.addEnchant(CustomEnchant.JETPACK, 1, false);
             item.setItemMeta(meta);
             event.setResult(item);
         }

         //TODO MAKE COST CONFIGURABLE
         int cost = 10;
         plugin.getServer().getScheduler().runTask(plugin, () -> anvilInventory.setRepairCost(cost));
    }
}
