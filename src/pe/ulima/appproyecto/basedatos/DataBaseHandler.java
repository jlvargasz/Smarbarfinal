package pe.ulima.appproyecto.basedatos;

import java.util.List;

import pe.ulima.appproyecto.dto.Producto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper{
	private SQLiteDatabase db;
	private static final String TABLA_PEDIDOS="pedidos";
	private static final String ID="id";
	private static final String NOMBRE="nombre";
	private static final String PRECIO="precio"; 
	public DataBaseHandler(Context context) {
		super(context, "appproyecto", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public boolean registrarPedido(List<Producto> pedido){
		db=this.getWritableDatabase();
		try {
			try {
				String CREAR_TABLA_PEDIDOS = "CREATE TABLE " + TABLA_PEDIDOS+ " ("
						+ ID + " INTEGER PRIMARY KEY,"
						+ NOMBRE + " TEXT,"
						+ PRECIO+ " TEXT)";
				db.execSQL(CREAR_TABLA_PEDIDOS);
			} catch (Exception e) {
				// TODO: handle exception
			}
			for (int i = 0; i < pedido.size(); i++) {
				ContentValues values = new ContentValues();
				values.put("TITULO", pedido.get(i).getNombre());
				values.put("FECHA", Double.toString(pedido.get(i).getPrecio()));
				//inserta
				db.insert(TABLA_PEDIDOS, null, values);
			}
			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
}
