package me.kohle.JoinCommand;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class JoinCommand extends JavaPlugin {
	
	
	//Setting default list
	String[] list = {"hi", "version"};


	public void loadConfiguration(){

		//Cut out having to use this.getConfig all the time final makes sure it doesn't get changed
		final FileConfiguration config = this.getConfig();
		config.addDefault("Commands", Arrays.asList(list));
		config.addDefault("FirstCommand", Arrays.asList(list));
		config.options().copyDefaults(true);
		saveConfig(); 
	}
	

	public PluginManager pm;
	private JoinCommandPlayerListener playerListener = new JoinCommandPlayerListener(this);
	
	Logger log = Logger.getLogger("Minecraft");
	

	  public void onEnable() {
		  
		  loadConfiguration();
		  
		  pm = getServer().getPluginManager();
		  pluginInfo("enabled, with a configuration file!");
		  pm.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.High, this);
	  }
	  public void onDisable() {
	    pluginInfo("disabled.");
	    reloadConfig(); // Now reloads the config when disabled (/reload can be used now and not a server restart)
	    //The save on disable that was here prevented writing to the file when the server was active.
	  } 
	  public static void pluginInfo(String message) {
	    	String v = "1.1-b";
	    	System.out.println("[JoinCommand] Version " + v + " " + message);
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		  Player player = null;  //Sets player to null so if issued by Console it will not NPE
		  if (sender instanceof Player) {	// Sets the sender if it is a player
			player = (Player) sender;
		  }
		  	if(commandLabel.equalsIgnoreCase("hi")) {
		  		if(player == null) {  //if there is no player it will issue a msg in the console
		  			sender.sendMessage("[JoinCommand] Only players can issue this command.");
		  		}
		  		else{	//if it has been sent by a player the default msgs are used.
		  			player.sendMessage("Please Edit the Config file, replace");
		  			player.sendMessage("hi and hi2 with the command(s) you want");
		  		}
				return true;
			}
		  else if(commandLabel.equalsIgnoreCase("jc-reload") && sender.hasPermission("JoinCommand.reload")) {   //ingame command to reload config, can also be used in console
			  reloadConfig();
			  sender.sendMessage("JoinCommand config reloaded.");
			  return true;
		  }
		return false;
	  }
}