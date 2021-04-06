package co.uniquindio.estructuras.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador 
{
	private ObjectInputStream lectorDeObjetos;
	private ObjectOutputStream escritorDeObjetos;
	public static final String RUTA_ARCHIVO_MODELOPROYECTODAT = "src/co/uniquindio/estructuras/resources/model.dat";
	
	
	/**
	 * Metodo para escribir los objetos
	 * 
	 * @param objeto
	 * 
	 */
	public void escribirObjetos(Object objeto) 
	{
		try 
		{
			escritorDeObjetos=new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_MODELOPROYECTODAT));
			escritorDeObjetos.writeObject(objeto);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("No se encontro el archivo .dat");
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo para leer los objetos
	 * 
	 */
	public Object leerObjeto()
	{	
		Object retorno=null;
		try 
		{
			lectorDeObjetos=new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_MODELOPROYECTODAT));
			try 
			{
				retorno=lectorDeObjetos.readObject();
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("No se encontro el archivo .dat");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

}
