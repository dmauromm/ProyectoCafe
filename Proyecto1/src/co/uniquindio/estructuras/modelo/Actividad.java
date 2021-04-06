package co.uniquindio.estructuras.modelo;

import java.util.ArrayList;
import java.io.Serializable;

import co.uniquindio.estructuras.excepciones.*;
import co.uniquindio.estructuras.listasEnlazadas.Cola;

public class Actividad implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	//=====Variables=====//
	private String nombre;
	private String descripcion;
	private boolean esObligatorio;
	private String esObligatorio2;
	public int tiempoMaximo;
	public int tiempoMinimo;

	
	public Cola colaTareas;
	public ArrayList<Actividad> enlaces;
	private int limiteConexiones;
	private int posicion;
	private Object dato;
	
	
	/**
	 * Constructor de la clase actividad
	 * 
	 * @param nombre : nombre de la actividad
	 * @param descripcion : descripcion de la actividad 
	 * @param esObligatorio : variable para conocer si es obligatoria o no
	 * 
	 */
	public Actividad(String nombre, String descripcion, boolean esObligatorio) 
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.esObligatorio = esObligatorio;
		
		
		colaTareas= new Cola();
		
		enlaces = new ArrayList<Actividad>();

		enlaces.add(null);
		
		limiteConexiones = 0;
	}


	/**
	 * Constructor 2 de la clase actividad
	 */
	public Actividad() 
	{
		colaTareas= new Cola();
		
		enlaces = new ArrayList<Actividad>();

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
	 * @return the esObligatorio2
	 */
	public String getEsObligatorio2() 
	{
		return esObligatorio2;
	}


	/**
	 * @param esObligatori02 the esObligatorio2 to set
	 */
	public void setEsObligatorio2(String esObligatorio2) 
	{
		this.esObligatorio2 = esObligatorio2;
	}


	/**
	 * @return the esObligatorio
	 */
	public boolean getEsObligatorio() 
	{
		return esObligatorio;
	}


	/**
	 * @param esObligatorio the esObligatorio to set
	 */
	public void setEsObligatorio(boolean esObligatorio) 
	{
		setEsObligatorio2((esObligatorio)?"Si":"No");
		this.esObligatorio = esObligatorio;
	}
	
	
	/**
	 * Metodo que retorna la posicion del nodo en una lista
	 * 
	 * @return posicion
	 */
	public int getPosicion()
	{
		return posicion;
	}
	

	/**
	 * Asigna o cambia el valor de la posicion del nodo
	 * 
	 * @param posicion
	 */
	public void setPosicion(int posicion) 
	{
		this.posicion = posicion;
	}
	
	
	/**
	 * @return the valor
	 */
	public Object getValor() 
	{
		return dato;
	}


	/**
	 * @param valor the valor to set
	 */
	public void setValor(Object dato) 
	{
		this.dato = dato;
	}
	

	/**
	 * @return the colaTareas
	 */
	public Cola getColaTareas() 
	{
		return colaTareas;
	}


	/**
	 * @param colaTareas the colaTareas to set
	 */
	public void setColaTareas(Cola colaTareas) 
	{
		this.colaTareas = colaTareas;
	}


	/**
	 * @return the tiempoMaximo
	 */
	public int getTiempoMaximo() 
	{
		return tiempoMaximo;
	}


	/**
	 * @param tiempoMaximo the tiempoMaximo to set
	 */
	public void setTiempoMaximo(int tiempoMaximo) 
	{
		this.tiempoMaximo = tiempoMaximo;
	}


	/**
	 * @return the tiempoMinimo
	 */
	public int getTiempoMinimo() 
	{
		return tiempoMinimo;
	}


	/**
	 * @param tiempoMinimo the tiempoMinimo to set
	 */
	public void setTiempoMinimo(int tiempoMinimo)
	{
		this.tiempoMinimo = tiempoMinimo;
	}


	/**
	 * Metodo que permite concetar un nodo con el otro
	 * 
	 * @param destino nodo al que se va a conectar
	 * @param indice  indice en el que se va a conectar //Como es una lista sencilla
	 *                en este caso seria el 0
	 */
	public void conectar(Actividad destino, int indice) 
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
	 * Metodo que desconecta un nodo en un indice especifico
	 * 
	 * @param indice
	 */
	public void desconectar(int indice) 
	{	
		enlaces.set(indice, null);
	}
	

	/**
	 * Metodo que indica si un nodo esta conectado en un indice especifico
	 * 
	 * @param indice
	 * @return true si esta conectado y false en caso contrario
	 */
	public boolean estaConectado(int indice) 
	{
		return enlaces.get(indice) != null;
	}
	

	/**
	 * Retorna el nodo destino al que esta concetado el nodo en esa posicion
	 * 
	 * @param indice
	 * @return
	 */
	public Actividad seguirEnlace(int indice) 
	{
		return enlaces.get(indice);
	}
	
	
	/**
	 * Metodo que agrega una nueva tarea al final de la cola
	 * 
	 * @param tarea : tarea que se agregara a la cola
	 * @throws TareaOpcionalException : excepcion para validar que no sean dos tareas opcionales
	 * 
	 */
	public void agregarTareaAlUltimo(Tarea tarea) throws TareaOpcionalException
	{
		Tarea tareaNueva=tarea;
		
		if(colaTareas.estaVacia()==true)
		{
			colaTareas.encolar(tareaNueva);
		}
		else
		{
			if(colaTareas.obtenerUltimo().esObligatoria()==false && tareaNueva.esObligatoria()==false)
			{
				throw new TareaOpcionalException("La tarea que desea crear no puede ser opcional");
			}
			else
			{
				colaTareas.encolar(tareaNueva);
			}
		}
	}
	
	
	/**
	 * Metodo auxliar que agrega una nueva tarea en una posicion especifica de la cola
	 * 
	 * @param tarea : tarea que se agregara a la cola
	 * @param posicion : posicion donde se requiere agregar la tarea
	 * 
	 */
	public void agregarTareaEnUnaPosicionAux(int posicion, Tarea tareaNueva)
	{
		Cola colaAux= new Cola();
		
			
		for (int i = 0; i < posicion; i++) 
	    {
			colaAux.encolar(colaTareas.desencolar());
	    }
	
		colaAux.encolar(tareaNueva);
	
		while (true) 
	    {
	       if (!colaTareas.estaVacia())
	       {
	           colaAux.encolar(colaTareas.desencolar());
	       } 
	       else
	       {
	          break;
	       }
	    }
		colaTareas = colaAux;
	}
	
	
	/**
	 * Metodo que agrega una nueva tarea en una posicion especifica con su respectiva validacion
	 * 
	 * @param tarea : tarea que se agregara a la cola
	 * @param posicion : posicion donde se requiere agregar la tarea
	 * @throws TareaOpcionalException : excepcion para validar que no sean dos tareas opcionales
	 * 
	 */
	public void agregarTareaEnUnaPosicion(int posicion, Tarea tareaNueva) throws TareaOpcionalException 
	{
		if(colaTareas.estaVacia()==true)
		{	
			colaTareas.encolar(tareaNueva);
		}
		else
		{
			if(buscarPosicionAnterior(posicion).esObligatoria()==false && tareaNueva.esObligatoria()==false )
			{
				throw new TareaOpcionalException("La tarea que desea crear no puede ser opcional");	
			}
			else
			{
				if(buscarPosicionSiguiente(posicion).esObligatoria()==false && tareaNueva.esObligatoria()==false)
				{
					throw new TareaOpcionalException("La tarea que desea crear no puede ser opcional");	
				}
				else
				{
					agregarTareaEnUnaPosicionAux(posicion, tareaNueva);
				}	
			}	
		}
	}
	
	
	/**
	 * Metodo que modifica una nueva tarea 
	 * 
	 * @param nombreTarea : nombre de la tarea que se requiere modificar
	 * @param nuevoNombre : nombre que reemplazara el existente
	 * @param nuevaDescripcion : descripcion que reemplazara la existente 
	 * @param esObligatoria : variable para conocer si la tarea es obligatoria o no 
	 * @param duracion : duracion enn minutos que posee la tarea
	 * 
	 */
	public void modificarTarea(String nombreTarea,String nuevoNombre,String nuevaDescripcion,boolean esObligatoria, int duracion)
	{
		Tarea encontrado= buscarTarea(nombreTarea);
		
		if(encontrado!=null)
		{
			encontrado.setNombre(nuevoNombre);
			encontrado.setDescripcion(nuevaDescripcion);
			encontrado.setEsObligatoria(esObligatoria);
			encontrado.setDuracion(duracion);
		}
	}
	
	
	/**
	 * Metodo que permite eliminar una tarea de la cola
	 * 
	 * @param nombreTarea : nombre de la tarea que se requiere eliminar
	 * 
	 */
	public void eliminarTarea(String nombreTarea)
	{
		Cola colaAux = new Cola();
		Tarea aux= colaTareas.getPrimero();
		
		while(aux!=null)
		{
			if(aux.getNombre().equalsIgnoreCase(nombreTarea))
			{
				aux=aux.seguirEnlace(0);
			}
			else
			{
				Tarea aux2= new Tarea();
				
				aux2.setNombre(aux.getNombre());
				aux2.setDescripcion(aux.getDescripcion());
				aux2.setEsObligatoria(aux.esObligatoria());
				aux2.setDuracion(aux.getDuracion());
				
				colaAux.encolar(aux2);
				aux=aux.seguirEnlace(0);
				
			}
		
		}
		colaTareas.borrarCola();
		
		colaTareas=colaAux;
	}
	
	
	/**
	 * Metodo que permite buscar una actividad que se encuentra dentro del proceso
	 * 
	 * @param nombreABuscar : nombre de la actividad a encontrar
	 * 
	 */
	public Tarea buscarTarea(String nombreABuscar) 
	{
		Tarea encontrado= null;
		boolean verificado= false;
		
		Tarea aux =colaTareas.getPrimero();
		
		while(aux!=null && verificado==false)
		{
			if(aux.getNombre().equals(nombreABuscar))
			{
				encontrado= aux;
				verificado= true;
			}
			aux= aux.seguirEnlace(0);
		}
		
		return encontrado;	
	}
	
	
	/**
	 * Metodo que permite buscar una tarea dada su decision
	 * 
	 * @param 
	 * 
	 */
	public Tarea buscarPosicionAnterior(int posicion) 
	{
		boolean verificado = false;
		Tarea encontrado=null;
		Tarea aux = colaTareas.getPrimero();
		
		for (int i = 0; i < colaTareas.size() && verificado == false; i++) 
		{
			if (i==posicion-1) 
			{
				encontrado=aux;
				verificado = true;
			}
			aux = aux.seguirEnlace(0);
		}
		
		return encontrado;
	}
	
	
	/**
	 * Metodo que permite buscar una tarea dada su decision
	 * 
	 * @param 
	 * 
	 */
	public Tarea buscarPosicionSiguiente(int posicion) 
	{
		boolean verificado = false;
		Tarea encontrado=null;
		Tarea aux = colaTareas.getPrimero();
		
		for (int i = 0; i < colaTareas.size() && verificado == false; i++) 
		{
			if (i==posicion) 
			{
				encontrado=aux;
				verificado = true;
			}
			aux = aux.seguirEnlace(0);
		}
		
		return encontrado;
	}
	
	
	/**
	 * Metodo que permite calcular el tiempo de duracion de las tareas
	 * 
	 */
	public int calcularTiempoDeDuracion()
	{
		int duracion=0;
		
		Tarea aux = colaTareas.getPrimero();

		while(aux!=null)
		{
			duracion = duracion + aux.getDuracion();
			aux = aux.seguirEnlace(0);
		}

		return duracion;
	}
	
	
	/**
	 * Metodo que permite calcular el tiempo minimo de la actividad
	 * 
	 */
	public int calcularTiempoMinimo()
	{
		int duracion=0;
		
		Tarea aux = colaTareas.getPrimero();

		while(aux!=null)
		{
			if(aux.esObligatoria()==true)
			{
				duracion = duracion + aux.getDuracion();
				aux = aux.seguirEnlace(0);
			}
			else
			{
				aux = aux.seguirEnlace(0);
			}
			
		}
		return duracion;
	}
	

	/**
	 * Metodo que retorna los datos de la actividad
	 * 
	 * @return datos de la actividad
	 * 
	 */
	@Override
    public String toString() 
    {
        return "Nombre:" +" "+nombre +"\n"+ "Descripcion:" +" "+ descripcion + "\n"+  "¿Es obligatorio?"+
        		" "+ esObligatorio +"\n" +"Tiempo minimo:"+" "+tiempoMinimo+"\n"+"Tiempo maximo:"+" "+tiempoMaximo
        		+"\n"+colaTareas.imprimir();
    }


}
