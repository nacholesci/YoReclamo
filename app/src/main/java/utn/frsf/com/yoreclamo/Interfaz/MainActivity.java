package utn.frsf.com.yoreclamo.Interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import utn.frsf.com.yoreclamo.R;

public class MainActivity extends AppCompatActivity {


    private Button botonMisReclamos;
    private Button botonMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        botonMisReclamos = (Button ) findViewById(R.id.misreclamosbutton);
        botonMapa = (Button) findViewById(R.id.vermapabutton) ;

        botonMisReclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MisReclamosActivity.class);
                startActivity(intent);
            }
        });

        botonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });




    }


}
