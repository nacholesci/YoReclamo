package utn.frsf.com.yoreclamo.ApiRest;


import android.provider.BaseColumns;

public class ReclamoDBApiRestMetaData {
    public static final String NOMBRE_BD = "yoreclamo.json";
    public static final String TABLA_RECLAMO = "reclamo";

    public static class TablaReclamoMetaData implements BaseColumns{
        public static final String ID = "id";
        public static final String DESCRIPCION = "descripcion";
        public static final String FECHA = "fecha";
        public static final String LATITUD = "latitud";
        public static final String LONGITUD = "longitud";
        public static final String NOMBRE = "nombre";
        public static final String EMAIL = "email";
        public static final String TELEFONO = "telefono";
        public static final String ESTADO = "estado";
        public static final String IMAGENRECLAMO = "imagenreclamo";
    }
}
