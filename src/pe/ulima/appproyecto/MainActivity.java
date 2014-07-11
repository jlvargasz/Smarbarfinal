package pe.ulima.appproyecto;

import java.util.ArrayList;
import java.util.List;

import pe.ulima.appproyecto.dto.Pedido;
import pe.ulima.appproyecto.dto.Producto;
import pe.ulima.appproyecto.servicios.IFServiciosLocales;
import pe.ulima.appproyecto.servicios.ServiciosLocales;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private IFServiciosLocales service = new ServiciosLocales();
	private List<Producto> array_producto = new ArrayList<Producto>();
	static private Pedido pedido = new Pedido();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mostrarProductos();
	}

	//mostrar Lista de productos disponibles
	public void mostrarProductos(){
		array_producto=service.listaProductos();
		 final ListView listview = (ListView) findViewById(R.id.lstProductos);
		 final adaptadorLista adapter = new adaptadorLista();
		 //Al dar click en un item de la lista
		 listview.setAdapter(adapter);listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	  	      @Override
	  	      public void onItemClick(AdapterView<?> parent, final View view,
	  	          int position, long id) {
	  	    	if (pedido.setPedido(array_producto.get(position))) {
	  	    		Toast.makeText(getApplicationContext(), "Agregado a Pedido: " +
	  		  	          array_producto.get(position).getNombre(), Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "No se pudo agregar: " +
		  		  	          array_producto.get(position).getNombre(), Toast.LENGTH_SHORT).show();
				}
	  	      }
	  	    });
		 
	}
	//Adaptador a lista Personalizada
	 private class adaptadorLista extends ArrayAdapter<Producto> {
			public adaptadorLista() {
				super(MainActivity.this,R.layout.personalizar_lista_productos);
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View itemView = convertView;
				if (itemView == null) {
					itemView = View.inflate(MainActivity.this,
							R.layout.personalizar_lista_productos, null);
				}

				Producto filaActual = array_producto.get(position);

				TextView titulo = (TextView) itemView
						.findViewById(R.id.txtNombreProducto);
				titulo.setText(filaActual.getNombre());
				TextView precio = (TextView) itemView
						.findViewById(R.id.txtPrecio);
				precio.setText("S/."+Double.toString(filaActual.getPrecio()));
				return itemView;
			}

			@Override
			public int getCount() {
				return array_producto.size();
			}
		}
	//Crear Menu
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
	    return true;
    }
	
	//Controlar Items del Menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	   		
	        case R.id.btnRegistrar://Al Presionar Boton Registrar
	        	try {
	        		pedido.getPedido().get(0).getNombre();
	        		if (service.RegistrarPedido(pedido.getPedido(),this)) {
		        		pedido.limpiarPedido();
		        	  Toast.makeText(getApplicationContext(), "Pedido Registrado", Toast.LENGTH_SHORT).show();
		          	}else{
		        	  Toast.makeText(getApplicationContext(), "Pedido no se pudo registrar", Toast.LENGTH_SHORT).show();
		          	}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Pedido está vacio", Toast.LENGTH_SHORT).show();
				}
	        		
	        	
	           return true;
	        case R.id.btVer://Al Presionar Boton Ver
	        	Intent i = new Intent(this, Ver_Perdido_Activity.class);
	    		startActivity(i);
	        	return true;
	        case R.id.btLimpiar://Al Presionar Boton Limpiar
	        	if (pedido.limpiarPedido()) {
		        	  Toast.makeText(getApplicationContext(), "Pedido Vacio", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "Pedido no se pudo limpiarr", Toast.LENGTH_SHORT).show();
				}
		        return true;
	        case R.id.btnSalir://Al Presionar Boton salir
	        	finish();
	        	return true;
	        default://Por defecto
	           return super.onOptionsItemSelected(item);
	    }
	}
}
