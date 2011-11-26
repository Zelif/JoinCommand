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

	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		//If the player is not present when the player joins:
		if(!(player == null)) {
			System.out.println("[JoinCommand] A non-player joined the server... what would do that?!");
		}
		@SuppressWarnings("unchecked")
		List<String> stuff = plugin.getConfig().getList("Commands", null);
		for (String s : stuff) {
		    player.performCommand(s);
		}
		}
	}