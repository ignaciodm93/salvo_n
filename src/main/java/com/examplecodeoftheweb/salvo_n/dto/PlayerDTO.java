package com.examplecodeoftheweb.salvo_n.dto;


import com.examplecodeoftheweb.salvo_n.model.Player;

import java.util.LinkedHashMap;
import java.util.Map;


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

}
