package es.marcosbejar.padeleasy_backend.exception;

public class PadeleasyException extends Exception{

    public class Mensajes{
        private Mensajes(){

        }
        public static final String CLAVE_ALMACEN_INCORRECTA = "La clave del almacén del certificado es inválida";
        public static final String ERROR_GENERAR_CERTIFICADO = "No se ha podido generar el certificado";
    }

    public PadeleasyException() {
    }

    public PadeleasyException(Exception e) {
        super(e);
    }

    public PadeleasyException(String error) {
        super(error);
    }
    public PadeleasyException(String error, Exception e) {
        super(error, e);
    }

    public PadeleasyException(Throwable cause) {
        super(cause);
    }
}
