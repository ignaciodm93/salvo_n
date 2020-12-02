package com.examplecodeoftheweb.salvo_n.dto;


import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.Player;
import com.examplecodeoftheweb.salvo_n.model.Score;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class PlayerDTO {

    //Player playerDao = new Player();

    //Constructor
    public PlayerDTO() {Map<String, Object> dto = new LinkedHashMap<>();
    }

    public Map<String, Object> makePlayerDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("name", player.getName());
        dto.put("email", player.getEmail());

        return dto;
    }


    //Nuevo  metodo U5 para leaderboard
    public Map<String, Object> makePlayerScoreDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        Map<String, Object> score = new LinkedHashMap<>();

        dto.put("id", player.getId());
        dto.put("email", player.getEmail());
        dto.put("score", score);
            score.put("total", player.getTotalScore());
            score.put("won", player.getWinScore());
            score.put("lost", player.getLostScore());
            score.put("tied", player.getTiedScore());
            score.put("finishDate", player.getScores().stream().map(p -> p.getFinishDate()).findFirst());
        return dto;
    }


    //Nuevo metodo U5 para games
    public Map<String, Object> makeGameScoreDTO(Player player){

        Map<String, Object> dto = new LinkedHashMap<>();
        Map<String, Object> score = new LinkedHashMap<>();

        dto.put("player", player.getId());
        dto.put("score", player.getTotalScore());
        dto.put("finishDate", player.getScores().stream().map(p -> p.getFinishDate()).findFirst());
        return dto;

    }

}
