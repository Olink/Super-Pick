package com.shankshock.SuperPick;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperPick extends JavaPlugin
{

	HashMap<Player, Boolean> users = new HashMap< Player, Boolean >();
	SuperPickBlockListener blockListener = new SuperPickBlockListener( this );
	SuperPickPlayerListener playerListener = new SuperPickPlayerListener( this );
	
	
	public boolean onCommand( CommandSender sender, Command command, String commandLabel, String[] args ){
		if( sender instanceof Player )
		{
			Player ply = (Player)sender;
			if( command.getName().toLowerCase().equals( "super" ) )
			{
				if( ply.hasPermission("SuperPick.super") )
				{
					Boolean status = users.remove( ply );
					if( status != null )
						status = !status;
					else
						status = true;
					users.put( ply, status );
					ply.sendMessage(ChatColor.AQUA + "Status of Super Pick: " + status );
				}
				else
				{
					ply.sendMessage( "You do not have access to this command!");
				}
				return true;
			}
		}
		return false;
	}
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents( playerListener, this);
    	pm.registerEvents( blockListener, this);
    }
    public void onDisable() {
    }
}
