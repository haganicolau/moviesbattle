package com.tech.ada.moviesbattle.dto;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScoreDtoTest {

    @Test
    void testToString() {
        Map<String, Integer> scoresMap = new HashMap<>();
        scoresMap.put("John", 100);
        scoresMap.put("Alice", 200);
        scoresMap.put("Bob", 150);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScores(scoresMap);

        String expected = "ScoreDto{scores={Bob=150, Alice=200, John=100}}";
        assertEquals(expected, scoreDto.toString());
    }

    @Test
    void testEquals() {
        Map<String, Integer> scoresMap1 = new HashMap<>();
        scoresMap1.put("John", 100);
        scoresMap1.put("Alice", 200);
        scoresMap1.put("Bob", 150);

        Map<String, Integer> scoresMap2 = new HashMap<>();
        scoresMap2.put("John", 100);
        scoresMap2.put("Alice", 200);
        scoresMap2.put("Bob", 150);

        Map<String, Integer> scoresMap3 = new HashMap<>();
        scoresMap3.put("John", 100);
        scoresMap3.put("Alice", 300);
        scoresMap3.put("Bob", 150);

        ScoreDto scoreDto1 = new ScoreDto();
        scoreDto1.setScores(scoresMap1);

        ScoreDto scoreDto2 = new ScoreDto();
        scoreDto2.setScores(scoresMap2);

        ScoreDto scoreDto3 = new ScoreDto();
        scoreDto3.setScores(scoresMap3);

        assertEquals(scoreDto1, scoreDto2);
        assertNotEquals(scoreDto1, scoreDto3);
    }

}
