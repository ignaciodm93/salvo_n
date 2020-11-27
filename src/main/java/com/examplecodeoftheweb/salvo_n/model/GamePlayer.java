package com.examplecodeoftheweb.salvo_n.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Salvo> salvos  = new HashSet<Salvo>();







    public void addShips(Ship newShip){
        this.ships.add(newShip);
        newShip.setGamePlayer(this);
    }








    //region Constructores
    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.joinDate = Date.from(Instant.now());
    }


    public GamePlayer(){

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
    //endregion

//CREO QUE faltan 2 metodos get




}
