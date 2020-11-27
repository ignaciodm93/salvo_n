package com.examplecodeoftheweb.salvo_n.dto;


import com.examplecodeoftheweb.salvo_n.model.Ship;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShipDTO {

    public ShipDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
    }
    //ver si tengo que isntanciarlo en el controlador para citarlo en el dto como this.ship

    public Map<String, Object> makeShipDTO(Ship ship){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", ship.getId());
        dto.put("type", ship.getType());
        dto.put("locations", ship.getLocations());
        //no se si falta algo

        return dto;
    }




}
