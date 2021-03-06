package utn.frsf.com.yoreclamo.Control.ApiRest;


import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import utn.frsf.com.yoreclamo.Entidad.Reclamo;

public class ReclamoApiRest implements ApiImplementation<Reclamo> {

    @Override
    public void crear(Reclamo entrada) {
        new AsynTaskBD().execute(ReclamoDBApiRestMetaData.TABLA_RECLAMO,"POST",entrada.toJSON());
    }

    @Override
    public void borrar(Integer id) {
        new AsynTaskBD().execute(ReclamoDBApiRestMetaData.TABLA_RECLAMO,"DELETE",id);
    }

    @Override
    public void actualizar(Reclamo entrada) {
        new AsynTaskBD().execute(ReclamoDBApiRestMetaData.TABLA_RECLAMO,"PUT",entrada.toJSON(),entrada.getId());
    }

    public Reclamo buscarPorId(Integer id){
        ArrayList<Reclamo> entrada = listarEnLista();
        for(Reclamo i: entrada)
            if(i.getId()==id)
                return i;
        return null;
    }

    public Reclamo buscarLatLng(LatLng latLng){
        ArrayList<Reclamo> entrada = listarEnLista();
        for(Reclamo i: entrada)
            if(i.getUbicacion().equals(latLng))
                return i;
        return null;
    }

    public Reclamo[] listarEnArreglo(){

        Reclamo[] salida = null;
        try {
            JSONArray lista = (JSONArray) new AsynTaskBD().execute(ReclamoDBApiRestMetaData.TABLA_RECLAMO,"GET").get();
            salida = new Reclamo[lista.length()];
            for(int i=0;i<lista.length();i++)
                salida[i]= new Reclamo((JSONObject) lista.get(i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return salida;
    }

    public ArrayList<Reclamo> listarEnLista(){

        ArrayList<Reclamo> salida = null;
        try {
            JSONArray lista = (JSONArray) new AsynTaskBD().execute(ReclamoDBApiRestMetaData.TABLA_RECLAMO,"GET").get();
            salida = new ArrayList<Reclamo>();
            for(int i=0;i<lista.length();i++)
                salida.add(new Reclamo((JSONObject) lista.get(i)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return salida;
    }
}
