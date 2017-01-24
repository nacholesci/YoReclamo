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

import utn.frsf.com.yoreclamo.Model.Reclamo;
import utn.frsf.com.yoreclamo.R;

public class Adaptador extends ArrayAdapter<Reclamo> {

    private List<Reclamo> mReclamos;
    private LayoutInflater inflater;

    public Adaptador (Context context, List<Reclamo> reclamos){
        super(context, R.layout.fila_reclamo, reclamos);
        inflater = LayoutInflater.from(context);
        mReclamos=reclamos;
    }

    @Override
    public boolean isEnabled (int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View row = convertView;

        if(row == null) {
            row = inflater.inflate(R.layout.fila_reclamo, parent,false);
            holder = new ViewHolder();
            holder.descripcion = (TextView) row.findViewById(R.id.descripcion);
            holder.fecha = (TextView) row.findViewById(R.id.fecha);
            holder.imagen = (ImageButton) row.findViewById(R.id.imageButton);

            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        holder.descripcion.setText(mReclamos.get(position).getDescripcion());
        holder.fecha.setText(mReclamos.get(position).getFecha());
        byte[] decodedString = Base64.decode(mReclamos.get(position).getImagenReclamo(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);

        return row;
    }



    public class ViewHolder {
        TextView descripcion;
        TextView fecha;
        ImageButton imagen;

    }
}
