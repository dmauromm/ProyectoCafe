package co.uniquindio.estructuras.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import co.uniquindio.estructuras.excepciones.DatoNoEncontradoException;
import co.uniquindio.estructuras.excepciones.ObjetoRepetidoExcepcion;
import co.uniquindio.estructuras.excepciones.TareaOpcionalException;
import co.uniquindio.estructuras.listasEnlazadas.Cola;
import co.uniquindio.estructuras.listasEnlazadas.ListaDoble;
import co.uniquindio.estructuras.modelo.Actividad;
import co.uniquindio.estructuras.modelo.Proceso;
import co.uniquindio.estructuras.modelo.Proyecto;
import co.uniquindio.estructuras.modelo.Tarea;
import co.uniquindio.estructuras.persistencia.Serializador;

public class ControladorProyecto
{
	//Instancia de proyecto
	Proyecto proyecto;
	Serializador serializadora=new Serializador();
	
	//ARRAYLIST AUXILIARES PARA CARGAR LOS DATOS DE LAS LISTAS ENLAZADAS 
	//ESTOS ARRAYLIST SE USAN ESPECIFICAMENTE PARA MOSTRAR LOS DATOS EN LAS TABLAS
	//DE LA INTERFAZ, TANTO LAS TABLAS DE BUSQUEDA COMO LAS TABLAS NOMALES
	ArrayList<Proceso>listaProcesosTabla=new ArrayList<>();
	ArrayList<Actividad>listaActividadesTabla=new ArrayList<>();
	ArrayList<Tarea>listaTareasTabla=new ArrayList<>();
	ArrayList<Proceso>procesoEncontradoTabla=new ArrayList<>();
	ArrayList<Actividad>actividadEncontradaTabla=new ArrayList<>();
	ArrayList<Tarea>tareaEncontradaTabla=new ArrayList<>();
	
	
	/**
	 * Controlador de la clase
	 * 
	 */
	public ControladorProyecto() 
	{
		try 
		{
			cargarResourceSerializable();
				
			if(proyecto == null)
			{
				inicializarDatos();
				guardarResourceSerializable();
			}	
		}
		catch (Exception  e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para inicializar los datos
	 * 
	 */
	private void inicializarDatos()
	{
		proyecto=new Proyecto();
	}
	

	/**
	 * Metodo para cargar los datos de el archivo
	 * 
	 */
	public void cargarResourceSerializable()
	{	
		proyecto = (Proyecto) serializadora.leerObjeto();
	}

	
	/**
	 * Metodo para guardar los datos del archivo
	 * 
	 */
	public void guardarResourceSerializable() 
	{	
	    serializadora.escribirObjetos(proyecto);
	}

	
	/**
	 * @return the proyecto
	 */
	public Proyecto getProyecto() 
	{
		return proyecto;
	}


	/**
	 * @param proyecto the proyecto to set
	 */
	public void setProyecto(Proyecto proyecto)
	{
		this.proyecto = proyecto;
	}


	/**
	 * @return the serializadora
	 */
	public Serializador getSerializadora() 
	{
		return serializadora;
	}


	/**
	 * @param serializadora the serializadora to set
	 */
	public void setSerializadora(Serializador serializadora) 
	{
		this.serializadora = serializadora;
	}


	/**
	 * @return the listaProcesosTabla
	 */
	public ArrayList<Proceso> getListaProcesosTabla() 
	{
		return listaProcesosTabla;
	}


	/**
	 * @param listaProcesosTabla the listaProcesosTabla to set
	 */
	public void setListaProcesosTabla(ArrayList<Proceso> listaProcesosTabla) 
	{
		this.listaProcesosTabla = listaProcesosTabla;
	}


	/**
	 * @return the listaActividadesTabla
	 */
	public ArrayList<Actividad> getListaActividadesTabla() 
	{
		return listaActividadesTabla;
	}


	/**
	 * @param listaActividadesTabla the listaActividadesTabla to set
	 */
	public void setListaActividadesTabla(ArrayList<Actividad> listaActividadesTabla) 
	{
		this.listaActividadesTabla = listaActividadesTabla;
	}


	/**
	 * @return the listaTareasTabla
	 */
	public ArrayList<Tarea> getListaTareasTabla()
	{
		return listaTareasTabla;
	}


	/**
	 * @param listaTareasTabla the listaTareasTabla to set
	 */
	public void setListaTareasTabla(ArrayList<Tarea> listaTareasTabla)
	{
		this.listaTareasTabla = listaTareasTabla;
	}

	/**
	 * @return the procesoEncontradoTabla
	 */
	public ArrayList<Proceso> getProcesoEncontradoTabla() 
	{
		return procesoEncontradoTabla;
	}


	/**
	 * @param procesoEncontradoTabla the procesoEncontradoTabla to set
	 */
	public void setProcesoEncontradoTabla(ArrayList<Proceso> procesoEncontradoTabla) 
	{
		this.procesoEncontradoTabla = procesoEncontradoTabla;
	}


	/**
	 * @return the actividadEncontradaTabla
	 */
	public ArrayList<Actividad> getActividadEncontradaTabla() 
	{
		return actividadEncontradaTabla;
	}


	/**
	 * @param actividadEncontradaTabla the actividadEncontradaTabla to set
	 */
	public void setActividadEncontradaTabla(ArrayList<Actividad> actividadEncontradaTabla) 
	{
		this.actividadEncontradaTabla = actividadEncontradaTabla;
	}


	/**
	 * @return the tareaEncontradaTabla
	 */
	public ArrayList<Tarea> getTareaEncontradaTabla() 
	{
		return tareaEncontradaTabla;
	}


	/**
	 * @param tareaEncontradaTabla the tareaEncontradaTabla to set
	 */
	public void setTareaEncontradaTabla(ArrayList<Tarea> tareaEncontradaTabla) 
	{
		this.tareaEncontradaTabla = tareaEncontradaTabla;
	}


	/**
	 * Metodo para llamar el crear proceso e implementar a la interfaz
	 * 
	 */
	public void crearProceso(String nombre, String id) 
	{	
		Proceso procesoNuevo=new Proceso();
		procesoNuevo.setId(id);
		procesoNuevo.setNombre(nombre);
		
		try 
		{
			proyecto.crearProceso(procesoNuevo);
			agregarProcesosTabla();
		} 
		catch (ObjetoRepetidoExcepcion e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar el modificar proceso e implementar a la interfaz
	 * 
	 */
	public void modificarProceso(String idBusqueda, String idNuevo, String nombreNuevo) 
	{
		try 
		{
			proyecto.modificarProceso(idBusqueda, idNuevo, nombreNuevo);
			agregarProcesosTabla();
		}
		catch (ObjetoRepetidoExcepcion e) 
		{
			e.printStackTrace();
		}
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar el eliminar proceso e implementar a la interfaz
	 * 
	 */
	public void eliminarProceso(String id) 
	{
		try
		{
			proyecto.eliminarProceso(id);
			agregarProcesosTabla();
			agregarActividadesTabla();
			agregarTareasTabla();
		}
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar el buscar proceso e implementar a la interfaz
	 * 
	 */
	public void buscarProceso(String id) 
	{	
		Proceso procesoEncontrado=null;
		procesoEncontradoTabla.clear();
		
		try {
			procesoEncontrado=proyecto.buscarProceso(id);
		} 
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
		
		if(procesoEncontrado!=null)
		{	
			Proceso procesoAgregado=new Proceso();
			procesoAgregado.setId(procesoEncontrado.getId());
			procesoAgregado.setNombre(procesoEncontrado.getNombre());
			
			procesoEncontradoTabla.add(procesoAgregado);
		}
	}
	
	
	/**
	 * Metodo para llamar el consultar tiempo maximo de ejecucion del
	 *  proceso e implementarlo a la interfaz
	 *  
	 *  @param idProceso : id del proceso al cual se le consultara el tiempo de ejecucion
	 * 
	 */
	public int consultarTiempoEjecucionMaximaProceso(String idProceso) 
	{
		int duracion=0;
		
		try 
		{
			duracion= proyecto.calcularDuracionMaximaProceso(idProceso);
		} 
		catch (DatoNoEncontradoException e)
		{
			e.printStackTrace();
		}
		
		return duracion;
	}
	
	
	/**
	 * Metodo para llamar el consultar tiempo minimo de ejecucion del
	 *  proceso e implementarlo a la interfaz
	 *  
	 *  @param idProceso : id del proceso al cual se le consultara el tiempo de ejecucion
	 * 
	 */
	public int consultarTiempoEjecucionMinimaProceso(String idProceso) 
	{
		int duracion=0;
		
		try 
		{
			duracion= proyecto.calcularDuracionMinimaProceso(idProceso);
		} 
		catch (DatoNoEncontradoException e)
		{
			e.printStackTrace();
		}
		
		return duracion;
	}
	
	/**
	 * Metodo para llamar el crear actividad en una posicion e implementar a la interfaz
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param obligatotiaOpcion
	 * @param idProceso
	 * @param nombreActividadPrecedente
	 * 
	 */
	public void crearActividadEnPosicion(String nombre, String descripcion, int obligatotiaOpcion, String idProceso, String nombreActividadPrecedente) 
	{	
		boolean esObligatoria=false;
		
		if(obligatotiaOpcion==0) 
		{	
			esObligatoria=true;
		}
		
		Actividad actividadNueva=new Actividad();
		actividadNueva.setNombre(nombre);
		actividadNueva.setDescripcion(descripcion);
		actividadNueva.setEsObligatorio(esObligatoria);
		
		try 
		{
			proyecto.crearActividadEnPosicion(idProceso, nombreActividadPrecedente, actividadNueva);
			agregarActividadesTabla();
		} 
		catch (DatoNoEncontradoException e)
		{
			e.printStackTrace();
		} 
		catch (ObjetoRepetidoExcepcion e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar el crear actividad al final e implementar a la interfaz
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param obligatotiaOpcion
	 * @param idProceso
	 * 
	 */
	public void crearActividadAlFinal(String nombre, String descripcion, int obligatotiaOpcion, String idProceso) 
	{	
		boolean esObligatoria=false;
		
		if(obligatotiaOpcion==0) 
		{	
			esObligatoria=true;
		}
		
		Actividad actividadNueva=new Actividad();
		actividadNueva.setNombre(nombre);
		actividadNueva.setDescripcion(descripcion);
		actividadNueva.setEsObligatorio(esObligatoria);
		
		try 
		{
			proyecto.crearActividadAlUltimo(idProceso, actividadNueva);
			agregarActividadesTabla();
		} 
		catch (ObjetoRepetidoExcepcion e) 
		{
			e.printStackTrace();
		} 
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar el crear actividad despues de la ultima creada
	 * e implementar a la interfaz
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param obligatotiaOpcion
	 * @param idProceso
	 * 
	 */
	public void crearActividadDespuesDeUltimaCreada(String nombre, String descripcion, int obligatotiaOpcion, String idProceso) 
	{	
		boolean esObligatoria=false;
		
		if(obligatotiaOpcion==0) 
		{	
			esObligatoria=true;
		}
		
		Actividad actividadNueva=new Actividad();
		actividadNueva.setNombre(nombre);
		actividadNueva.setDescripcion(descripcion);
		actividadNueva.setEsObligatorio(esObligatoria);
		
		try 
		{
			proyecto.crearActividadDespuesDeLaUltimaCreada(idProceso, actividadNueva);
			agregarActividadesTabla();
		}
		catch (ObjetoRepetidoExcepcion e) 
		{	
			e.printStackTrace();
		}
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Metodo para llamar el buscar actividad e implementar a la interfaz
	 * 
	 * @param nombreActividad
	 * 
	 */
	public void buscarActividad(String nombreActividad) 
	{
		
		Actividad actividadEncontrada=null;
		actividadEncontradaTabla.clear();
		
		try 
		{
			actividadEncontrada=proyecto.buscarActividad(nombreActividad);	
			proyecto.calcularTiempoMaximoYMinimo(nombreActividad);
			JOptionPane.showMessageDialog(null, actividadEncontrada.toString());
		}
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
		
		if(actividadEncontrada!=null) 
		{
			Actividad actividadNueva=new Actividad();
			actividadNueva.setNombre(actividadEncontrada.getNombre());
			actividadNueva.setDescripcion(actividadEncontrada.getDescripcion());
			actividadNueva.setEsObligatorio(actividadEncontrada.getEsObligatorio());
			actividadNueva.setTiempoMaximo(actividadEncontrada.getTiempoMaximo());
			actividadNueva.setTiempoMinimo(actividadEncontrada.getTiempoMinimo());
			
			actividadEncontradaTabla.add(actividadNueva);
		}
	}
	
	
	/**
	 * Metodo para llamar el intercambiar actividad  e implementar a la interfaz
	 * 
	 * @param idProceso
	 * @param nombreActividad
	 * @param nombreActividad2
	 * @param opcion
	 * 
	 */
	public void intercambiarActividad(String idProceso, String nombreActividad, String nombreActividad2, int opcion) 
	{	
		try 
		{
			proyecto.intercambiarActividad(idProceso, nombreActividad, nombreActividad2, opcion);
			agregarActividadesTabla();
		} 
		catch (ObjetoRepetidoExcepcion e)
		{	
			e.printStackTrace();
		}
		catch (DatoNoEncontradoException e)
		{
			e.printStackTrace();
		}	
		
	}
	
	
	/**
	 * Metodo para llamar crear tarea  e implementar a la interfaz
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param obligatotiaOpcion
	 * @param duracion
	 * @param idProceso
	 * @param nombreActividad
	 * 
	 */
	public void agregarTarea(String nombre, String descripcion, int obligatotiaOpcion, int duracion, String idProceso, String nombreActividad) 
	{	
		boolean esObligatoria=false;
		
		if(obligatotiaOpcion==0) 
		{	
			esObligatoria=true;
		}
		
		Tarea tareaNueva=new Tarea();
		tareaNueva.setNombre(nombre);
		tareaNueva.setDescripcion(descripcion);
		tareaNueva.setEsObligatoria(esObligatoria);
		tareaNueva.setDuracion(duracion);
		
		try 
		{
			proyecto.crearTarea(idProceso, nombreActividad, tareaNueva);
			agregarTareasTabla();
		}
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
		catch (TareaOpcionalException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar crear tarea en una posicion e implementar a la interfaz
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param obligatotiaOpcion
	 * @param duracion
	 * @param idProceso
	 * @param nombreActividad
	 * @param posicion
	 * 
	 */
	public void agregarTareaEnPosicion(String nombre, String descripcion, int obligatotiaOpcion, int duracion, String idProceso, String nombreActividad, int posicion) 
	{
		boolean esObligatoria=false;
		
		if(obligatotiaOpcion==0)
		{	
			esObligatoria=true;
		}
		
		Tarea tareaNueva=new Tarea();
		tareaNueva.setNombre(nombre);
		tareaNueva.setDescripcion(descripcion);
		tareaNueva.setEsObligatoria(esObligatoria);
		tareaNueva.setDuracion(duracion);
		
		try
		{
			proyecto.crearTareaEnUnaPosicion(idProceso, nombreActividad, posicion, tareaNueva);
			agregarTareasTabla();
		} 
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
		catch (TareaOpcionalException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para llamar el buscar tarea desde el inicio  e implementar a la interfaz
	 * 
	 * @param nombreTarea : Tarea a buscar
	 * 
	 */
	public void buscarTareaInicio(String nombreTarea) 
	{
		Tarea tareaEncontrada=null;
		tareaEncontradaTabla.clear();
		
		try 
		{
			tareaEncontrada=proyecto.buscarTareaDesdeElInicio(nombreTarea);
		}
		catch (DatoNoEncontradoException e) 
		{
			e.printStackTrace();
		}
		
		if(tareaEncontrada!=null)
		{	
			Tarea tareaNueva=new Tarea();
			tareaNueva.setNombre(tareaEncontrada.getNombre());
			tareaNueva.setDescripcion(tareaEncontrada.getDescripcion());
			tareaNueva.setEsObligatoria(tareaEncontrada.esObligatoria());
			tareaNueva.setDuracion(tareaEncontrada.getDuracion());
			
			tareaEncontradaTabla.add(tareaNueva);
		}
	}

	
	/**
	 * Metodo para llamar el buscar tarea desde el actual  e implementar a la interfaz
	 * 
	 * @param nombreTarea :nombre de la tarea a buscar
	 * 
	 */
	public void buscarTareaDesdeActual(String nombreTarea)
	{	
		Tarea tareaEncontrada=null;
		tareaEncontradaTabla.clear();
		
		try 
		{
			tareaEncontrada=proyecto.buscarTareaDesdeElActual(nombreTarea);
		} 
		catch (DatoNoEncontradoException e) 
		{
		
			e.printStackTrace();
		}
		
		if(tareaEncontrada!=null) 
		{
			Tarea tareaNueva=new Tarea();
			tareaNueva.setNombre(tareaEncontrada.getNombre());
			tareaNueva.setDescripcion(tareaEncontrada.getDescripcion());
			tareaNueva.setEsObligatoria(tareaEncontrada.esObligatoria());
			tareaNueva.setDuracion(tareaEncontrada.getDuracion());
			
			tareaEncontradaTabla.add(tareaNueva);
		}
	}
	
	
	/**
	 * Metodo para llamar el buscar tarea dado el nombre de la actividad  e implementar a la interfaz
	 * 
	 * @param nombreTarea : nombre de la tarea a buscar
	 * @param nombreActividad : nombre de la actividad que contiene la tarea
	 * 
	 */
	public void buscarTareaPorNombreActividad(String nombreActividad, String nombreTarea) 
	{	
		Tarea tareaEncontrada=null;
		tareaEncontradaTabla.clear();
		
		try
		{
			tareaEncontrada=proyecto.buscarTareaPorNombreDeActividad(nombreActividad, nombreTarea);
		} 
		catch (DatoNoEncontradoException e)
		{
			e.printStackTrace();
		}
		
		if(tareaEncontrada!=null) 
		{
			Tarea tareaNueva=new Tarea();
			tareaNueva.setNombre(tareaEncontrada.getNombre());
			tareaNueva.setDescripcion(tareaEncontrada.getDescripcion());
			tareaNueva.setEsObligatoria(tareaEncontrada.esObligatoria());
			tareaNueva.setDuracion(tareaEncontrada.getDuracion());
			
			tareaEncontradaTabla.add(tareaNueva);
		}
	}
	
	
	/**
	 * Metodo para llenar los arraylist auxiliares para mostrar los datos en las tablas de la interfaz
	 * 
	 */
	public void agregarProcesosTabla() 
	{
		Proceso aux=proyecto.getListaDeProcesos().getPrimero();
		int tamaño=proyecto.getListaDeProcesos().size();
		listaProcesosTabla.clear();
		
		for(int i=0;i<tamaño;i++) 
		{
			Proceso procesoAgregado=new Proceso();
			procesoAgregado.setId(aux.getId());
			procesoAgregado.setNombre(aux.getNombre());
			
			listaProcesosTabla.add(procesoAgregado);
			
			aux=aux.seguirEnlace(0);
		}
	}
	
	
	/**
	 * Metodo para llenar los arraylist auxiliares para mostrar los datos en las tablas de la interfaz
	 * 
	 */
	public void agregarActividadesTabla() 
	{
		Proceso procesoAux=proyecto.getListaDeProcesos().getPrimero();
		int tamañoProcesos=proyecto.getListaDeProcesos().size();
		listaActividadesTabla.clear();
		
		for(int i=0;i<tamañoProcesos;i++) 
		{
			ListaDoble listaActividades=procesoAux.getListaDeActividades();
			Actividad aux=listaActividades.getPrimero();
			
			for(int j=0;j<listaActividades.size();j++) 
			{
				Actividad actividadAgregada=new Actividad();
				actividadAgregada.setNombre(aux.getNombre());
				actividadAgregada.setDescripcion(aux.getDescripcion());
				actividadAgregada.setEsObligatorio(aux.getEsObligatorio());
				
				listaActividadesTabla.add(actividadAgregada);
				aux=aux.seguirEnlace(0);
			}
			
			procesoAux=procesoAux.seguirEnlace(0);
		}
	}
	
	
	/**
	 * Metodo para llenar los arraylist auxiliares para mostrar los datos en las tablas de la interfaz
	 * 
	 */
	public void agregarTareasTabla() 
	{
		Proceso procesoAux=proyecto.getListaDeProcesos().getPrimero();
		int tamañoProcesos=proyecto.getListaDeProcesos().size();
		listaTareasTabla.clear();
		
		for(int i=0;i<tamañoProcesos;i++)
		{
			ListaDoble listaActividades=procesoAux.getListaDeActividades();
			Actividad actividadAux=listaActividades.getPrimero();
			
			for(int j=0;j<listaActividades.size();j++)
			{
				Cola colaTareasActividad=actividadAux.getColaTareas();
				Tarea aux=colaTareasActividad.getPrimero();
				
				for(int k=0;k<colaTareasActividad.size();k++) 
				{
					Tarea tareaAgregada=new Tarea();
					tareaAgregada.setNombre(aux.getNombre());
					tareaAgregada.setDescripcion(aux.getDescripcion());
					tareaAgregada.setDuracion(aux.getDuracion());
					tareaAgregada.setEsObligatoria(aux.esObligatoria());
					
					listaTareasTabla.add(tareaAgregada);
					
					aux=aux.seguirEnlace(0);
				}
				actividadAux=actividadAux.seguirEnlace(0);
			}
			procesoAux=procesoAux.seguirEnlace(0);
		}
	}

}
