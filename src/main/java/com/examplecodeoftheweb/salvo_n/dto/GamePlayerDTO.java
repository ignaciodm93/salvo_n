package com.examplecodeoftheweb.salvo_n.dto;


import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Salvo;
import com.examplecodeoftheweb.salvo_n.model.Ship;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GamePlayerDTO {


    public GamePlayerDTO() {Map<String, Object> dto = new LinkedHashMap<>();


    }

    public Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer){
        PlayerDTO playerDto = new PlayerDTO();
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", playerDto.makePlayerDTO(gamePlayer.getPlayer()));

        return dto;
    }


    //Para el RequestMapping de game_view (MISMO METODO PERO PRIMER INTENTO)
    public Map<String, Object> makeGameByGPidDTO(GamePlayer gp){

        GameDTO gameDTO = new GameDTO();
        ShipDTO shipDTO = new ShipDTO();

        //Uso el GAME DTO tomando al gp pasado por parametro como base
        Map<String, Object> dto = gameDTO.makeGameDTO(gp.getGame());

        //C2. Consigo las naves del game player (las que le pertenecen)
        //List<Ship> ships = gp.getShips().stream().collect(Collectors.toList());

        //Repocco las ships obtenidas y almacenadas en la lista "ships" y le aplico a cada una el dtoShips
        //Version 1, con este si necesito el punto C2.
        //dto.put("ships", ships.stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        //Version 2, con esto no necesito el punto C2.
        dto.put("ships", gp.getShips().stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        return dto;
    }




    public Map<String, Object> makeGameViewDTO(GamePlayer gp){

        GameDTO gameDTO = new GameDTO();
        ShipDTO shipDTO = new ShipDTO();
        SalvoDTO salvoDTO = new SalvoDTO();

        //Uso el GAME DTO tomando al gp pasado por parametro como base
        Map<String, Object> dto = gameDTO.makeGameDTO(gp.getGame());    //aca estoy citando el makeGameDTO que usa el de score

        Map<String, Object> hits = new LinkedHashMap<>();

        hits.put("self", new ArrayList<>());
        hits.put("opponent", new ArrayList<>());


        //C2. Consigo las naves del game player (las que le pertenecen)
        //List<Ship> ships = gp.getShips().stream().collect(Collectors.toList());

        //Repocco las ships obtenidas y almacenadas en la lista "ships" y le aplico a cada una el dtoShips
        //Version 1, con este si necesito el punto C2.
        //dto.put("ships", ships.stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        //Version 2, con esto no necesito el punto C2.
        dto.put("ships", gp.getShips().stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        //Prueba
        //dto.put("salvoes", gp.getGame().getGamePlayers().stream().map(gamePlayer -> gamePlayer.getSalvos().stream().map(s -> salvoDTO.makeSalvoDTO(s)).collect(Collectors.toList())).collect(Collectors.toList()));

        dto.put("salvoes", gp.getGame().getGamePlayers().stream().flatMap(gamePlayer -> gamePlayer.getSalvos().stream()
                .map(salvo -> salvoDTO.makeSalvoDTO(salvo)))
                .collect(Collectors.toList()));

        dto.put("hits", hits);
        //dto.put("gameState", "PLACESHIPS");
        dto.put("gameState", "PLAY");

        return dto;
    }




    //Original
    /*
    *  public Map<String, Object> makeGameViewDTO(GamePlayer gp){

        GameDTO gameDTO = new GameDTO();
        ShipDTO shipDTO = new ShipDTO();

        //Uso el GAME DTO tomando al gp pasado por parametro como base
        Map<String, Object> dto = gameDTO.makeGameDTO(gp.getGame());

        //C2. Consigo las naves del game player (las que le pertenecen)
        //List<Ship> ships = gp.getShips().stream().collect(Collectors.toList());

        //Repocco las ships obtenidas y almacenadas en la lista "ships" y le aplico a cada una el dtoShips
        //Version 1, con este si necesito el punto C2.
        //dto.put("ships", ships.stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        //Version 2, con esto no necesito el punto C2.
        dto.put("ships", gp.getShips().stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        return dto;
    }
    * */





}
