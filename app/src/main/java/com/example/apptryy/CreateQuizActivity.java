package com.example.apptryy;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizActivity extends AppCompatActivity {
    private List<Question> questionList = new ArrayList<>();
    private int questionCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        EditText questionEditText = findViewById(R.id.questionEditText);
        EditText option1EditText = findViewById(R.id.option1EditText);
        EditText option2EditText = findViewById(R.id.option2EditText);
        EditText option3EditText = findViewById(R.id.option3EditText);
        EditText option4EditText = findViewById(R.id.option4EditText);
        EditText correctOptionEditText = findViewById(R.id.correctOptionEditText);
        Button addButton = findViewById(R.id.addButton);
        Button finishButton = findViewById(R.id.finishButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = questionEditText.getText().toString();
                String option1 = option1EditText.getText().toString();
                String option2 = option2EditText.getText().toString();
                String option3 = option3EditText.getText().toString();
                String option4 = option4EditText.getText().toString();
                int correctOption = Integer.parseInt(correctOptionEditText.getText().toString());

                questionList.add(new Question(question, option1, option2, option3, option4, correctOption));
                questionCounter++;

                if (questionCounter >= 20) {
                    addButton.setEnabled(false);
                }

                questionEditText.setText("");
                option1EditText.setText("");
                option2EditText.setText("");
                option3EditText.setText("");
                option4EditText.setText("");
                correctOptionEditText.setText("");
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionList.size() >= 10) {
                    // Save questions to a global state or database here
                    QuizApp.setQuestionList(questionList);
                    Toast.makeText(CreateQuizActivity.this, "Quiz created successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreateQuizActivity.this, "Please add at least 10 questions.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
