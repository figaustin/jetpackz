package com.etsuni.jetpackz;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersFlying {
    private List<Player> playersFlying = new ArrayList<>();
    private static PlayersFlying instance = new PlayersFlying();

    private PlayersFlying() {

    }

    public static PlayersFlying getInstance() {
        return instance;
    }

    public List<Player> getPlayersFlying() {
        return playersFlying;
    }

    public void setPlayersFlying(List<Player> playersFlying) {
        this.playersFlying = playersFlying;
    }


}
