package co.uniquindio.estructuras.modeloJUnit;


import java.io.Serializable;


public class ListaDoble implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Variables
	private Actividad primero;
	private Actividad actual;
	private Actividad ultimo;

	private int longitud;
	
	
	/**
	 * Contructor de la clase
	 * 
	 */
	public ListaDoble()
	{
		primero = null;
		actual = null;
		ultimo = null;

		longitud = 0;
	}
	

//Set y gets
	
	/**
	 * @return the primero
	 */
	public Actividad getPrimero() 
	{
		return primero;
	}


	/**
	 * @param primero the primero to set
	 */
	public void setPrimero(Actividad primero)
	{
		this.primero = primero;
	}


	/**
	 * @return the actual
	 */
	public Actividad getActual() 
	{
		return actual;
	}


	/**
	 * @param actual the actual to set
	 */
	public void setActual(Actividad actual) 
	{
		this.actual = actual;
	}


	/**
	 * @return the ultimo
	 */
	public Actividad getUltimo() 
	{
		return ultimo;
	}


	/**
	 * @param ultimo the ultimo to set
	 */
	public void setUltimo(Actividad ultimo) 
	{
		this.ultimo = ultimo;
	}


	/**
	 * @return the longitud
	 */
	public int size() 
	{
		return longitud;
	}


	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(int longitud)
	{
		this.longitud = longitud;
	}

	
//Metodos para comprobar si esta vacia	
	
	/**
	 * Metodo para verificar si la lista esta vacia
	 * 
	 * @return true si esta vacia y else si tiene algun elemento
	 */
	public boolean estaVacia()
	{
		if (primero == null) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	
//Metodos de agregar
	
	/**
	 * Metodo agregar actividades a la lista
	 * 
	 * @param actividad : actividad que se agregara a la lista
	 * 
	 */
	public void agregar(Actividad actividad) 
	{
		Actividad nuevo= actividad;
		
		if (longitud == 0) 
		{
			primero = nuevo;
		} 
		else 
		{
			ultimo.conectar(nuevo, 0);
			nuevo.conectar(ultimo, 1);
		}
		actual = nuevo;
		ultimo = nuevo;
		longitud++;
	}
	
	
	/**
	 * Metodo agregar una actividad a la lista en la primera posicion
	 * 
	 * @param actividad : actividad que se agregara a la lista
	 * 
	 */
	public void agregarActividadPrimero(Actividad nuevaActividad)
	{
		Actividad nuevo= nuevaActividad;

		nuevo.conectar(primero, 0);
		primero.conectar(nuevo, 1);
		primero = nuevo;
		actual = nuevo;
		
		longitud++;
		
	}

	
	/**
	 * Metodo para agregar una actividad despues de una actividad especificada por el usuario
	 * 
	 * @param posicion : posicion donde se agregara
	 * @param actividadNueva : actividad que se agregara a la lista
	 */
	public void agregarActividadEnPosicion(int posicion, Actividad actividadNueva) 
	{	
		boolean x = false;
		actual = primero;
		Actividad aux=null;
		
		for (int i = 0; i < longitud && x == false; i++) 
		{
			if (i == posicion) 
			{
				aux= actual.seguirEnlace(0);
				actual.desconectar(0);
				actual.conectar(actividadNueva, 0);
				actual.seguirEnlace(0).conectar(actual, 1);
				actual.seguirEnlace(0).conectar(aux, 0);
				actual=actual.seguirEnlace(0);
				longitud++;
				x = true;
			}
			else
			{
				actual = actual.seguirEnlace(0);
			}
		}
	}
	
	
	/**
	 * Metodo para agregar una actividad despues de la ultima actividad de la lista
	 * 
	 * @param actividadNueva : actividad que se agregara a la lista
	 */
	public void agregarActividadAlUltimo(Actividad actividadNueva)
	{
		Actividad nuevo= actividadNueva;
		
		if(longitud==0)
		{
			primero=nuevo;
		}
		else
		{
			ultimo.conectar(nuevo, 0);
			nuevo.conectar(ultimo, 1);
			ultimo = nuevo;
			actual=ultimo;
			longitud++;
		}
	}
	
	
	/**
	 * Metodo para agregar una actividad despues de la ultima actividad creada en la lista
	 * 
	 * @param actividadNueva : actividad que se agregara a la lista
	 */
	public void agregarDespuesDeLaUltimaCreada(Actividad actividadNueva) 
	{	
		Actividad aux;
		
		if(actual==primero) 
		{	
			actual.conectar(actividadNueva, 0);
			actual.seguirEnlace(0).conectar(actual, 1);
		}
		else
		{	
			aux=actual.seguirEnlace(0);
			actual.conectar(actividadNueva, 0);
			actual.seguirEnlace(0).conectar(actual, 1);
			actual.seguirEnlace(0).conectar(aux, 0);
			longitud++;
		}
	}
	
	
//Metodos para eliminar
	
	/**
	 * Metodo para eliminar una actividad de la lista
	 * 
	 * @param nombre: nombre de la actividad a borrar
	 */
	public void eliminarPorElemento(String nombre)
	{
		boolean verificado = false;
		actual = primero;
		
		for (int i = 0; i < longitud && verificado == false; i++) 
		{
			if (actual.getNombre().equalsIgnoreCase(nombre)) 
			{
				actual.seguirEnlace(0).seguirEnlace(0).conectar(actual, 1);
				actual.conectar(actual.seguirEnlace(0).seguirEnlace(0), 0);
				verificado = true;
				longitud--;
			}
			actual = actual.seguirEnlace(0);
		}
		actual = primero;
	}
	
	
	/**
	 * Metodo para eliminar una actividad por una posicion dada de la lista
	 * 
	 * @param posicion : posicion a borrar de la lista
	 */
	public void eliminarPorPosicion(int posicion) 
	{
		boolean x = false;
		
		actual = primero;
		
		int pos = posicion;
		Actividad aux=null;
		
		for (int i = 0; i < longitud && x == false; i++) 
		{
			if (i == pos) 
			{
				aux= actual.seguirEnlace(0);
				x = true;
			}
			else
			{
				actual = actual.seguirEnlace(0);
			}
		}
		
		actual=primero;
		x=false;
		
		for(int i=0;i<longitud && x==false;i++) 
		{
			if(pos==0) 
			{
				primero=aux;
				longitud--;
			}
			else
			{	
				if(i==posicion-1) 
				{	
					actual.desconectar(0);
					actual.conectar(aux, 0);
					longitud--;
				}
				else
				{	
					actual=actual.seguirEnlace(0);
				}			
			}
		}
	}
	
	
//Metodos para recorrer	
	
	/**
	 * Metodo para ir a la sigueinte posicion
	 * 
	 */
	public boolean irSiguiente() 
	{
		if (actual != null) 
		{
			actual = actual.seguirEnlace(0);
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	
// Imprimir	
	
	/**
	 * Metodo para imprimir la lista
	 * 
	 */
	public String imprimir() 
	{
		Actividad aux = primero;
		String cadena = "\nActividades:\n\n";
		
		if(longitud != 0) 
		{
			while(aux!=null )
			{
				cadena+= aux.toString();
				aux=aux.seguirEnlace(0);
			}
		}
		else 
		{
			cadena += "No hay actividades\n";
		}
		
		return cadena;	
	}
}
