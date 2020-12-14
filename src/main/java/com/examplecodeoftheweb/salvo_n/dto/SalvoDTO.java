package com.examplecodeoftheweb.salvo_n.dto;

import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Salvo;
import com.examplecodeoftheweb.salvo_n.model.Ship;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SalvoDTO {

    public SalvoDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
    }
    //ver si tengo que instanciarlo en el controlador para citarlo en el dto como this.ship

    //prueba
    public Map<String, Object> makeSalvoDTO(Salvo salvo){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", salvo.getTurn());

        //Guarda aca que si le tiro gamePlayer me tira error de loop
        dto.put("player", salvo.getGamePlayer().getPlayer().getId());
        dto.put("locations", salvo.getLocations());


        return dto;
    }






    //Prueba 11.12.20
    public Map<String, Object> getHitsDto(Salvo salvoShoot){


        Map<String, Object> dtoHits = new LinkedHashMap<>();

        Map<String, Object> shipHits = new LinkedHashMap<>();

        GamePlayer me = salvoShoot.getGamePlayer();

        Set<Ship> enemyShipList = GamePlayer.getOpponent(me).get().getShips();
        Set<Salvo> mySalvos = me.getSalvos();

        dtoHits.put("Hits", enemyShipList.stream().filter(ship -> ship.getLocations().equals(enemyShipList.stream().map(s -> s.getLocations()))));


        return dtoHits;

    }






















}
