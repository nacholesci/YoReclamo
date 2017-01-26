package utn.frsf.com.yoreclamo.Control.ApiRest;


public interface ApiImplementation<T> {
    public void crear(T entrada);
    public void borrar(Integer id);
    public void actualizar(T entrada);
}
