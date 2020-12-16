package com.examplecodeoftheweb.salvo_n.controller;

import com.examplecodeoftheweb.salvo_n.dto.SalvoDTO;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Salvo;
import com.examplecodeoftheweb.salvo_n.model.Ship;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.GameRepository;
import com.examplecodeoftheweb.salvo_n.repository.SalvoRepository;
import com.examplecodeoftheweb.salvo_n.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {


    @Autowired
    GamePlayerRepository gamePlayerRepository;


    @Autowired
    SalvoRepository salvoRepository;


    @Autowired
    GameRepository gameRepository;

    //hacer GET


    @RequestMapping(path = "/games/players/{gamePlayerID}/salvoes", method = RequestMethod.GET)
    public ResponseEntity<Object> addSalvos(@PathVariable long gamePlayerID, Authentication authentication) {


        //Chequear que el gp exista
        if (gamePlayerRepository.findById(gamePlayerID).get() == null) {
            return new ResponseEntity<>(Util.makeMap("error", "GamePlayer doesn't exists"), HttpStatus.UNAUTHORIZED);
        }

        //chequear que el jugador este loggeado
        if (Util.isGuest(authentication)) {
            return new ResponseEntity<>(Util.makeMap("error", "Is guest, not logged in"), HttpStatus.UNAUTHORIZED);
        }

        //chequear que el que entro sea el del tablero que le corresponda
        if (authentication.getName() != gamePlayerRepository.findById(gamePlayerID).get().getPlayer().getEmail()) {
            return new ResponseEntity<>(Util.makeMap("error", "Not matching Ids"), HttpStatus.FORBIDDEN);
        }


        SalvoDTO salvoDTO = new SalvoDTO();

        List<Map<String, Object>> salvosFromPlayer = gamePlayerRepository.findById(gamePlayerID).get().getSalvos().stream().map(s -> salvoDTO.makeSalvoDTO(s)).collect(Collectors.toList());

        return new ResponseEntity<>(salvosFromPlayer, HttpStatus.ACCEPTED);

    }









    @RequestMapping(path = "/games/players/{gamePlayerID}/salvoes", method = RequestMethod.POST)
    public ResponseEntity<Map> addSalvos(@PathVariable long gamePlayerID, @RequestBody Salvo salvo, Authentication authentication) {


        GamePlayer mePlayer = gamePlayerRepository.findById(gamePlayerID).get();
        //GamePlayer enemyPlayer = gameRepository.findById(mePlayer.getGame().getId()).get().getGamePlayers().stream().filter(s -> s.getId() != mePlayer.getId()).findFirst().get();
        GamePlayer enemyPlayer = GamePlayer.getOpponent(mePlayer).get();

        if (!GamePlayer.getOpponent(mePlayer).isPresent()) {
            return new ResponseEntity<>(Util.makeMap("error", "There is no enemy yet"), HttpStatus.UNAUTHORIZED);
        } else {

            //Chequear que el gp exista
            if (gamePlayerRepository.findById(gamePlayerID).get() == null) {
                return new ResponseEntity<>(Util.makeMap("error", "GamePlayer doesn't exists"), HttpStatus.UNAUTHORIZED);
            }

            //chequear que el jugador este loggeado
            if (Util.isGuest(authentication)) {
                return new ResponseEntity<>(Util.makeMap("error", "Is guest, not logged in"), HttpStatus.UNAUTHORIZED);
            }

            //chequear que el que entro sea el del tablero que le corresponda
            if (authentication.getName() != gamePlayerRepository.findById(gamePlayerID).get().getPlayer().getEmail()) {
                return new ResponseEntity<>(Util.makeMap("error", "Not matching Ids"), HttpStatus.FORBIDDEN);
            }


            long meTurnSalvo = mePlayer.getSalvos().size();
            long enemyTurnSalvo = enemyPlayer.getSalvos().size();

            if (meTurnSalvo == enemyTurnSalvo || meTurnSalvo < enemyTurnSalvo) {

                salvo.setTurn(meTurnSalvo+1);
                salvo.setGamePlayer(mePlayer);

                salvoRepository.save(salvo);

                mePlayer.addSalvo(salvo);
                gamePlayerRepository.save(mePlayer);

            }else{
                return new ResponseEntity<>(Util.makeMap("error", "Not your turn"), HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>(Util.makeMap("OK", "Salvo saved"), HttpStatus.CREATED);
        }


    }






}
