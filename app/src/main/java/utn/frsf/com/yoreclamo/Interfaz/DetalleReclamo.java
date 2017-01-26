package utn.frsf.com.yoreclamo.Interfaz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import utn.frsf.com.yoreclamo.R;

public class DetalleReclamo extends AppCompatActivity {

    private TextView textView_detalle_Descripcion;
    private TextView textView_detalle_Estado;
    private TextView textView_detalle_Fecha;
    private TextView textView_detalle_Nombre;
    private TextView textView_detalle_Numero;
    private TextView textView_detalle_Email;
    private ImageView imageView_detalle_ImagenReclamo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo);
        textView_detalle_Descripcion = (TextView) findViewById(R.id.textView_detalle_Descripcion);
        textView_detalle_Estado = (TextView) findViewById(R.id.textView_detalle_Estado);
        textView_detalle_Fecha = (TextView) findViewById(R.id.textView_detalle_Fecha);
        textView_detalle_Nombre = (TextView) findViewById(R.id.textView_detalle_Nombre);
        textView_detalle_Numero = (TextView) findViewById(R.id.textView_detalle_Numero);
        textView_detalle_Email = (TextView) findViewById(R.id.textView_detalle_Email);
        imageView_detalle_ImagenReclamo = (ImageView) findViewById(R.id.imageView_detalle_ImagenReclamo);

        textView_detalle_Descripcion.setText(getIntent().getStringExtra("reclamo_descripcion"));
        textView_detalle_Estado.setText(getIntent().getStringExtra("reclamo_estado"));
        textView_detalle_Fecha.setText(getIntent().getStringExtra("reclamo_fecha"));
        textView_detalle_Nombre.setText(getIntent().getStringExtra("reclamo_nombre"));
        textView_detalle_Numero.setText(getIntent().getStringExtra("reclamo_numero"));
        textView_detalle_Email.setText(getIntent().getStringExtra("reclamo_email"));
        byte[] decodedString = Base64.decode(getIntent().getStringExtra("reclamo_imagen"), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView_detalle_ImagenReclamo.setImageBitmap(decodedByte);


    }
}
