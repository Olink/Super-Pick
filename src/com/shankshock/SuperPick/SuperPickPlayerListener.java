package com.shankshock.SuperPick;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.shankshock.SuperPick.SuperPick;

public class SuperPickPlayerListener extends PlayerListener {
    private final SuperPick plugin;
    
    public SuperPickPlayerListener(SuperPick instance) {
        plugin = instance;
    }
    
    public void onPlayerQuit( PlayerQuitEvent event )
    {
    	Player p = event.getPlayer();
    	plugin.users.remove( p );
    }
    
}

