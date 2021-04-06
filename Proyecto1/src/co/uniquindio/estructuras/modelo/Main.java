package co.uniquindio.estructuras.modelo;

import co.uniquindio.estructuras.excepciones.DatoNoEncontradoException;
import co.uniquindio.estructuras.excepciones.ObjetoRepetidoExcepcion;
import co.uniquindio.estructuras.excepciones.TareaOpcionalException;
import co.uniquindio.estructuras.persistencia.Serializador;

public class Main 
{
	public static void main(String[] args) throws ObjetoRepetidoExcepcion, DatoNoEncontradoException, TareaOpcionalException 
	{
		Serializador serializadora=new Serializador();
				
		Proyecto proyecto=(Proyecto) serializadora.leerObjeto();

		if(proyecto==null) 
		{
			proyecto=new Proyecto();
					
			//=============Proceso============//
					
			Proceso nuevoProceso = new Proceso("456", "Desayunos");
			proyecto.crearProceso(nuevoProceso);
					
			Proceso nuevoProceso2 = new Proceso("323", "Almuerzos");
			proyecto.crearProceso(nuevoProceso2);
	
			proyecto.buscarProceso("323");
					
					
			//=============Actividad============//
					
			Actividad actividadNueva= new Actividad("Preparar cafe", "Que sea para el cliente", true);
			proyecto.crearActividadNormal("456", actividadNueva);
			
			Actividad actividadNueva2= new Actividad("Preparar huevos", "Que sean revueltos", true);
			proyecto.crearActividadNormal("456", actividadNueva2);
			
			Actividad actividadNueva3= new Actividad("Preparar arepas", "Que sean de maiz", true);
			proyecto.crearActividadNormal("456", actividadNueva3);
			
			Actividad actividadNueva4= new Actividad("Repartir desayunos", "Que no se caigan", false);
			proyecto.crearActividadNormal("456", actividadNueva4);
			
			
			Actividad actividadNueva5= new Actividad("Preparar arroz", "Que sea arroz blanco", true);
			proyecto.crearActividadNormal("323", actividadNueva5);
			
			Actividad actividadNueva6= new Actividad("Preparar jugo", "Que sea de naranja", true);
			proyecto.crearActividadNormal("323", actividadNueva6);
			
			Actividad actividadNueva7= new Actividad("Preparar carne", "Que sea de res", true);
			proyecto.crearActividadNormal("323", actividadNueva7);
			
			Actividad actividadNueva8= new Actividad("Repartir almuerzos", "Que no se enfrie", true);
			proyecto.crearActividadNormal("323", actividadNueva8);
			
//			Actividad actividadEncontrada = proyecto.buscarActividad("Diseño");
			
//			proyecto.intercambiarActividad("323", "Repartir almuerzos", "Preparar carne", 0);
					
					
			//=============Tarea============//
			
			Tarea tareaNueva= new Tarea("Escoger granos", "Cafe negro", true, 5);
			proyecto.crearTarea("456", "Preparar cafe", tareaNueva);
			
			Tarea tareaNueva2= new Tarea("Moler los granos", "Con la maquina", false, 10);
			proyecto.crearTarea("456", "Preparar cafe", tareaNueva2);

			Tarea tareaNueva3= new Tarea("Preparar y servir", "Servir en taza", true, 5);
			proyecto.crearTarea("456", "Preparar cafe", tareaNueva3);
			
			Tarea tareaNueva4= new Tarea("Romper los huevos", "Ponerlos en un recipiente", true, 4);
			proyecto.crearTarea("456", "Preparar huevos", tareaNueva4);
			
			Tarea tareaNueva5= new Tarea("Revolver los huevos", "A fuego lento", true, 15);
			proyecto.crearTarea("456", "Preparar huevos", tareaNueva5);
			
			Tarea tareaNueva6= new Tarea("Servir los huevos", "Presentar en los platos", true,20);
			proyecto.crearTarea("456", "Preparar huevos", tareaNueva6);
			
			Tarea tareaNueva7= new Tarea("Moler el maiz", "En la maquina", true, 20);
			proyecto.crearTarea("456", "Preparar arepas", tareaNueva7);
			
			Tarea tareaNueva8= new Tarea("Amazar", "Con las manos", false, 15);
			proyecto.crearTarea("456", "Preparar arepas", tareaNueva8);
			
			Tarea tareaNueva9= new Tarea("Asar las arepas", "En la parrilla", true, 20);
			proyecto.crearTarea("456", "Preparar arepas", tareaNueva9);
			
			Tarea tareaNueva10= new Tarea("Llamar a los meseros", "Para repartir los desayunos", false, 20);
			proyecto.crearTarea("456", "Repartir desayunos", tareaNueva10);
			
			Tarea tareaNueva11= new Tarea("Montar en banjeas", "Ordenadamente", true, 20);
			proyecto.crearTarea("456", "Repartir desayunos", tareaNueva11);
			
			Tarea tareaNueva12= new Tarea("Repartir", "A las mesas indicadas", true, 20);
			proyecto.crearTarea("456", "Repartir desayunos", tareaNueva12);
			
			
			
			Tarea tareaNueva13= new Tarea("Lavar arroz", "Con agua limpia", true, 5);
			proyecto.crearTarea("323", "Preparar arroz", tareaNueva13);
			
			Tarea tareaNueva14= new Tarea("Agregar a la olla", "Con su respectiva agua y sal", false, 10);
			proyecto.crearTarea("323", "Preparar arroz", tareaNueva14);

			Tarea tareaNueva15= new Tarea("Cocinar", "Dejar que se cocine bien", true, 30);
			proyecto.crearTarea("323", "Preparar arroz", tareaNueva15);
			
			Tarea tareaNueva16= new Tarea("Esprimir las naranjas", "Con esprimidor", true, 10);
			proyecto.crearTarea("323", "Preparar jugo", tareaNueva16);
			
			Tarea tareaNueva17= new Tarea("Agregar al recipiente", "Recipiente limpio", true, 4);
			proyecto.crearTarea("323", "Preparar jugo", tareaNueva17);
			
			Tarea tareaNueva18= new Tarea("Servir en vasos", "Para repartir", true, 10);
			proyecto.crearTarea("323", "Preparar jugo", tareaNueva18);
			
			Tarea tareaNueva19= new Tarea("Cortar la carne", "Tamaños iguales", false, 10);
			proyecto.crearTarea("323", "Preparar carne", tareaNueva19);
			
			Tarea tareaNueva20= new Tarea("Adobar la carne", "Varias especies", true, 8);
			proyecto.crearTarea("323", "Preparar carne", tareaNueva20);
			
			Tarea tareaNueva21= new Tarea("Asar la carne", "Fuego lento", true, 20);
			proyecto.crearTarea("323", "Preparar carne", tareaNueva21);
			
			Tarea tareaNueva22= new Tarea("Llamar meseros", "Para repartir", false, 4);
			proyecto.crearTarea("323", "Repartir almuerzos", tareaNueva22);
			
			Tarea tareaNueva23= new Tarea("Agregar a las banjeas", "5 por bandeja", true, 15);
			proyecto.crearTarea("323", "Repartir almuerzos", tareaNueva23);
			
			Tarea tareaNueva24= new Tarea("Repartir los almuerzos", "A las mesas", true, 20);
			proyecto.crearTarea("323", "Repartir almuerzos", tareaNueva24);
			
			
			
//			Tarea tareaEncontrada = proyecto.buscarTareaPorNombreDeActividad("323", "Preparar carne", "Asar la carne");
//			
//			Tarea tareaEncontrada2 = proyecto.buscarTareaDesdeElInicio("323", "Agregar a las bandejas");
//			
//			Tarea tareaEncontrada3 = proyecto.buscarTareaDesdeElActual("323", "Repartir los almuerzos");
			
			int duracionMax = proyecto.calcularDuracionMaximaProceso("456");
			int duracionMin = proyecto.calcularDuracionMinimaProceso("456");	
			
			proyecto.calcularTiempoMaximoYMinimo("Repartir almuerzos");
					
			//=============Mostrar============
					
			serializadora.escribirObjetos(proyecto);
				
			String cadena = proyecto.toString();
			System.out.println(cadena);
			
			System.out.println("El tiempo maximo de ejecucion es de " +duracionMax+ " minutos");
			System.out.println("El tiempo minimo de ejecucion es de " +duracionMin+ " minutos");
			
//			System.out.println("====Actividad encontrada====");
//			System.out.println(actividadEncontrada.toString());
			
			
			//========Buscar=======//
//			System.out.println("====Tarea encontrada====");
//			System.out.println(tareaEncontrada.toString());
//			
//			System.out.println(tareaEncontrada2.toString());
//			
//			System.out.println(tareaEncontrada3.toString());
//			
//			System.out.println("====Actividad encontrada====");
//			System.out.println(actividadEncontrada.toString());
				
		}
		else 
		{	
			String cadena = proyecto.toString();	
			System.out.println(cadena);
			
			//========Buscar=======//
//			System.out.println("====Tarea encontrada====");
//			System.out.println(tareaEncontrada.toString());
//			
//			System.out.println(tareaEncontrada2.toString());
//			
//			System.out.println(tareaEncontrada3.toString());
//			
//			System.out.println("====Actividad encontrada====");
//			System.out.println(actividadEncontrada.toString());
		}
	}
}
