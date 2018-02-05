package com.bulgaria.a_arshinkov.hangman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Multiplayer extends AppCompatActivity {

    String mWord;
    short mFailCounter;
    short mGuessedLetters;

    Button tryButton;
    EditText enterLetterET;
    TextView wrongTypedLettTV;

    List<String> usedLetters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");

        Log.d("Word", wordSent);

        mWord = wordSent.toUpperCase();

        Log.d("Word lenght is", String.valueOf(mWord.length()));

        createTextViews(wordSent);

        tryButton = findViewById(R.id.tryButton);
        mFailCounter = 0;
        mGuessedLetters = 0;

        tryButton.setOnClickListener(onClick);

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            enterLetterET = findViewById(R.id.enterLetterET);

            String letter = enterLetterET.getText().toString().toUpperCase();

            enterLetterET.setText("");

            //Hides the keyboard in order for player to see the picture
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            if (letter.length() == 0){
                Toast toast = Toast.makeText(getApplicationContext(), "Please, enter a letter!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                checkedLetter(letter);
            }

            if (mGuessedLetters == mWord.length()) {
                Intent intent = new Intent(Multiplayer.this, YouWin.class);

                intent.putExtra("WORD_REVEAL", mWord);

                startActivity(intent);
            }

            Log.d("MYLOG", "The letter is " + letter);
        }
    };

    public void checkedLetter(String introducedLetter) {

        boolean letterGuessed = false;


        for (int i = 0; i < mWord.length(); i++) {
            if (mWord.charAt(i) == introducedLetter.charAt(0)) {
                letterGuessed = true;
                showLettersAtIndex(i, introducedLetter.charAt(0));
                mGuessedLetters++;
            }
        }

        if (usedLetters.contains(introducedLetter)) {
            Toast toast = Toast.makeText(getApplicationContext(), "You have already used that letter!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (!letterGuessed) {
            letterFailed(Character.toString(introducedLetter.charAt(0)));
        }

        usedLetters.add(introducedLetter);
    }

    private void letterFailed(String letterFailed) {
        mFailCounter++;

        wrongTypedLettTV = findViewById(R.id.wrongTypedLettTV);
        String previousFail = wrongTypedLettTV.getText().toString();

        if (mFailCounter == 1) {
            wrongTypedLettTV.setText(letterFailed);
        } else {
            wrongTypedLettTV.setText(previousFail + ", " + letterFailed);
        }

        ImageView imageView = findViewById(R.id.hangmanImV);

        switch (mFailCounter) {
            case 1:
                imageView.setImageResource(R.drawable.hangman_02);
                break;
            case 2:
                imageView.setImageResource(R.drawable.hangman_03);
                break;
            case 3:
                imageView.setImageResource(R.drawable.hangman_04);
                break;
            case 4:
                imageView.setImageResource(R.drawable.hangman_05);
                break;
            case 5:
                imageView.setImageResource(R.drawable.hangman_06);
                break;
            default:
                imageView.setImageResource(R.drawable.hangman_07);
                Intent intent = new Intent(Multiplayer.this, GameOver.class);

                intent.putExtra("WORD_REVEAL", mWord);

                startActivity(intent);
                break;
        }
    }

    private void showLettersAtIndex(int position, char letterGuessed) {
        LinearLayout layoutLetter = findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));
    }

    public void createTextViews(String word) {
        LinearLayout layoutLetters = findViewById(R.id.layoutLetters);

        for (int i = 0; i < word.length(); i++) {
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);

            layoutLetters.addView(newTextView);
        }
    }
}
