package utn.frsf.com.yoreclamo.ApiRest;


import utn.frsf.com.yoreclamo.Model.Reclamo;

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
}
