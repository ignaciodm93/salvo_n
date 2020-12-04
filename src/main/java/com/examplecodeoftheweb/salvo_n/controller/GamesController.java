package com.examplecodeoftheweb.salvo_n.controller;


import com.examplecodeoftheweb.salvo_n.dto.GameDTO;
import com.examplecodeoftheweb.salvo_n.dto.GamePlayerDTO;
import com.examplecodeoftheweb.salvo_n.dto.PlayerDTO;
import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Player;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.GameRepository;
import com.examplecodeoftheweb.salvo_n.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.examplecodeoftheweb.salvo_n.repository.PlayerRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GamesController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    GameRepository gameRepository;


    //Creo la ruta (es igualmente la que redirecciona cuando pido unirme a un juego ya creado)
    /*@RequestMapping(path ="/game/{nn}/players", method = RequestMethod.POST)
    public ResponseEntity<Object> Join(@PathVariable long gameId, Authentication authentication) {

        //Si no encuentra usuario registrado con ese nombre, deuvelve que no esta logeado.
        if(isGuest(authentication)) return new ResponseEntity<>(makeMap("error", "You are not logged in"), HttpStatus.FORBIDDEN);




    }*/



    //GET
    @RequestMapping("/games")
    public Map<String, Object> getGameAll(Authentication authentication) {
        Map<String,  Object>  dto = new LinkedHashMap<>();

        if(Util.isGuest(authentication)){
            dto.put("player", "Guest");
        }else{
            Player player  = playerRepository.findByEmail(authentication.getName());
            PlayerDTO playerDTO   =   new PlayerDTO(player);
            dto.put("player", playerDTO.makePlayerDTO(player));
        }

        dto.put("games", gameRepository.findAll()
                .stream()
                .map(game -> {
                    GameDTO gameDTO =   new GameDTO(game);
                    return  gameDTO.makeGameDTO(game);
                })
                .collect(Collectors.toList()));

        return dto;

    }



    //Encontrar los datos del juego (jugadores), por el id del jugador pero solo las naves del que ingresamos el id.
 /*   @RequestMapping("/game_view/{id}")
    public Map<String, Object> getGameView(@PathVariable Long id){
        GamePlayerDTO gamePlayerDTO = new GamePlayerDTO();
        return gamePlayerDTO.makeGameViewDTO(gamePlayerRepository.getOne(id));
    }*/


/*
    @RequestMapping(value = "/game_view/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable Long id, Authentication authentication){
        if(Util.isGuest(authentication)) {
            return new ResponseEntity<>(Util.makeMap("error", "is Guest"), HttpStatus.UNAUTHORIZED);
        }

        GamePlayerDTO gamePlayerDTO = new GamePlayerDTO();
        return new ResponseEntity<>(gamePlayerDTO.makeGameViewDTO(gamePlayerRepository.getOne(id)), HttpStatus.ACCEPTED);
    }*/






}
