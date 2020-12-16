package com.examplecodeoftheweb.salvo_n.util;

import com.examplecodeoftheweb.salvo_n.dto.HitsDTO;
import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Ship;
import com.examplecodeoftheweb.salvo_n.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;

public class Util {




    public static Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    public static boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }



    public static List<String> getLocationsByType(String type, GamePlayer self){
            return self.getShips().size() == 0 ? new ArrayList<>() : self.getShips().stream().filter(ship -> ship.getType().equals(type)).findFirst().get().getLocations();
    }


    public static boolean changeState(GamePlayer self){

        //false = placeships
        //true = play

        Game game = self.getGame();

        boolean trigger = false;

        if(self.getShips().size() != 0 && self.getOpponent(self).get().getShips().size() != 0){
            trigger = true;
        }

        return trigger;
    }



    /*public static String setState(GamePlayer self){

        String state;
        Game game = self.getGame();
        GamePlayer opp = GamePlayer.getOpponent(self).get();
        Set<Ship> selfShips = self.getShips();
        Set<Ship> oppShips = opp.getShips();

        if(selfShips.isEmpty()){
            state = "PLACESHIPS";
        }
        if(oppShips.isEmpty()){
            state = "WAITINGFOROPP";
        }else{
            state = "PLAY";
        }

        return state;
    }*/

    public static String gameState(GamePlayer gamePlayer){
        if(gamePlayer.getShips().isEmpty()){
            return "PLACESHIPS";
        }
        if(gamePlayer.getGame().getGamePlayers().size() == 1){
            return "WAITINGFOROPP";
        }


        HitsDTO hitsDTO = new HitsDTO();

        List<Map<String, Object>> hits = hitsDTO.makeHitsDTO(gamePlayer);


        return "PLAY";
    }



    public static String stateGame(GamePlayer gamePlayer){

        if(gamePlayer.getGame().getGamePlayers().size()==2) {
            HitsDTO dtoHit= new HitsDTO();
            int mySelfImpact= dtoHit.makeDamage(gamePlayer);
            int opponentImpact= dtoHit.makeDamage(gamePlayer.getOpponent(gamePlayer).get());
            if(mySelfImpact==17 && opponentImpact==17){
                return  "TIDE";
            }else if(mySelfImpact==17 && (gamePlayer.getSalvos().size() == GamePlayer.getOpponent(gamePlayer).get().getSalvos().size() )){
                return "LOSE";
            }else if(opponentImpact==17 && (gamePlayer.getSalvos().size() == GamePlayer.getOpponent(gamePlayer).get().getSalvos().size() )){
                return "WON";
            }
        }
        if (gamePlayer.getShips().isEmpty()) {
            return "PLACESHIPS";
        }else if(gamePlayer.getGame().getGamePlayers().size()==1 || GamePlayer.getOpponent(gamePlayer).get().getShips().size() == 0){
            return "WAITINGFOROPP";
        }else if(gamePlayer.getSalvos().size() > GamePlayer.getOpponent(gamePlayer).get().getSalvos().size() ){
            return "WAIT";
        }
        else {
            return "PLAY";
        }
    }



}
