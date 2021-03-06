package de.worketplace.team06.client;

import java.util.Vector;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Team;
import de.worketplace.team06.shared.report.HTMLReportWriter;

/**
 * Superklasse für alle Views des Report Generators.
 * 
 * @author Roski
 */
public abstract class ReportView extends View {
	protected ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	protected HTMLReportWriter writer = new HTMLReportWriter();

	public ReportView() {
		setBreadcrumb();
	}

	/**
	 * Hängt dem aktuellen View-Widget einen HTML-String als HTML-Widget hinzu.
	 * 
	 * @param text
	 *            HTML-String um es diesem Widget anzuhängen
	 */
	protected void append(String text) {
		HTML html = new HTML(text);
		this.add(html);
	}

	/**
	 * Generiert eine ListBox mit allen Bewerbern des aktuellen Benutzers.
	 * 
	 * @param callback
	 *            Callback.runOnePar wird aufgerufen, sobald ein anderes Element
	 *            in der ListBox selektiert wird
	 * @return Gibt eine ListBox mit allen Bewerbern zu dem aktuellen Benutzer
	 *         zurück
	 */
	protected ListBox getAllApplicantsOfCurrentUserInput(final Callback callback) {
		final ListBox allUsers = new ListBox();
		reportGenerator.getAllApplicantsForAllCallsFrom((Person) ClientsideSettings.getCurrentUser(),
				new AsyncCallback<Vector<OrgaUnit>>() {
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Vector<OrgaUnit> result) {
						for (OrgaUnit orgaUnit : result) {
							if (orgaUnit instanceof Person) {
								allUsers.addItem(
										((Person) orgaUnit).getFirstName() + " " + ((Person) orgaUnit).getLastName(),
										String.valueOf(orgaUnit.getID()));
							} else if (orgaUnit instanceof Team) {
								allUsers.addItem("Team " + ((Team) orgaUnit).getName(),
										String.valueOf(orgaUnit.getID()));
							} else if (orgaUnit instanceof Organisation) {
								allUsers.addItem("Organisation " + ((Organisation) orgaUnit).getName(),
										String.valueOf(orgaUnit.getID()));
							}
						}
						DomEvent.fireNativeEvent(Document.get().createChangeEvent(), allUsers);
					}
				});
		allUsers.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if (callback instanceof Callback) {
					callback.runOnePar(allUsers);
				}
			}
		});
		return allUsers;
	}
}