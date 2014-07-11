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

public class Ver_Perdido_Activity extends Activity {
	private Pedido service = new Pedido();
	private List<Producto> array_producto = new ArrayList<Producto>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver__perdido_);
		mostrarPedido();
		calcularSubTotal();
	}
	//mostrar Lista de pedido
		public void mostrarPedido(){
			array_producto=service.getPedido();
			 final ListView listview = (ListView) findViewById(R.id.lstPedido);
			 final adaptadorLista adapter = new adaptadorLista();
			 //Al dar click en un item de la lista
			 listview.setAdapter(adapter);listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		  	      @Override
		  	      public void onItemClick(AdapterView<?> parent, final View view,
		  	          int position, long id) {
		  	    	
		  	    		Toast.makeText(getApplicationContext(),array_producto.get(position).getNombre() +" - " +
		  	    		Double.toString(array_producto.get(position).getPrecio()), Toast.LENGTH_SHORT).show();
					
		  	      }
		  	    });
			 
		}
		//Adaptador a lista Personalizada
		 private class adaptadorLista extends ArrayAdapter<Producto> {
				public adaptadorLista() {
					super(Ver_Perdido_Activity.this,R.layout.personalizar_lista_productos);
				}

				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					View itemView = convertView;
					if (itemView == null) {
						itemView = View.inflate(Ver_Perdido_Activity.this,
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
		 //calcular subTotal
		 public void calcularSubTotal(){
			 double temp=0;
			 for (int i = 0; i < array_producto.size(); i++) {
				temp=temp +array_producto.get(i).getPrecio();
			}
			TextView subTotal = (TextView) findViewById(R.id.txtSubTotal);
			subTotal.setText("SubTotal: S/."+Double.toString(temp));
		 }
		//Crear Menu
			@Override
		    public boolean onCreateOptionsMenu(Menu menu) {
				getMenuInflater().inflate(R.menu.ver__perdido_, menu);
			    return true;
		    }
			
			//Controlar Items del Menu
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
			   switch (item.getItemId()) {
			        case R.id.btnRegresar://Al Presionar Boton regresar
			        	onBackPressed();
			        	return true;
			        default://Por defecto
			           return super.onOptionsItemSelected(item);
			    }
			}
}
