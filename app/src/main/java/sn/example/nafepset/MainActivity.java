package sn.example.nafepset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sn.example.NaFepSet.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button tof = findViewById(R.id.put);
        tof.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent ordre1=new Intent(getApplicationContext(),SignupActivity.class);
                                       startActivity(ordre1);
                                   }
                               }
        );
    }
}