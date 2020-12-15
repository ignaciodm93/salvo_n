package com.examplecodeoftheweb.salvo_n.dto;


import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Salvo;
import com.examplecodeoftheweb.salvo_n.model.Ship;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.SalvoRepository;
import com.examplecodeoftheweb.salvo_n.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.*;
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








    //PRUEBA
    public Map<String, Object> makeGameViewDTO(GamePlayer gp){

        GameDTO gameDTO = new GameDTO();
        ShipDTO shipDTO = new ShipDTO();
        SalvoDTO salvoDTO = new SalvoDTO();

        //Uso el GAME DTO tomando al gp pasado por parametro como base
        Map<String, Object> dto = gameDTO.makeGameDTO(gp.getGame());    //aca estoy citando el makeGameDTO que usa el de score
        //Map[] self = new Map[3];
        Map<String, Object> hits = new LinkedHashMap<>();
        /*Map<String, Object> self = new LinkedHashMap<>();*/

        //hits.put("self", new ArrayList<>());
        HitsDTO hitsDTO = new HitsDTO();
        /*hits.put("self", hitsDTO.makeHitsDTO(gp));
        hits.put("opponent", hitsDTO.makeHitsDTO(GamePlayer.getOpponent(gp).get()));*/


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

        //ORIGINALES





        if(gp.getGame().getGamePlayers().size() == 2){
            hits.put("self", hitsDTO.makeHitsDTO(gp));
            hits.put("opponent", hitsDTO.makeHitsDTO(GamePlayer.getOpponent(gp).get()));

        }else{

            hits.put("self", new ArrayList<>());
            hits.put("opponent", new ArrayList<>());
        }

        dto.put("hits", hits);
        dto.put("gameState", Util.stateGame(gp));






        return dto;
    }


    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    SalvoRepository salvoRepository;



    //PRUEBAS

    public String[]  makeHitsDTO(Salvo salvo, String loc){

        GamePlayer self = gamePlayerRepository.findById(salvoRepository.getOne(salvo.getId()).getGamePlayer().getId()).get();
        Set<Ship> selfShips = self.getShips();
        List<String> salvoLocations = salvo.getLocations();
        List<String> hits = new ArrayList<>();
        GamePlayer opponent = GamePlayer.getOpponent(self).get();
        List<Ship> opponentShips = opponent.getShips().stream().collect(Collectors.toList());

        Map<String, Object> dtoHits = new LinkedHashMap<>();

            for (Ship ship: opponentShips
                 ) {

                for (String shLoc: ship.getLocations()
                     ) {

                    if(loc == shLoc){
                        hits.add(loc);
                    }
                }
            }

        String[] arrayHits = hits.toArray(new String[0]);

        hits.toArray(arrayHits);

        return arrayHits;
    }



    public String[]  makeHitsDTO2(Salvo salvo){

        long highest = salvo.getTurn();


        GamePlayer self = gamePlayerRepository.findById(salvoRepository.getOne(salvo.getId()).getGamePlayer().getId()).get();
        Set<Ship> selfShips = self.getShips();
        List<String> salvoLocations = salvo.getLocations();
        List<String> hits = new ArrayList<>();
        GamePlayer opponent = GamePlayer.getOpponent(self).get();
        List<Ship> opponentShips = opponent.getShips().stream().collect(Collectors.toList());

        Map<String, Object> dtoHits = new LinkedHashMap<>();

        for (Ship ship: opponentShips
        ) {

            for (String shLoc: ship.getLocations()
            ) {


                for (String salvoLoc:salvo.getLocations()
                     ) {

                    if(salvoLoc == shLoc){
                        hits.add(salvoLoc);
                    }

                }
            }
        }

        String[] arrayHits = hits.toArray(new String[0]);

        hits.toArray(arrayHits);

        return arrayHits;
    }


    public String[]  makeHitsDTO3(GamePlayer gp){

      long lastTurn = gp.getSalvos().stream().map(s -> s.getTurn()).max(Long::compare).get();

      Salvo lastSalvo = gp.getSalvos().stream().filter(s -> s.getTurn() == lastTurn).findFirst().get();

        Set<Ship> selfShips = gp.getShips();
        List<String> salvoLocations = lastSalvo.getLocations();
        List<String> hits = new ArrayList<>();
        GamePlayer opponent = GamePlayer.getOpponent(gp).get();
        List<Ship> opponentShips = opponent.getShips().stream().collect(Collectors.toList());

        Map<String, Object> dtoHits = new LinkedHashMap<>();

        for (Ship ship: opponentShips
        ) {

            for (String shLoc: ship.getLocations()
            ) {


                for (String salvoLoc:lastSalvo.getLocations()
                ) {

                    if(salvoLoc == shLoc){
                        hits.add(salvoLoc);
                    }

                }
            }
        }

        String[] arrayHits = hits.toArray(new String[0]);

        hits.toArray(arrayHits);

        return arrayHits;


    }





































    //Asi el json no esta completo pero se me muestra bien el gameState
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //region ORIGINAL VIERNES
    /*public Map<String, Object> makeGameViewDTO(GamePlayer gp){

        GameDTO gameDTO = new GameDTO();
        ShipDTO shipDTO = new ShipDTO();
        SalvoDTO salvoDTO = new SalvoDTO();

        //Uso el GAME DTO tomando al gp pasado por parametro como base
        Map<String, Object> dto = gameDTO.makeGameDTO(gp.getGame());    //aca estoy citando el makeGameDTO que usa el de score

        Map<String, Object> hits = new LinkedHashMap<>();

        //hits.put("self", new ArrayList<>());
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

        //ORIGINALES
        dto.put("hits", hits);
        //dto.put("gameState", "PLACESHIPS");
        dto.put("gameState", "PLAY");










        return dto;

    }*/


    //endregion
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------





  /*  //prueba 11.12.20
    public static long getLastTurn(GamePlayer gamePlayer){

        List<Long> turns = gamePlayer.getSalvos().stream().map(s -> s.getTurn()).collect(Collectors.toList());

        return Collections.max(turns).longValue();

    }*/


    //prueba 11.12.20
    public static List<String> getHitLocations(Salvo salvo){

        GamePlayer self = salvo.getGamePlayer();

        List<String> myHits = new ArrayList<>();

        for (Ship ship: self.getShips()){

            for (String loc: salvo.getLocations()){

                if(ship.getLocations().contains(loc)){
                    myHits.add(loc);
                }
            }
        }

        return myHits;
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
