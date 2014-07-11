package pe.ulima.appproyecto.dto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	static private List<Producto> pedido=null ;
	public Pedido() {
		if(pedido==null){
			pedido = new ArrayList<Producto>();
		}
	}
	public boolean setPedido(Producto prod) {
		try {
			pedido.add(prod);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public List<Producto> getPedido() {
		// TODO Auto-generated method stub
		return pedido;
	}
	public boolean limpiarPedido(){
		pedido = null;
		if (getPedido()==null) {
			return true;
		}else{
			return false;
		}
		
	}
}
