package com.examplecodeoftheweb.salvo_n.dto;

import com.examplecodeoftheweb.salvo_n.model.Score;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreDTO {


    public static Map<String, Object> makeScoreDTO(Score s) {
        Map<String, Object> dto = new LinkedHashMap<>();

        dto.put("player", s.getPlayer().getId());
        dto.put("score", s.getScore());
        dto.put("finishDate", s.getFinishDate());

        return dto;
    }


}
