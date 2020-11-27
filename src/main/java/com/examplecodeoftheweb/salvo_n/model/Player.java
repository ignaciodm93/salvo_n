package com.examplecodeoftheweb.salvo_n.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String email;


    //Si lo cambio, crashea. Chequear el game.
   // @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    //Set<GamePlayer> gamePlayersSet;

    //nombre de la variable que vengo a buscar
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;    //nombre del set de base de datos



    public long getId() {
        return id;
    }

    //Metodo para agregarle al player un juego al que pertenece
    public void addGame(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }


    //prueba 20.11.20 chequeado con Gabriel
    public List<Game> getGames(){
     return gamePlayers.stream().map(gp -> gp.getGame()).collect(Collectors.toList());
    }



    //Constructor por defecto vacio
        //NECESARIO POR SPRING
    public Player() { }

    public Player(String name, String email) {
        this.email = email;
        this.name = name;
    }

    //region Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name + " " + email;
    }


    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
    //endregion
}
