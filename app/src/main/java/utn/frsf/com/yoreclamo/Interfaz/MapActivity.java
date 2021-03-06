package utn.frsf.com.yoreclamo.Interfaz;

import utn.frsf.com.yoreclamo.Control.ApiRest.ReclamoApiRest;
import utn.frsf.com.yoreclamo.Entidad.Reclamo;
import utn.frsf.com.yoreclamo.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends AppCompatActivity implements com.google.android.gms.maps.OnMapReadyCallback {

    private GoogleMap myMap;
    private Marker nuevo;
    private MarkerOptions nuevoOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        myMap = googleMap; // Asigno al mapa una vez lista
        myMap.getUiSettings().setZoomControlsEnabled(true); // Controles de Zoom [ + / - ]
        myMap.getUiSettings().setMapToolbarEnabled(true); // Barra de herramientas
        myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() { // Creo un click para empezar a realizar un reclamo
            @Override
            public void onMapClick(final LatLng latLng) {
              /* Intent i = new Intent(MapActivity.this,AltaReclamoActivity.class);
                i.putExtra("coordenadas",latLng);
                startActivityForResult(i,CODIGO_RESULTADO_ALTA_RECLAMO);*/
                nuevoOpciones = new MarkerOptions();
                nuevoOpciones.position(latLng);

                nuevo = myMap.addMarker(nuevoOpciones);

                new AlertDialog.Builder(MapActivity.this) //Ventana de Dialogo
                        .setView(LayoutInflater.from(MapActivity.this).inflate(R.layout.alert_crear_reclamo, null))
                        .setMessage("")

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                nuevo.remove();
                            }
                        })
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle Locacion = new Bundle();
                                Locacion.putParcelable("LatLng",latLng);

                                Intent i = new Intent(MapActivity.this,AltaReclamo.class);
                                i.putExtra("bundle",Locacion);
                                startActivityForResult(i,1990);
                            }
                        })
                        .create()
                        .show();
            }

        });
        localizarMiPosicion();
        cargarMarcadores();
        myMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Reclamo[] Reclamos = new ReclamoApiRest().listarEnArreglo();
                Reclamo encontrado = null;
                for (int i=0;i<Reclamos.length;i++) {
                    if(Reclamos[i].getUbicacion().equals(marker.getPosition())) {
                        encontrado = Reclamos[i];
                        i=Reclamos.length;
                    }
                }
                //Bundle bundleReclamo = new Bundle();

                //bundleReclamo.putParcelable("bundleReclamo",encontrado);

                Intent i = new Intent(MapActivity.this,DetalleReclamo.class);
                i.putExtra("reclamo_descripcion", encontrado.getDescripcion());
                i.putExtra("reclamo_estado",encontrado.getEstado());
                i.putExtra("reclamo_fecha",encontrado.getFecha());
                i.putExtra("reclamo_nombre",encontrado.getNombre());
                i.putExtra("reclamo_numero",encontrado.getTelefono());
                i.putExtra("reclamo_email",encontrado.getEmail());
                i.putExtra("reclamo_imagen",encontrado.getImagenReclamo());
                startActivity(i);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1990 && resultCode == RESULT_OK) {
            /*nuevoOpciones.title(data.getStringExtra("Descripcion"));
            nuevo.remove();
            nuevo = myMap.addMarker(nuevoOpciones);*/
            cargarMarcadores();
        }
        else if (requestCode == 1990 && resultCode == RESULT_CANCELED) {
            nuevo.remove();
        }
    }

    private void localizarMiPosicion(){
        if (ContextCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},9999);
            return;
        }

        myMap.setMyLocationEnabled(true);
    }
    private void cargarMarcadores(){
        Reclamo[] Reclamos = new ReclamoApiRest().listarEnArreglo();
        for (int i=0;i<Reclamos.length;i++) {
            MarkerOptions a = new MarkerOptions().position(Reclamos[i].getUbicacion())
                    .title(Reclamos[i].getDescripcion());

            myMap.addMarker(a);
        }
    }
}
