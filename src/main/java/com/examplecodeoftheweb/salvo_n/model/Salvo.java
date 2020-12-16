package com.examplecodeoftheweb.salvo_n.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private long turn;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();


    //prueba
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayer_id")
    private GamePlayer gamePlayer;

    public Salvo() {
    }

    public Salvo(int turn, List<String> locations, GamePlayer gamePlayer) {
        this.turn = turn;
        this.locations = locations;
        this.gamePlayer = gamePlayer;
    }

    //region Setters
    public void setTurn(long turn) {
        this.turn = turn;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
    //endregion

    //region Getters
    public long getId() {
        return id;
    }

    public long getTurn() {
        return turn;
    }


    public Salvo saveSalvo(GamePlayer gamePlayer){
        this.gamePlayer = gamePlayer;
        return this;
    }


    public List<String> getLocations() {
        return locations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    //endregion

    //Sin usar, es muy similar al setTurn
    public void addTurn(long add){
        this.turn += add;
    }


}
