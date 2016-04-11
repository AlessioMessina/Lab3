package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {

	public void create(Studente s) {

		Connection conn = DBConnect.getConnection();

		String sql = "INSERT INTO studente (matricola, cognome, nome, CDS) VALUES (?, ?, ?, ?);";

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);

			st.setInt(1, s.getMatricola());
			st.setString(2, s.getCognome());
			st.setString(3, s.getNome());
			st.setString(4, s.getCds());

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Studente read(int matricola) {
		Studente s = new Studente(matricola, null, null, null);
		if (read(s))
			return s;
		else
			return null;
	}

	/**
	 * Legge da database lo {@link Studente} che possiede la matricola data da
	 * {@code s.matricola}, e riempie di conseguenza tutti i rimanenti campi di
	 * {@code s}.
	 * 
	 * Se la matricola viene trovata, ritorna {@code true}, altrimenti ritorna
	 * {@code false} e non modifica nessun campo di {@code s}.
	 * 
	 * @param s
	 *            Lo studente la cui matricola è da ricercare ed cui campi
	 *            saranno riempiti, se la matricola esiste.
	 * @return true se esiste, false se non è stato trovato
	 */
	public boolean read(Studente s) {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT matricola, cognome, nome, CDS FROM studente WHERE matricola = ?;";

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);

			st.setInt(1, s.getMatricola());

			ResultSet res = st.executeQuery();

			if (res.next()) {

				s.setCognome(res.getString("cognome"));
				s.setNome(res.getString("nome"));
				s.setCds(res.getString("cds"));

				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public void updateCds(Studente s) {
		String sql = "UPDATE studente SET cds=? WHERE matricola=?;";

		Connection conn = DBConnect.getConnection();

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);

			st.setString(1, s.getCds());
			st.setInt(2, s.getMatricola());

			int n = st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateCds(int matricola, String cds) {
		Studente s = new Studente(matricola, null, null, cds);
		updateCds(s);
	}

	public List<Studente> searchByCognome(String cognome) {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT matricola, cognome, nome, CDS FROM studente WHERE cognome = ?;";

		List<Studente> result = new ArrayList<>() ;
		
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);

			st.setString(1, cognome);

			ResultSet res = st.executeQuery();

			while (res.next()) {

				Studente s = new Studente(
						res.getInt("matricola"),
						res.getString("cognome"),
						res.getString("nome"),
						res.getString("cds")
						) ;
				
				result.add(s) ;
			}
			
			return result ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
