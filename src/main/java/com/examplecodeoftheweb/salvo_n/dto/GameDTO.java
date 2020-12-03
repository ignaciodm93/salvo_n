package com.examplecodeoftheweb.salvo_n.dto;


import com.examplecodeoftheweb.salvo_n.model.Game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameDTO {



    public GameDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
    }

    public GameDTO(Game game){

    }

    public Map<String, Object> makeGameDTO(Game game){
        GamePlayerDTO gamePlayerDto = new GamePlayerDTO();
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getCreated());
        dto.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(gamePlayer -> gamePlayerDto.makeGamePlayerDTO(gamePlayer))
                .collect(Collectors.toList())
        );

        //nuevo adding 1/12/20 MAL
        /*
        PlayerDTO playerDTO = new PlayerDTO();
        dto.put("scores", game.getGamePlayers().stream().map(gp -> gp.getPlayer()).map(player -> playerDTO.makeGameScoreDTO(player)).collect(Collectors.toList()));
        return dto;
        */

        dto.put("scores", game.getScores().stream().map(s -> ScoreDTO.makeScoreDTO(s)).collect(Collectors.toList()));
        return dto;



    }



    //prueba mia para mostrar todos los barcos de un mismo game (10)
    public Map<String, Object> makeGameInfo(Game game){
        ShipDTO shipDTO = new ShipDTO();
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("GAME ID", game.getId());
        dto.put("GAME DATE", game.getCreated());
        //dto.put("GamePlayers", game.getGamePlayers());
        //Este me tira loop -> dto.put("Ships", game.getGamePlayers().stream().map(s -> s.getShips()).collect(Collectors.toList()));
        //dto.put("Ships", shipDTO.makeShipDTO(Ships);
       dto.put("PLAYERS", game.getGamePlayers().stream().map(gp -> gp.getPlayer().getName()));

        //List<Set<Ship>> dto2 = game.getGamePlayers().stream().map(gp -> gp.getShips()).collect(Collectors.toList());
        //dto.put("Locations", game.getGamePlayers().stream().map(s -> s.getShips().stream().map(l -> l.getLocations()).collect(Collectors.toList())));


        dto.put("ALL SHIPS", game.getGamePlayers().stream().map(gamePlayer -> gamePlayer.getShips().stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList())));




        //dto.put("PLAYERS", game.getGamePlayers().stream().map(gp -> gp.getPlayer().getName(), s));

        return dto;
        //C2. Consigo las naves del game player (las que le pertenecen)
        //List<Ship> ships = gp.getShips().stream().collect(Collectors.toList());

        //Repocco las ships obtenidas y almacenadas en la lista "ships" y le aplico a cada una el dtoShips
        //Version 1, con este si necesito el punto C2.
        //dto.put("ships", ships.stream().map(ship -> shipDTO.makeShipDTO(ship)).collect(Collectors.toList()));

        //Version 2, con esto no necesito el punto C2.


    }





}
