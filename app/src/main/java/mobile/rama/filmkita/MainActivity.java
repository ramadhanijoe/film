package mobile.rama.filmkita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Popular(View view){
        Intent i = new Intent(MainActivity.this,PopularMovie.class);
        startActivity(i);
    }
}
