package utn.frsf.com.yoreclamo.Interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import utn.frsf.com.yoreclamo.Adaptador.Adaptador;
import utn.frsf.com.yoreclamo.ApiRest.ReclamoApiRest;
import utn.frsf.com.yoreclamo.Model.Reclamo;
import utn.frsf.com.yoreclamo.R;

public class MisReclamosActivity extends AppCompatActivity {

    private ListView listaReclamos;
    private Adaptador listaReclamosAdaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reclamos);

        // Inicializamos las variables
        //Reclamo[] misReclamos = new ReclamoApiRest().listarEnArreglo();

        listaReclamos = (ListView) findViewById(R.id.listaReclamos);
        ArrayList<Reclamo> lista = new ReclamoApiRest().listarEnLista();
        listaReclamosAdaptador = new Adaptador(MisReclamosActivity.this, /*Reclamo.LISTA_EJEMPLO*/lista);
        listaReclamos.setAdapter(listaReclamosAdaptador);

        //registerForContextMenu(listaReclamos);

        listaReclamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reclamo item = (Reclamo) parent.getItemAtPosition(position);
                Log.i("Reclamo",item.getDescripcion());
            }
        });

    }



}

