package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * The class use this libraries: org.apache.commons and commons-dbutils
 * static que crea el objeto y usa ese
 * @author Andrei Nicolae Depner
 */
public class JdbcUtils {
	private static final String URL = "jdbc:postgresql://localhost:5432/swapi";
	private static final String USER = "postgres";
	private static final String PASS = "1234";
	//private static final Scanner sc = new Scanner(System.in);

	private static Connection con = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static CallableStatement cs = null;
	private static ResultSet res = null;
	
	/**
	 * Abrimos la conexion de la base de datos.
	 */
	public static void conection() {
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
			e.printStackTrace();
		}
	}
	
	/**
	 * Conexion a la base de datos que guarda los nombres de todas las base de 
	 * datos existentes
	 */
	public static void conectionCheckDataBase() {
		try {
			String url = "jdbc:postgresql://localhost:5432/";
			con = DriverManager.getConnection(url, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
			e.printStackTrace();
		}
	}
	
	/**
	 * Cierra la conexion de la base de datos.
	 */
	public static void disconnect() {
		try {
			con.close();
			st.close();
			ps.close();
			cs.close();
			res.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		} catch(NullPointerException e) {
			//Quiet
		}
	}
	
	/**
	 * Metodo que devuelve el numero de registros modificados en una sentencia de 
	 * tipo insert, update, delete, DCL(create table....)
	 * @param sql
	 * @param param Lista de parametros asignados a cada interogacion
	 * @return Devuelve el numero de registros afectados o -1 en caso de que falle;
	 */
	public static int executePreparedStatementDML(String sql, List<Object> param) {
		try {
			ps = con.prepareStatement(sql);
						
			for (int i = 1; i<= StringUtils.countMatches(sql, "?"); i++) 
				ps.setObject(i, param.get(i-1));
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ps.toString());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que devuelve el numero de registros modificados en una sentencia de 
	 * tipo insert, update, delete, DCL(create table....)
	 * @param sql
	 * @param param Lista de parametros asignados a cada interogacion
	 * @return Devuelve el numero de registros afectados o -1 en caso de que falle;
	 */
	public static int executePreparedStatementDML(String sql, Object... param) {
		return executePreparedStatementDML(sql, Arrays.asList(param));
	}
	
	/**
	 * Metodo generico que llama a un CallableStatement y devuelve el resultado 
	 * que nos devuelve el metodo
	 * @param metodo Metodo del procedimiento almacenda en la base de datos 
	 * junto con sus interogantes
	 * @param tipo Un tipo del del listado de Types, por ejemplo Types.INTEGER
	 * @param param Lista con los parametros a cambiar por las interogaciones
	 * @return Develve el objeto que saca del procedimiento de la base de datos
	 */
	public static Object executeCallableStatement(String metodo, int tipo, List<Object> param) {
		try {
			cs = con.prepareCall("{call " + metodo + "}");
			cs.registerOutParameter(1, tipo); // lo que devuelve el proc almacenado
			for (int i = 1; i<= StringUtils.countMatches(metodo, "?"); i++) 
				cs.setObject(i, param.get(i-1));
			cs.execute();
		
			return cs.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo generico que llama a un CallableStatement y devuelve el resultado 
	 * que nos devuelve el metodo
	 * @param metodo Metodo del procedimiento almacenda en la base de datos 
	 * junto con sus interogantes
	 * @param tipo Un tipo del del listado de Types, por ejemplo Types.INTEGER
	 * @param param Lista con los parametros a cambiar por las interogaciones
	 * @return Develve el objeto que saca del procedimiento de la base de datos
	 */
	public static Object executeCallableStatement(String metodo, int tipo, Object... param) {
		return executeCallableStatement(metodo, tipo, Arrays.asList(param));
	}
	
	/**
	 * Metodo generico que llama a un CallableStatement y devuelve el resultado 
	 * que nos devuelve el metodo
	 * @param metodo Metodo del procedimiento almacenda en la base de datos 
	 * junto con sus interogantes
	 * @param param Lista con los parametros a cambiar por las interogaciones
	 * @return ResultSet Devuleve un ResultSet que saca del procedimiento de la base de datos
	 */
	public static ResultSet executeCallableStatement(String metodo, List<Object> param) {
		try {
			cs = con.prepareCall("{call " + metodo + "}");
			for (int i = 1; i<= StringUtils.countMatches(metodo, "?"); i++) 
				cs.setObject(i, param.get(i-1));
			
			return cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet executeCallableStatement(String metodo, Object... param) {
		return executeCallableStatement(metodo, Arrays.asList(param));
	}
	
	/**
	 * Metodo que devuelve el ResultSet de una sentencia de tipo select ejecutado
	 * PreparedStatement (Con seguridad)
	 * @param sql
	 * @param param Lista de parametros asignados a cada interogacion
	 * @return Devuelve el ResultSet
	 */
	public static ResultSet executeSentenciaSelect(String sql, List<Object> param) {
		try {
			ps = con.prepareStatement(sql);
						
			for (int i = 1; i<= StringUtils.countMatches(sql, "?"); i++) 
				ps.setObject(i, param.get(i-1));
			
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que devuelve el ResultSet de una sentencia de tipo select ejecutado
	 * PreparedStatement (Con seguridad)
	 * @param sql
	 * @param param Lista de parametros asignados a cada interogacion
	 * @return Devuelve el ResultSet
	 */
	public static ResultSet executeSentenciaSelect(String sql, Object... param) {
		return executeSentenciaSelect(sql, Arrays.asList(param));
	}
	
	/**
	 * Metodo que devuelve el ResultSet de una sentencia de tipo select ejecutado
	 * con Statement (Sin seguridad)
	 * @param sql
	 * @param param Lista de parametros asignados a cada interogacion
	 * @return Devuelve el ResultSet
	 */
	public static ResultSet executeStatementSelect(String sql) {
		try {
			st = con.createStatement();
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo que devuelve el numero de registros modificados en una sentencia de 
	 * tipo insert, update, delete, DCL(create table....) ejecutado con 
	 * Statement (Sin seguridad)
	 * @param sql
	 * @return Devuelve el numero de registros afectados o -1 en caso de que falle;
	 */
	public static int executeSentenciaDml(String sql) {
		try {
			st = con.createStatement();
			return st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
