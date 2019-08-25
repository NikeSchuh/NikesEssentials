package me.Nike.HelloWorld.Commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCMD implements CommandExecutor {
	
	public HashMap<Player, UUID> tpa = new HashMap<Player, UUID>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
 
 if(sender instanceof Player) {
 
 
       Player p = (Player) sender;
       if(cmd.getName().equalsIgnoreCase("tpa")) {
         if(args.length == 1) {
         Player a = Bukkit.getServer().getPlayer(args[0]);
         if (a == null) {
        	 p.sendMessage(ChatColor.RED + "Dieser Spieler ist nicht Online!");
        	 return true;
         }
     if(a == p) {
      p.sendMessage("§cDu kannst dich nicht zu dir selbst teleportieren");
     return true;
 } else {
      a.sendMessage("§7Du hast eine Teleportationsanfrage von §6" + p.getName() + " §7erhalten.");
      p.sendMessage(" §7Du hast eine Teleportationsanfrage an §6" + a.getName() + " §7gesendet.");
      if(tpa.containsKey(a)) {
      tpa.remove(a);
      tpa.put(p, a.getUniqueId());
      }
   } 
     }else{
       p.sendMessage("§cFalsche Benutzung: /tpa (Spieler)");
       return true;
         }
     }
 
       if(cmd.getName().equalsIgnoreCase("tpaccept")) {
           if(args.length == 0) {
           if(tpa.containsKey(p)) {
              UUID a = tpa.get(p);
              Bukkit.getPlayer(a);
              Bukkit.getPlayer(a).teleport(p);
              p.sendMessage("§6" + Bukkit.getPlayer(a).getName() + " §7hat die Anfrage §aangenommen.");
               Bukkit.getPlayer(a).sendMessage("§7Du wurdest zu §6" + p.getName() + " §7teleportiert");
                tpa.remove(p);
          }else{
       p.sendMessage("§7Keine §aTeleportationsanfragen §7gefunden");
        return true;
        }
    }else{
   p.sendMessage("§cFalsche Benutzung! /tpaccept");
            }
         }
 
	  }
 return true;
   }
}  
