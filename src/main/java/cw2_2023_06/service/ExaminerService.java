package cw2_2023_06.service;

import cw2_2023_06.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ExaminerService {
    public Collection<Question> getQuestions(int amount);
}
