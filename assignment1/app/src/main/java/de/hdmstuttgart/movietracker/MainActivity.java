package de.hdmstuttgart.movietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count=0;
    TextView textView;
    Intent intent;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> count());
    }

    private void count() {
        textView = findViewById(R.id.textView);
        count++;
        if(count<=4) {
            textView.setText(getString(R.string.clicked_x_times,count));
        }else{
            textView.setText("Your count is " + count);
            intent = new Intent(this, ResultActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

}