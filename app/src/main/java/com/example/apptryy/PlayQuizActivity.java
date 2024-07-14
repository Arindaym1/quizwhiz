package com.example.apptryy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayQuizActivity extends AppCompatActivity {
    private List<Question> questionList;
    private List<Question> selectedQuestions;
    private int questionCounter = 0;
    private int score = 0;

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        nextButton = findViewById(R.id.nextButton);

        questionList = QuizApp.getQuestionList();

        if (questionList == null || questionList.isEmpty()) {
            Log.e("PlayQuizActivity", "Question list is null or empty");
            finish();
            return;
        }

        selectedQuestions = new ArrayList<>(questionList);
        Collections.shuffle(selectedQuestions);
        selectedQuestions = selectedQuestions.subList(0, Math.min(5, selectedQuestions.size()));

        showNextQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                showNextQuestion();
            }
        });
    }

    private void showNextQuestion() {
        if (questionCounter < selectedQuestions.size()) {
            Question currentQuestion = selectedQuestions.get(questionCounter);
            questionTextView.setText(currentQuestion.getQuestion());
            option1RadioButton.setText(currentQuestion.getOption1());
            option2RadioButton.setText(currentQuestion.getOption2());
            option3RadioButton.setText(currentQuestion.getOption3());
            option4RadioButton.setText(currentQuestion.getOption4());
            optionsRadioGroup.clearCheck();
            questionCounter++;
        } else {
            Intent intent = new Intent(PlayQuizActivity.this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer() {
        int selectedOption = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId())) + 1;
        int correctOption = selectedQuestions.get(questionCounter - 1).getCorrectOption();

        if (selectedOption == correctOption) {
            score++;
        }
    }
}
