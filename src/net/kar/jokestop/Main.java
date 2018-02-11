package net.kar.jokestop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents((Listener)this, this);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equals("/stop")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.sendMessage("Stopping the server.");
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage("§7您所在的服务器已经关闭，已将您传送到备用服务器。");
                    p.kickPlayer("Server stopped.");
                    cancel();
                }
            }.runTaskTimer(this, 20L, 1L);
        }
    }

}
