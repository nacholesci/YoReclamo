package utn.frsf.com.yoreclamo.Entidad;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import utn.frsf.com.yoreclamo.Control.ApiRest.ReclamoDBApiRestMetaData;

public class Reclamo {

    private int mId;
    private String mDescripcion;
    private String mFecha;
    private LatLng mUbicacion;
    private String mNombre;
    private String mEmail;
    private String mTelefono;
    private String mEstado;
    private String mImagenReclamo;

    public Reclamo (){};
    public Reclamo (JSONObject jsonObject){
        try {
            mId = jsonObject.getInt(ReclamoDBApiRestMetaData.TablaReclamoMetaData.ID);
            mDescripcion = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.DESCRIPCION);
            mFecha = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.FECHA);
            mUbicacion = new LatLng(
                    jsonObject.getDouble(ReclamoDBApiRestMetaData.TablaReclamoMetaData.LATITUD),
                    jsonObject.getDouble(ReclamoDBApiRestMetaData.TablaReclamoMetaData.LONGITUD)
            );
            mNombre = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.NOMBRE);
            mEmail = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.EMAIL);
            mTelefono = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.TELEFONO);
            mEstado = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.ESTADO);
            mImagenReclamo = jsonObject.getString(ReclamoDBApiRestMetaData.TablaReclamoMetaData.IMAGENRECLAMO);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public Reclamo (int id, String descripcion, String fecha,LatLng Ubicacion, String nombre,
                                        String email, String telefono, String estado, String image){

        this.setId(id);
        this.setDescripcion(descripcion);
        this.setFecha(fecha);
        this.setUbicacion(Ubicacion);
        this.setNombre(nombre);
        this.setEmail(email);
        this.setTelefono(telefono);
        this.setEstado(estado);
        this.setImagenReclamo(image);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImagenReclamo() {
        return mImagenReclamo;
    }

    public void setImagenReclamo(String image) {
        mImagenReclamo = image;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getTelefono() {
        return mTelefono;
    }

    public void setTelefono(String telefono) {
        mTelefono = telefono;
    }

    public String getEstado() {
        return mEstado;
    }

    public void setEstado(String estado) {
        mEstado = estado;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }

    public String getFecha() {
        return mFecha;
    }

    public void setFecha(String fecha) {
        mFecha = fecha;
    }

    public LatLng getUbicacion() {return mUbicacion;}

    public void setUbicacion(LatLng ubicacion) {mUbicacion = ubicacion;}



    public static final Reclamo[]  LISTA_EJEMPLO= new Reclamo[]{

            new Reclamo(1,  "Pozo", "2016/12/25",  new LatLng(22.8,22.0),  "Ema",
                    "ema@hotmail.com",  "422222","No resuelto",  "sdasd"),
            new Reclamo(2, "Semaforo", "2016/12/25",  new LatLng(22.8,22.0),  "Ema",
                                "ema@hotmail.com",  "422222","No resuelto",  "sdasd"),
            new Reclamo(3, "Pozo", "2016/12/25",  new LatLng(22.8,22.0),  "Ema",
                                "ema@hotmail.com",  "422222","No resuelto",  "sdasd")

    };

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.DESCRIPCION,mDescripcion);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.FECHA,mFecha);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.LATITUD,mUbicacion.latitude);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.LONGITUD,mUbicacion.longitude);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.NOMBRE,mNombre);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.EMAIL,mEmail);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.TELEFONO,mTelefono);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.ESTADO,mEstado);
            jsonObject.put(ReclamoDBApiRestMetaData.TablaReclamoMetaData.IMAGENRECLAMO,mImagenReclamo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("JSON-RECLAMO: ",jsonObject.toString());
        return jsonObject;
    }
}
