package utn.frsf.com.yoreclamo.Model;

import android.widget.ImageView;

public class Reclamo {

    private int mId;
    private String mDescripcion;
    private String mFecha;
    private Double mLatitud;
    private Double mLongitud;
    private String mNombre;
    private String mEmail;
    private String mTelefono;
    private String mEstado;
    private String mImagePath;


    public Reclamo (int id, String descripcion, String fecha, Double latitud, Double longitud, String nombre,
                                        String email, String telefono, String estado, String imagePath){

        this.setId(id);
        this.setDescripcion(descripcion);
        this.setFecha(fecha);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setNombre(nombre);
        this.setEmail(email);
        this.setTelefono(telefono);
        this.setEstado(estado);
        this.setImagePath(imagePath);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
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

    public Double getLatitud() {
        return mLatitud;
    }

    public void setLatitud(Double latitud) {
        mLatitud = latitud;
    }

    public Double getLongitud() {
        return mLongitud;
    }

    public void setLongitud(Double longitud) {
        mLongitud = longitud;
    }



    public static final Reclamo[]  LISTA_EJEMPLO= new Reclamo[]{

            new Reclamo(1,  "Pozo", "2016/12/25",  25.05, 26.05,  "Ema",
                    "ema@hotmail.com",  "422222","No resuelto",  "sdasd"),
            new Reclamo(2, "Semaforo", "2016/12/25",  25.05, 26.05,  "Ema",
                                "ema@hotmail.com",  "422222","No resuelto",  "sdasd"),
            new Reclamo(3, "Pozo", "2016/12/25",  25.05, 26.05,  "Ema",
                                "ema@hotmail.com",  "422222","No resuelto",  "sdasd")

    };
}
