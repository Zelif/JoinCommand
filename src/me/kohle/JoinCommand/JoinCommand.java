package me.kohle.JoinCommand;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class JoinCommand extends JavaPlugin {
	//Setting default list
	String[] list = {"jc", "version"}; //Changed all "hi" to jc hopefully no other plugin uses jc

	public void loadConfiguration(){
		//Cut out having to use this.getConfig all the time final makes sure it doesn't get changed.
		final FileConfiguration config = this.getConfig();
		config.addDefault("Commands", Arrays.asList(list));
		config.addDefault("FirstCommand", Arrays.asList(list));
		config.options().copyDefaults(true);
		saveConfig(); 
	}
	Logger log = Logger.getLogger("Minecraft");
	public void onEnable() {
		loadConfiguration();
		new JoinCommandPlayerListener(this);
		
	  }
	  public void onDisable() {
		  reloadConfig(); // Now reloads the config when disabled (/reload can be used now and not a server restart)
	    //The save on disable that was here prevented writing to the file when the server was active.
	  } 
	  
	  private void addCommand(String[] args , CommandSender sender) {
		  String listType = args[0]; //this gets the list name
		  List<String> loadedList = this.getConfig().getStringList(listType);
		  if(!(loadedList == null) && listType == "Commands" || listType == "FirstCommand"){ //checks config to see if it exists
			  String s = "";
			  for(int i = 1; i < args.length; i++){
				  s = s + args[i] + " ";
			  }
			  loadedList.add((String) s);
			  this.getConfig().addDefault(listType, loadedList); 
			  sender.sendMessage("Command " + (String) s + "added to " + args[0]);
			  saveConfig();
		  }
		  else{
			  sender.sendMessage("Incorrect list name, enter either Commands or FirstCommand");
		  }

	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		  Player player = null;  //Sets player to null so if issued by Console it will not NPE
		  if (sender instanceof Player) {	// Sets the sender if it is a player
			player = (Player) sender;
		  }
		  	if(commandLabel.equalsIgnoreCase("jc")) {
		  		if(player == null) {  //if there is no player it will issue a msg in the console
		  			sender.sendMessage("[JoinCommand] Only players can issue this command.");
		  		}
		  		else{	//if it has been sent by a player the default msgs are used.
		  			player.sendMessage("Please Edit the Config file, replace");
		  			player.sendMessage("'jc' and 'version' with the command(s) you want!");
		  		}
				return true;
			}
		  else if(commandLabel.equalsIgnoreCase("jc-reload") && sender.hasPermission("JoinCommand.reload")) {   //ingame command to reload config, can also be used in console
			  reloadConfig();
			  sender.sendMessage("JoinCommand configuration file reloaded.");
			  return true;
		  }
		  else if((commandLabel.equalsIgnoreCase("jc-addcommand") || commandLabel.equalsIgnoreCase("jc-ac")) && sender.hasPermission("JoinCommand.addcommand")) {   //ingame command to add items to config, can also be used in console  
			  if(!(args[0] == null || args[1] == null)){
				  addCommand(args , sender);
			  	return true;
			  }
			  else{
				  sender.sendMessage("Not enough parameters.");
			  }
		  }
		return false;
	  }

}