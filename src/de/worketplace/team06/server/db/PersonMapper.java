package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class PersonMapper {
	
	private static PersonMapper personMapper = null;
	
	protected PersonMapper() {
		
	}

	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}
		
		return personMapper;
	}
	
	public Person findByKey(int id)
	
	public Person findBy schauen 
	
	public Person insert(Person a)
	
	public Person update(Person a)
	
	public void delete(Person a)
}
