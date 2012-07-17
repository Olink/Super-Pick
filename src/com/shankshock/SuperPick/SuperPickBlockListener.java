package com.shankshock.SuperPick;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class SuperPickBlockListener implements Listener {
    private final SuperPick plugin;
    
    public SuperPickBlockListener(SuperPick instance) {
        plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockDamage( BlockDamageEvent event )
    {
    	Player p = event.getPlayer();
    	Material h = p.getItemInHand().getType();
    	Material b = event.getBlock().getType();
    	if( plugin.users.containsKey( p ) && plugin.users.get( p ) && b != Material.BEDROCK
    			&& (h == Material.DIAMOND_PICKAXE || h == Material.GOLD_PICKAXE ||
    					h == Material.IRON_PICKAXE || h==Material.STONE_PICKAXE ||
    					h == Material.WOOD_PICKAXE ) )
    		event.setInstaBreak( true );
    }
    
}

