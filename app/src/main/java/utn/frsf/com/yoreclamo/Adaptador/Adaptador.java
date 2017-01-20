package utn.frsf.com.yoreclamo.Adaptador;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import utn.frsf.com.yoreclamo.Model.Reclamo;
import utn.frsf.com.yoreclamo.R;

public class Adaptador extends BaseAdapter {

    private Context mContext;
    private Reclamo[] mReclamos;

    public Adaptador (Context context, Reclamo[] reclamos){
        mContext = context;
        mReclamos = reclamos;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Reclamo[] getReclamos() {
        return mReclamos;
    }

    public void setReclamos(Reclamo[] reclamos) {
        mReclamos = reclamos;
    }

    @Override
    public int getCount() {
        return mReclamos.length;
    }

    @Override
    public Object getItem(int position) {
        return mReclamos[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fila_reclamo,null);
            holder = new ViewHolder();
            holder.descripcion = (TextView) convertView.findViewById(R.id.descripcion);
            holder.fecha = (TextView) convertView.findViewById(R.id.fecha);
            holder.imagen = (ImageButton) convertView.findViewById(R.id.imageButton);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.descripcion.setText(mReclamos[position].getDescripcion());
        holder.fecha.setText(mReclamos[position].getFecha());
        byte[] decodedString = Base64.decode(mReclamos[position].getImagenReclamo(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);

        // Setear la imagen
        // holder.descripcion.setText(mReclamos[position].getImagePath());

        return convertView;
    }

    public class ViewHolder {
        TextView descripcion;
        TextView fecha;
        ImageButton imagen;

    }
}
