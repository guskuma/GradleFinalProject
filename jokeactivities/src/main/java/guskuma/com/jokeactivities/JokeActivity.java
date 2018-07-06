package guskuma.com.jokeactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    public static final String PARAM_JOKE = "param_joke";

    private TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mJokeTextView = findViewById(R.id.jokeTextView);

        String joke = getIntent().getStringExtra(PARAM_JOKE);

        if(joke == null){
            Toast.makeText(this, "Nenhuma piada passada como parâmetro!", Toast.LENGTH_LONG).show();
        } else {
            mJokeTextView.setText(joke);
        }
    }
}
