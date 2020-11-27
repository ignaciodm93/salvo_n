package com.examplecodeoftheweb.salvo_n.model;


import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String type;
    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gamePlayerID")
    private GamePlayer gamePlayer;


    //region Constructores
    public Ship() {
    }

    //Constructor de prueba con 2 parametros



    public Ship(String type, List<String> locations, GamePlayer gamePlayer) {
        this.type = type;
        this.locations = locations;
        this.gamePlayer = gamePlayer;
    }
    //endregion






    //region Getters
    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<String> getLocations() {
        return locations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    //endregion

    //region Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
    //endregion
}
