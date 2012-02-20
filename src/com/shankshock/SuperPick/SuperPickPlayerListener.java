package com.shankshock.SuperPick;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SuperPickPlayerListener implements Listener {
    private final SuperPick plugin;
    
    public SuperPickPlayerListener(SuperPick instance) {
        plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit( PlayerQuitEvent event )
    {
    	Player p = event.getPlayer();
    	plugin.users.remove( p );
    }
    
}

