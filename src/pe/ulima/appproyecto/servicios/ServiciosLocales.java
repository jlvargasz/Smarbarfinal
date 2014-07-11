package pe.ulima.appproyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import pe.ulima.appproyecto.basedatos.DataBaseHandler;
import pe.ulima.appproyecto.dto.Producto;

public class ServiciosLocales implements IFServiciosLocales{
	@Override
	public boolean RegistrarPedido(List<Producto> prod,Context context) {
		DataBaseHandler db = new DataBaseHandler(context);
		if (db.registrarPedido(prod)) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Producto> listaProductos() {
		List<Producto> lista = new ArrayList<Producto>();
		Producto prodA = new Producto("Pollo a la Brasa", 17.00);
		Producto prodB = new Producto("Lomo Saltado", 35.00);
		Producto prodC = new Producto("Arroz con pollo", 20.00);
		Producto prodD = new Producto("Aji de Gallina", 20.00);
		Producto prodE = new Producto("Pollo Saltado", 25.00);
		Producto prodF = new Producto("Seco a la Norteña", 25.00);
		Producto prodG = new Producto("Ceviche", 30.00);
		Producto prodH = new Producto("Ceviche Mixto", 33.00);
		lista.add(prodA);
		lista.add(prodB);
		lista.add(prodC);
		lista.add(prodD);
		lista.add(prodE);
		lista.add(prodF);
		lista.add(prodG);
		lista.add(prodH);
		return lista;
	}
	
	
}
