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
    private int turn;

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
    public void setTurn(int turn) {
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

    //public int getTurn() {
        //return turn;
    //}

    public List<String> getLocations() {
        return locations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    //endregion
}
