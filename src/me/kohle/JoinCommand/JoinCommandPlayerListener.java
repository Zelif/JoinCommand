package me.kohle.JoinCommand;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;



@SuppressWarnings("unused")
public class JoinCommandPlayerListener extends PlayerListener {

	public JoinCommand plugin;
  	

    

	public JoinCommandPlayerListener(JoinCommand instance) {
		plugin = instance;
	}
	
	public void filterCommand(Player player , String listType){
		List<Object> stuff = plugin.getConfig().getList(listType);
        if(!(stuff == null)){
        	for (Object s : stuff) {
        		player.performCommand((String) s); 
        		//Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), (String) s);
        		//Thinking about making this command done by the server not the player as new people might not have any permissions.
        		//Only for first join tho
        	}
        }
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
        String name = player.getName();
        
		if(player == null) { 	//If the player is not present when the player joins
			System.out.println("[JoinCommand] A non-player joined the server... what would do that?!");
		}

        if(!player.hasPlayedBefore()){
        	String listType = "FirstComamnd";   //Call to get FirstCommands if its the players first time logging on
            filterCommand(player , listType);
        	System.out
        	.println( "[JoinCommand] " + name + ": logged in for first time. " );
        }

    	String listType = "Commands";
        filterCommand(player , listType);
		}
}