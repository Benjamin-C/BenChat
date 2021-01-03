package dev.benjaminc.benchat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BenChatLib {
    
    public static void sendMessageByPerm(String msg, Player sender, String perm) {
    	List<Player> sentTo = getPlayers(perm, (p) -> {
			if(sender instanceof Player) {
				if(((Player) sender).getUniqueId().equals(p.getUniqueId())) {
					return false;
				}
			}
			return true;
		});
		sendMessageToList(msg, sender, sentTo);
    }
    
    public static void sendMessageToList(String msg, Player sender, List<Player> list) {
    	for(Player p : list) {
			String sndr = (sender == null) ? "Server" : ((Player) sender).getName();
			int toCount = list.size() - 1;
			p.sendMessage("{" + sndr + " -> You" + ((toCount > 0) ? "+" + toCount : "") + "} " + msg);
		}
		if(list.size() == 0) {
			sender.sendMessage("Message not sent to anyone");
		} else {
			sender.sendMessage("{You ->" + stringifyPlayerList(list) + "} " + msg);
		}
    }
    
    protected static String stringifyPlayerList(List<Player> list) {
		if(list.size() == 0) {
			return "";
		} else if(list.size() == 1) {
			return list.get(0).getName();
		} else if(list.size() == 2) {
			return list.get(0).getName() + " and " + list.get(1).getName();
		} else {
			String sendermsg = list.get(0).getName();
			for(int i = 1; i < list.size() - 1; i++) {
				sendermsg += ", " + list.get(i).getName();
			}
			return sendermsg + " and " + list.get(list.size()-1).getName();
		}
	}
	protected static interface PlayerTestRunnable {
		abstract boolean test(Player p);
	}
	protected static List<Player> getPlayers(String perm, PlayerTestRunnable test) {
		List<Player> players = new ArrayList<Player>();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission(perm)) {
				if(test != null) {
					if(test.test(p)) {
						players.add(p);
					};
				} else {
					players.add(p);
				}
			}
		}
		return players;
	}
	protected static List<Player> getPlayers(String group) {
		return getPlayers(group, null);
	}
}
