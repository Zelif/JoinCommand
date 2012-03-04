package me.kohle.JoinCommand;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinCommandPlayerListener implements Listener {
	JoinCommand plugin;
	public JoinCommandPlayerListener(JoinCommand instance) {
        	plugin = instance;
	}
	
	public void filterCommand(Player player , String listType){
		List<String> stuff = plugin.getConfig().getStringList(listType);
        	if(!(stuff == null)){
        		for (String s : stuff) {
        			player.performCommand((String) s); 
        		}
        	}
	}
    	@EventHandler(priority = EventPriority.HIGH)	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
        	String name = player.getName();
        	if(!player.hasPlayedBefore() && player.hasPermission("JoinCommand.FirstCommand")) {
        		String listType = "FirstCommand";   //Call to get FirstCommands if its the players first time logging on
        		filterCommand(player , listType);
        		System.out.println( "[JoinCommand] The player " + name + " logged in for first time. " );
        	}
        	if(player.hasPermission("JoinCommand.NormalCommand")) {
    			String listType = "Commands";
    			filterCommand(player , listType);
        	}

	}
}