package moby.evaluacion.exceptions;

public class TecnologiaNotFoundException extends RuntimeException{

    public TecnologiaNotFoundException(){super("Nombre de tecnologia o version inexistente");}
}
