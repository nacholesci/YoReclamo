package utn.frsf.com.yoreclamo.Interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import utn.frsf.com.yoreclamo.Adaptador.Adaptador;
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
        listaReclamos = (ListView) findViewById(R.id.listaReclamos);
        listaReclamosAdaptador = new Adaptador(this, Reclamo.LISTA_EJEMPLO);
        listaReclamos.setAdapter(listaReclamosAdaptador);

        registerForContextMenu(listaReclamos);
    }



}

