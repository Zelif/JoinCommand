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

public class JoinCommand extends JavaPlugin {
	
	public void loadConfiguration(){
		
	     String[] list = {"hi", "hi2"};
	     this.getConfig().addDefault("Commands", Arrays.asList(list));
	     //See "Creating you're defaults"
	     this.getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
	     
	     
	     //Save the config whenever you manipulate it
	     this.saveConfig(); 
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
	    this.saveConfig();
	  } 
	  public static void pluginInfo(String message) {
	    	String v = "1.0-l";
	    	System.out.println("[JoinCommand] Version " + v + " " + message);
	  }
	  
	  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		  Player player =  (Player)sender;
		  if(commandLabel.equalsIgnoreCase("hi")) {
			  player.sendMessage("This is a default command in JoinCommand!");
			  return true;
		  } else if(commandLabel.equalsIgnoreCase("hi2")) {
			  player.sendMessage("Another default command!");
			  return true;
		  }
		return false;
	  }
}