package co.uniquindio.estructuras.modelo;

import java.io.Serializable;
import java.util.ArrayList;


import co.uniquindio.estructuras.excepciones.*;
import co.uniquindio.estructuras.listasEnlazadas.*;

public class Proceso implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Variables
	private String id;
	private String nombre;
	public int tiempoMaximo;
	public int tiempoMinimo;

	
	public ArrayList<Proceso> enlaces;
	private int limiteConexiones;
	private int posicion;
	
	private ListaDoble listaDeActividades;

	
	/**
	 * Constructor de la clase Proceso
	 * 
	 * @param id
	 * @param nombre
	 *
	 */
	public Proceso(String id, String nombre)
	{
		this.id = id;
		this.nombre = nombre;
		
		listaDeActividades = new ListaDoble();
		
		enlaces = new ArrayList<Proceso>();

		enlaces.add(null);
		
		posicion=0;
		
		limiteConexiones = 0;
	}
	

	/**
	 * Constructor 2 de la clase proceso
	 */
	public Proceso()
	{
		listaDeActividades = new ListaDoble();
		
		enlaces = new ArrayList<Proceso>();

		enlaces.add(null);
		
		posicion=0;
		
		limiteConexiones = 0;
	}

	
	/**
	 * @return the id
	 */
	public String getId() 
	{
		return id;
	}

	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) 
	{
		this.id = id;
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
	 * @return the listaDeActividades
	 */
	public ListaDoble getListaDeActividades() 
	{
		return listaDeActividades;
	}

	
	/**
	 * @param listaDeActividades the listaDeActividades to set
	 */
	public void setListaDeActividades(ListaDoble listaDeActividades)
	{
		this.listaDeActividades = listaDeActividades;
	}
	
	
	/**
	 * Asigna o Cambia el valor de la posicion del nodo
	 * 
	 * @param posicion
	 */
	public void setPosicion(int posicion) 
	{
		this.posicion = posicion;
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
	 * 
	 */
	public void conectar(Proceso destino, int indice) 
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
	public Proceso seguirEnlace(int indice)
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
	
	
	/**
	 * Metodo que agrega las actividades de forma normal a la lista doble
	 * 
	 * @param actividadNueva : es la actividad que se agregara a la lista
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos actividades
	 * 									 iguales
	 */
	public void agregarActividadNormal(Actividad actividadNueva) throws ObjetoRepetidoExcepcion
	{
		Actividad nuevaActividad = actividadNueva;
		
		if(listaDeActividades.estaVacia()==true)
		{	
			listaDeActividades.agregar(nuevaActividad);
		}
		else
		{
			if(buscarActividadBooleano(nuevaActividad.getNombre())!=true)
			{
				listaDeActividades.agregar(nuevaActividad);
			}
			else
			{
				throw new ObjetoRepetidoExcepcion("La actividad que desea crear ya existe");
			}
		}
	}
	
	
	/**
	 * Metodo que agrega las actividades al final de la lista
	 * 
	 * @param actividadNueva : es la actividad que se agregara 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos actividades
	 * 									 iguales
	 * 
	 */
	public void agregarActividadAlUltimo(Actividad actividadNueva) throws ObjetoRepetidoExcepcion
	{
		Actividad nuevaActividad = actividadNueva;
		
		if(listaDeActividades.estaVacia()==true)
		{
			listaDeActividades.agregar(nuevaActividad);
		}
		else
		{
			if(buscarActividadBooleano(nuevaActividad.getNombre())!=true)
			{
				listaDeActividades.agregarActividadAlUltimo(nuevaActividad);
			}
			else
			{
				throw new ObjetoRepetidoExcepcion("La actividad que desea crear ya existe");
			}
		}
	}
	
	
	/**
	 * Metodo que agrega las actividades despues de la ultima actividad creada a la lista
	 * 
	 * @param nombre: nombre de la actividad a encontrar
	 * @param actividadNueva : es la actividad que se agregara 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos actividades
	 * 									 iguales
	 * 
	 */
	public void agregarActividadEnPosicion(String nombre, Actividad actividadNueva) throws DatoNoEncontradoException, ObjetoRepetidoExcepcion{
		
		boolean encontrado=obtenerActividad(nombre);
		int posicion=buscarPosicion(nombre);
		
		if(encontrado!=true) 
		{	
			throw new DatoNoEncontradoException("La actividad no existe");	
		}
		else 
		{
			if(listaDeActividades.estaVacia()) {
				
				listaDeActividades.agregar(actividadNueva);
			}
			else {
				
				if(buscarActividadBooleano(actividadNueva.getNombre())!=true)
				{
					listaDeActividades.agregarActividadEnPosicion(posicion, actividadNueva);
				}
				else
				{
					throw new ObjetoRepetidoExcepcion("La actividad que desea crear ya existe");
				}	
			}
		}
	}
	
	
	/**
	 * Metodo que agrega las actividades despues de la ultima actividad de la lista
	 * 
	 * @param actividadNueva : es la actividad que se agregara 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos actividades
	 * 									 iguales
	 * 
	 */
	public void agregarDespuesDeUltimaActividadCreada(Actividad actividadNueva) throws ObjetoRepetidoExcepcion
	{
		if(buscarActividadBooleano(actividadNueva.getNombre())!=true)
		{
			listaDeActividades.agregarDespuesDeLaUltimaCreada(actividadNueva);
		}
		else
		{
			throw new ObjetoRepetidoExcepcion("La actividad que desea crear ya existe");
		}	
	}
	
	
	/**
	 * Metodo para modificar las actividades de la lista
	 * 
	 * @param nombreActividad : nombre de la actividad a modificar
	 * @param nombre : nombre que reemplazara el de la actividad existente
	 * @param descripcion : descripcion que reemplazara la que posee la actividad existente
	 * @param esObligatorio : variable quee reemplazara si la actividad es obligatoria o no
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos actividades
	 * 									 iguales
	 * @throws DatoNoEncontradoException 
	 * 
	 */
	public void modificarAcitividad(String nombreActividad,String nombre, String descripcion, boolean esObligatorio) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		
		if(encontrado!=null)
		{
			if(buscarActividadBooleano(nombre)!=true)
			{
				encontrado.setNombre(nombre);
				encontrado.setDescripcion(descripcion);
				encontrado.setEsObligatorio(esObligatorio);
			}
			else
			{
				throw new ObjetoRepetidoExcepcion("La actividad que desea crear ya existe");
			}
		}
	}
	
	
	/**
	 * Metodo para intercambiar dos actividades en la lista 
	 * 
	 * @param nombreActividad  : nombre de la actividad a intercambiar
	 * @param nombreActividad2 : nombre de la actividad a intercambiar
	 * @throws DatoNoEncontradoException 
	 * 
	 */
	public void intercambiarActividad(String nombreActividad,String nombreActividad2, int opcion) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		Actividad aux= new Actividad();
		aux.setNombre(encontrado.getNombre());
		aux.setDescripcion(encontrado.getDescripcion());
		aux.setEsObligatorio(encontrado.getEsObligatorio());
		aux.setColaTareas(encontrado.getColaTareas());
		
		Actividad encontrado2= buscarActividad(nombreActividad2);
		Actividad aux2= new Actividad();
		aux2.setNombre(encontrado2.getNombre());
		aux2.setDescripcion(encontrado2.getDescripcion());
		aux2.setEsObligatorio(encontrado2.getEsObligatorio());
		aux2.setColaTareas(encontrado2.getColaTareas());
		
		if(encontrado!=null && encontrado2!=null)
		{
			if(opcion==0) 
			{
				encontrado.setNombre(aux2.getNombre());
				encontrado.setDescripcion(aux2.getDescripcion());
				encontrado.setEsObligatorio(aux2.getEsObligatorio());
				encontrado.setColaTareas(aux2.getColaTareas());
					
				encontrado2.setNombre(aux.getNombre());
				encontrado2.setDescripcion(aux.getDescripcion());
				encontrado2.setEsObligatorio(aux.getEsObligatorio());
				encontrado2.setColaTareas(aux.getColaTareas());
			}
			else
			{
				encontrado.setNombre(aux2.getNombre());
				encontrado.setDescripcion(aux2.getDescripcion());
				encontrado.setEsObligatorio(aux2.getEsObligatorio());
					
				encontrado2.setNombre(aux.getNombre());
				encontrado2.setDescripcion(aux.getDescripcion());
				encontrado2.setEsObligatorio(aux.getEsObligatorio());
			}
		}
		else
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
	}
	
	
	/**
	 * Metodo para eliminar una actividad de la lista
	 * 
	 * @param nombre : nombre de la actividad a eliminar
	 * 
	 * @throws DatoNoEncontradoException: excepcion para validar si el Objeto existe en la lista
	 * 
	 */
	public void eliminarActividad(String nombre)throws DatoNoEncontradoException
	{
		boolean encontrado= obtenerActividad(nombre);
		
		if(encontrado!=true)
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
		else
		{
			int posicion= buscarPosicion(nombre);
			listaDeActividades.eliminarPorPosicion(posicion);
		}
	}
	
	
	/**
	 * Metodo que permite obtener una actividad de la lista
	 * 
	 * @param nombre : nombre de la actividad a encontrar
	 * 
	 * @throws DatoNoEncontradoException: excepcion para validar si el Objeto existe en la lista
	 * 
	 */
	public boolean obtenerActividad(String nombre) throws DatoNoEncontradoException
	{
		boolean encontrado= buscarActividadBooleano(nombre);
		
		if(encontrado!=true)
		{
			throw new DatoNoEncontradoException("La actividad no existe");	
		}
		else
		{
			return encontrado;
		}
	}
	
	
	/**
	 * Metodo que permite calcular el tiempo minimo y maximo de la actividad
	 * 
	 * @param nombreActividad : nombre de la actividad a encontrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el Objeto existe en la lista
	 * 
	 */
	public void calcularMaximoYMinimo(String nombreActividad) throws DatoNoEncontradoException
	{
		int duracion=0;
		
		Actividad aux= buscarActividad(nombreActividad);
	
		if(aux!=null)
		{
			duracion += aux.calcularTiempoDeDuracion();
			aux.setTiempoMaximo(duracion);
			
			duracion= 0;
			
			duracion += aux.calcularTiempoMinimo();	
			aux.setTiempoMinimo(duracion);
			
			aux = aux.seguirEnlace(0);
		}
		else
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
	}
	
	
	/**
	 * Metodo para buscar la posicion de una actividad de la lista
	 * 
	 * @param nombre: nombre de la actividad a buscar
	 */
	public int buscarPosicion(String nombre) 
	{
		int posicion = 0;
		boolean verificado = false;
		Actividad aux = listaDeActividades.getPrimero();
		
		for (int i = 0; i < listaDeActividades.size() && verificado == false; i++) 
		{
			if (aux.getNombre().equals(nombre)) 
			{
				posicion = i;
				verificado = true;
			}
			aux = aux.seguirEnlace(0);
		}
		return posicion;
	}
	
	
	/**
	 * Metodo para buscar una actividad de la lista
	 * 
	 * @param nombre: nombre de la actividad a buscar
	 */
	public boolean buscarActividadBooleano(String nombre)
	{
		boolean verificado = false;
		Actividad aux=new Actividad();
		aux=listaDeActividades.getPrimero();
		
		while (aux != null && verificado==false)
		{
			if(aux.getNombre().equals(nombre))
			{
				verificado = true;
			}
			else
			{
				aux= aux.seguirEnlace(0);
			}
		}
		return verificado;
	}
	
	/**
	 * Metodo que permite buscar una actividad que se encuentra dentro del proceso
	 * 
	 * @param nombreABuscar : nombre de la actividad a encontrar
	 * @return encontrado : retorna la actividad si existe en la lista
	 * @throws DatoNoEncontradoException : excepcion para validar si existe el dato
	 */
	public Actividad buscarActividad(String nombreABuscar) throws DatoNoEncontradoException 
	{
		Actividad encontrado= null;
		boolean verificado= false;
		
		Actividad aux =listaDeActividades.getPrimero();
		
		if(aux==null)
		{
			throw new DatoNoEncontradoException("Lista vacia");
		}
		else
		{
			while(aux!=null && verificado==false)
			{
				if(aux.getNombre().equals(nombreABuscar))
				{
					encontrado= aux;
					verificado= true;
				}
				aux= aux.seguirEnlace(0);
			}
		}
		
		
		return encontrado;	
	}
	
	
	/**
	 * Metodo puente para agregar una tarea a la actividad dada
	 * 
	 * @param nombreActividad : nombre de la actividad donde se agregara la tarea
	 * @param tarea : tarea que se agregara a la actividad
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el Objeto existe en la lista
	 * @throws TareaOpcionalException : excepcion para validar si se puede agregar la actividad
	 * 
	 */
	public void agregarTarea(String nombreActividad, Tarea tarea) throws DatoNoEncontradoException, TareaOpcionalException
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		
		if(encontrado!=null)
		{
			encontrado.agregarTareaAlUltimo(tarea);
		}
		else
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
	}
	
	
	/**
	 * Metodo puente para agregar una tarea en cierta posicion a la actividad dada
	 * 
	 * @param nombreActividad : nombre de la actividad donde se agregara la tarea
	 * @param posicion : posicion donde se quiere agregar la tarea
	 * @param tarea : tarea que se agregara a la actividad
	 * 
	 * @throws TareaOpcionalException : excepcion para validar si se puede agregar la tarea
	 * @throws DatoNoEncontradoException : excepcion para validar si la actividad existe
	 * 
	 */
	public void agregarTareaEnUnaPosicion(String nombreActividad,int posicion, Tarea tarea) throws TareaOpcionalException, DatoNoEncontradoException 
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		
		if(encontrado!=null)
		{
			encontrado.agregarTareaEnUnaPosicion(posicion, tarea);
		}
		else
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
	}
	
	
	/**
	 * Metodo para modificar una tarea 
	 * 
	 * @param nombreActividad : nombre de la actividad donde se agregara la tarea
	 * @param nuevoNombre : nombre que reemplazara el de la tarea existente en la actividad
	 * @param nuevaDescripcion : descripcion que reemplazara la que posee la tarea existente en la lista 
	 * @param esObligatoria : varible que reemplazara si la actividad es obligatoria o no
	 * @param duracion : duracion de la tarea en minutos
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si la actividad existe
	 * 
	 */
	public void modificarTarea(String nombreActividad,String nombreTarea,String nuevoNombre,String nuevaDescripcion,boolean esObligatoria,int duracion) throws DatoNoEncontradoException
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		
		if(encontrado!=null)
		{
			encontrado.modificarTarea(nombreTarea, nuevoNombre, nuevaDescripcion, esObligatoria, duracion);
		}
		else
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
	}
	
	
	/**
	 * Metodo para buscar una tarea desde la primera actividad de la lista de actividades
	 * 
	 * @param nombreTareaABuscar : nombre de la tarea que se desea encontrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el dato existe o no
	 * 
	 * @return tareaEncontrada : retorna la tarea si existe en la cola
	 * 
	 */
	public Tarea buscarTareaDesdeElInicio(String nombreTareaABuscar) throws DatoNoEncontradoException
	{
		Actividad aux= listaDeActividades.getPrimero();
		boolean verificado= false;
		Tarea tareaEncontrada=null;
		
		if(aux==null)
		{
			throw new DatoNoEncontradoException("Lista de activiades vacia");
		}
		else
		{
			while(aux!=null && verificado==false)
			{
				if(aux.buscarTarea(nombreTareaABuscar)!=null)
				{
					tareaEncontrada = aux.buscarTarea(nombreTareaABuscar);
					verificado=true;
				}
				aux=aux.seguirEnlace(0);
			}
		}
		
		return tareaEncontrada;
	}
	
	
	/**
	 * Metodo para buscar una tarea desde la actividad actual de la lista de actividades
	 * 
	 * @param nombreTareaABuscar : nombre de la tarea que se desea encontrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el dato existe o no
	 * 
	 *  @return tareaEncontrada : retorna la tarea si existe en la cola
	 * 
	 */
	public Tarea buscarTareaDesdeElActual(String nombreTareaABuscar) throws DatoNoEncontradoException
	{
		Actividad aux= listaDeActividades.getActual();
		
		boolean verificado= false;
		Tarea tareaEncontrada=null;
		
		if(aux==null)
		{
			throw new DatoNoEncontradoException("Lista de activiades vacia");
		}
		else
		{
			while(aux!=null && verificado==false)
			{
				if(aux.buscarTarea(nombreTareaABuscar)!=null)
				{
					tareaEncontrada = aux.buscarTarea(nombreTareaABuscar);
					verificado=true;
				}
				aux=aux.seguirEnlace(0);
				
				if(aux==null)
				{
					aux=listaDeActividades.getPrimero();
				}
			}
		}
		
		return tareaEncontrada;
	}
	
	
	/**
	 * Metodo para buscar una tarea dado su nombre
	 * 
	 * @param nombreTareaABuscar : nombre de la tarea que se desea encontrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el dato existe o no
	 * 
	 *  @return tareaEncontrada : retorna la tarea si existe en la cola
	 * 
	 */
	public Tarea buscarTareaPorNombreDeActividad(String nombreActividad,String nombreTareaABuscar) throws DatoNoEncontradoException
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		Tarea tareaEncontrada=null;
		
		if(encontrado!=null)
		{
			tareaEncontrada = encontrado.buscarTarea(nombreTareaABuscar);
		}

		
		return tareaEncontrada;
	}
	
	
	/**
	 * Metodo para eliminar una tarea de la lista de actividades
	 * 
	 * @param nombreActividad : nombre de la actividad donde se encuentra la tarea
	 * @param nombreTarea : nombre de la tarea que se requiere eliminar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el dato existe o no
	 * 
	 * 
	 */
	public void eliminarTarea(String nombreActividad, String nombreTarea) throws DatoNoEncontradoException
	{
		Actividad encontrado= buscarActividad(nombreActividad);
		
		if(encontrado!=null)
		{
			encontrado.eliminarTarea(nombreTarea);
		}
		else
		{
			throw new DatoNoEncontradoException("La actividad no existe");
		}
	}
	
	
	/**
	 * Metodo puente que permite calcular la duracion maxima del proceso
	 * 
	 * @throws DatoNoEncontradoException : excepcion para va
	 * 
	 */
	public int calcularDuracionMaximaProceso() throws DatoNoEncontradoException
	{
		int duracion=0;
		
		Actividad aux=listaDeActividades.getPrimero();
		
		if(aux==null)
		{
			throw new DatoNoEncontradoException("La lista esta vacia");
		}
		else
		{
			while(aux!=null)
			{
				duracion += aux.calcularTiempoDeDuracion();
				aux = aux.seguirEnlace(0);
			}
		}
		return duracion;	
	}
	
	
	/**
	 * Metodo puente que permite calcular la duracion del proceso
	 * 
	 * @throws DatoNoEncontradoException : excepcion para va
	 * 
	 */
	public int calcularDuracionMinimaProceso() throws DatoNoEncontradoException
	{
		int duracion=0;
		
		Actividad aux=listaDeActividades.getPrimero();
		
		if(aux==null)
		{
			throw new DatoNoEncontradoException("La lista esta vacia");
		}
		else
		{
			while(aux!=null)
			{
				if(aux.getEsObligatorio()==true)
				{
					duracion += aux.calcularTiempoDeDuracion();
					aux = aux.seguirEnlace(0);
				}
				else
				{
					aux = aux.seguirEnlace(0);
				}
			}
		}
		return duracion;	
	}
	
	
	/**
	 * Metodo que retorna los datos del proceso
	 * 
	 * @return datos del proceso
	 * 
	 */
	@Override
	public String toString() 
	{
		return "Id:" + id +"\n" +"Nombre=" + nombre+"\n"+"Tiempo minimo:"+" "+tiempoMinimo+"\n"+"Tiempo maximo:"+" "+tiempoMaximo+"\n"+ listaDeActividades.imprimir()+"\n"+"\n";
	}
	
	
}
