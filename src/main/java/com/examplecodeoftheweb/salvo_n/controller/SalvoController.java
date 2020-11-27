package com.examplecodeoftheweb.salvo_n.controller;


import com.examplecodeoftheweb.salvo_n.dto.GameDTO;
import com.examplecodeoftheweb.salvo_n.dto.GamePlayerDTO;
import com.examplecodeoftheweb.salvo_n.dto.PlayerDTO;
import com.examplecodeoftheweb.salvo_n.dto.ShipDTO;
import com.examplecodeoftheweb.salvo_n.model.Game;
import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import com.examplecodeoftheweb.salvo_n.model.Player;
import com.examplecodeoftheweb.salvo_n.model.Ship;
import com.examplecodeoftheweb.salvo_n.repository.GamePlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.GameRepository;
import com.examplecodeoftheweb.salvo_n.repository.PlayerRepository;
import com.examplecodeoftheweb.salvo_n.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController //permite que nuestra clase controladortodo lo que devuelva , lo hara en json.
@RequestMapping("/api") //barra previa por default
public class SalvoController {

    @Autowired //acceso al repository directo desde aca
    GameRepository gameRepository;

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    PlayerRepository playerRepository;


    @RequestMapping("/players")
    public List<Map<String, Object>> getPlayerAll(){
        PlayerDTO playerDto = new PlayerDTO();
        return playerRepository.findAll()
                .stream()
                .map(player ->  playerDto.makePlayerDTO(player))
                .collect(Collectors.toList());
    }



    

    @Autowired
    ShipRepository shipRepository;


    @RequestMapping("/ships")
    public List<Map<String, Object>> getShipAll(){
        ShipDTO shipDTO = new ShipDTO();
        return shipRepository.findAll()
                .stream()
                .map(ship -> shipDTO.makeShipDTO(ship))
                .collect(Collectors.toList());
    }


    @RequestMapping("/gamePlayers")
    public List<Map<String, Object>> getGamePlayers(){
    GamePlayerDTO gpDTO = new GamePlayerDTO();

            return gamePlayerRepository.findAll()
                    .stream()
                    .map(gp -> gpDTO.makeGamePlayerDTO(gp))
                    .collect(Collectors.toList());
    }



    @RequestMapping("/games")
    public List<Map<String, Object>> getGameAll(){
        GameDTO gameDto = new GameDTO();
        return gameRepository.findAll()
                .stream()
                .map(game -> gameDto.makeGameDTO(game))
                .collect(Collectors.toList());
    }


    //Encontrar los datos del juego (jugadores), por el id del jugador pero solo las naves del que ingresamos el id.
    @RequestMapping("/game_view/{id}")
    public Map<String, Object> getGameView(@PathVariable Long id){
        GamePlayerDTO gamePlayerDTO = new GamePlayerDTO();
        return gamePlayerDTO.makeGameViewDTO(gamePlayerRepository.getOne(id));
    }





//region Metodos de prueba comentados
/*
    @RequestMapping("/game_view/{pd_id}")
    public Map<String, Object> getGame(@PathVariable long id){
         var gameSelected = gamePlayerRepository.findById(id).stream()
                .map(gamePlayer -> makeGameDTO()

        return
    }

    @RequestMapping("game_view")
    public Map<String, Object>

-----------------------------------------------------------------------


    //Metodo para ver todos los juegos (adicional)

    @RequestMapping("/game_viewsS")
    public List<Map<String, Object>> getAllGames(){
        return gameRepository.findAll().stream()
                            .map(g -> makeGameDTO(g))
                            .collect(Collectors.toList());
    }


   -----------------------------------------------------------------------

      @RequestMapping("/prueba")
    public List<Map<String, Object>> getGameAlls(){
        return gameRepository.findAll()
                .stream()
                .map(game -> makeGameViewDTO(gameRepository game))
                .collect(Collectors.toList());
    }

    -----------------------------------------------------------------------

        //Encontrar los datos del juego, usando el id del player
    @RequestMapping("/game_views/gamePlayer/{gp_id}")
    public List<Map<String, Object>> getGame(@PathVariable long gp_id){
       Game gameFound = gameRepository.findById(gamePlayerRepository.findById(gp_id).get().getGame().getId();

    }

    -----------------------------------------------------------------------






  //PRUEBA MIA ADICIONAL, no terminado
    //Metodo para mostrar la informacion completa del juego, con id del juego:
    @RequestMapping("/game_info/{id}")
    public Map<String, Object> getGameVista(@PathVariable Long id){
        return makeGameAllInfo(gameRepository.findById(id).get());
    }


    private Map<String, Object> makeGameAllInfo(Game game){

        //Uso el GAME DTO tomando al gp pasado por parametro como base
        Map<String, Object> dto = makeGameDTO(game);

        //Consigo las naves del game player (las que le pertenecen)
        List<GamePlayer> gamePlayers = game.getGamePlayers().stream().collect(Collectors.toList());

        dto.put("Game Players", gamePlayers.stream().map(gamePlayer -> makeGamePlayerDTO(gamePlayer)).collect(Collectors.toList()));

        //Repocco las ships obtenidas y almacenadas en la lista "ships" y le aplico a cada una el dtoShips
        //<Ship> ships = gamePlayers.stream().map(gp -> gp.getShips()).collect(Collectors.toList()));

        dto.put("Ships", gamePlayers.stream().map(gamePlayer -> makeShipDTO(gamePlayer.getShips().)).collect(Collectors.toList()));


        dto.put("Play: ", game.getGamePlayers().)



        return dto;
    }

----------------------------------------------------------------------------------






DAVID
    //Para consumir de este metodo lo que se muestra en el html games, o el que sea,
    //tenemos que citarlo desde el .js del html correspondiente con el metodo get.
    @RequestMapping("/game_views/{nn}")
    public Map<String,  Object> getGameViewByGamePlayerID(@PathVariable Long nn) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(nn).get();

        Map<String,  Object>  dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created",  gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers()
                .stream()
                .map(gamePlayer1 -> makeGamePlayerDTO(gamePlayer1))
                .collect(Collectors.toList()));
        dto.put("ships",  gamePlayer.getShips()
                .stream()
                .map(ship -> makeShipDTO(ship))
                .collect(Collectors.toList()));

        return  dto;
    }




----------------------------------------------------------------------------------




private Map<String, Object> makePlayerDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("name", player.getName());
        dto.put("email", player.getEmail());

        return dto;
    }







*/
//endregion

    //region Metodos mios de prueba:
/*
    @RequestMapping("/juegos_views/{nn}")
    public Map<String,  Object> getGameViewByGamePlayerID(@PathVariable Long nn) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(nn).get();

        Game g1 = gameRepository.findById(gamePlayer.getGame().getId()).get();

        GamePlayerDTO gpDTO = new GamePlayerDTO();
        GameDTO gameDTO = new GameDTO();

        Map<String,  Object>  dto = new LinkedHashMap<>();
        //dto.put("created",  gamePlayer.getGame().getCreationDate());

        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers()
                .stream()
                .map(gp -> gpDTO.makeGamePlayerDTO(gp))
                .collect(Collectors.toList()));

        //dto.put("ships", gamePlayer.getShips().stream().map(s -> makeAllShipsDTO(g1)).collect(Collectors.toList()));
        //Game g1 = gameRepository.findById(gamePlayer)
        //dto.put("ships", gamePlayer.getShips().stream().map(s -> makeAllShipsDTO(g1)).collect(Collectors.toList()));
        //dto.put("ships", gamePlayer.get)

        //dto.put("ships", getShipAll());

        //dto.put("infos", makeAllShipsDTO(g1));

        //dto.put("ships", g1.getGamePlayers().stream().map(gp -> gp.getShips()));




        return  gameDTO.makeGameInfo(g1);
    }
*/

    //Metodo para mostrar todos los datos del juego poniendo el id de la partida como parametro:
        //Muestro quienes juegan y las naves y ubicaciones de cada uno.
    @RequestMapping("/juegos_views/{nn}")
    public Map<String,  Object> getGameViewByGamePlayerID(@PathVariable Long nn) {
        //dto.put("ships", g1.getGamePlayers().stream().map(gp -> gp.getShips()));
        GameDTO gameDTO = new GameDTO();

        Map<String,  Object>  dto = new LinkedHashMap<>();

        return gameDTO.makeGameInfo(gameRepository.findById(nn).get());
    }



    private Map<String, Object> makeAllShipsDTO(Game g){
        Map<String, Object> dto = new LinkedHashMap<>();
        Game g1 = gameRepository.findById(g.getId()).get();

        GamePlayer gp1 = g1.getGamePlayers().iterator().next();

        List<GamePlayer> listaPlayers = g1.getGamePlayers().stream().collect(Collectors.toList());

        //dto.put("gamePlayers", g.getGamePlayers().stream().map(m -> m.getId()).collect(Collectors.toList()));

        //dto.put("ships", g1.getGamePlayers().stream().map(m -> m.getShips()).collect(Collectors.toList()));

        //dto.put("ships", g1.getGamePlayers().stream().map(m -> m.getPlayer()));

        dto.put("ships jugador 1: ", listaPlayers.stream().iterator().next().getShips().stream().collect(Collectors.toList()));

        return dto;
    }
    //endregion






    //para este metodo ahora me falta hacer el dto respectivo, o chequear si esta, en gameplayerDTO la clase
    @RequestMapping("/gamePlayers/{id}")
    public Map<String, Object> getGamePlayerInfo(@PathVariable Long id){
        GamePlayerDTO gamePlayerDTO = new GamePlayerDTO();
        ShipDTO shipDTO = new ShipDTO();

        //gpDTO = gamePlayerRepository.findById(id).stream().map(gp -> gpDTO.makeGamePlayerDTO(gamePlayerRepository.findById(id).get())).collect(Collectors.toList());

        return gamePlayerDTO.makeGamePlayerDTO(gamePlayerRepository.findById(id).get());
       

    }




}
