package utn.frsf.com.yoreclamo.Interfaz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import utn.frsf.com.yoreclamo.Control.ApiRest.ReclamoApiRest;
import utn.frsf.com.yoreclamo.Control.MyReceiver;
import utn.frsf.com.yoreclamo.Entidad.Reclamo;
import utn.frsf.com.yoreclamo.R;

public class AltaReclamo extends AppCompatActivity {

    private EditText editText_Titulo;
    private EditText editText_Nombre;
    private EditText editText_Telefono;
    private EditText editText_Email;
    private Button button_RealizarReclamo;
    private Button button_CancelarReclamo;
    private ImageView imageView_ImagenReclamo;
    private Bitmap photobmp;
    private Uri miImageUri;
    private final Integer ClaveIntent = 1991;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reclamo);

        editText_Titulo = (EditText) findViewById(R.id.editText_Titulo);
        editText_Nombre = (EditText) findViewById(R.id.editText_nombre);
        editText_Telefono = (EditText) findViewById(R.id.editText_Telefono);
        editText_Email = (EditText) findViewById(R.id.editText_Email);
        button_RealizarReclamo = (Button) findViewById(R.id.button_RealizarReclamo);
        button_CancelarReclamo = (Button) findViewById(R.id.button_CancelarReclamo);
        imageView_ImagenReclamo = (ImageView) findViewById(R.id.imageView_ImagenReclamo);

        imageView_ImagenReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, ClaveIntent);
            }
        });

        button_RealizarReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_Titulo.getText().toString().isEmpty()){
                    Toast.makeText(AltaReclamo.this, "Debe ingresar una descripción del reclamo", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(editText_Nombre.getText().toString().isEmpty()){
                    Toast.makeText(AltaReclamo.this, "Debe ingresar su nombre", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(editText_Telefono.getText().toString().isEmpty()){
                    Toast.makeText(AltaReclamo.this, "Debe ingresar un telefono", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(editText_Email.getText().toString().isEmpty()){
                    Toast.makeText(AltaReclamo.this, "Debe ingresar un correo electrónico", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng ubicacion = bundle.getParcelable("LatLng");

                Reclamo nuevoReclamo = new Reclamo();

                //Cargamos los datos en la variable nuevo reclamo
                nuevoReclamo.setNombre(editText_Nombre.getText().toString());
                nuevoReclamo.setDescripcion(editText_Titulo.getText().toString());
                nuevoReclamo.setUbicacion(ubicacion);
                nuevoReclamo.setEstado(getResources().getString(R.string.Reclamo_no_resuelto));
                nuevoReclamo.setEmail(editText_Email.getText().toString());
                nuevoReclamo.setTelefono(editText_Telefono.getText().toString());
                //fecha de nuevo reclamo
                Calendar c = new GregorianCalendar();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = df.format(c.getTime());
                nuevoReclamo.setFecha(fecha);

                //cargamos imagen a string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                photobmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                nuevoReclamo.setImagenReclamo(encodedImage);
                //nuevoReclamo.setImagenReclamo("prueba");

                //La tarea ascincronica para cargar en la base de datos
                // esta hecho en la otra aplicacion de pablo (va aca abajo)
                new ReclamoApiRest().crear(nuevoReclamo);





                Intent intent_Notificacion = new Intent("EmisionAltaReclamo");
                Bundle b = new Bundle();
                b.putDouble("LatLng-Lat",nuevoReclamo.getUbicacion().latitude);
                b.putDouble("LatLng-Lng",nuevoReclamo.getUbicacion().longitude);
                b.putString("Estado",getResources().getString(R.string.Reclamo_en_solucion));
                intent_Notificacion.putExtra("bundle",b);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AltaReclamo.this,0,intent_Notificacion,0);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

                Random r = new Random();
                int tiempo = r.nextInt(10 - 5 + 1) + 5; // Entre 5 y 10 segundos demora procesar el reclamo
                am.set(AlarmManager.RTC,System.currentTimeMillis() + tiempo * 1000,pendingIntent);

                //Éxito
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });

        button_CancelarReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ClaveIntent && resultCode == RESULT_OK) {

            miImageUri = data.getData();
           //String aaa = miImageUri.getEncodedPath();
            //String a = getRealPathFromURI(miImageUri);
            //Log.i("PATH - ",a);
            //photobmp = BitmapFactory.decodeFile(a);

            imageView_ImagenReclamo.setImageURI(miImageUri);
            imageView_ImagenReclamo.setTag(miImageUri.getPath());

            imageView_ImagenReclamo.buildDrawingCache();
            photobmp = imageView_ImagenReclamo.getDrawingCache();

        }
    }
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
