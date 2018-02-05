package com.bulgaria.a_arshinkov.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button singlePl;
    Button multiPl;
    Button addWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //not casting to Button because in newer version of Android Studio is redundant
//        singlePl = (Button) findViewById(R.id.singlePlButton);
//        multiPl = (Button) findViewById(R.id.multiPlButton);
//        addWord = (Button) findViewById(R.id.addWordToDBBUtton);

        singlePl = findViewById(R.id.singlePlButton);
        multiPl = findViewById(R.id.multiPlButton);
        addWord = findViewById(R.id.addWordToDBBUtton);

        singlePl.setOnClickListener(onClick);
        multiPl.setOnClickListener(onClick);
        addWord.setOnClickListener(onClick);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.singlePlButton) {
                Intent intent = new Intent(MainActivity.this, Singleplayer.class);
                startActivity(intent);
            } else if (view.getId() == R.id.multiPlButton) {
                Intent intent = new Intent(MainActivity.this, MultiplayerWordEnter.class);
                startActivity(intent);
            } else if (view.getId() == R.id.addWordToDBBUtton) {
                Intent intent = new Intent(MainActivity.this, AddWordToDB.class);
                startActivity(intent);
            }
        }
    };
}
