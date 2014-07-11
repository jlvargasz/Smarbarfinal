package pe.ulima.appproyecto.servicios;

import java.util.List;

import android.content.Context;

import pe.ulima.appproyecto.dto.Producto;

public interface IFServiciosLocales {
	public boolean RegistrarPedido(List<Producto> prod,Context context);
	public List<Producto> listaProductos();
}
