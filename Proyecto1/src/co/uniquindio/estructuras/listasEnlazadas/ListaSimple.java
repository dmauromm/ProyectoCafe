package co.uniquindio.estructuras.listasEnlazadas;

import java.io.Serializable;

import co.uniquindio.estructuras.modelo.*;

public class ListaSimple implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Variables
	private Proceso primero;
	private Proceso actual;
	private Proceso ultimo;
	
	private int longitud;

	
	/**
	 * Contructor de la clase
	 * 
	 */
	public ListaSimple(Proceso primero, Proceso actual, Proceso ultimo, int longitud) 
	{
		this.primero = null;
		this.actual = null;
		this.ultimo = null;
		this.longitud = 0;
	}
	
	
	/**
	 * Contructor 2 de la clase
	 * 
	 */
	public ListaSimple() 
	{
		
	}


	/**
	 * @return the primero
	 */
	public Proceso getPrimero() 
	{
		return primero;
	}


	/**
	 * @param primero the primero to set
	 */
	public void setPrimero(Proceso primero)
	{
		this.primero = primero;
	}


	/**
	 * @return the actual
	 */
	public Proceso getActual() 
	{
		return actual;
	}


	/**
	 * @param actual the actual to set
	 */
	public void setActual(Proceso actual) 
	{
		this.actual = actual;
	}


	/**
	 * @return the ultimo
	 */
	public Proceso getUltimo() 
	{
		return ultimo;
	}


	/**
	 * @param ultimo the ultimo to set
	 */
	public void setUltimo(Proceso ultimo) 
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


	/**
	 * Metodo para comprobar si la lista esta vacia o no
	 * 
	 * @return retorna true o false
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

	
	/**
	 * Metodo para agregar procesos a la lista de forma secuencial
	 * 
	 * @param procesoNuevo : proceso nuevo que se agregara 
	 * 
	 */
	public void agregarNormal(Object procesoNuevo)
	{
		Proceso nuevo = (Proceso) procesoNuevo;
		
		if (longitud == 0) 
		{
			primero = nuevo;
			
		} 
		else
		{
			ultimo.conectar(nuevo, 0);
		}
		actual = nuevo;
		ultimo = nuevo;

		longitud++;
	}
	
	
	/**
	 * Metodo para agregar un proceso en la primera posicion
	 * 
	 * @param procesoNuevo : proceso nuevo que se agregara 
	 * 
	 */
	public void agregarPrimero(Object procesoNuevo)
	{
		Proceso nuevo= (Proceso) procesoNuevo;

		nuevo.conectar(primero, 0);
		primero = nuevo;
		longitud++;

		actual = nuevo;
	}
	
	
	/**
	 * Metodo para avanzar al siguiente de la lista
	 *  
	 */
	public void irSiguiente() 
	{
		if (actual != null) 
		{
			actual = actual.seguirEnlace(0);
		}
	}
	
	
	/**
	 * Metodo para agregar un proceso a la derecha de uno ya creado
	 * 
	 */
	public void insertarALaDerecha(Object procesoNuevo) 
	{
		Proceso nuevo=(Proceso) procesoNuevo;
	

		actual = primero;
		
		if(longitud==0)
		{
			agregarNormal(procesoNuevo);
		}
		else 
		{	
			if (actual != ultimo) 
			{
				nuevo.conectar(actual.seguirEnlace(0),0);
				actual.conectar(nuevo, 0);
				longitud++;
			}	
			nuevo=actual;
		}
	}
	
	
	/**
	 * Metodo para agregar un proceso al final de la lista
	 * 
	 */
	public void insertarAlUltimo(String id, String nombre)
	{
		Proceso nuevo=new Proceso();
		nuevo.setId(id);
		nuevo.setNombre(nombre);
		
		if(longitud==0)
		{	
			primero=nuevo;
			
		}
		else
		{
			if(ultimo!=null)
			{
				ultimo.conectar(nuevo, 0);
				longitud++;
				ultimo = nuevo;
			}	
		}
	}


	/**
	 * Metodo para agregar un proceso en una posicion especifica
	 * 
	 */
	public void agregarPorPosicion(Object proceso, int posicion)
	{
		Proceso nuevo=(Proceso) proceso;
		nuevo.setPosicion(posicion);
		
		boolean verificado = false;
		posicion = posicion - 1;
		actual = primero;
			
		
		for (int i = 0; i < longitud && verificado == false; i++) 
		{
			if (actual.getPosicion() == posicion) 
			{
				nuevo.conectar(actual.seguirEnlace(0), 0);
				actual.conectar(nuevo, 0);
				verificado = true;
				longitud++;
			}
			actual = actual.seguirEnlace(0);
		}
	}

	
	/**
	 * Metodo para recorrer desde el inicio
	 * 
	 */
	public void recorrerSencillo() 
	{
		actual= primero ;
			
		while(actual!=null)
		{
			actual= actual.seguirEnlace(0);		
		}
	}
		
	
	/**
	 * Metodo para eliminar el proceso actual de la lista
	 * 
	 */
	public void eliminarActual()
	{
		actual = primero.seguirEnlace(0);
		actual.desconectar(0);
		actual.conectar(actual.seguirEnlace(0), 0);
		
		longitud--;
		
		for (int i = 0; i < longitud; i++) 
		{
			actual.setPosicion(i);
			actual = actual.seguirEnlace(0);
		}
	}
	
	
	/**
	 * Metodo para eliminar un proceso de la lista
	 * 
	 */
	public void eliminarSoloUnElementoEnLista() 
	{
		if(ultimo==primero)
		{
			primero=null;
			actual=null;
			ultimo=null;
				
			longitud=0;
		}
	}
	
	
	/**
	 * Metodo para eliminar el primer proceso de la lista
	 * 
	 */
	public void eliminarPrimero() 
	{	
		if(primero==ultimo)
		{
			primero = null;
			ultimo  = null;
		}
		else
		{
			primero = primero.seguirEnlace(0);
			longitud--;
			actual = primero;
		}
	}
		
	
	/**
	 * Metodo para eliminar el ultimo proceso de la lista
	 * 
	 */
	public void eliminarUltimo()
	{	
		if(primero == ultimo)
		{
			ultimo = null;
		} 
		else 
		{
			actual = primero;
				
			while(actual.seguirEnlace(0) != ultimo)
			{
				actual = actual.seguirEnlace(0);
			}
				
			actual.seguirEnlace(0).desconectar(0);
			ultimo= actual;
			longitud--;
		}			
	}
		
		
	/**
	 * Metodo para eliminar un proceso dada su posicion de la lista
	 * 
	 */
	public void eliminarPorPosicion(int posicion) 
	{
		boolean x = false;
			
		actual = primero;
			
		int pos = posicion;
		Proceso aux=null;
			
		for (int i = 0; i < longitud && x == false; i++) 
		{
			if (i == pos) 
			{
				aux=actual.seguirEnlace(0);
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


	/**
	 * Metodo para vaciar la lista
	 * 
	 */
	public void vaciarLista()
	{
		Proceso aux=primero;
		Proceso aux2= aux;
			
		while(aux!=null && longitud!=0) 
		{
			if(aux2==aux)
			{
				aux.seguirEnlace(0);
				aux2=null;
				longitud--;
			}		
			aux2=aux;	
		}
	}
		
	
	/**
	 * Metodo para imprimir la lista
	 * 
	 */
	public String imprimirLista()
	{
		Proceso aux=new Proceso();
		aux = primero;
		String cadena = "\nProcesos:\n\n";
			
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
			cadena += "No hay Procesos";
		}
		return cadena;
	}
}
