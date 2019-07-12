package de.bnder.rainbowCraft.gamePlay.gameBase.lobby;

//Made by EnderLPs | bnder.de
//https://bnder.de
//©Jan Brinkmann (EnderLPs)

import de.bnder.rainbowCraft.gamePlay.gameUtils.GameUtils;
import de.bnder.rainbowCraft.gamePlay.gameUtils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoPlace implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        PlayerUtils playerUtils = new PlayerUtils(p);
        if (playerUtils.isInGame()) {
            GameUtils gameUtils = new GameUtils(playerUtils.getGame());
            if (gameUtils.isInLobby(p)) {
                e.setCancelled(true);
            }
        }
    }

}