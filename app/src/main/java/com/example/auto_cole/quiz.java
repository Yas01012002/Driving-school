package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class quiz extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC;
    Button submitBtn;


    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);

        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);

        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("total questions:" + totalQuestion);
        loadNewQuestion();


    }

    void loadNewQuestion() {
        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);

    }

    void finishQuiz() {
        String passStatus="";
        if(score>totalQuestion*0.60){
            passStatus="Réussi";

        }else {
            passStatus="échoué";}
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score est:"+score+"sur"+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface,i) ->restartQuiz())
                .setCancelable(false)
                .show();

    }

    void restartQuiz() {
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }


    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.rgb(243,221,219));
        ansB.setBackgroundColor(Color.rgb(243,221,219));
        ansC.setBackgroundColor(Color.rgb(243,221,219));

        Button clickButton = (Button) view;
        if (clickButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        } else {
            selectedAnswer = clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.rgb(234,137,101));
        }

    }}
