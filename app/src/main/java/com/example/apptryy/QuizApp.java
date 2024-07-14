package com.example.apptryy;


import android.app.Application;

import java.util.List;

public class QuizApp extends Application {
    private static List<Question> questionList;

    public static List<Question> getQuestionList() {
        return questionList;
    }

    public static void setQuestionList(List<Question> questions) {
        questionList = questions;
    }
}

