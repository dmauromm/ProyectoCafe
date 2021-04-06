package co.uniquindio.estructuras.vista;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import co.uniquindio.estructuras.controlador.ControladorProyecto;
import co.uniquindio.estructuras.modelo.Actividad;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import co.uniquindio.estructuras.modelo.Proceso;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import co.uniquindio.estructuras.modelo.Tarea;

public class VistaProyecto 
{
	private DataBindingContext m_bindingContext;

	protected Shell shlProyectoEstructurasDe;
	private ControladorProyecto controlador;
	private Text txtNombreProceso;
	private Text txtIdProceso;
	private Table table_Procesos;
	private Text txtNombreActividad;
	private Text txtDescripcionActividad;
	private Text txtIdProcesoCrearActividad;
	private Table table_ActividadesTabla;
	private Text txtDescripcionTarea;
	private Text txtDuracionTarea;
	private Text txtNombreActividadAgregarTarea;
	private Text txtIdProcesoBuscarTabla;
	private Table table_ProcesosBusqueda;
	private Text txtNombreActividadBuscar;
	private Text txtNombreActividadBuscarTarea;
	private Text txtNombreTarea;
	private Text txtNombreActividadIntercambiar;
	private Text txtNombreActividadIntercambiar2;
	private Table table_ActividadTablaBuscar;
	private Table table_TareasBusqueda;
	private Proceso procesoSeleccionado;
	private Actividad actividadSeleccionada;
	private Text txtIdProcesoAgregarTarea;
	private Text txtPosicionAgregarTarea;
	private TableViewer tableViewer;
	private TableViewer tableViewer_3;
	private TableViewer tableViewer_1;
	private TableViewer tableViewer_4;
	private TableViewer tableViewer_5;
	private Text txtNombreTareaBuscar;
	private Text txtIdProcesoIntercambiarActividad;
	private Combo combo_ModoBusquedaTarea;
	private Table table_Tareas;
	private TableViewer tableViewer_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					VistaProyecto window = new VistaProyecto();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VistaProyecto() {
		
		controlador=new ControladorProyecto();
		controlador.agregarProcesosTabla();
		controlador.agregarActividadesTabla();
		controlador.agregarTareasTabla();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlProyectoEstructurasDe.open();
		shlProyectoEstructurasDe.layout();
		while (!shlProyectoEstructurasDe.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlProyectoEstructurasDe = new Shell();
		shlProyectoEstructurasDe.setSize(1253, 728);
		shlProyectoEstructurasDe.setText("Proyecto Estructuras de datos");
		shlProyectoEstructurasDe.setLayout(null);
		
		TabFolder tabFolder = new TabFolder(shlProyectoEstructurasDe, SWT.NONE);
		tabFolder.setBounds(0, 0, 1233, 671);
		
		TabItem tbtmProcesos = new TabItem(tabFolder, SWT.NONE);
		tbtmProcesos.setText("Procesos");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmProcesos.setControl(composite);
		
		Group grpInformacionDelProceso = new Group(composite, SWT.NONE);
		grpInformacionDelProceso.setText("Informacion del proceso");
		grpInformacionDelProceso.setBounds(10, 10, 565, 178);
		
		Label lblNombreProceso = new Label(grpInformacionDelProceso, SWT.NONE);
		lblNombreProceso.setBounds(10, 38, 70, 20);
		lblNombreProceso.setText("Nombre:");
		
		Label lblId = new Label(grpInformacionDelProceso, SWT.NONE);
		lblId.setBounds(10, 73, 43, 20);
		lblId.setText("Id:");
		
		txtNombreProceso = new Text(grpInformacionDelProceso, SWT.BORDER);
		txtNombreProceso.setText("Ingrese el nombre del proceso a crear");
		txtNombreProceso.setBounds(86, 38, 356, 26);
		
		txtIdProceso = new Text(grpInformacionDelProceso, SWT.BORDER);
		txtIdProceso.setText("Ingrese el id del proceso a crear");
		txtIdProceso.setBounds(86, 73, 356, 26);
		
		Button btnCrearProceso = new Button(grpInformacionDelProceso, SWT.NONE);
		btnCrearProceso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtNombreProceso.getText()!=null && txtIdProceso.getText()!=null) {
					
					controlador.crearProceso(txtNombreProceso.getText(), txtIdProceso.getText());
					initDataBindings();
					txtNombreProceso.setText("");
					txtIdProceso.setText("");
				}
				
			}
		});
		btnCrearProceso.setBounds(214, 122, 90, 30);
		btnCrearProceso.setText("Crear");
		
		Group grpTablaConLos = new Group(composite, SWT.NONE);
		grpTablaConLos.setText("Tabla con los procesos creados");
		grpTablaConLos.setBounds(10, 203, 565, 365);
		
		tableViewer = new TableViewer(grpTablaConLos, SWT.BORDER | SWT.FULL_SELECTION);
		table_Procesos = tableViewer.getTable();
		table_Procesos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				procesoSeleccionado= (Proceso) table_Procesos.getItem(table_Procesos.getSelectionIndex()).getData();
			}
		});
		table_Procesos.setLinesVisible(true);
		table_Procesos.setHeaderVisible(true);
		table_Procesos.setBounds(10, 28, 545, 273);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblcIdProcesoTabla = tableViewerColumn.getColumn();
		tblcIdProcesoTabla.setWidth(176);
		tblcIdProcesoTabla.setText("Id");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombreProcesoTabla = tableViewerColumn_1.getColumn();
		tblclmnNombreProcesoTabla.setWidth(364);
		tblclmnNombreProcesoTabla.setText("Nombre");
		
		Button btnModificar = new Button(grpTablaConLos, SWT.NONE);
		btnModificar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(procesoSeleccionado!=null) {
					
					if(procesoSeleccionado.getId()!=null && txtIdProceso.getText()!=null && txtNombreProceso.getText()!=null) {
						
						controlador.modificarProceso(procesoSeleccionado.getId(), txtIdProceso.getText(), txtNombreProceso.getText());
						initDataBindings();
					}
					
				}
			}
		});
		btnModificar.setBounds(10, 325, 75, 30);
		btnModificar.setText("Modificar");
		
		Button btnEliminar = new Button(grpTablaConLos, SWT.NONE);
		btnEliminar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(procesoSeleccionado!=null) {
					
					controlador.eliminarProceso(procesoSeleccionado.getId());
					initDataBindings();
				}
			}
		});
		btnEliminar.setBounds(95, 325, 83, 30);
		btnEliminar.setText("Eliminar");
		
		Group grpModificarOEliminarUnProceso = new Group(composite, SWT.NONE);
		grpModificarOEliminarUnProceso.setText("Buscar un proceso");
		grpModificarOEliminarUnProceso.setBounds(585, 10, 630, 558);
		
		Label lblParaModificarO = new Label(grpModificarOEliminarUnProceso, SWT.NONE);
		lblParaModificarO.setBounds(10, 30, 313, 36);
		lblParaModificarO.setText("Para buscar un proceso debe buscarlo por su id");
		
		Label lblIngreseDato = new Label(grpModificarOEliminarUnProceso, SWT.NONE);
		lblIngreseDato.setBounds(10, 72, 110, 20);
		lblIngreseDato.setText("Ingrese el dato:");
		
		txtIdProcesoBuscarTabla = new Text(grpModificarOEliminarUnProceso, SWT.BORDER);
		txtIdProcesoBuscarTabla.setText("Ingrese el id del proceso a buscar");
		txtIdProcesoBuscarTabla.setBounds(10, 98, 597, 31);
		
		Button btn_BuscarProceso = new Button(grpModificarOEliminarUnProceso, SWT.NONE);
		btn_BuscarProceso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtIdProcesoBuscarTabla.getText()!=null) {
					
					controlador.buscarProceso(txtIdProcesoBuscarTabla.getText());
					txtIdProcesoBuscarTabla.setText("");
					initDataBindings();
				}
				
			}
		});
		btn_BuscarProceso.setBounds(10, 146, 83, 25);
		btn_BuscarProceso.setText("Buscar\r\n");
		
		Group grpResultadoDeBusqueda = new Group(grpModificarOEliminarUnProceso, SWT.NONE);
		grpResultadoDeBusqueda.setText("Resultado de busqueda");
		grpResultadoDeBusqueda.setBounds(10, 197, 610, 232);
		
		tableViewer_3 = new TableViewer(grpResultadoDeBusqueda, SWT.BORDER | SWT.FULL_SELECTION);
		table_ProcesosBusqueda = tableViewer_3.getTable();
		table_ProcesosBusqueda.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				procesoSeleccionado= (Proceso) table_ProcesosBusqueda.getItem(table_ProcesosBusqueda.getSelectionIndex()).getData();
			}
		});
		table_ProcesosBusqueda.setLinesVisible(true);
		table_ProcesosBusqueda.setHeaderVisible(true);
		table_ProcesosBusqueda.setBounds(10, 24, 590, 198);
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer_3, SWT.NONE);
		TableColumn tblcIdProcesoTablaBuscar = tableViewerColumn_6.getColumn();
		tblcIdProcesoTablaBuscar.setWidth(183);
		tblcIdProcesoTablaBuscar.setText("Id");
		
		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer_3, SWT.NONE);
		TableColumn tblclmnNombreProcesoTablaBuscar = tableViewerColumn_7.getColumn();
		tblclmnNombreProcesoTablaBuscar.setWidth(402);
		tblclmnNombreProcesoTablaBuscar.setText("Nombre");
		
		Button btnConsultarTiempoDe = new Button(grpModificarOEliminarUnProceso, SWT.NONE);
		btnConsultarTiempoDe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if(procesoSeleccionado!=null)
				{
					int duracionMaxima=controlador.consultarTiempoEjecucionMaximaProceso(procesoSeleccionado.getId());
					procesoSeleccionado.setTiempoMaximo(duracionMaxima);
					
					int duracionMinima= controlador.consultarTiempoEjecucionMinimaProceso(procesoSeleccionado.getId());
					procesoSeleccionado.setTiempoMinimo(duracionMinima);
					
					JOptionPane.showMessageDialog(null, "El proceso "+procesoSeleccionado.getNombre()+ " tiene un tiempo maximo de ejecucion de " +
													procesoSeleccionado.getTiempoMaximo() +" minutos"+"\n\n"+"El proceso "+procesoSeleccionado.getNombre()+ " tiene un tiempo minimo de ejecucion de " +
													procesoSeleccionado.getTiempoMinimo() +"minutos");
				}
			}
		});
		btnConsultarTiempoDe.setBounds(411, 518, 209, 30);
		btnConsultarTiempoDe.setText("Consultar tiempo de duracion");
		
		TabItem tbtmActividades = new TabItem(tabFolder, SWT.NONE);
		tbtmActividades.setText("Actividades");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmActividades.setControl(composite_1);
		
		Group grpInformacionDeLa = new Group(composite_1, SWT.NONE);
		grpInformacionDeLa.setText("Informacion de la actividad");
		grpInformacionDeLa.setBounds(10, 10, 485, 623);
		
		Label lblNombre_1 = new Label(grpInformacionDeLa, SWT.NONE);
		lblNombre_1.setBounds(10, 26, 70, 20);
		lblNombre_1.setText("Nombre: ");
		
		Label lblDescripcion = new Label(grpInformacionDeLa, SWT.NONE);
		lblDescripcion.setBounds(10, 65, 88, 20);
		lblDescripcion.setText("Descripcion:");
		
		Label lblesObligatoria = new Label(grpInformacionDeLa, SWT.NONE);
		lblesObligatoria.setBounds(10, 109, 112, 20);
		lblesObligatoria.setText("\u00BFEs obligatoria?");
		
		txtNombreActividad = new Text(grpInformacionDeLa, SWT.BORDER);
		txtNombreActividad.setText("Ingrese el nombre de la actividad a crear");
		txtNombreActividad.setBounds(105, 26, 370, 26);
		
		txtDescripcionActividad = new Text(grpInformacionDeLa, SWT.BORDER);
		txtDescripcionActividad.setText("Ingrese la descripcion de la actividad a crear");
		txtDescripcionActividad.setBounds(104, 65, 371, 26);
		
		Combo combo_ActividadObligatoria = new Combo(grpInformacionDeLa, SWT.NONE);
		combo_ActividadObligatoria.setItems(new String[] {"Si", "No"});
		combo_ActividadObligatoria.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		combo_ActividadObligatoria.setBounds(128, 106, 152, 28);
		combo_ActividadObligatoria.setText("Seleccione si o no");
		
		Group grpcomoDeseaAgregar = new Group(grpInformacionDeLa, SWT.NONE);
		grpcomoDeseaAgregar.setText("\u00BFComo desea agregar la actividad y en que proceso?");
		grpcomoDeseaAgregar.setBounds(10, 170, 465, 443);
		
		Combo combo_ModoAgregacionActividad = new Combo(grpcomoDeseaAgregar, SWT.NONE);
		combo_ModoAgregacionActividad.setItems(new String[] {"Indicando el nombre de la actividad que le precede", "Agregandola al final de las demas", "Despues de la ultima actividad creada"});
		combo_ModoAgregacionActividad.setBounds(10, 26, 445, 23);
		combo_ModoAgregacionActividad.setText("Seleccione la forma de crear la actividad");
		
		Label lblActividad = new Label(grpcomoDeseaAgregar, SWT.NONE);
		lblActividad.setBounds(10, 68, 107, 20);
		lblActividad.setText("Id del proceso:");
		
		txtIdProcesoCrearActividad = new Text(grpcomoDeseaAgregar, SWT.BORDER);
		txtIdProcesoCrearActividad.setText("Ingrese el id del proceso donde desea crear la actividad");
		txtIdProcesoCrearActividad.setBounds(119, 68, 336, 26);
		
		tableViewer_1 = new TableViewer(grpcomoDeseaAgregar, SWT.BORDER | SWT.FULL_SELECTION);
		table_ActividadesTabla = tableViewer_1.getTable();
		table_ActividadesTabla.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				actividadSeleccionada = (Actividad) table_ActividadesTabla.getItem(table_ActividadesTabla.getSelectionIndex()).getData();
			}
		});
		table_ActividadesTabla.setLinesVisible(true);
		table_ActividadesTabla.setHeaderVisible(true);
		table_ActividadesTabla.setBounds(10, 115, 445, 258);
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblcNombreActividadTabla = tableViewerColumn_2.getColumn();
		tblcNombreActividadTabla.setWidth(142);
		tblcNombreActividadTabla.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmn_DescripcionActividadTabla = tableViewerColumn_3.getColumn();
		tblclmn_DescripcionActividadTabla.setWidth(173);
		tblclmn_DescripcionActividadTabla.setText("Descripcion\r\n");
		
		TableColumn tblclmnesObligatoriaTabla = new TableColumn(table_ActividadesTabla, SWT.NONE);
		tblclmnesObligatoriaTabla.setWidth(123);
		tblclmnesObligatoriaTabla.setText("\u00BFEs obligatoria?");
		
		Button btnAgregarActividad = new Button(grpcomoDeseaAgregar, SWT.NONE);
		btnAgregarActividad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int opcionAgregacion=combo_ModoAgregacionActividad.getSelectionIndex();
				
				switch(opcionAgregacion) {
				
					case 0: if(actividadSeleccionada!=null) 
					{
								if(txtNombreActividad.getText()!=null && txtDescripcionActividad.getText()!=null && txtIdProcesoCrearActividad.getText()!=null && actividadSeleccionada.getNombre()!=null) {
									
									controlador.crearActividadEnPosicion(txtNombreActividad.getText(), txtDescripcionActividad.getText(), combo_ActividadObligatoria.getSelectionIndex(), txtIdProcesoCrearActividad.getText(), actividadSeleccionada.getNombre());
									txtNombreActividad.setText(""); 
									txtDescripcionActividad.setText(""); 
									txtIdProcesoCrearActividad.setText("");
									initDataBindings();
								}
						
							}
							else 
							{
								System.out.println("Actividad no encontrada");
							}
							break;
							
					case 1: if(txtNombreActividad.getText()!=null && txtDescripcionActividad.getText()!=null && txtIdProcesoCrearActividad.getText()!=null) 
							{
								controlador.crearActividadAlFinal(txtNombreActividad.getText(), txtDescripcionActividad.getText(), combo_ActividadObligatoria.getSelectionIndex(), txtIdProcesoCrearActividad.getText());
								txtNombreActividad.setText(""); 
								txtDescripcionActividad.setText(""); 
								txtIdProcesoCrearActividad.setText("");
								initDataBindings();
							}
							
							break;
							
					case 2: if(txtNombreActividad.getText()!=null && txtDescripcionActividad.getText()!=null && txtIdProcesoCrearActividad.getText()!=null) 
							{
								controlador.crearActividadDespuesDeUltimaCreada(txtNombreActividad.getText(), txtDescripcionActividad.getText(), combo_ActividadObligatoria.getSelectionIndex(), txtIdProcesoCrearActividad.getText());
								txtNombreActividad.setText(""); 
								txtDescripcionActividad.setText(""); 
								txtIdProcesoCrearActividad.setText("");
								initDataBindings();
							}
						
							break;
							
					default: System.out.println("No se encontro una opcion");
							break;
				}
			}
		});
		btnAgregarActividad.setBounds(10, 391, 90, 30);
		btnAgregarActividad.setText("Agregar");
		
		Group grpModificarOEliminar = new Group(composite_1, SWT.NONE);
		grpModificarOEliminar.setText("Buscar una actividad");
		grpModificarOEliminar.setBounds(506, 10, 384, 623);
		
		Label lblParaModificarO_1 = new Label(grpModificarOEliminar, SWT.NONE);
		lblParaModificarO_1.setBounds(10, 37, 364, 37);
		lblParaModificarO_1.setText("Para buscar una actividad debe hacerlo por el nombre");
		
		Label lblIngreseElDato = new Label(grpModificarOEliminar, SWT.NONE);
		lblIngreseElDato.setBounds(10, 94, 116, 21);
		lblIngreseElDato.setText("Ingrese el dato:");
		
		txtNombreActividadBuscar = new Text(grpModificarOEliminar, SWT.BORDER);
		txtNombreActividadBuscar.setText("Ingrese el nombre de la actividad a buscar");
		txtNombreActividadBuscar.setBounds(10, 124, 362, 31);
		
		Button btnBuscarActividad = new Button(grpModificarOEliminar, SWT.NONE);
		btnBuscarActividad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtNombreActividadBuscar.getText()!=null) {
					
					controlador.buscarActividad(txtNombreActividadBuscar.getText());
					txtNombreActividadBuscar.setText("");
					initDataBindings();
				}
				
			}
		});
		btnBuscarActividad.setBounds(10, 171, 105, 31);
		btnBuscarActividad.setText("Buscar");
		
		Group grpResultadoDeBusqueda_1 = new Group(grpModificarOEliminar, SWT.NONE);
		grpResultadoDeBusqueda_1.setText("Resultado de busqueda");
		grpResultadoDeBusqueda_1.setBounds(10, 247, 362, 366);
		
		tableViewer_4 = new TableViewer(grpResultadoDeBusqueda_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_ActividadTablaBuscar = tableViewer_4.getTable();
		table_ActividadTablaBuscar.setLinesVisible(true);
		table_ActividadTablaBuscar.setHeaderVisible(true);
		table_ActividadTablaBuscar.setBounds(10, 22, 342, 274);
		
		TableViewerColumn tableViewerColumn_8 = new TableViewerColumn(tableViewer_4, SWT.NONE);
		TableColumn tblclmnNombreActividadTablaBuscar = tableViewerColumn_8.getColumn();
		tblclmnNombreActividadTablaBuscar.setWidth(144);
		tblclmnNombreActividadTablaBuscar.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(tableViewer_4, SWT.NONE);
		TableColumn tblcTiempoMinimoTablaBuscar = tableViewerColumn_9.getColumn();
		tblcTiempoMinimoTablaBuscar.setWidth(100);
		tblcTiempoMinimoTablaBuscar.setText("Minimo");
		
		TableViewerColumn tableViewerColumn_14 = new TableViewerColumn(tableViewer_4, SWT.NONE);
		TableColumn tblcTiempoMaximoTablaBuscar = tableViewerColumn_14.getColumn();
		tblcTiempoMaximoTablaBuscar.setWidth(92);
		tblcTiempoMaximoTablaBuscar.setText("Maximo");
		
		Group grpIntercambiarActividades = new Group(composite_1, SWT.NONE);
		grpIntercambiarActividades.setText("Intercambiar actividades");
		grpIntercambiarActividades.setBounds(908, 10, 306, 623);
		
		Label lblNombreDeLa = new Label(grpIntercambiarActividades, SWT.NONE);
		lblNombreDeLa.setBounds(10, 126, 182, 20);
		lblNombreDeLa.setText("Nombre de la actividad 1:");
		
		txtNombreActividadIntercambiar = new Text(grpIntercambiarActividades, SWT.BORDER);
		txtNombreActividadIntercambiar.setText("Ingrese el nombre de la actividad a buscar");
		txtNombreActividadIntercambiar.setBounds(10, 152, 232, 26);
		
		Label lblNombreDeLa_1 = new Label(grpIntercambiarActividades, SWT.NONE);
		lblNombreDeLa_1.setBounds(10, 197, 182, 20);
		lblNombreDeLa_1.setText("Nombre de la actividad 2:");
		
		txtNombreActividadIntercambiar2 = new Text(grpIntercambiarActividades, SWT.BORDER);
		txtNombreActividadIntercambiar2.setText("Ingrese el nombre de la actividad a buscar\r\n");
		txtNombreActividadIntercambiar2.setBounds(10, 223, 232, 26);
		
		Label lbldeseaIntercambiarLas = new Label(grpIntercambiarActividades, SWT.NONE);
		lbldeseaIntercambiarLas.setBounds(10, 278, 254, 40);
		lbldeseaIntercambiarLas.setText("\u00BFDesea intercambiar las actividades \ncon sus tareas?");
		
		Combo combo_IntercambiarTareasActividades = new Combo(grpIntercambiarActividades, SWT.NONE);
		combo_IntercambiarTareasActividades.setItems(new String[] {"Si", "No"});
		combo_IntercambiarTareasActividades.setBounds(10, 324, 158, 28);
		combo_IntercambiarTareasActividades.setText("Seleccione si o no");
		
		Button btnIntercambiarActividad = new Button(grpIntercambiarActividades, SWT.NONE);
		btnIntercambiarActividad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtIdProcesoIntercambiarActividad.getText()!=null && txtNombreActividadIntercambiar.getText()!= null && txtNombreActividadIntercambiar2.getText()!=null) {
					
					controlador.intercambiarActividad(txtIdProcesoIntercambiarActividad.getText(), txtNombreActividadIntercambiar.getText(), txtNombreActividadIntercambiar2.getText(), combo_IntercambiarTareasActividades.getSelectionIndex());
					txtIdProcesoIntercambiarActividad.setText("");
					txtNombreActividadIntercambiar.setText("");
					txtNombreActividadIntercambiar2.setText("");
					initDataBindings();
					
				}
				
			}
		});
		btnIntercambiarActividad.setBounds(10, 378, 105, 30);
		btnIntercambiarActividad.setText("Intercambiar");
		
		txtIdProcesoIntercambiarActividad = new Text(grpIntercambiarActividades, SWT.BORDER);
		txtIdProcesoIntercambiarActividad.setText("Ingrese el id del proceso");
		txtIdProcesoIntercambiarActividad.setBounds(10, 78, 232, 26);
		
		Label lblIdDelProceso = new Label(grpIntercambiarActividades, SWT.NONE);
		lblIdDelProceso.setBounds(10, 50, 105, 22);
		lblIdDelProceso.setText("Id del proceso:");
		
		TabItem tbtmTareas = new TabItem(tabFolder, SWT.NONE);
		tbtmTareas.setText("Tareas");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmTareas.setControl(composite_2);
		
		Group grpInformacionDeLa_1 = new Group(composite_2, SWT.NONE);
		grpInformacionDeLa_1.setText("Informacion de la tarea");
		grpInformacionDeLa_1.setBounds(10, 10, 600, 618);
		
		Label lblDescripcion_1 = new Label(grpInformacionDeLa_1, SWT.NONE);
		lblDescripcion_1.setBounds(10, 73, 92, 20);
		lblDescripcion_1.setText("Descripcion:");
		
		Label lblesObligatoria_Tarea = new Label(grpInformacionDeLa_1, SWT.NONE);
		lblesObligatoria_Tarea.setBounds(10, 113, 114, 20);
		lblesObligatoria_Tarea.setText("\u00BFEs obligatoria?");
		
		txtDescripcionTarea = new Text(grpInformacionDeLa_1, SWT.BORDER);
		txtDescripcionTarea.setText("Ingrese la descripcion de la tarea a agregar");
		txtDescripcionTarea.setBounds(110, 70, 480, 26);
		
		Combo combo_TareaObligatoria = new Combo(grpInformacionDeLa_1, SWT.NONE);
		combo_TareaObligatoria.setItems(new String[] {"Si", "No"});
		combo_TareaObligatoria.setBounds(130, 110, 159, 23);
		combo_TareaObligatoria.setText("Seleccione si o no");
		
		Label lblDuracionEnMinutos_Tarea = new Label(grpInformacionDeLa_1, SWT.NONE);
		lblDuracionEnMinutos_Tarea.setBounds(10, 147, 150, 20);
		lblDuracionEnMinutos_Tarea.setText("Duracion (En minutos):");
		
		txtDuracionTarea = new Text(grpInformacionDeLa_1, SWT.BORDER);
		txtDuracionTarea.setText("Ingrese la duracion");
		txtDuracionTarea.setBounds(175, 144, 415, 26);
		
		Group grpcomoDeseaAgregar_1 = new Group(grpInformacionDeLa_1, SWT.NONE);
		grpcomoDeseaAgregar_1.setText("\u00BFComo desea agregar la tarea y en qu\u00E9 actividad?");
		grpcomoDeseaAgregar_1.setBounds(10, 191, 580, 417);
		
		Combo combo_ModoAgreagacionTarea = new Combo(grpcomoDeseaAgregar_1, SWT.NONE);
		combo_ModoAgreagacionTarea.setItems(new String[] {"Insertar al final de la secuencia de tareas", "Insertar en una posicion determinada"});
		combo_ModoAgreagacionTarea.setBounds(10, 30, 560, 23);
		combo_ModoAgreagacionTarea.setText("Seleccione la forma de agregar la tarea");
		
		Label lblPosicion_Tarea_Actividad = new Label(grpcomoDeseaAgregar_1, SWT.NONE);
		lblPosicion_Tarea_Actividad.setBounds(10, 129, 85, 20);
		lblPosicion_Tarea_Actividad.setText("Actividad: ");
		
		txtNombreActividadAgregarTarea = new Text(grpcomoDeseaAgregar_1, SWT.BORDER);
		txtNombreActividadAgregarTarea.setText("Ingrese la actividad donde agregara la tarea");
		txtNombreActividadAgregarTarea.setBounds(101, 126, 469, 26);
		
		Button btnAgregar_Tarea = new Button(grpcomoDeseaAgregar_1, SWT.NONE);
		btnAgregar_Tarea.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int opcion=combo_ModoAgreagacionTarea.getSelectionIndex();
				
				switch(opcion) {
				
					case 0: if(txtNombreTarea.getText()!=null && txtDescripcionTarea.getText()!=null && txtIdProcesoAgregarTarea.getText()!=null && txtNombreActividadAgregarTarea.getText()!=null) {
								
								controlador.agregarTarea(txtNombreTarea.getText(), txtDescripcionTarea.getText(), combo_TareaObligatoria.getSelectionIndex(), Integer.valueOf(txtDuracionTarea.getText()), txtIdProcesoAgregarTarea.getText(), txtNombreActividadAgregarTarea.getText());
								txtNombreTarea.setText("");
								txtDescripcionTarea.setText(""); 
								txtDuracionTarea.setText("");
								txtIdProcesoAgregarTarea.setText("");
								txtNombreActividadAgregarTarea.setText("");
								initDataBindings();
							}
						
							break;
							
					case 1: if(txtNombreTarea.getText()!=null && txtDescripcionTarea.getText()!=null && txtDuracionTarea.getText()!=null && txtIdProcesoAgregarTarea.getText()!=null && txtNombreActividadAgregarTarea.getText()!=null && txtPosicionAgregarTarea.getText()!=null) {
						
								controlador.agregarTareaEnPosicion(txtNombreTarea.getText(), txtDescripcionTarea.getText(), combo_TareaObligatoria.getSelectionIndex(), Integer.valueOf(txtDuracionTarea.getText()), txtIdProcesoAgregarTarea.getText(), txtNombreActividadAgregarTarea.getText(), Integer.valueOf(txtPosicionAgregarTarea.getText()));
								txtNombreTarea.setText("");
								txtDescripcionTarea.setText(""); 
								txtDuracionTarea.setText("");
								txtPosicionAgregarTarea.setText("");
								txtIdProcesoAgregarTarea.setText("");
								txtNombreActividadAgregarTarea.setText("");
								initDataBindings();
							}
							break;
							
					default: System.out.println("Opcion no encontrada");
							break;
				
				}
			}
		});
		btnAgregar_Tarea.setBounds(10, 377, 90, 30);
		btnAgregar_Tarea.setText("Agregar");
		
		Label lblProceso = new Label(grpcomoDeseaAgregar_1, SWT.NONE);
		lblProceso.setBounds(10, 82, 79, 20);
		lblProceso.setText("Id proceso:");
		
		txtIdProcesoAgregarTarea = new Text(grpcomoDeseaAgregar_1, SWT.BORDER);
		txtIdProcesoAgregarTarea.setText("Ingrese el id del proceso donde se encuentra la actividad");
		txtIdProcesoAgregarTarea.setBounds(101, 82, 469, 26);
		
		Label lblPosicionDeAgregacion = new Label(grpcomoDeseaAgregar_1, SWT.NONE);
		lblPosicionDeAgregacion.setBounds(10, 174, 166, 20);
		lblPosicionDeAgregacion.setText("Posicion de agregacion:");
		
		txtPosicionAgregarTarea = new Text(grpcomoDeseaAgregar_1, SWT.BORDER);
		txtPosicionAgregarTarea.setText("Ingrese la posicion donde desea agregar la tarea");
		txtPosicionAgregarTarea.setBounds(178, 174, 392, 26);
		
		tableViewer_2 = new TableViewer(grpcomoDeseaAgregar_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_Tareas = tableViewer_2.getTable();
		table_Tareas.setLinesVisible(true);
		table_Tareas.setHeaderVisible(true);
		table_Tareas.setBounds(10, 218, 560, 153);
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tblclmnNombre_TablaTareas = tableViewerColumn_4.getColumn();
		tblclmnNombre_TablaTareas.setWidth(148);
		tblclmnNombre_TablaTareas.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tblclmnDescripcinTablaTareas = tableViewerColumn_5.getColumn();
		tblclmnDescripcinTablaTareas.setWidth(204);
		tblclmnDescripcinTablaTareas.setText("Descripci\u00F3n");
		
		TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tblclmnesObligatoria_TablaTareas = tableViewerColumn_10.getColumn();
		tblclmnesObligatoria_TablaTareas.setWidth(100);
		tblclmnesObligatoria_TablaTareas.setText("\u00BFEs obligatoria?");
		
		TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(tableViewer_2, SWT.NONE);
		TableColumn tblclmnDuracin_TablaTareas = tableViewerColumn_15.getColumn();
		tblclmnDuracin_TablaTareas.setWidth(103);
		tblclmnDuracin_TablaTareas.setText("Duraci\u00F3n");
		
		Label lblNombre = new Label(grpInformacionDeLa_1, SWT.NONE);
		lblNombre.setBounds(10, 32, 70, 20);
		lblNombre.setText("Nombre:");
		
		txtNombreTarea = new Text(grpInformacionDeLa_1, SWT.BORDER);
		txtNombreTarea.setText("Ingrese el nombre de la tarea a agregar");
		txtNombreTarea.setBounds(110, 26, 480, 26);
		
		Group grpModificarOEliminar_1 = new Group(composite_2, SWT.NONE);
		grpModificarOEliminar_1.setText("Buscar tarea");
		grpModificarOEliminar_1.setBounds(616, 10, 599, 618);
		
		Label lblParaModificarO_2 = new Label(grpModificarOEliminar_1, SWT.NONE);
		lblParaModificarO_2.setBounds(10, 43, 579, 35);
		lblParaModificarO_2.setText("Para buscar una tarea debe seleccionar de qué manera y llenar los campos necesarios");
		
		Label lblIngreseElDato_1 = new Label(grpModificarOEliminar_1, SWT.NONE);
		lblIngreseElDato_1.setBounds(10, 190, 163, 22);
		lblIngreseElDato_1.setText("Nombre de la actividad:");
		
		txtNombreActividadBuscarTarea = new Text(grpModificarOEliminar_1, SWT.BORDER);
		txtNombreActividadBuscarTarea.setText("Ingrese el nombre de la actividad donde se encuentra la actividad");
		txtNombreActividadBuscarTarea.setBounds(10, 218, 579, 27);
		
		Group grpResultadoDeBusqueda_2 = new Group(grpModificarOEliminar_1, SWT.NONE);
		grpResultadoDeBusqueda_2.setText("Resultado de busqueda");
		grpResultadoDeBusqueda_2.setBounds(10, 301, 579, 274);
		
		tableViewer_5 = new TableViewer(grpResultadoDeBusqueda_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_TareasBusqueda = tableViewer_5.getTable();
		table_TareasBusqueda.setLinesVisible(true);
		table_TareasBusqueda.setHeaderVisible(true);
		table_TareasBusqueda.setBounds(10, 22, 559, 242);
		
		TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(tableViewer_5, SWT.NONE);
		TableColumn tblclmnNombreBusquedaTarea = tableViewerColumn_11.getColumn();
		tblclmnNombreBusquedaTarea.setText("Nombre");
		tblclmnNombreBusquedaTarea.setWidth(172);
		
		TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(tableViewer_5, SWT.NONE);
		TableColumn tblclmnDescripcionBusquedaTarea = tableViewerColumn_12.getColumn();
		tblclmnDescripcionBusquedaTarea.setWidth(255);
		tblclmnDescripcionBusquedaTarea.setText("Descripci\u00F3n");
		
		TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(tableViewer_5, SWT.NONE);
		TableColumn tblclmnDuracin = tableViewerColumn_13.getColumn();
		tblclmnDuracin.setWidth(127);
		tblclmnDuracin.setText("Duraci\u00F3n");
		
		Button btnBuscar_Tarea = new Button(grpModificarOEliminar_1, SWT.NONE);
		btnBuscar_Tarea.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int opcion=combo_ModoBusquedaTarea.getSelectionIndex();
				
				switch(opcion) {
				
					case 0: if(txtNombreTareaBuscar.getText()!=null) {
						
								controlador.buscarTareaInicio(txtNombreTareaBuscar.getText());
								txtNombreTareaBuscar.setText("");
								initDataBindings();
							}
						
							break;
							
					case 1: if(txtNombreTareaBuscar.getText()!=null) {
						
								controlador.buscarTareaDesdeActual(txtNombreTareaBuscar.getText());
								txtNombreTareaBuscar.setText("");
								initDataBindings();
							}
						
							break;
					
					case 2: if(txtNombreActividadBuscarTarea.getText()!=null && txtNombreTareaBuscar.getText()!=null) {
								
								controlador.buscarTareaPorNombreActividad(txtNombreActividadBuscarTarea.getText(), txtNombreTareaBuscar.getText());
								txtNombreTareaBuscar.setText("");
								txtNombreActividadBuscarTarea.setText("");
								initDataBindings();
							}
						
							break;
							
					default: System.out.println("Opcion no encontrada");
				}
				
			}
		});
		btnBuscar_Tarea.setBounds(10, 256, 86, 27);
		btnBuscar_Tarea.setText("Buscar");
		
		Label lblNewLabel = new Label(grpModificarOEliminar_1, SWT.NONE);
		lblNewLabel.setBounds(10, 130, 141, 20);
		lblNewLabel.setText("Nombre de la tarea:");
		
		txtNombreTareaBuscar = new Text(grpModificarOEliminar_1, SWT.BORDER);
		txtNombreTareaBuscar.setText("Ingrese el nombre de la tarea a buscar");
		txtNombreTareaBuscar.setBounds(10, 156, 579, 26);
		
		combo_ModoBusquedaTarea = new Combo(grpModificarOEliminar_1, SWT.NONE);
		combo_ModoBusquedaTarea.setItems(new String[] {"Buscar tarea desde el inicio", "Buscar tarea desde la actividad actual", "Buscar tarea por el nombre de la actividad"});
		combo_ModoBusquedaTarea.setBounds(10, 84, 579, 28);
		combo_ModoBusquedaTarea.setText("Seleccione la forma de buscar");
		m_bindingContext = initDataBindings();

	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Proceso.class, new String[]{"id", "nombre"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaProcesosTablaControladorObserveList = PojoProperties.list("listaProcesosTabla").observe(controlador);
		tableViewer.setInput(listaProcesosTablaControladorObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_1 = PojoObservables.observeMaps(listContentProvider_1.getKnownElements(), Proceso.class, new String[]{"id", "nombre"});
		tableViewer_3.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		tableViewer_3.setContentProvider(listContentProvider_1);
		//
		IObservableList procesoEncontradoTablaControladorObserveList = PojoProperties.list("procesoEncontradoTabla").observe(controlador);
		tableViewer_3.setInput(procesoEncontradoTablaControladorObserveList);
		//
		ObservableListContentProvider listContentProvider_2 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_2 = PojoObservables.observeMaps(listContentProvider_2.getKnownElements(), Actividad.class, new String[]{"nombre", "descripcion", "esObligatorio2"});
		tableViewer_1.setLabelProvider(new ObservableMapLabelProvider(observeMaps_2));
		tableViewer_1.setContentProvider(listContentProvider_2);
		//
		IObservableList listaActividadesTablaControladorObserveList = PojoProperties.list("listaActividadesTabla").observe(controlador);
		tableViewer_1.setInput(listaActividadesTablaControladorObserveList);
		//
		ObservableListContentProvider listContentProvider_3 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_3 = PojoObservables.observeMaps(listContentProvider_3.getKnownElements(), Actividad.class, new String[]{"nombre", "tiempoMinimo", "tiempoMaximo"});
		tableViewer_4.setLabelProvider(new ObservableMapLabelProvider(observeMaps_3));
		tableViewer_4.setContentProvider(listContentProvider_3);
		//
		IObservableList actividadEncontradaTablaControladorObserveList = PojoProperties.list("actividadEncontradaTabla").observe(controlador);
		tableViewer_4.setInput(actividadEncontradaTablaControladorObserveList);
		//
		ObservableListContentProvider listContentProvider_5 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_5 = PojoObservables.observeMaps(listContentProvider_5.getKnownElements(), Tarea.class, new String[]{"nombre", "descripcion", "duracion"});
		tableViewer_5.setLabelProvider(new ObservableMapLabelProvider(observeMaps_5));
		tableViewer_5.setContentProvider(listContentProvider_5);
		//
		IObservableList tareaEncontradaTablaControladorObserveList = PojoProperties.list("tareaEncontradaTabla").observe(controlador);
		tableViewer_5.setInput(tareaEncontradaTablaControladorObserveList);
		//
		ObservableListContentProvider listContentProvider_4 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_4 = PojoObservables.observeMaps(listContentProvider_4.getKnownElements(), Tarea.class, new String[]{"nombre", "descripcion", "esObligatoria2", "duracion"});
		tableViewer_2.setLabelProvider(new ObservableMapLabelProvider(observeMaps_4));
		tableViewer_2.setContentProvider(listContentProvider_4);
		//
		IObservableList listaTareasTablaControladorObserveList = PojoProperties.list("listaTareasTabla").observe(controlador);
		tableViewer_2.setInput(listaTareasTablaControladorObserveList);
		//
		return bindingContext;
	}
}
