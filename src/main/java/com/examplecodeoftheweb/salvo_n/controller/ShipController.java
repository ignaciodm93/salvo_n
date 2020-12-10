package com.examplecodeoftheweb.salvo_n.controller;

import com.examplecodeoftheweb.salvo_n.dto.ShipDTO;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Ship;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.GameRepository;
import com.examplecodeoftheweb.salvo_n.repository.PlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.ShipRepository;
import com.examplecodeoftheweb.salvo_n.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShipController {


    @Autowired
    GameRepository gameRepository;

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ShipRepository shipRepository;








    @RequestMapping(path = "/games/players/{nn}/ships", method = RequestMethod.GET)
    public ResponseEntity<Object> getShipsByPlayer(@PathVariable long nn, Authentication authentication){

        if (Util.isGuest(authentication)) {
            return new ResponseEntity<>(Util.makeMap("error", "Is guest, must be logged in"), HttpStatus.UNAUTHORIZED);
        }

        if (authentication.getName() != gamePlayerRepository.findById(nn).get().getPlayer().getEmail()) {
            return new ResponseEntity<>(Util.makeMap("error", "Not matching Ids, not your ships"), HttpStatus.FORBIDDEN);
        }


    ShipDTO shipDTO = new ShipDTO();
    List<Map<String,  Object>>  dto = new ArrayList<>();

    dto = gamePlayerRepository.findById(nn).get().getShips().stream().map(s -> shipDTO.makeShipDTO(s)).collect(Collectors.toList());

    return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);

    }





    @RequestMapping(path = "/games/players/{gamePlayerID}/ships", method = RequestMethod.POST)
    public ResponseEntity<Map> addShips(@PathVariable long gamePlayerID, @RequestBody List<Ship> ships, Authentication authentication) {


        if (gamePlayerRepository.findById(gamePlayerID).get() == null) {
            return new ResponseEntity<>(Util.makeMap("error", "GamePlayer doesn't exists"), HttpStatus.UNAUTHORIZED);
        }

        //chequear que el jugador este loggeado
        if (Util.isGuest(authentication)) {
            return new ResponseEntity<>(Util.makeMap("error", "Is guest"), HttpStatus.UNAUTHORIZED);
        }

        //chequear que el que entro sea el del tablero que le corresponda
        if (authentication.getName() != gamePlayerRepository.findById(gamePlayerID).get().getPlayer().getEmail()) {
            return new ResponseEntity<>(Util.makeMap("error", "Not matching Ids"), HttpStatus.FORBIDDEN);
        }

        //Chequeo que haya pasado 5 ships como maximo
        if (gamePlayerRepository.findById(gamePlayerID).get().getShips().size() > 0) {
            return new ResponseEntity<>(Util.makeMap("error", "Already has ships"), HttpStatus.FORBIDDEN);
        }


        //Chequeo primero que no pase para guardar mas de 5
        if(ships.size() > 5){
            return new ResponseEntity<>(Util.makeMap("error", "Too many ships"), HttpStatus.FORBIDDEN);
        }

        GamePlayer newGP = gamePlayerRepository.findById(gamePlayerID).get();

        gamePlayerRepository.findById(gamePlayerID).get().addShips(ships);
        shipRepository.saveAll(ships.stream().map(s -> s.saveShip(newGP)).collect(Collectors.toList()));

        gamePlayerRepository.save(gamePlayerRepository.findById(gamePlayerID).get());
        return new ResponseEntity<>(Util.makeMap("OK", "Success"), HttpStatus.CREATED);
    }





    //Segundo metodo de practica, no se usa
    @RequestMapping(path = "/games/players/{nn}/shipsPractica", method = RequestMethod.GET)
    public ResponseEntity<Object> getMyShips(@PathVariable long nn, Authentication authentication){

        if(Util.isGuest(authentication)){
            return new ResponseEntity<>(Util.makeMap("error", "GamePlayer doesn't exists"), HttpStatus.UNAUTHORIZED);
        }

        if(gamePlayerRepository.findById(nn) != gamePlayerRepository.findById(playerRepository.findByEmail(authentication.getName()).getId())){
            return new ResponseEntity<>(Util.makeMap("error", "not your game bro"), HttpStatus.UNAUTHORIZED);
        }

        ShipDTO shipDTO = new ShipDTO();

        List<Map<String, Object>> shipsFromPlayer = gamePlayerRepository.findById(nn).get().getShips().stream().map(s -> shipDTO.makeShipDTO(s)).collect(Collectors.toList());

        return new ResponseEntity<>(shipsFromPlayer, HttpStatus.ACCEPTED);



    }













}
