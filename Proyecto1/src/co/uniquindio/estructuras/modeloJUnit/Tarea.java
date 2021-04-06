package co.uniquindio.estructuras.modeloJUnit;

import java.io.Serializable;
import java.util.ArrayList;

public class Tarea implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	//Variables
	private String nombre;
	private String descripcion;
    private boolean esObligatoria;
    private String esObligatoria2;
    private int duracion;
    
    private int posicion;
    public ArrayList<Tarea> enlaces;
    private int limiteConexiones;
    
    
    /**
	 * Constructor de la clase
	 */
	public Tarea(String nombre, String descripcion, boolean esObligatoria, int duracion) 
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.esObligatoria = esObligatoria;
		this.duracion = duracion;
		
		enlaces = new ArrayList<Tarea>();

		enlaces.add(null);
		
		limiteConexiones = 0;
	}

	
	/**
	 * Constructor 2 de la clase
	 */
	public Tarea() 
	{
		enlaces = new ArrayList<Tarea>();

		enlaces.add(null);
		
		limiteConexiones = 0;
		
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() 
	{
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() 
	{
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}


	/**
	 * @return the esObligatoria
	 */
	public boolean esObligatoria() 
	{
		return esObligatoria;
	}


	/**
	 * @param esObligatoria the esObligatoria to set
	 */
	public void setEsObligatoria(boolean esObligatoria)
	{
		setEsObligatoria2((esObligatoria)?"Si":"No");
		this.esObligatoria = esObligatoria;
	}


	/**
	 * @return the esObligatoria2
	 */
	public String getEsObligatoria2()
	{
		return esObligatoria2;
	}


	/**
	 * @param esObligatoria2 the esObligatoria2 to set
	 */
	public void setEsObligatoria2(String esObligatoria2) 
	{
		this.esObligatoria2 = esObligatoria2;
	}

	/**
	 * @return the esObligatoria
	 */
	public boolean isEsObligatoria() {
		return esObligatoria;
	}
	/**
	 * @return the duracion
	 */
	public int getDuracion() 
	{
		return duracion;
	}


	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(int duracion) 
	{
		this.duracion = duracion;
	}

	
	/**
	 * @return the posicion
	 */
	public int getPosicion() 
	{
		return posicion;
	}


	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(int posicion) 
	{
		this.posicion = posicion;
	}


	/**
	 * @return the enlaces
	 */
	public ArrayList<Tarea> getEnlaces() 
	{
		return enlaces;
	}


	/**
	 * @param enlaces the enlaces to set
	 */
	public void setEnlaces(ArrayList<Tarea> enlaces)
	{
		this.enlaces = enlaces;
	}


	/**
	 * Metodo que permite concetar un nodo con el otro
	 * 
	 * @param destino nodo al que se va a conectar
	 * @param indice  indice en el que se va a conectar //Como es una lista sencilla
	 *                en este caso seria el 0
	 * @throws limiteConexionesException
	 */

	public void conectar(Tarea destino, int indice) 
	{
		if (indice <= limiteConexiones || limiteConexiones == 0) 
		{
			if (indice >= enlaces.size())
			{
				int n = indice - enlaces.size();
		
				for (int i = 0; i < n; i++) 
				{
					enlaces.add(null);
				}
			
			enlaces.add(destino);
			} 
			else 
			{
				enlaces.set(indice, destino);
			}
		}
	}
	
	
	/**
	 * Retorna el nodo destino al que esta concetado el nodo en esa posicion
	 * 
	 * @param indice
	 * @return
	 */
	public Tarea seguirEnlace(int indice) 
	{
		return enlaces.get(indice);
	}
	
	
	/**
	 * Metodo que desconecta un nodo en un indice especifico
	 * 
	 * @param indice
	 */
	public void desconectar(int indice)
	{
		enlaces.set(indice, null);
	}


	@Override
	public String toString() 
	{
		return "Nombre:" + nombre +"\n"+ "Descripcion:" + descripcion +"\n"+ "¿EsObligatoria?" + esObligatoria
				+"\n"+ "Duracion:" + duracion + "\n"+"\n";
	}
}
