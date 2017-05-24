package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Project;
/**
 * 
 */
public class PartnerProfileMapper {

	private static PartnerProfileMapper partnerProfileMapper = null;
	 /**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected PartnerProfileMapper(){
		
	}
	
	public static PartnerProfileMapper partnerProfileMapper(){
		if (partnerProfileMapper == null){
			partnerProfileMapper = new PartnerProfileMapper();
		}
		return partnerProfileMapper; 
	}
	
	public PartnerProfile findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id, created FROM Team " + "WHERE id= " + id);
    		
    		if (rs.next()) {
    			PartnerProfile part = new PartnerProfile();
    			part.setID(rs.getInt("id"));
    			part.setLastEdit(rs.getDate("lastEdit"));
    			part.setCreated(rs.getTimestamp("created"));
    		}	
    	}
    	catch (SQLExpetion e){
    		e.printStackTrace();
    	}
    	return rs;
    }
  
    public Vector<PartnerProfile> findAll() {
        Connection con = DBConnection.connection();
        Vector<PartnerProfile> result = new Vector<PartnerProfile>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, lastEdit, created "
        	+ "FROM Team ");
        	
        	while (rs.next()){
        		Project part = new Project();
        		part.setID(rs.getInt("id"));
        		part.setLastEdit(rs.getDate("lastEdit"))
        		part.setCreated(rs.getTimestamp("created"));
        		
        		result.addElement(part);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    
    
    public PartnerProfile insert (PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM partnerProfile ");
        	
        	if (rs.next()){
        	part.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO partnerProfile (id, lastEdit, created) " 
        	+ "VALUES (" 
        	+ part.getID() + ", " 
        	+ part.getLastEdit() + "','"
        	+ "')");
        	}
        }
       catch (SQLExpetion e){
    	e.printStackTrace();
    }
    
    return part;
    } 
    
    public PartnerProfile update(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE project " 
        	+ "SET lastEdit=\"" + part.getlastEdit() + "\", "
        	+ "WHERE id=" + part.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return part;
    }

    public void delete(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM partnerProfile " + "WHERE id=" + part.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
	
}
