package de.bnder.rainbowCraft.gamePlay.commands.admin;

/*
 * Copyright (C) 2019 Jan Brinkmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import de.bnder.rainbowCraft.main.Main;
import de.bnder.rainbowCraft.gamePlay.gameUtils.GameUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;

public class cmdCreateCamera implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()) {
                if (args.length == 1) {
                    String map = args[0];
                    GameUtils gameUtils = new GameUtils(map);
                    if (gameUtils.gameExists()) {
                        Location pLoc = p.getLocation();
                        ArrayList<String> cameraList = new ArrayList<String>();
                        cameraList.addAll(Main.gamesC.getStringList("Game" + "." + map + ".cameraList"));
                        String spawn = String.valueOf(cameraList.size());
                        cameraList.add(spawn);
                        Main.gamesC.set("Game" + "." + map + ".cameraList", cameraList);
                        Main.gamesC.set("Game" + "." + map + ".camera" + "." + spawn + ".world", pLoc.getWorld().getUID().toString());
                        Main.gamesC.set("Game" + "." + map + ".camera" + "." + spawn + ".x", pLoc.getX());
                        Main.gamesC.set("Game" + "." + map + ".camera" + "." + spawn + ".y", pLoc.getY());
                        Main.gamesC.set("Game" + "." + map + ".camera" + "." + spawn + ".z", pLoc.getZ());
                        Main.gamesC.set("Game" + "." + map + ".camera" + "." + spawn + ".yaw", pLoc.getYaw());
                        Main.gamesC.set("Game" + "." + map + ".camera" + "." + spawn + ".pitch", pLoc.getPitch());
                        try {
                            Main.gamesC.save(Main.gamesFile);
                            p.sendMessage(Main.prefix + " §2Kamera §a" + spawn + " §2erstellt.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }
}
