package dev.benjaminc.benchat;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

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
			String sendperm = Keys.PERMISSION + "." + Keys.PERMISSION_QUICKCHAT + "." + Keys.PERMISSION_QUICKCHAT_SEND + "." + label;
			String getperm = Keys.PERMISSION + "." + Keys.PERMISSION_QUICKCHAT + "." + Keys.PERMISSION_QUICKCHAT_GET + "." + label;
			boolean send = (sender.hasPermission(sendperm) && sender instanceof Player);
			boolean get = (sender.hasPermission(getperm) && sender instanceof Player);
			boolean console = sender instanceof ConsoleCommandSender;
			if(send || get || console) {
				if(args[0].equals("$cmd")) {
					List<Player> sentTo = BenChatLib.getPlayers(label);
					
					if(sentTo.size() == 0) {
						sender.sendMessage("Message not sent to anyone");
					} else {
						sender.sendMessage("Sent message to " + BenChatLib.stringifyPlayerList(sentTo) + ".");
					}
					return true;
				}
			}
			if(send || console) {
				String msg = args[0];
				for(int i = 1; i < args.length; i++) {
					msg += " " + args[i];
				}

				BenChatLib.sendMessageByPerm(msg, (sender instanceof Player) ? (Player) sender : null, getperm);
			} else {
				if(!sender.hasPermission(sendperm)) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!" + ChatColor.RESET);
				} else {
					sender.sendMessage("You can't use this command in this interface");
				}
			}
		} else if(label == Keys.COMMAND_QUICKCHAT) {
			sender.sendMessage("You need to use a quickchat alias to send a message");
		}
		
		return false;
	}
}
