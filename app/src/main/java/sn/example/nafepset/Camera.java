package sn.example.nafepset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import sn.example.NaFepSet.R;

public class Camera extends AppCompatActivity {


    final int REQUEST_ID_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        final Button photo = findViewById(R.id.button);
        final Button rt = findViewById(R.id.retur);
        final ImageView img = findViewById(R.id.imageView2);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ipslappel = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ipslappel, REQUEST_ID_IMAGE_CAPTURE);
            }
        });

        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ordre4 = new Intent(getApplicationContext(), Geolocalisation.class);
                startActivity(ordre4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bp = (Bitmap) extras.get("data");
            final ImageView img = findViewById(R.id.imageView2);
            img.setImageBitmap(bp);
        }
    }

}