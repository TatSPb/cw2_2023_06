package cw2_2023_06.service;

import cw2_2023_06.model.Question;

import java.util.Collection;

public interface ExaminerService {
    public Collection<Question> getQuestions(int amount);
}
