package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.model.StatusQuiz;
import com.tech.ada.moviesbattle.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryQuizDto <T, F> implements IFactoryDto<QuizDto, Quiz> {

    private final DateUtil dateUtil;

    @Autowired
    public FactoryQuizDto(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    /**
     * Implements the buildFromEntity method defined in the IFactoryDto interface. It receives an entity and returns
     * a dto
     * @param quiz
     * @return
     */
    @Override
    public QuizDto buildFromEntity(Quiz quiz) {
        QuizDto dto = new QuizDto();
        dto.setStartDateTime(dateUtil.localDateTimeToString(quiz.getStartDateTime()));
        dto.setStatus(quiz.getStatus().name());
        dto.setId(quiz.getId());
        dto.setMistakes(quiz.getMistakes());
        return dto;
    }

    /**
     * Implements the buildFromDto method defined in the IFactoryDto interface. It receives a dto and returns
     * an entity.
     * @param dto
     * @return
     */
    @Override
    public Quiz buildFromDto(QuizDto dto) {
        Quiz quiz = new Quiz();
        quiz.setStartDateTime(dateUtil.stringToLocalDateTime(dto.getStartDateTime()));
        quiz.setStatus(StatusQuiz.valueOf(dto.getStatus()));
        quiz.setId(dto.getId());
        quiz.setMistakes(dto.getMistakes());
        return quiz;
    }
}
