package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class MarketplaceMapper {
	
	private static MarketplaceMapper marketplaceMapper = null;
	
	
	protected MarketplaceMapper() {
		
	}

	public static MarketplaceMapper marketplaceMapper() {
		if (marketplaceMapper == null) {
			marketplaceMapper = new MarketplaceMapper();
		}
		
		return marketplaceMapper;
	}
	
	
	/**
	 * Die Insert Methode wird nur beim Anlegen eines neuen Marktplatzes aufgerufen. Zuerst wird der
	 * nächste freie Primarykey ermittelt und dach wird ein neuer Datensatz mit der nächsten freien
	 * ID in die Datenbank gespeichert. 
	 * 
	 * @param m
	 * @return
	 */
	
	public Marketplace insert(Marketplace m) {
		
	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM marketplace ");
			
			if (rs.next()) {
				
				m.setID(rs.getInt("maxid") + 1);
		
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO marketplace (id, created, title, description, orgaUnitID) " + "VALUES (" + m.getID() + ",'" + m.getCreated() + "','" + m.getTitle() + "','" + m.getDescription() + "','" + m.getOrgaUnitID() + "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return m;
	}
	
	public Vector<Marketplace> findAll() {
		
		Connection con = DBConnection.connection();
			
			Vector<Marketplace> result = new Vector<Marketplace>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("Select id, created, title, description, orgaUnitID FROM marketplace " + "ORDER BY id");
				
				
				while (rs.next()) {
					
					Marketplace m = new Marketplace();
					m.setID(rs.getInt("id"));
					m.setCreated(rs.getTimestamp("created"));	
					m.setTitle(rs.getString("title"));
					m.setDescription(rs.getString("description"));
					m.setOrgaUnitID(rs.getInt("orgaUnitID"));
					
					result.addElement(m);
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			return result;
		}
	
		/**
		 * Die update Methode aktualisiert ein bereits in der Datenbank gespeicherten Tupel mit
		 * den aktuellen Werten eines Marketplace Objektes.  
		 * @param m
		 * @return
		 */
	
		public Marketplace update(Marketplace m) {
			 Connection con = DBConnection.connection();

			    try {
			      Statement stmt = con.createStatement();

			      stmt.executeUpdate("UPDATE marketplace " + "SET title=\""
			          + m.getTitle() + "\", " + "description=\"" + m.getDescription() + "\" "
			          + "WHERE id=" + m.getID());

			    }
			    catch (SQLException e) {
			      e.printStackTrace();
			    }

			    // Um Analogie zu insert(Marketplace m) zu wahren, geben wir m zurück
			    return m;	
		}
		
		//AutoGeneratedBy Johannes
		

		public Vector<Marketplace> findByOrgaUnitID(int ouid) {	
			Connection con = DBConnection.connection();
			Vector<Marketplace> result = new Vector<Marketplace>();
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, created, title, description, orgaUnitID FROM marketplace "
				          + "WHERE orgaUnitID=" + ouid + " ORDER BY id");

				      // Für jeden Eintrag im Suchergebnis wird nun ein Marketplace Objekt erstellt.
				      while (rs.next()) {
				        Marketplace m = new Marketplace();
				        m.setID(rs.getInt("id"));
				        m.setCreated(rs.getTimestamp("created"));
				        m.setTitle(rs.getString("title"));
				        m.setDescription(rs.getString("description"));
				        m.setOrgaUnitID(rs.getInt("orgaUnitID"));

				        // Hinzufügen des neuen Objekts zum Ergebnisvektor
				        result.addElement(m);
				      }
				    }
				    catch (SQLException e2) {
				      e2.printStackTrace();
				    }

		// Ergebnisvektor zurückgeben
		return result;
		}
		
		public void delete(Marketplace m) {
			Connection con = DBConnection.connection();

		    try {
		    	Statement stmt = con.createStatement();
		    	//Löschen der Person aus der Tabelle orgaunit und person.
		    	stmt.executeUpdate("DELETE FROM marketplace WHERE marketplace.id= " + m.getID());
		    	
		    }
		    catch (SQLException e2) {
					  e2.printStackTrace();		
		    }
			
		}

		public Marketplace findByID(int marketplaceID) {
	    	Connection con = DBConnection.connection();
			
			try {						
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM marketplace WHERE id = " + marketplaceID);		
				
				if (rs.next()) {
					Marketplace m = new Marketplace();
					m.setID(rs.getInt("id"));
					m.setCreated(rs.getTimestamp("created"));
					m.setTitle(rs.getString("title"));
					m.setDescription(rs.getString("description"));
					
					return m;
				}			
			}
			catch (SQLException e2) {
				e2.printStackTrace();
				return null;
			}
			return null;
		}	
	

	
/*	
 * 
 * 
 * PATRICK
 * 
	public Marketplace findByKey(int id)
	
	public Marketplace findBy schauen 
	
	public Marketplace insert(Marketplace a)
	
	public Marketplace update(Marketplace a)
	
	public void delete(Marketplace a)
*/
/*Unsicher ob die Methode benötigt wird
	public Vector<Marketplace> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
*/	

	
}
