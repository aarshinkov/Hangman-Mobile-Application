package com.bulgaria.a_arshinkov.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class MultiplayerWordEnter extends AppCompatActivity {

    Button beginMultiGameButton;
    EditText enterMultiPlWordET;
    String wordToGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_word_enter);

        beginMultiGameButton = findViewById(R.id.beginMultiGameButton);
        beginMultiGameButton.setOnClickListener(onClick);

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MultiplayerWordEnter.this, Multiplayer.class);

            enterMultiPlWordET = findViewById(R.id.enterMultiPlWordET);
            wordToGuess = enterMultiPlWordET.getText().toString();

            //To use StringUtils add compile 'org.apache.commons:commons-lang3:3.7' to build.gradle(Module: app) in dependencies
            if (StringUtils.isBlank(wordToGuess)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please, enter a word!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            intent.putExtra("WORD_IDENTIFIER", wordToGuess);
            enterMultiPlWordET.setText("");
            startActivity(intent);
        }
    };
}
