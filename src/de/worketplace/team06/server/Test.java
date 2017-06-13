package de.worketplace.team06.server;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Team;

public class Test {

	public static void main(String[] args) {
		

		
		WorketplaceAdministrationImpl admin = new WorketplaceAdministrationImpl();
		admin.init();
	

//		testOfCreateOrganisation(admin);
      testOfSaveOrganisation(admin);		
		
//		testOfCreateTeam(admin);
//		testOfSaveTeam(admin);
		
//		testOfCreatePerson(admin);
//		testOfSavePerson(admin);
//		testOfDeletePerson(admin);
//		testOfGetPerson(admin);
		
//		testOfCreateMarketplace(admin);
//		testOfGetAllMarketplaces(admin);
//		testOfGetMarketplaceFor(admin);
//		testOfSaveMarketplace(admin);
		
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		
//		java.util.Date date = getItSomehow();
//		Timestamp timestamp = new Timestamp(date.getTime());
//		preparedStatement = connection.prepareStatement("SELECT * FROM tbl WHERE ts > ?");
//		preparedStatement.setTimestamp(1, timestamp);
//		
//		
//		
//		Timestamp timestamp = resultSet.getTimestamp("ts");
//		java.util.Date date = timestamp; // You can just upcast.
//		
//		System.out.println(timestamp);
//      private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currentTime = sdf.format(m.getCreated());
		
		
		
	}
	
	

	/*
	 * Organisation
	 *
	 */
	
	public static void testOfCreateOrganisation(WorketplaceAdministrationImpl admin) {
		admin.createOrganisation("1 Nice Unternehmen vong Niceigkeit her", "rofliksdeh1337lol", "1Unternehmen", "Stadtstrand 1", 13337, "Stutututtgart");
		admin.createOrganisation("Malerei", "Gmal234", "CoolStrich", "Hansestraße 4", 87765, "Hamburg");
	}
	
	private static void testOfSaveOrganisation(WorketplaceAdministrationImpl admin) {
		Organisation o = admin.getOrganisationByGoogleID("Gmal234");
		System.out.println("Hier kommt der Organisationsname: " + o.getName());
		
		o.setDescription("Blub");
		o.setPartnerProfileID(999);
		
		o.setName("Blub");
		o.setStreet("Blub");
		o.setCity("Blub");
		o.setZipcode(999);
		
		admin.saveOrganisation(o);
		
	}
	
	
	/*
	 * TEAM
	 * 
	 */
	
	public static void testOfCreateTeam(WorketplaceAdministrationImpl admin) {
		admin.createTeam("Ist wohl ein Team", "G2349jf", "Superknechte", 1000);
		admin.createTeam("ahjooo", "Gagasdg234", "Glücksspechte", 500);
		
	}

	private static void testOfSaveTeam(WorketplaceAdministrationImpl admin) {
		Team t = admin.getTeamByGoogleID("G2349jf");
		System.out.println("Hier kommt der Teamname: " + t.getName());
		
		t.setDescription("Blub");
		t.setPartnerProfileID(999);
		t.setMembercount(999);
		t.setName("Blub");
		
		admin.saveTeam(t);
		
	}

	
	/*
	 * 
	 * Person
	 * 
	 */

	private static void testOfSavePerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		p.setCity("Blub");
		p.setDescription("Blub");
		p.setFirstName("Blub");
		p.setLastName("Blub");
		p.setStreet("Blub");
		p.setPartnerProfileID(999);
		p.setZipcode(97898);
		
		admin.savePerson(p);
		
	}


	public static void testOfDeletePerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G256061");
		admin.deletePerson(p);
	}
	
	public static void testOfGetPerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("ID: " + p.getID());
		System.out.println("Created: " + p.getCreated());
		System.out.println("GoogleID: " + p.getGoogleID());
		System.out.println("Description: " + p.getDescription());
		System.out.println("PartnerProfileID: " + p.getPartnerProfileID());
		System.out.println("Type: " + p.getType());
		
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
	}
	
	public static void testOfCreatePerson(WorketplaceAdministrationImpl admin) {
	
		admin.createPerson("Hans", "Mayer", "lagistraße 6", 86637, "Augsburg", "Ein Mensch", "G1337");
		admin.createPerson("Thomas", "Mueller", "schuttstr 6", 86637, "Langweid", "Auch Ein Mensch", "G256060");
		
	}
	
	
	
	

	
	/*
	 * Marketplacemethoden
	 */
	
	
	public static void testOfGetAllMarketplaces(WorketplaceAdministrationImpl admin) {
		
		Vector<Marketplace> myVector = admin.getAllMarketplaces();
		
		for (Marketplace laufvariable : myVector)
		{
		    System.out.println(laufvariable.getTitle());
		    System.out.println(laufvariable.getDescription());
		    System.out.println(laufvariable.getID());
		    System.out.println(laufvariable.getCreated());
		    System.out.println();
		}
		
	}
	
	
	public static void testOfCreateMarketplace(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		
		
		
		
		Marketplace m = null;
		m = admin.createMarketplace("Metallmarkt", "Alles mit Metall", p);
		
		Marketplace n = null;
		n = admin.createMarketplace("Gartenmarkt", "Alles im Garten", p);
		
		Marketplace t = null;
		
		t = admin.createMarketplace("Baumarkt", "Alles mit Bauen", p);
		}
	
	private static void testOfGetMarketplaceFor(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("Hier kommt die Person für welche die Marktplätze ermittelt werden sollen ! ");
		System.out.println("ID: " + p.getID());
		System.out.println("Created: " + p.getCreated());
		System.out.println("GoogleID: " + p.getGoogleID());
		System.out.println("Description: " + p.getDescription());
		System.out.println("PartnerProfileID: " + p.getPartnerProfileID());
		System.out.println("Type: " + p.getType());
		
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
		
		Vector<Marketplace> myVector = admin.getMarketplacesFor(p);
		
		for (Marketplace laufvariable : myVector)
		{
		    System.out.println("Titel: " + laufvariable.getTitle());
		    System.out.println("Beschreibung : " + laufvariable.getDescription());
		    System.out.println("ID : " + laufvariable.getID());
		    System.out.println("Erstellungszeitstempel: " + laufvariable.getCreated());
		    System.out.println("OrgaUnitID: " + laufvariable.getOrgaUnitID());
		    System.out.println();
		}
		
	}
	
	private static void testOfSaveMarketplace(WorketplaceAdministrationImpl admin) {
Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("Hier kommt die Person für welche die Marktplätze ermittelt werden sollen ! ");
		System.out.println("ID: " + p.getID());
		System.out.println("Created: " + p.getCreated());
		System.out.println("GoogleID: " + p.getGoogleID());
		System.out.println("Description: " + p.getDescription());
		System.out.println("PartnerProfileID: " + p.getPartnerProfileID());
		System.out.println("Type: " + p.getType());
		
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
		
		Vector<Marketplace> myVector = admin.getMarketplacesFor(p);
		Marketplace m = myVector.elementAt(0);
		
		m.setDescription("Ich habe ein Update verführt.");
		m.setTitle("Ich auch ändern Titel");
		admin.saveMarketplace(m);
		
	}
	
	

}