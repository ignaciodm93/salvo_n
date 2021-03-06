package com.examplecodeoftheweb.salvo_n.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date joinDate;
    //ids nuevos
    //private long gameId;
    //private long playerId;



    //creo que bien
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;
    //creo que bien (esta bien, David lo hizo asi tambien)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    //prueba lean
    @OneToMany(mappedBy = "gamePlayer", fetch=FetchType.EAGER)
    private Set<Ship> ships = new HashSet<Ship>();

    //si pincha, usar el de arriba, pero en teoria este esta Mejor
/*
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "GamePlayerID")
    private Set<Ship> ships = new HashSet<Ship>();
*/
    //prueba brian
    /*
    * @OneToMany(mappedBy = "gameplayer", fetch = FetchType.EAGER)
    * private Set<Ship> ships;
    * */



    //ensayo para conexion con Salvos. Si pincha, borrar. Funciona, guarda con las mayusculas.
    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    @OrderBy
    private Set<Salvo> salvos  = new HashSet<Salvo>();


    //region Mathods
    public void addShip(Ship newShip){
        this.ships.add(newShip);
        newShip.setGamePlayer(this);
    }

    public void addShips(List<Ship> ships){
        ships = ships;
    }


    public void addSalvos(List<Salvo> salvos){
        salvos = salvos;
    }



    //Prueba
    public void addSalvo(Salvo newSalvo){
        this.salvos.add(newSalvo);
        newSalvo.setGamePlayer(this);
    }



    //endregion

    //region Constructores
    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.joinDate = Date.from(Instant.now());
    }


    public GamePlayer(){
         Set<Ship> ships = new HashSet<Ship>();
        Set<Salvo> salvos  = new HashSet<Salvo>();
    }
    //endregion

    //region Getters & Setters

    public long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }


    public Player getPlayer() {
        return player;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void setJoinDate(LocalDateTime date) {
        this.joinDate = joinDate;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public Set<Salvo> getSalvos() {
        return salvos;
    }




    //endregion

    //region Methods

    public Score getScore(){
        return this.player.getGameScore(this.game);
    }


    public static Optional<GamePlayer> getOpponent(GamePlayer gamePlayer){
        GamePlayer opponent = gamePlayer.getGame().getGamePlayers().stream()
                .filter(gp -> gp.getId() != gamePlayer.getId()).findFirst().get();
        return Optional.of(opponent);
    }



    //endregion


    public long getLastTurn(GamePlayer gamePlayer){

        //long turn = gamePlayer.getSalvos().stream().max(Long:: compare).get();

        long turn = gamePlayer.getSalvos().stream().map(s -> s.getTurn()).max(Long:: compare).get();


        return turn;

    }


    public static long CurrentTurn(GamePlayer gp){

        List<Long> turnsList = gp.getSalvos().stream().map(s -> s.getTurn()).collect(Collectors.toList());

        long lastTurn = Collections.max(turnsList);

        return lastTurn;
    }















}
