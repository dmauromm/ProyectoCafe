package co.uniquindio.estructuras.excepciones;

public class DatoNoEncontradoException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public DatoNoEncontradoException(String mensaje) 
	{
			super(mensaje);
	}
}
