package com.examplecodeoftheweb.salvo_n.model;


import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime created;



    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;


    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<Score> scores;  //ver si set o list



    /*
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;
    */





    //no necesario porque los relacionamos al mismo juego cuando los pasamos por el constructor en el CLR
    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }




    //region Constructores
    public Game(LocalDateTime date) {
        this.created = date;
        gamePlayers = new HashSet<GamePlayer>();
    }

    public Game(LocalDateTime created, Set<GamePlayer> gamePlayers, Set<Score> scores) {
        this.created = created;
        this.gamePlayers = gamePlayers;
        this.scores = scores;
    }

    //prueba
    public Game(){
        created = LocalDateTime.now();
    }
    //endregion

/*
    public Game findGame(long id){
       return game
    }
*/


    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    //region Getters y setters
    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return created;
    }

    public void setDate(LocalDateTime date) {
        this.created = date;
    }


    public LocalDateTime getCreated() {
        return created;
    }


    public Set<Score> getScores() {
        return scores;
    }
}
