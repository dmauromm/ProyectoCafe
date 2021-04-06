package co.uniquindio.estructuras.excepciones;

public class ObjetoRepetidoExcepcion extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public ObjetoRepetidoExcepcion(String mensaje) 
	{
		super(mensaje);
	}
}
