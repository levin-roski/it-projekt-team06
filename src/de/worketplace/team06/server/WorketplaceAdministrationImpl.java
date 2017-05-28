package de.worketplace.team06.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.shared.*;
//import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.server.db.*;



public class WorketplaceAdministrationImpl extends RemoteServiceServlet implements WorketplaceAdministration{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Referenzen der Datenbank Mapper
	 * ***************************************************************************
	 */
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObjekt "Application" mit der Datenbank
	 * abgleicht.
	 */
	private ApplicationMapper appMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Call" mit der Datenbank
	 * abgleicht.
	 */
	private CallMapper callMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Enrollment" mit der Datenbank
	 * abgleicht.
	 */
	private EnrollmentMapper enrollMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Marketplace" mit der Datenbank
	 * abgleicht.
	 */
	private MarketplaceMapper marketMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Organisation" mit der Datenbank
	 * abgleicht.
	 */
	private OrganisationMapper orgaMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Partner" mit der Datenbank
	 * abgleicht.
	 */
	private PartnerProfileMapper partnerMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Person" mit der Datenbank
	 * abgleicht.
	 */
	private PersonMapper personMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Project" mit der Datenbank
	 * abgleicht.
	 */
	private ProjectMapper projectMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Property" mit der Datenbank
	 * abgleicht.
	 */
	private PropertyMapper propertyMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Rating" mit der Datenbank
	 * abgleicht.
	 */
	private RatingMapper ratingMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Team" mit der Datenbank
	 * abgleicht.
	 */
	private TeamMapper teamMapper = null;
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Referenzen der Datenbank Mapper
	 * ***************************************************************************
	 */

	

	public void init() throws IllegalArgumentException{

		//DB Mapper initialisieren
		this.appMapper = ApplicationMapper.applicationMapper();
		this.callMapper = CallMapper.callMapper();
		this.enrollMapper = EnrollmentMapper.enrollmentMapper();
		this.marketMapper = MarketplaceMapper.marketplaceMapper();
		this.orgaMapper = OrganisationMapper.organisationMapper();
		this.partnerMapper = PartnerProfileMapper.partnerProfileMapper();
		this.personMapper = PersonMapper.personMapper();
		this.projectMapper = ProjectMapper.projecteMapper();
		this.propertyMapper = PropertyMapper.propertyMapper();
		this.ratingMapper = RatingMapper.ratingMapper();
		this.teamMapper = TeamMapper.teamMapper();
		
		
	}
	
	public Person getTestUnit() throws IllegalArgumentException {
		Person test = new Person();
		test.setFirstName("Hans");
		return test;
	}

	/**
	 *  
	 */
	@Override
	public boolean checkExistence(int userID) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für BusinessObjekte
	 * ***************************************************************************
	 */

	
	/*
	 * ------------------------------
	 * -- METHODEN für APPLICATION --
	 * ------------------------------
	 */
	
	/**
	 *  Erstellen einer Bewerbung für eie Ausschreibung.
	 */
	@Override
	public Application applyFor(Call call, OrgaUnit applicantOrgaUnit, Date createDate, String applicationText)
			throws IllegalArgumentException {
		Application a = new Application();
		a.setCreated(created);
		a.setApplicationText(applicationText);
		
		//***WICHTIG*** Hier muss noch das BO angepasst werden.
		//a.setCall(call.getID());
		//a.setOrgaUnit(applicantOrgaUnit.getID());
		
		//Setzen einer vorlaueufigen ID
		a.setID(1);
		
		//Speichern einer ausgehenden Bewerbung in der Datenbank.
		return this.appMapper.insert(a);
	}
	
	/**
	 *  Speichern von Änderungen einer Bewerbung.
	 */
	@Override
	public void saveApplication(Application application) throws IllegalArgumentException {
		this.appMapper.update(application);
		
	}

	/**
	 *  Löschen einer Bewerbung.
	 */
	@Override
	public void deleteApplication(Application application) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 *  Auslesen aller Bewerbungen
	 */
	@Override
	//Die Methode ist ggf. sinnfrei. Bewerbungen sollten entweder für ein Projekt, eine Ausschreibung oder einen USer ausgelesen werden.
	public Vector<Application> getAllApplications() throws IllegalArgumentException {
		//Auslesen aller Bewerbungen aus der DB
		return this.appMapper.findAll();
	}

	/**
	 *  Auslesen aller Bewerbungen für eine Organisationseinheit.
	 */
	@Override
	public Vector<Application> getApplicationsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals pr�fen...
		//Auslesen aller Bewerbungen f�r eine OrgaUnit aus der DB
		int findID = orgaUnit.getID();
		return this.appMapper.findByOrganisationApplicant(findID);
	}
	
	/**
	 *  Auslesen aller Bewerbungen für ein Projekt einer Organisationseinheit.
	 */
	@Override
	public Vector<Application> getApplicationsForProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/*
	 * -----------------------
	 * -- METHODEN für CALL --
	 * -----------------------
	 */
	
	/**
	 *  Erstellen einer Ausschreibung.
	 */
	@Override
	public Call createCall(Project project, Person projectLeaderPerson, PartnerProfile partnerProfile, String title,
			String description, Date deadline) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  Speichern von Änderungen einer Ausschreibung.
	 */
	@Override
	public void saveCall(Call call) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  Löschen einer Ausschreibung.
	 */
	@Override
	public void deleteCall(Call call) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  Auslesen aller Ausschreibungen
	 */
	@Override
	public Vector<Call> getAllCalls() throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Calls aus der DB
		return this.callMapper.findAll();
	}
	
	
	
	/*
	 * -----------------------------
	 * -- METHODEN für ENROLLMENT --
	 * -----------------------------
	 */
	
	/**
	 *  Erstellen einer Beteiligung
	 */
	@Override
	public Enrollment createEnrollment(Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate,
			int period) throws IllegalArgumentException {
		Enrollment e = new Enrollment();
		//***WICHTIG*** Hier muss noch das BO angepasst werden.
		//e.setProject(project.getID());
		//e.setOrgaUnit(orgaUnit.getID());
		//e.setRating(rating.getID());
		e.setCreated(startDate);
		e.setEndDate(endDate);
		e.setPeriod(period);
		
		//Setzen einer vorlauefigen ID
		e.setID(1);
		
		return this.enrollMapper.insert(e);
		
	}

	/**
	 *  Speichern von Änderungen einer Beteiligung.
	 */
	@Override
	public void saveEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		this.enrollMapper.update(enrollment);
				
	}

	/**
	 *  Löschen einer Beteiligung.
	 */
	@Override
	public void deleteEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	
	
	/*
	 * ------------------------------
	 * -- METHODEN für MARKETPLACE --
	 * ------------------------------
	 */
	
	/**
	 *  Erstellen eines Marktplatzes
	 */
	@Override
	public Marketplace createMarketplace(String title) throws IllegalArgumentException {
		// Wir ben�tigen f�r den Marktplatz ein CreateDate oder nicht? Ggf. beim speichern in der Datenbank erst eintragen...
		Marketplace m = new Marketplace();
		m.setTitle(title);
		//m.setCreated();
		
		//Setzen einer vorlaueufigen ID
		m.setID(1);
		
		//Objekt in der DB speichern
		return this.marketMapper.insert(m);
	}

	/**
	 *  Speichern von Änderungen eines Marktplatzes
	 */
	@Override
	public void saveMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		this.marketMapper.update(marketplace);
	}

	/**
	 *  Löschen eines Marktplatzes
	 */
	@Override
	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 *  Auslesen aller Marktplätze
	 */
	@Override
	public Vector<Marketplace> getAllMarketplaces() throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Marktpl�tze aus der DB
		return this.marketMapper.findAll();
	}

	/**
	 *  Auslesen aller Marktplätze für eine Organisations-Einheit
	 */
	@Override
	public Vector<Marketplace> getMarketplacesFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		return this.marketMapper.findById(orgaUnit.getID());
	}

	
	
	/*
	 * -------------------------------
	 * -- METHODEN für ORGANISATION --
	 * -------------------------------
	 */
	
	/**
	 *  Erstellen einer Organisation
	 */
	public Organisation createOrganisation(String description, String googleID, String name, String street, int zipcode, String city) throws IllegalArgumentException {
		
		Organisation o = new Organisation();
		Date created = new Date();
		
		o.setCreated(created);
		o.setDescription(description);
		o.setGoogleID(googleID);
		
		o.setName(name);
		o.setStreet(street);
		o.setZipcode(zipcode);
		o.setCity(city);
		
		/**
		 *  Siehe createPerson
		 */
		o.setID(1);
		
		return this.orgaMapper.insert(o);
	}
	
	/**
	 *  Speichern von Änderungen einer Organisation
	 */
	@Override
	public void saveOrganisation(Organisation organisation) throws IllegalArgumentException {
		this.orgaMapper.update(organisation);
		
	}
	
	
	
	/*
	 * ---------------------------------
	 * -- METHODEN für PARTNERPROFILE --
	 * ---------------------------------
	 */
	
	/**
	 *  Erstellen eines PartnerProfils
	 */
	@Override
	public PartnerProfile createPartnerProfileFor(Call call, Vector<Property> propertyList)
			throws IllegalArgumentException {
		
		PartnerProfile profile = new PartnerProfile();
		Date created = new Date();
		
		profile.setCreated(created);
		profile.setLastedit(created);
	
		profile.setPropertyList(propertyList);
		profile.setID(1);
		
		profile = this.partnerMapper.insert(profile);
		call.setPartnerProfileID(profile.getID());
		this.callMapper.update(call);
		
		return profile;
	}

	/**
	 *  Erstellen eines PartnerProfils für eine Organisation
	 */
	@Override
	public PartnerProfile createPartnerProfileFor(OrgaUnit orgaunit, Vector<Property> propertyList)
			throws IllegalArgumentException {
		
		PartnerProfile profile = new PartnerProfile();
		Date created = new Date();
		
		profile.setCreated(created);
		profile.setLastedit(created);
	
		profile.setPropertyList(propertyList);
		profile.setID(1);
		
		profile = this.partnerMapper.insert(profile);
		orgaunit.setPartnerProfileID(profile.getID()); 
		//OrgaUnitMapper
		//this..update(orgaunit);
		
		return profile;
	}

	/**
	 *  Auslesen eines PartnerProfils für eine Ausschreibung
	 */
	@Override
	public PartnerProfile getPartnerProfileFor(Call call) throws IllegalArgumentException {
		return this.partnerMapper.findPartnerProfileByID(call.getPartnerProfileID());
	}

	/**
	 *  Auslesen eines PartnerProfils für eine Organisations-Einheit.
	 */
	@Override
	public PartnerProfile getPartnerProfileFor(OrgaUnit orgaunit) throws IllegalArgumentException {
		return this.partnerMapper.findPartnerProfileByID(orgaunit.getPartnerProfileID());
	}

	/**
	 *  Speichern von Änderungen für ein PartnerProfil 
	 */
	@Override
	public void savePartnerProfileFor(PartnerProfile partnerProfile) throws IllegalArgumentException {
		this.partnerMapper.update(partnerProfile);
		
	}
	
	
	
	/*
	 * -------------------------
	 * -- METHODEN für PERSON --
	 * -------------------------
	 */
	
	/**
	 *  Methode zum erstellen einer Person. Es werden alle Attribute bis auf die partnerprofileID 
	 *  gesetzt. Die Partnerprofile id kann zu einem spätzeren Zeitpunkt über die savePerson Methode
	 *  gespeichert werden. 
	 *  
	 */
	public Person createPerson(String firstName, String lastName, String street, int zipcode, String city, String description, String googleID) throws IllegalArgumentException {
		Person p = new Person();
		Date created = new Date();
		
		p.setCreated(created);
		p.setDescription(description);
		p.setGoogleID(googleID);
		
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setStreet(street);
		p.setZipcode(zipcode);
		p.setCity(city);
		
		
		/**
		 *  Setzen einer vorläufigen ID. 
		 *  Der korrekte bzw vortlaufende Primärschlüssel (=id) wird über eine Datenbankabfrage in der
		 *  personMapper.insert Methode generiert. (Die Id muss mit den Datensätzen in der Datenbank
		 *  konsistent sein) 
		 */
		p.setID(1);
		
		return this.personMapper.insert(p);
	}
	
	/**
	 *  Speichern von Änderungen einer Person
	 */
	@Override
	public void savePerson(Person person) throws IllegalArgumentException {
		this.personMapper.update(person);
	}

	
	
	/*
	 * --------------------------
	 * -- METHODEN für PROJECT --
	 * --------------------------
	 */
	
	/**
	 *  Erstellen eines Projekts auf einem Marktplatz
	 */
	@Override
	public Project createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson,
			OrgaUnit projectOwnerOrgaUnit, Date startDate, Date endDate) throws IllegalArgumentException {
		
		Project p = new Project();
		p.setTitle(title);
		p.setDescription(description);
		p.setProjectLeaderID(projectLeaderPerson.getID());
		p.setProjectOwnerID(projectOwnerOrgaUnit.getID());
		p.setStartDate(startDate);
		p.setEndDate(endDate);
		
		//Setzen einer vorlaueufigen ID
		p.setID(1);
		
		//Objekt in der DB speichern
		return this.projectMapper.insert(p);
	}

	/**
	 *  Speichern von Änderungen eines Projekts.
	 */
	@Override
	public void saveProject(Project project) throws IllegalArgumentException {
		this.projectMapper.update(project);
		
	}

	/**
	 *  Löschen eines Projekts
	 */
	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  Auslesen aller Projekte.
	 */
	@Override
	public Vector<Project> getAllProjects() throws IllegalArgumentException {
		//Auslesen aller Projekte aus der DB
		return this.projectMapper.findAll();
	}

	/**
	 *  Auslesen aller Projekte einer Organisations-Einheit
	 */
	@Override
	public Vector<Project> getProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals pr�fen...
		//Auslesen aller Projekte f�r eine OrgaUnit aus der DB
		int findID = orgaUnit.getID();
		return this.orgaMapper.findById(findID);
	}
	
	/**
	 *  Auslesen aller Projekte für einen Marktplatz
	 */
	@Override
	public Vector<Project> getProjectsFor(Marketplace marketplace) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals prüfen! Methode für das Suchen nach Projekten für einen Marktplatz im Mapper anlegen
		int findID = marketplace.getID();
		return this.orgaMapper.findByMarketplace(marketplace);
	}
	

	
	/*
	 * ---------------------------
	 * -- METHODEN für PROPERTY --
	 * ---------------------------
	 */
	
	/**
	 *  Erstellen von Eigenschaften für ein PartnerProfil
	 */
	@Override
	public Property createProperty(PartnerProfile partnerProfile, String name, String value)
			throws IllegalArgumentException {
		Property p = new Property();
		p.setName(name);
		p.setValue(value);
		
		//Setzen einer vorläufigen ID
		p.setID(1);
		
		//Objekt in der Datenbank speichern
		return propertyMapper.insert(p);
	}

	/**
	 *  Speichern von Änderungen einer Eigenschaft
	 */
	@Override
	public void saveProperty(Property property) throws IllegalArgumentException {
		this.propertyMapper.update(property);
	}

	/**
	 *  
	 */
	@Override
	public Vector<Property> getAllPropertiesFor(PartnerProfile partnerprofile) throws IllegalArgumentException {
		//***WICHTIG*** Methode muss im Mapper angelegt werden!
		return this.propertyMapper.findByPartnerProfile(partnerprofile);
	}

	
	
	/*
	 * -------------------------
	 * -- METHODEN für RATING --
	 * -------------------------
	 */
	
	/**
	 *  Erstellen einer Bewertung für eine Bewerbung
	 */
	@Override
	public Rating rateApplication(Application application, Double rating, String ratingStatemant)
			throws IllegalArgumentException {
		Rating r = new Rating();
		r.setRating(rating);
		r.setRatingStatement(ratingStatement);
		
		//***WICHTIG*** Hier fehlt noch die Zuweisung zur Application. Wie realisieren wir das genau?
		
		//Setzen einer vorläufigen ID
		r.setID(1);
		
		//Speichern des Objekts in der Datenbank
		return this.ratingMapper.insert(rating);
	}

	/**
	 *  Speichern von Änderungen einer Bewertung
	 */
	@Override
	public void saveRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  Löschen einer Bewertung
	 */
	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*
	 * -----------------------
	 * -- METHODEN für TEAM --
	 * -----------------------
	 */
	
	/**
	 *  Erstellen eines Teams
	 */
	public Team createTeam(String description, String googleID, String name, int membercount) throws IllegalArgumentException {
		
		Team t = new Team();
		Date created = new Date();
		
		t.setCreated(created);
		t.setDescription(description);
		t.setGoogleID(googleID);
		
		t.setName(name);
		t.setMembercount(membercount);
		
		/**
		 *  Siehe createPerson
		 */
		t.setID(1);
		
		return this.teamMapper.insert(t);
	}
		
	/**
	 *  Speichern von Änderungen an einem Team.
	 */
	@Override
	public void saveTeam(Team team) throws IllegalArgumentException {
		this.teamMapper.update(team);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Business Objekte
	 * ***************************************************************************
	 */

}
