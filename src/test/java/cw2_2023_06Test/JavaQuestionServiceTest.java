package cw2_2023_06Test;

import cw2_2023_06.exception.*;
import cw2_2023_06.model.*;
import cw2_2023_06.service.*;
import cw2_2023_06.service.impl.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionServiceImpl();

    @BeforeEach
    public void beforeEach() {
        questionService.add(new Question("Q1", "A1"));
        questionService.add(new Question("Q2", "A2"));
        questionService.add(new Question("Q3", "A3"));
    }

    @AfterEach
    public void afterEach() {
        new HashSet<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void add1Test() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q4", "A4");

        Assertions.assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void add2Test() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q2", "A2");

        Assertions.assertThat(questionService.add("Q2", "A2"))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }


    @Test
    public void add2NegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add("Q2", "A2"));
    }

    @Test
    public void removeTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q2", "A2");

        Assertions.assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());

        Assertions.assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q4", "A4")));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(questionService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q3", "A3")
                );
    }

    @Test
    public void getRandomQuestionTest() {
        Assertions.assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();
        assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }
}