package de.worketplace.team06.server.report;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.shared.*;
import de.worketplace.team06.server.WorketplaceAdministrationImpl;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.client.NotLoggedInException;
import de.worketplace.team06.client.UserChangedException;
import de.worketplace.team06.client.WindowAlertException;
import de.worketplace.team06.server.db.*;

/**
 * @author Toby
 *
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WorketplaceAdministration wpadmin = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
	
	}
	
	/**
	 * Methode zur Initalisierung der WorketplaceAdministration
	 */
	@Override
	public void init() throws IllegalArgumentException {
	    /*
	     * Intantiierung von einer WorketplaceAdministrationImpl zum internen Gebrauch
	     */
	    WorketplaceAdministrationImpl impl = new WorketplaceAdministrationImpl();
	    impl.init();
	    this.wpadmin = impl;
	}
	
	protected WorketplaceAdministration getWorketplaceAdministration() {
		return this.wpadmin;
	}
	  
	

}
