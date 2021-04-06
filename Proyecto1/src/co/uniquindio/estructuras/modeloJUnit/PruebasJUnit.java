package co.uniquindio.estructuras.modeloJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import co.uniquindio.estructuras.excepciones.DatoNoEncontradoException;
import co.uniquindio.estructuras.excepciones.ObjetoRepetidoExcepcion;
import co.uniquindio.estructuras.excepciones.TareaOpcionalException;

public class PruebasJUnit {
	
	Proyecto proyecto=new Proyecto();
	
	public void escenario() {
		
		Proceso procesoNuevo=new Proceso("678", "Reparar carro");
		proyecto.crearProceso(procesoNuevo);
	}
	
	public void escenario2() {
		
		Proceso procesoNuevo=new Proceso("678", "Reparar carro");
		proyecto.crearProceso(procesoNuevo);
		
		Actividad actividadNueva1=new Actividad("Revisar carro", "Revisarlo todo", true);
		try {
			proyecto.crearActividadAlUltimo("678", actividadNueva1);
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void escenario3() {
		
		Proceso procesoNuevo=new Proceso("678", "Reparar carro");
		proyecto.crearProceso(procesoNuevo);
		
		Actividad actividadNueva1=new Actividad("Revisar carro", "Revisarlo todo", true);
		Actividad actividadNueva2=new Actividad("Abrir capo", "Abrir la parte de adelante", true);
		Actividad actividadNueva3=new Actividad("Mirar motor", "La parte principal del carro", true);
		
		try {
			proyecto.crearActividadAlUltimo("678", actividadNueva1);
			proyecto.crearActividadEnPosicion("678", "Revisar carro", actividadNueva2);
			proyecto.crearActividadDespuesDeLaUltimaCreada("678", actividadNueva3);
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void escenario4() {
		
		Proceso procesoNuevo=new Proceso("678", "Reparar carro");
		proyecto.crearProceso(procesoNuevo);
		
		Actividad actividadNueva1=new Actividad("Revisar carro", "Revisarlo todo", true);
		Actividad actividadNueva2=new Actividad("Abrir capo", "Abrir la parte de adelante", true);
		Actividad actividadNueva3=new Actividad("Mirar motor", "La parte principal del carro", true);
		
		try {
			proyecto.crearActividadAlUltimo("678", actividadNueva1);
			proyecto.crearActividadEnPosicion("678", "Revisar carro", actividadNueva2);
			proyecto.crearActividadDespuesDeLaUltimaCreada("678", actividadNueva3);
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Tarea tareaNueva1=new Tarea("Desarmar motor", "Para revisar sus fallas", true, 30);
		Tarea tareaNueva2=new Tarea("Mirar parte interior", "Para revisar sus fallas", true, 10);
		
		try {
			proyecto.crearTarea("678", "Mirar motor", tareaNueva1);
			proyecto.crearTareaEnUnaPosicion("678", "Revisar carro", 0, tareaNueva2);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TareaOpcionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCrearProceso() {
		Proceso procesoNuevo=new Proceso("678", "Reparar carro");
		
		assertEquals(proyecto.crearProceso(procesoNuevo), true);
		
	}
	
	@Test
	public void testBuscarProcesoBoolean() {
		
		escenario();
		
		assertEquals(proyecto.buscarProcesoBoolean("678"), true);
	}

	
	@Test
	public void testCrearActividadAlUltimo() {
		
		Actividad actividadNueva=new Actividad("Revisar carro", "Revisarlo todo", true);
		escenario();
		
		try {
			assertEquals(proyecto.crearActividadAlUltimo("678", actividadNueva), true);
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testCrearActividadEnPosicion() {
		
		Actividad actividadNueva=new Actividad("Abrir capo", "Abrir la parte de adelante", true);
		escenario2();
		
		try {
			assertEquals(proyecto.crearActividadEnPosicion("678", "Revisar carro", actividadNueva), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCrearActividadDespuesDeLaUltimaCreada() {
		
		Actividad actividadNueva=new Actividad("Mirar motor", "La parte principal del carro", true);
		escenario2();
		
		try {
			assertEquals(proyecto.crearActividadDespuesDeLaUltimaCreada("678", actividadNueva), true);
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testIntercambiarActividad() {
		
		escenario3();
		
		try {
			assertEquals(proyecto.intercambiarActividad("678", "Revisar carro", "Mirar motor", 0), true);
		} catch (ObjetoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testBuscarActividadBoolean() {
		
		escenario3();
		
		assertEquals(proyecto.buscarActividadBoolean("Abrir capo"), true);
		
	}

	@Test
	public void testCrearTarea() {
		
		Tarea tareaNueva=new Tarea("Desarmar motor", "Para revisar sus fallas", true, 30);
		escenario3();
		
		try {
			assertEquals(proyecto.crearTarea("678", "Mirar motor", tareaNueva), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TareaOpcionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCrearTareaEnUnaPosicion() {
		
		Tarea tareaNueva=new Tarea("Mirar parte interior", "Para revisar sus fallas", true, 10);
		escenario3();
		
		try {
			assertEquals(proyecto.crearTareaEnUnaPosicion("678", "Revisar carro", 0, tareaNueva), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TareaOpcionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testBuscarTareaDesdeElInicio() {
		
		escenario4();
		
		try {
			assertEquals(proyecto.buscarTareaDesdeElInicio("Mirar parte interior"), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testBuscarTareaDesdeElActual() {
		
		escenario4();
		
		try {
			assertEquals(proyecto.buscarTareaDesdeElActual("Desarmar motor"), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testBuscarTareaPorNombreDeActividad() {
		
		escenario4();
		
		try {
			assertEquals(proyecto.buscarTareaPorNombreDeActividad("Mirar motor", "Desarmar motor"), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCalcularDuracion() {
		
		escenario4();
		
		try {
			int actual=40;
			
			assertEquals(proyecto.calcularDuracion("678"), actual);
			
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCalcularTiempoMaximoYMinimo() {
		
		escenario4();
		
		try {
			assertEquals(proyecto.calcularTiempoMaximoYMinimo("Revisar carro"), true);
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
