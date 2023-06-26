package cw2_2023_06.service.impl;

import cw2_2023_06.exception.IncorrectAmountOfQuestions;
import cw2_2023_06.model.Question;
import cw2_2023_06.service.ExaminerService;
import cw2_2023_06.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new IncorrectAmountOfQuestions();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
