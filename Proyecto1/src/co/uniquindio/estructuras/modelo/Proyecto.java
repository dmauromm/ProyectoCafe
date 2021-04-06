package co.uniquindio.estructuras.modelo;

import java.io.Serializable;

import co.uniquindio.estructuras.excepciones.*;
import co.uniquindio.estructuras.listasEnlazadas.*;

public class Proyecto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	ListaSimple listaDeProcesos= new ListaSimple();


	/**
	 * @return the listaDeProcesos
	 */
	public ListaSimple getListaDeProcesos() 
	{
		return listaDeProcesos;
	}


	/**
	 * @param listaDeProcesos the listaDeProcesos to set
	 */
	public void setListaDeProcesos(ListaSimple listaDeProcesos) 
	{
		this.listaDeProcesos = listaDeProcesos;
	}


	/**
	 * Metodo que agrega los Procesos a la lista
	 * 
	 * @param nuevoProceso : proceso que se agregara a la lista
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos Procesos
	 * 									 iguales
	 * 
	 */
	public void crearProceso(Proceso nuevoProceso) throws ObjetoRepetidoExcepcion
	{
		Proceso procesoNuevo= nuevoProceso;
	
		if(listaDeProcesos.estaVacia())
		{
			listaDeProcesos.agregarNormal(procesoNuevo);
		}
		else
		{
			if(buscarProcesoBoolean(nuevoProceso.getId())!=true)
			{
				listaDeProcesos.agregarNormal(procesoNuevo);
			}
			else
			{	
				throw new ObjetoRepetidoExcepcion("El proceso que desea crear ya existe");	
			}	
		}
	}
	
	
	/**
	 * Metodo que modifica los Procesos de la lista
	 * 
	 * @param idBusqueda : id del proceso que se requiere modificar
	 * @param id : id con el cual se reemplazara el ya existente
	 * @param nombre : nombre con el cual se reemplazara el ya existente 
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se encuentren dos Procesos
	 * 									 iguales
	 * @throws DatoNoEncontradoException 
	 * 
	 */
	public void modificarProceso(String idBusqueda,String id, String nombre) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Proceso encontrado=buscarProceso(idBusqueda);
		
		if(encontrado!= null)
		{
			if(buscarProcesoBoolean(id)!=true)
			{
				encontrado.setId(id);
				encontrado.setNombre(nombre);
			}
			else
			{
				throw new ObjetoRepetidoExcepcion("El proceso que desea crear ya existe");	
			}
		}
	}
	
	
	/**
	 * Metodo que elimina los Procesos de la lista 
	 * 
	 * @param id: id del proceso que se requiere eliminar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso existe o no 
	 * 
	 */
	public void eliminarProceso(String id) throws DatoNoEncontradoException
	{	
		boolean encontrado= obtenerProceso(id);
		
		if(encontrado!=true)
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
		else
		{
			int posicion=buscarPosicion(id);
			listaDeProcesos.eliminarPorPosicion(posicion);
		}
	}
	
	
	/**
	 * Metodo que busca un Proceso de la lista 
	 * 
	 * @param idBuscar: id del proceso que se requiere encontrar
	 * @return encontrado : retorna el proceso si se llega a encontrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso existe o no 
	 * 
	 */
	public Proceso buscarProceso(String idBuscar) throws DatoNoEncontradoException 
	{
		Proceso encontrado= null;
		boolean verificado= false;
		
		Proceso aux =listaDeProcesos.getPrimero();
		
		if(aux==null)
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
		else
		{
			while(aux!=null && verificado==false)
			{
				if(aux.getId().equalsIgnoreCase(idBuscar))
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
	 * Metodo que busca un Proceso de la lista 
	 * 
	 * @param id: id del proceso que se requiere encontrar
	 * @return verificado : si se llega a encontrar retorna un true 
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso existe o no 
	 * 
	 */
	public boolean buscarProcesoBoolean(String id)
	{
		Proceso aux=listaDeProcesos.getPrimero();
			
		boolean verificado=false;
			
		while(aux!=null && verificado == false)
		{
			if (aux.getId().equalsIgnoreCase(id)) 
			{
				verificado=true;
			}
			else
			{
				aux=aux.seguirEnlace(0);
			}
		}
		return verificado;		 
	}
	
	
	/**
	 * Metodo que busca un Proceso de la lista 
	 * 
	 * @param id: id del proceso que se requiere encontrar
	 * @return posicion : retorna la posicion donde se encuentra el proceso
	 * 
	 */
	public int buscarPosicion(String id) 
	{
		int posicion = 0;
		boolean verificado= false;
		Proceso aux = listaDeProcesos.getPrimero();
			
			
		for (int i = 0; i < listaDeProcesos.size() && verificado == false; i++) 
		{
			if (aux.getId().equals(id)) 
			{
				posicion = i;
				verificado = true;
			}
			aux= aux.seguirEnlace(0);
		}
		return posicion;
	}
	
	
	/**
	 * Metodo que obtine un Proceso de la lista 
	 * 
	 * @param id: id del proceso que se requiere obtener
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso existe o no 
	 * 
	 */
	public boolean obtenerProceso(String id) throws DatoNoEncontradoException
	{
		boolean encontrado= buscarProcesoBoolean(id);
		
		if(encontrado!=true)
		{
			throw new DatoNoEncontradoException("El proceso no existe");	
		}
		else
		{
			return encontrado;
		}

	}

	
	/**
	 * Metodo puente que agrega las actividades de forma normal a los procesos
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nuevaActividad : actividad que se agregara al proceso 
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se creen dos actividades iguales 
	 * 									 en un mismo proceso
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso existe o no 
	 * 
	 */
	public void crearActividadNormal(String idProceso,Actividad nuevaActividad) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.agregarActividadNormal(nuevaActividad);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que agrega las actividades despues de una actividad especificada por el usuario
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nombre : nombre para encontrar la posicion de la ultima actividad agregada
	 * @param nuevaActividad : actividad que se agregara al proceso 
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se creen dos actividades iguales 
	 * 									 en un mismo proceso
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void crearActividadEnPosicion(String idProceso, String nombre, Actividad actividadNueva) throws DatoNoEncontradoException, ObjetoRepetidoExcepcion 
	{	
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.agregarActividadEnPosicion(nombre, actividadNueva);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que agrega las actividades al final 
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nuevaActividad : actividad que se agregara al proceso 
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se creen dos actividades iguales 
	 * 									 en un mismo proceso
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void crearActividadAlUltimo(String idProceso,Actividad nuevaActividad) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.agregarActividadAlUltimo(nuevaActividad);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que agrega las actividades despues de la ultima actividad creada en la lista
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nuevaActividad : actividad que se agregara al proceso 
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se creen dos actividades iguales 
	 * 									 en un mismo proceso
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void crearActividadDespuesDeLaUltimaCreada(String idProceso, Actividad actividadNueva) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException 
	{	
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.agregarDespuesDeUltimaActividadCreada(actividadNueva);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que modifica una actividad de la lista
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nombreActividad : nombre de la actividad que se requiere modificar
	 * @param nombre : nombre que reemplazara el ya existente
	 * @param descripcion : descripcion que reemplazara la que ya existe
	 * @param esObligatorio : variable que comprueba si la actividad es obligatoira o no 
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se creen dos actividades iguales 
	 * 									 en un mismo proceso
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void modificarActividad(String idProceso,String nombreActividad,String nombre, String descripcion, boolean esObligatorio) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.modificarAcitividad(nombreActividad, nombre, descripcion, esObligatorio);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que intercambia dos actividades de posicion
	 * 
	 * @param idProceso : id del proceso donde se encuentran las actividades
	 * @param nombreActividad : nombre de la actividad a intercambiar
	 * @param nombreActividad2 : nombre de la actividad a intercambiar
	 * 
	 * @throws ObjetoRepetidoExcepcion : excepcion para validar que no se creen dos actividades iguales 
	 * 									 en un mismo proceso
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void intercambiarActividad(String idProceso,String nombreActividad,String nombreActividad2, int opcion) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.intercambiarActividad(nombreActividad, nombreActividad2, opcion);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	
	/**
	 * Metodo puente que permite calcular el tiempo minimo y maximo de la actividad
	 * 
	 * @param idProceso : proceso donde se encuentra la actividad
	 * @param nombreActividad : nombre de la actividad a encontrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el Objeto existe en la lista
	 * 
	 */
	public void calcularTiempoMaximoYMinimo(String nombreActividad) throws DatoNoEncontradoException
	{
		Proceso procesoAux= listaDeProcesos.getPrimero();
	
		while(procesoAux!=null) {
			
			if(procesoAux.buscarActividadBooleano(nombreActividad) != false)
			{
				procesoAux.calcularMaximoYMinimo(nombreActividad);
			}
			
			procesoAux=procesoAux.seguirEnlace(0);	
		}
	}

	
	/**
	 * Metodo puente que elimina una actividad de la lista 
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nombreActividad : nombre de la actividad que se requiere eliminar
	 *
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void eliminarActividad(String idProceso,String nombreActividad) throws DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!= null)
		{
			encontrado.eliminarActividad(nombreActividad);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que busca una actividad de la lista de procesos 
	 * 
	 * @param idProceso : id del proceso al cual se le agregara la actividad
	 * @param nombreActividad : nombre de la actividad que se buscara
	 *
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public Actividad buscarActividad(String nombreActividad) throws DatoNoEncontradoException
	{
		Proceso procesoAux= listaDeProcesos.getPrimero();
		Actividad actividadEncontrada = null;
		
		while(procesoAux!=null) 
		{
			if(procesoAux.buscarActividadBooleano(nombreActividad) != false)
			{
				actividadEncontrada = procesoAux.buscarActividad(nombreActividad);
			}
			
			procesoAux=procesoAux.seguirEnlace(0);	
		}

		return actividadEncontrada;
	}
	
	
	/**
	 * Metodo puente que agrega una tarea a la cola 
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreActividad : nombre de la actividad donde se agregara la tarea
	 * @param tarea : tarea que se agregara en la actividad encontrada
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void crearTarea(String idProceso, String nombreActividad, Tarea tarea) throws DatoNoEncontradoException, TareaOpcionalException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.agregarTarea(nombreActividad, tarea);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que agrega una tarea en una posicion dada de la cola 
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreActividad : nombre de la actividad donde se agregara la tarea
	 * @param posicion : posicion donde se agregara la tarea
	 * @param tarea : tarea que se agregara en la actividad encontrada
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void crearTareaEnUnaPosicion(String idProceso, String nombreActividad,int posicion, Tarea tarea) throws DatoNoEncontradoException, TareaOpcionalException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.agregarTareaEnUnaPosicion(nombreActividad, posicion, tarea);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que agrega una tarea a la cola 
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreActividad : nombre de la actividad donde se agregara la tarea
	 * @param nombreTarea : nombre de la tarea que se desea modificar
	 * @param nuevoNombre : nombre que reemplazara el ya existente
	 * @param nuevaDescripcion : descripcion que reemplazara la que posee la tarea a modificar
	 * @param esObligatoria : variable para comprobar si la actividad es obligatoria o opcional
	 * @param duracion : duracion que reemplazara la que posee la tarea a modificar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void modificarTarea(String idProceso,String nombreActividad,String nombreTarea,String nuevoNombre,String nuevaDescripcion,boolean esObligatoria,int duracion) throws DatoNoEncontradoException 
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.modificarTarea(nombreActividad, nombreTarea, nuevoNombre, nuevaDescripcion, esObligatoria, duracion);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo puente que busca una tarea de la cola que tiene la lista de actividades desde el inicio
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreTareaABuscar : nombre de la tarea que se desea modificar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public Tarea buscarTareaDesdeElInicio(String nombreTareaABuscar) throws DatoNoEncontradoException
	{
		Proceso procesoAux=listaDeProcesos.getPrimero();
		
		if(procesoAux==null) {
			
			throw new DatoNoEncontradoException("El proceso no existe");
		}
		
		Tarea tareaEncontrada = null;
		
		while(procesoAux!=null) {
			
			tareaEncontrada = procesoAux.buscarTareaDesdeElInicio(nombreTareaABuscar);
			
			if(tareaEncontrada!=null) {
				
				return tareaEncontrada;
			}
			
			procesoAux=procesoAux.seguirEnlace(0);
		}
		
		
		return tareaEncontrada;
	}
	
	
	/**
	 * Metodo puente que busca una tarea de la cola que tiene la lista de actividades desde el actual
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreTareaABuscar : nombre de la tarea que se desea modificar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public Tarea buscarTareaDesdeElActual(String nombreTareaABuscar) throws DatoNoEncontradoException
	{
		Proceso procesoAux=listaDeProcesos.getPrimero();
		
		if(procesoAux==null) 
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
		
		Tarea tareaEncontrada = null;
		
		while(procesoAux!=null) 
		{
			tareaEncontrada = procesoAux.buscarTareaDesdeElActual(nombreTareaABuscar);
			
			if(tareaEncontrada!=null)
			{
				return tareaEncontrada;
			}
			procesoAux=procesoAux.seguirEnlace(0);
		}
		return tareaEncontrada;
	}
	
	
	/**
	 * Metodo puente que busca una tarea de la cola que tiene la lista de actividades 
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreActividad : nombre de la actividad donde se encuentra la tarea
	 * @param nombreTareaABuscar : nombre de la tarea que se desea modificar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public Tarea buscarTareaPorNombreDeActividad(String nombreActividad,String nombreTareaABuscar) throws DatoNoEncontradoException
	{
		Proceso procesoAux=listaDeProcesos.getPrimero();
		
		if(procesoAux==null) 
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
		
		Tarea tareaEncontrada = null;
		
		while(procesoAux!=null) 
		{
			tareaEncontrada = procesoAux.buscarTareaPorNombreDeActividad(nombreActividad, nombreTareaABuscar);
			
			if(tareaEncontrada!=null) 
			{
				return tareaEncontrada;
			}
			
			procesoAux=procesoAux.seguirEnlace(0);
		}
		return tareaEncontrada;
	}
	
	
	/**
	 * Metodo puente que elimina una Tarea de la cola
	 * 
	 * @param idProceso : id del proceso donde se encuentra la actividad requerida
	 * @param nombreActividad : nombre de la actividad donde se encuentra la tarea
	 * @param nombreTarea : nombre de la tarea a borrar
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el proceso
	 * 
	 */
	public void eliminarTarea(String idProceso,String nombreActividad,String nombreTarea) throws DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		if(encontrado!=null)
		{
			encontrado.eliminarTarea(nombreActividad, nombreTarea);
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}
	}
	
	
	/**
	 * Metodo que permite calcular el tiempo maximo de realizacion de un proceso
	 * 
	 * @param idProceso : id del proceso a calcular su tiempo
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el Objeto existe en la lista
	 * 
	 */
	public int calcularDuracionMaximaProceso(String idProceso) throws DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		int duracion=0;
		
		if(encontrado!=null)
		{
			duracion += encontrado.calcularDuracionMaximaProceso();
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}	
		
		return duracion;

	}
	
	
	/**
	 * Metodo que permite calcular el tiempo maximo de realizacion de un proceso
	 * 
	 * @param idProceso : id del proceso a calcular su tiempo
	 * 
	 * @throws DatoNoEncontradoException : excepcion para validar si el Objeto existe en la lista
	 * 
	 */
	public int calcularDuracionMinimaProceso(String idProceso) throws DatoNoEncontradoException
	{
		Proceso encontrado= buscarProceso(idProceso);
		
		int duracion=0;
		
		if(encontrado!=null)
		{
			duracion += encontrado.calcularDuracionMinimaProceso();
		}
		else
		{
			throw new DatoNoEncontradoException("El proceso no existe");
		}	
		
		return duracion;
	}


	/**
	 * Metodo que busca un Proceso de la lista 
	 * 
	 * @return retorna los datos del proyecto
	 * 
	 */
	@Override
	public String toString() 
	{
		return "Proyecto\n"  + listaDeProcesos.imprimirLista() +"\n";
	}	
	
}