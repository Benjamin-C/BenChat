package dev.benjaminc.benchat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BenChatCommand implements CommandExecutor {

	BenChatMain plugin;
	
	public BenChatCommand(BenChatMain p) {
		plugin = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.length() == 1) {
			if(args.length < 1) {
				sender.sendMessage("You must specify a message");
				return true;
			}
			String msg = args[0];
			for(int i = 1; i < args.length; i++) {
				msg += " " + args[i];
			}
			String sentTo = "";
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.hasPermission(Keys.PERMISSION + "." + Keys.PERMISSION_QUICKMSG + "." + label)) {
					p.sendMessage(label + ":>" + msg + "<");
					if(sentTo.length() != 0) {
						sentTo += ", ";
					}
					sentTo += p.getName();
				}
			}
			if(sentTo.length() > 0) {
				String sendermsg = "Sent message to " + sentTo.substring(0, sentTo.lastIndexOf(',') + 1) + " and " + sentTo.substring(sentTo.lastIndexOf(',')) + ".";
				sender.sendMessage(sendermsg);
			}
		} else {
			sender.sendMessage("Message!");
		}
		
		return false;
	}
}
