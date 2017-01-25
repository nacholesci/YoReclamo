package utn.frsf.com.yoreclamo.Adaptador;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import utn.frsf.com.yoreclamo.Model.Reclamo;
import utn.frsf.com.yoreclamo.R;

public class Adaptador extends ArrayAdapter<Reclamo> {


    public Adaptador (Context context, List<Reclamo> reclamos){
        super(context, 0, reclamos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        if(convertView == null) {
            convertView = inflater.inflate(R.layout.fila, parent,false);

            holder = new ViewHolder();
            holder.descripcion = (TextView) convertView.findViewById(R.id.descripcion);
            holder.fecha = (TextView) convertView.findViewById(R.id.fecha);
            holder.imagen = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Reclamo item = getItem(position);
        byte[] decodedString = Base64.decode(item.getImagenReclamo(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.descripcion.setText(item.getDescripcion());
        holder.fecha.setText(item.getFecha());
        holder.imagen.setImageBitmap(decodedByte);

        return convertView;
    }

    static class ViewHolder {
        TextView descripcion;
        TextView fecha;
        ImageView imagen;

    }
}
