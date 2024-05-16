package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.dto.MovieDto;
import com.tech.ada.moviesbattle.dto.QuestionDto;
import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.Movie;
import com.tech.ada.moviesbattle.model.Question;
import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.model.StatusQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FactoryQuestionDto <T, F> implements IFactoryDto<QuestionDto, Question> {

    private final FactoryMovieDto<MovieDto, Movie> factoryMovieDto;
    private final FactoryQuizDto<QuizDto, Quiz> factoryQuizDto;

    @Autowired
    public FactoryQuestionDto(FactoryMovieDto<MovieDto, Movie> factoryMovieDto,
                              FactoryQuizDto<QuizDto, Quiz> factoryQuizDto) {
        this.factoryMovieDto = factoryMovieDto;
        this.factoryQuizDto = factoryQuizDto;
    }

    /**
     * Implements the buildFromEntity method defined in the IFactoryDto interface. It receives an entity and returns
     * a dto
     * @param question
     * @return
     */
    @Override
    public QuestionDto buildFromEntity(Question question) {
        QuestionDto questionDto = new QuestionDto();
        QuizDto quizDto = this.factoryQuizDto.buildFromEntity(question.getQuiz());
        questionDto.setQuiz(quizDto);
        questionDto.setMovies(getMovieDtoList(question.getMovieList()));
        questionDto.setStatus(question.getStatus().name());
        questionDto.setQuestionId(question.getId());
        return questionDto;
    }

    /**
     * Overall, this method encapsulates the logic for converting a list of Movie entities into a list of MovieDto
     * objects, providing a convenient way to perform this conversion within the application.
     * @param movies
     * @return
     */
    private List<MovieDto> getMovieDtoList(List<Movie> movies) {
        List<MovieDto> dtoList = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDto dto = this.factoryMovieDto.buildFromEntity(movie);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Implements the buildFromDto method defined in the IFactoryDto interface. It receives a dto and returns
     * an entity.
     * @param dto
     * @return
     */
    @Override
    public Question buildFromDto(QuestionDto dto) {
        Question question = new Question();
        Quiz quiz = this.factoryQuizDto.buildFromDto(dto.getQuiz());
        question.setQuiz(quiz);
        question.setMovieList(getMovieListFromDto(dto.getMovies()));
        question.setStatus(StatusQuestion.valueOf(dto.getStatus()));
        return question;
    }

    /**
     * The getMovieListFromDto method is a private utility method responsible for converting a list of MovieDto objects
     * into a list of corresponding Movie entities.
     * @param dtoList
     * @return
     */
    private List<Movie> getMovieListFromDto(List<MovieDto> dtoList) {
        List<Movie> entityList = new ArrayList<>();
        for (MovieDto dto : dtoList) {
            Movie entity = this.factoryMovieDto.buildFromDto(dto);
            entityList.add(entity);
        }
        return entityList;
    }

}
