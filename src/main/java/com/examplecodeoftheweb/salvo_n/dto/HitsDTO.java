package com.examplecodeoftheweb.salvo_n.dto;

import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Salvo;
import com.examplecodeoftheweb.salvo_n.model.Ship;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.util.Util;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class HitsDTO {

    @Autowired
    GamePlayerRepository gamePlayerRepository;


    private Map<String, Object> dto;

    public HitsDTO() {
        this.dto = new LinkedHashMap<>();

    }

    //Funciona
    public List<Map<String, Object>> makeHitsDTO(GamePlayer gamePlayer){

        List<Map<String, Object>> hits = new ArrayList<>();

        List<String>carrierLocations = Util.getLocationsByType("carrier", gamePlayer);
        List<String>battleshipLocations = Util.getLocationsByType("battleship", gamePlayer);
        List<String>submarineLocations = Util.getLocationsByType("submarine", gamePlayer);
        List<String>destroyerLocations = Util.getLocationsByType("destroyer", gamePlayer);
        List<String>patrolBoatLocations = Util.getLocationsByType("patrolboat", gamePlayer);



        long carrierDamage = 0;
        long battleShipDamage = 0;
        long submarineDamage = 0;
        long destroyerDamage = 0;
        long patrolboatDamage = 0;

        long turn = 0;

        for(Salvo salvo: GamePlayer.getOpponent(gamePlayer).get().getSalvos()){


            Map<String, Object> damagesPerTurn = new LinkedHashMap<>();
            List<String>hitCellsList = new ArrayList<>();
            Map<String, Object>hitsMapPerTurn = new LinkedHashMap<>();



            long carrierHits = 0;
            long battleShipHits = 0;
            long submarineHits = 0;
            long destroyerHits = 0;
            long patrolboatHits = 0;


            long missed = 5;


            for(String location: salvo.getLocations()){


                if (carrierLocations.contains(location)){
                    hitCellsList.add(location);

                    carrierDamage += 1;
                    carrierHits += 1;
                    missed--;




                }



                if (battleshipLocations.contains(location)){
                    hitCellsList.add(location);

                    battleShipDamage += 1;
                    battleShipHits += 1;
                    missed--;
                }



                if (submarineLocations.contains(location)){
                    hitCellsList.add(location);

                    submarineDamage += 1;
                    submarineHits += 1;
                    missed--;
                }



                if (destroyerLocations.contains(location)){
                    hitCellsList.add(location);

                    destroyerDamage += 1;
                    destroyerHits += 1;
                    missed--;
                }



                if (patrolBoatLocations.contains(location)){
                    hitCellsList.add(location);

                    patrolboatDamage += 1;
                    patrolboatHits += 1;
                    missed--;
                }


                damagesPerTurn.put("carrierHits", carrierHits);
                damagesPerTurn.put("battleshipHits", battleShipHits);
                damagesPerTurn.put("submarineHits", submarineHits);
                damagesPerTurn.put("destroyerHits", destroyerHits);
                damagesPerTurn.put("patrolboatHits", patrolboatHits);


                hitsMapPerTurn.put("turn", salvo.getTurn());


                //Damages
                damagesPerTurn.put("carrier", carrierDamage);
                damagesPerTurn.put("battleship", battleShipDamage);
                damagesPerTurn.put("submarine", submarineDamage);
                damagesPerTurn.put("destroyer", destroyerDamage);
                damagesPerTurn.put("patrolboat", patrolboatDamage);

            }


            hitsMapPerTurn.put("missed", missed);


            //Maps
            hitsMapPerTurn.put("hitLocations", hitCellsList);
            hitsMapPerTurn.put("damages", damagesPerTurn);


            //Lista de maps
            hits.add(hitsMapPerTurn);

        }

        return hits;
    }










    public int makeDamage(GamePlayer gamePlayer){
        List<String>carrierLocations= new ArrayList<String>();
        List<String>battleshipLocations = new ArrayList<>();
        List<String>submarineLocations = new ArrayList<>();
        List<String>destroyerLocations = new ArrayList<>();
        List<String>patrolBoatLocations = new ArrayList<>();

        carrierLocations= Util.getLocationsByType("carrier", gamePlayer);
        battleshipLocations=Util.getLocationsByType("battleship", gamePlayer);
        submarineLocations=Util.getLocationsByType("submarine", gamePlayer);
        destroyerLocations=Util.getLocationsByType("destroyer",gamePlayer);
        patrolBoatLocations=Util.getLocationsByType("patrolboat", gamePlayer);

        int countImpact=0;

        for(Salvo salvo: GamePlayer.getOpponent(gamePlayer).get().getSalvos()){

            for(String location: salvo.getLocations()){
                if (carrierLocations.contains(location)){
                    countImpact++;
                }
                if (battleshipLocations.contains(location)){
                    countImpact++;
                }
                if (submarineLocations.contains(location)){
                    countImpact++;
                }
                if (destroyerLocations.contains(location)){
                    countImpact++;
                }
                if (patrolBoatLocations.contains(location)){
                    countImpact++;
                }
            }

        }
        return countImpact++;

    }
















    //Prueba boolean
    public boolean getHitDTO(Salvo salvo){
        Map<String, Object> dto = new LinkedHashMap<>();

        boolean hitted = false;

        GamePlayer me = salvo.getGamePlayer();

        Set<Ship> enemyShipList = GamePlayer.getOpponent(me).get().getShips();
        Set<Salvo> mySalvos = me.getSalvos();

        dto.put("Hits", enemyShipList.stream().filter(ship -> ship.getLocations().equals(mySalvos.stream().map(s -> s.getLocations()))));

        if(!dto.isEmpty()){
            hitted = true;
        }

        return hitted;
    }

    //Prueba full
    public Map<String,Object> getDetailedHitsDTO(Salvo salvo){
        Map<String, Object> dtoHits = new LinkedHashMap<>();

        Map<String, Object> shipHits = new LinkedHashMap<>();

        GamePlayer me = salvo.getGamePlayer();

        Set<Ship> enemyShipList = GamePlayer.getOpponent(me).get().getShips();
        Set<Salvo> mySalvos = me.getSalvos();


        dtoHits.put("Hits", enemyShipList.stream().filter(ship -> ship.getLocations().equals(salvo.getLocations().stream())));


        return dtoHits;
    }



    public static long CurrentTurn(GamePlayer gp){

        List<Long> turnsList = gp.getSalvos().stream().map(s -> s.getTurn()).collect(Collectors.toList());

        long lastTurn = Collections.max(turnsList);

        return lastTurn;
    }


    /*public Map<String, Object> getHitts(GamePlayer gp){
        Map<String, Object> dtoHits = new LinkedHashMap<>();

        Set<Ship> myShips = gp.getShips();
        List<String> myShipsLocations = myShips.stream().map(s -> s.getLocations().stream().collect(Collectors.toList();

        GamePlayer enemy = GamePlayer.getOpponent(gp).get();
        Salvo enemyLastSalvo = enemy.getSalvos().stream().filter(s -> s.getTurn() == CurrentTurn(enemy)).findFirst().get();


        Map<String, Object> meHitted =


        //dtoHits.put("hitLocations", meHitted);



        return dtoHits;
    }*/

/*

    public Map<String, Object> getSelfHitLocations(GamePlayer gp){




    }
*/






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







}
