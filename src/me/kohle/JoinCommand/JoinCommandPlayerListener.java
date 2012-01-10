package me.kohle.JoinCommand;


import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;



public class JoinCommandPlayerListener extends PlayerListener {

	public JoinCommand plugin;
  	

    

	public JoinCommandPlayerListener(JoinCommand instance) {
		plugin = instance;
	}
	public void hasPlayedBefore(){
		
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
        String name = player.getName();
        
        if(!player.hasPlayedBefore()){
            //Call to get FirstCommands if the person has no dat file (first time logging on)
            List<Object> stuff2 = plugin.getConfig().getList("FirstCommand");
            player.sendMessage("Welcome");
        for (Object s : stuff2) {
        	player.performCommand((String) s); 
        	System.out
        	.println( "[JoinCommand] " + name + ": logged in for first time. " );
        	}
        }

		//If the player is not present when the player joins:
		if(player == null) {
			System.out.println("[JoinCommand] A non-player joined the server... what would do that?!");
		}
		List<Object> stuff = plugin.getConfig().getList("Commands");
		if(!(stuff == null)){
			for (Object s  : stuff) {
				player.performCommand((String) s);
			}
		}
	}
}