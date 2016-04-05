package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {
	
	public void create(Studente s) {
		
		Connection conn = DBConnect.getConnection() ;
		
		// String sql = "INSERT INTO `iscritticorsi`.`studente` (`matricola`, `cognome`, `nome`, `CDS`) VALUES (333333, 'ZZZ', 'ZZZ', 'ZZZ99');" ;

		//String sql = "INSERT INTO `iscritticorsi`.`studente` (`matricola`, `cognome`, `nome`, `CDS`) "+
		//"VALUES ("+s.getMatricola()+", '"+s.getCognome()+"', '"+s.getNome()+"', '"+s.getCds()+"');" ;
		
		String sql = "INSERT INTO `iscritticorsi`.`studente` (`matricola`, `cognome`, `nome`, `CDS`) VALUES (?, ?, ?, ?);" ;
		
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			
			st.setInt(1, s.getMatricola());
			st.setString(2, s.getCognome());
			st.setString(3, s.getNome());
			st.setString(4, s.getCds());
			
			st.executeUpdate() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Studente read(int matricola) {
		return null ;
	}
	
	/**
	 * Legge da database lo {@link Studente} che possiede
	 * la matricola data da {@code s.matricola}, e riempie
	 * di conseguenza tutti i rimanenti campi di {@code s}.
	 * 
	 * Se la matricola viene trovata, ritorna {@code true}, altrimenti
	 * ritorna {@code false} e non modifica nessun campo di {@code s}.
	 * 
	 * @param s Lo studente la cui matricola è da ricercare ed cui campi saranno riempiti, se la matricola esiste.
	 * @return true se esiste, false se non è stato trovato
	 */
	public boolean read(Studente s) {
		return true ;
	}
	
	public void updateCds(Studente s) {}
	public void updateCds(int matricola, String cds) {}
	
	public List<Studente> searchByCognome(String cognome) {}
	//public List<Studente> searchByCognome(Studente s) {}
}
