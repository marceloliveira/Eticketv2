package br.jus.tjse.eticket.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	private static final Conexao INSTANCE = new Conexao();
	
	public static synchronized Conexao getInstance() {
		return INSTANCE;
	}
	
	private Conexao(){
		
	}
	
	private Connection con;
	
	public Connection getConexao() {
		
		if (con == null) {
		
			try {
				
				Class.forName("org.postgresql.Driver");
				
				String url = "jdbc:postgresql://localhost:5432/eticket";
				
				con = DriverManager.getConnection(url, "postgres", "postgres");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return con;
		
	}

}
