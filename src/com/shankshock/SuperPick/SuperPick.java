package com.shankshock.SuperPick;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class SuperPick extends JavaPlugin
{

	HashMap<Player, Boolean> users = new HashMap< Player, Boolean >();
	SuperPickBlockListener blockListener = new SuperPickBlockListener( this );
	SuperPickPlayerListener playerListener = new SuperPickPlayerListener( this );
	public static PermissionHandler Permissions;
	
	
	public boolean onCommand( CommandSender sender, Command command, String commandLabel, String[] args ){
		if( sender instanceof Player )
		{
			Player ply = (Player)sender;
			if( command.getName().toLowerCase().equals( "super" ) )
			{
				if( Permissions.has( ply , "SuperPick.super") )
				{
					Boolean status = users.remove( ply );
					if( status != null )
						status = !status;
					else
						status = true;
					users.put( ply, status );
					ply.sendMessage( "Status of Super Pick:" + status );
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
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
		PluginManager pm = getServer().getPluginManager();
		setupPermissions();
    	pm.registerEvent( Event.Type.BLOCK_DAMAGE, blockListener, Priority.Low, this);
    	pm.registerEvent( Event.Type.PLAYER_QUIT, playerListener, Priority.Low, this);
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        System.out.println("Goodbye world!");
    }
    
    private void setupPermissions() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        Logger log = Logger.getLogger("Minecraft");
        if (this.Permissions == null) {
            if (test != null) {
                this.Permissions = ((Permissions)test).getHandler();
                System.out.println("started permissions");
            } else {
                log.info("Permission system not detected, defaulting to OP");
            }
        }
    }

}
