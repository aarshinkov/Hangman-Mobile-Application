package com.bulgaria.a_arshinkov.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    TextView wordShowTV;
    Button returnToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        wordShowTV = findViewById(R.id.wordShowTV);

        String wordSent = getIntent().getStringExtra("WORD_REVEAL");

        wordShowTV.setText("The word was: " + wordSent);

        returnToMainButton = findViewById(R.id.returnToMainButton);

        returnToMainButton.setOnClickListener(onClick);

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GameOver.this, MainActivity.class);
            startActivity(intent);
        }
    };
}
