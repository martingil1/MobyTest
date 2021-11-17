package moby.evaluacion.exceptions;

public class DniDuplicateException extends RuntimeException{

    public DniDuplicateException(){super("El dni ya esta registrado");}
}
