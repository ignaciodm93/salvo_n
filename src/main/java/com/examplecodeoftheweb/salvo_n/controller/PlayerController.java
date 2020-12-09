package com.examplecodeoftheweb.salvo_n.controller;

import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Player;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.GameRepository;
import com.examplecodeoftheweb.salvo_n.repository.PlayerRepository;
import com.examplecodeoftheweb.salvo_n.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PlayerController {


    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    GameRepository gameRepository;


    /*@RequestMapping(path ="/games/{nn}/players", method = RequestMethod.POST)  // chequear porque este no me esta funcionando, capaz es games en singular
    public ResponseEntity<Map<String, Object>> joinGame(@PathVariable long idGame, Authentication authentication){


        if(Util.isGuest(authentication)){
            return new ResponseEntity<>(Util.makeMap("error", "Is guest"), HttpStatus.UNAUTHORIZED);
        }

        Player player = playerRepository.findByEmail(authentication.getName());

        Game gameToJoin = gameRepository.getOne(idGame);

        //si el juego esta vacio
        if(gameToJoin == null){
            return new ResponseEntity<>(Util.makeMap("error", "No such game"), HttpStatus.FORBIDDEN);
        }

        long gamePlayersCount = gameToJoin.getGamePlayers().size();

        if(gamePlayersCount == 1){
            Player playerAux = gameRepository.getOne(idGame).getGamePlayers().stream().findFirst().get().getPlayer();
            if(playerAux.getId() == player.getId()){

                return new ResponseEntity<>(Util.makeMap("error", "already playing"), HttpStatus.FORBIDDEN);

            }else {
                GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(gameToJoin, player));
                return new ResponseEntity<>(Util.makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);
            }

        }else{
            return new ResponseEntity<>(Util.makeMap("error", "game is full!"), HttpStatus.FORBIDDEN);
        }



    }*/







/*


    @RequestMapping(path ="/game/{idGame}/players", method = RequestMethod.POST)  // chequear porque este no me esta funcionando, capaz es games en singular
    public ResponseEntity<Map<String, Object>> joinGame(@PathVariable long idGame, Authentication authentication){


        if(Util.isGuest(authentication)){
            return new ResponseEntity<>(Util.makeMap("error", "Is guest"), HttpStatus.UNAUTHORIZED);
        }

        Player player = playerRepository.findByEmail(authentication.getName());

        Game gameToJoin = gameRepository.getOne(idGame);

        //si el juego esta vacio
        if(gameToJoin == null){
            return new ResponseEntity<>(Util.makeMap("error", "No such game"), HttpStatus.FORBIDDEN);
        }

        long gamePlayersCount = gameToJoin.getGamePlayers().size();

        if(gamePlayersCount == 1){
            Player playerAux = gameRepository.getOne(idGame).getGamePlayers().stream().findFirst().get().getPlayer();
            if(playerAux.getId() == player.getId()){

                return new ResponseEntity<>(Util.makeMap("error", "already playing"), HttpStatus.FORBIDDEN);

            }else {
                GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(gameToJoin, player));
                return new ResponseEntity<>(Util.makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);
            }

        }else{
            return new ResponseEntity<>(Util.makeMap("error", "game is full!"), HttpStatus.FORBIDDEN);
        }

    }
*/







    @RequestMapping(path = "/game/{game_id}/players", method = RequestMethod.POST)
    public ResponseEntity<Object> Join(@PathVariable Long game_id,  Authentication authentication) {
        if(Util.isGuest(authentication))
            return new ResponseEntity<>(Util.makeMap("error", "You are not logged in."), HttpStatus.UNAUTHORIZED);

        Player player = playerRepository.findByEmail(authentication.getName());
        if(player == null)
            return new ResponseEntity<>(Util.makeMap("error", "Database error. Player not found."), HttpStatus.INTERNAL_SERVER_ERROR);

        Game game = gameRepository.findById(game_id).orElse(null);
        if(game == null)
            return new ResponseEntity<>(Util.makeMap("error", "Game not found."), HttpStatus.FORBIDDEN);

        if (game.getGamePlayers().contains(player))
            return new ResponseEntity<>(Util.makeMap("error", "You are already in the game!"), HttpStatus.FORBIDDEN);

        if(game.getGamePlayers().size() >= 2)
            return new ResponseEntity<>(Util.makeMap("error", "Game is full!"), HttpStatus.FORBIDDEN);

        GamePlayer gamePlayer = new GamePlayer(game, player);
        if(gamePlayer == null)
            return new ResponseEntity<>(Util.makeMap("error", "Database error. Couldn't create GamePlayer."), HttpStatus.INTERNAL_SERVER_ERROR);

        gamePlayerRepository.save(gamePlayer);
        return new ResponseEntity<>(Util.makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);
    }






}
