package co.uniquindio.estructuras.modeloJUnit;

import java.io.Serializable;



public class Cola implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	//Variables
	public Tarea primero;
	public Tarea ultimo;
	public int tamanio;

	
	/**
	 * Agrega un elemento en la Cola
	 * 
	 * @param dato elemento a guardar en la Cola
	 */
	public void encolar(Tarea tareaNueva)
	{	
		
		if(estaVacia()) 
		{
			primero = ultimo = tareaNueva;
		}
		else
		{
			ultimo.conectar(tareaNueva, 0);
			ultimo = tareaNueva;
		}
		
		tamanio++;
	}
	
	
	/**
	 * Retorna y elimina el elemento que está al incio de la Cola
	 * 
	 * @return Primer elemento de la Cola
	 */
	public Tarea desencolar() 
	{	
		if(estaVacia())
		{
			throw new RuntimeException("La Cola está vacía");
		}
		
		Tarea tarea = primero;
		primero = primero.seguirEnlace(0);
		
		if(primero==null)
		{
			ultimo = null;
		}
		tamanio--;
		
		return tarea;
	}
	

	/**
	 * Obtiene el ultiimo elemento de la cola
	 * 
	 */
	public Tarea obtenerUltimo() 
	{
		Tarea aux=null;
		Tarea aux2= ultimo;
		
		aux=aux2;
		
		return aux;
	}
	
	
	/**
	 * Verifica si la Cola está vacía
	 * 
	 * @return true si está vacía
	 */
	public boolean estaVacia() 
	{
		return primero == null;
	}
	
	
	/**
	 * Borra completamente la Cola
	 */
	public void borrarCola() 
	{
		primero = ultimo = null;
		tamanio = 0;
	}

	
	/**
	 * @return the primero
	 */
	public Tarea getPrimero()
	{
		return primero;
	}

	
	/**
	 * @return the ultimo
	 */
	public Tarea getUltimo() 
	{
		return ultimo;
	}

	
	/**
	 * @return the tamaño
	 */
	public int size() 
	{
		return tamanio;
	}
	
	
	/**
	 * Verifica si la Cola es idéntica a la actual
	 * 
	 * @param cola Cola a comparar
	 * @return True si son iguales
	 */
	public boolean sonIdenticas(Cola cola)
	{	
		Cola clon1 =  clone();
		Cola clon2 =  cola.clone();
		
		if(clon1.size() == clon2.size())
		{	
			while( !clon1.estaVacia())
			{				
				if( !clon1.desencolar().equals( clon2.desencolar()))
				{
					return false;
				}				
			}
		}
		else
		{
			return false;
		}
		return  true;
	}
	

	/**
	 * Clona la cola
	 * 
	 */
	@Override
	protected Cola clone() 
	{	
		Cola nueva = new Cola();
		Tarea aux = primero;
		
		while(aux!=null)
		{
			nueva.encolar(aux);
			aux = aux.seguirEnlace(0);
		}
		return nueva;		
	}
	
	
	public String imprimir() 
	{
		Tarea aux = primero;
		String cadena = "\nTeareas:\n\n";
		
		if(tamanio != 0) 
		{
			while(aux!=null)
			{
				cadena+= aux.toString();
				aux=aux.seguirEnlace(0);
			}
		}
		else 
		{
			cadena += "No hay Tareas\n\n";
		}
		
		return cadena;	
	}
}
