package com.examplecodeoftheweb.salvo_n.dto;

import com.examplecodeoftheweb.salvo_n.model.Salvo;
import com.examplecodeoftheweb.salvo_n.model.Ship;

import java.util.LinkedHashMap;
import java.util.Map;

public class SalvoDTO {

    public SalvoDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
    }
    //ver si tengo que instanciarlo en el controlador para citarlo en el dto como this.ship

    /*
    public Map<String, Object> makeShipDTO(Salvo salvo){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("", );


        return dto;
    }*/


}
