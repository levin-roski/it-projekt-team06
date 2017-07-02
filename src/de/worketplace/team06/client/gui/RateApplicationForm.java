package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Rating;

/**
 * Formular für die Bewertung einer Bewerbung.
 * 
 * @author Roski
 */
public class RateApplicationForm extends Form {
	private Label nameLabel = new Label("Bewertung");
	private ListBox ratingInput = new ListBox();
	private Label descriptionLabel = new Label("Bewertungstext");
	private TextArea descriptionInput = new TextArea();
	private boolean shouldUpdate = false;
	private boolean rpcFinished = false;
	private Rating toChangeRating;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;
	/*
	 * Grid mit 3 Zeilen und 2 Spalten für das Formular bereitstellen. Danach
	 * nötige Panels einfügen und diesem Widget hinzufügen.
	 */
	private final Grid form = new Grid(3, 2);

	/**
	 * Im Konstruktor kann eine selektierte Bewertung übergeben werden, die dann
	 * bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Bewertung erstellt werden soll.
	 * 
	 * @param pToChangeRating
	 *            Rating, das im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public RateApplicationForm(Rating pToChangeRating, final boolean pHeadline) {
		if (pToChangeRating != null) {
			shouldUpdate = true;
			toChangeRating = pToChangeRating;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Bewertung bearbeiten", true);
			addHeadline = createHeadline("Bewertung hinzufügen", true);
		}
	}

	/**
	 * Im Konstruktor kann eine selektierte Bewertung übergeben werden, die dann
	 * bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Bewertung erstellt werden soll.
	 * 
	 * @param pToChangeRating
	 *            Bewertung, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public RateApplicationForm(Rating pToChangeRating, final boolean pHeadline, final boolean pClosingHeadline,
			final Application currentApplication) {
		this(pToChangeRating, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Bewertung bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Bewertung hinzufügen", true);
		}

		form.setWidth("100%");
		form.setWidget(0, 0, nameLabel);
		form.setWidget(0, 1, ratingInput);
		ratingInput.addItem("0.1");
		ratingInput.addItem("0.2");
		ratingInput.addItem("0.3");
		ratingInput.addItem("0.4");
		ratingInput.addItem("0.5");
		ratingInput.addItem("0.6");
		ratingInput.addItem("0.7");
		ratingInput.addItem("0.8");
		ratingInput.addItem("0.9");
		ratingInput.addItem("1.0");
		ratingInput.setVisibleItemCount(1);
		form.setWidget(1, 0, descriptionLabel);
		form.setWidget(1, 1, descriptionInput);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);

		if (shouldUpdate == false) {
			worketplaceAdministration.getRatingFor(currentApplication, new AsyncCallback<Rating>() {
				public void onFailure(Throwable caught) {
				}

				public void onSuccess(Rating result) {
					if (result instanceof Rating) {
						toChangeRating = result;
						shouldUpdate = true;
					}
					rpcFinished = true;
				}
			});
		} else {
			rpcFinished = true;
		}

		class RpcWrapper {
			protected Timer t;

			public RpcWrapper() {
				t = new Timer() {
					public void run() {
						if (rpcFinished) {
							t.cancel();
							/*
							 * Falls eine selektierte Bewerbung übergeben wurde
							 * und jetzt dargestellt werden soll
							 */
							if (shouldUpdate) {
								if (changeHeadline != null) {
									root.add(changeHeadline);
								}
								int indexToFind = -1;
								for (int i = 0; i < ratingInput.getItemCount(); i++) {
									if (ratingInput.getItemText(i).equals(String.valueOf(toChangeRating.getRating()).substring(0, 3))) {
										indexToFind = i;
										break;
									}
								}
								ratingInput.setSelectedIndex(indexToFind);
								descriptionInput.setText(toChangeRating.getRatingStatement());
								final Button saveButton = new Button("Speichern");
								saveButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										if (ratingInput.getSelectedValue().length() == 0) {
											Window.alert("Bitte vergeben eine Bewertung");
										} else if (descriptionInput.getText().length() == 0) {
											Window.alert("Bitte beschreiben Sie Ihre Bewertung genauer");
										} else if (ratingInput.getSelectedValue() == "Bitte wählen") {
											Window.alert("Bitte beschreiben Sie Ihre Bewertung genauer");
										} else {
											toChangeRating.setRating(Float.parseFloat(ratingInput.getSelectedValue()));
											toChangeRating.setRatingStatement(descriptionInput.getText());
											worketplaceAdministration.saveRating(toChangeRating,
													new AsyncCallback<Void>() {
														public void onFailure(Throwable caught) {
															Window.alert(
																	"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
														}

														public void onSuccess(Void result) {
															renderFormSuccess();
															Window.alert("Die Bewertung wurde erfolgreich geändert");
														}
													});
										}
									}
								});
								final VerticalPanel panel = new VerticalPanel();
								panel.add(saveButton);
								final Button deleteButton = new Button("Bewertung entfernen");
								deleteButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										final boolean confirmDelete = Window
												.confirm("Möchten Sie die Bewertung wirklich löschen?");
										if (confirmDelete) {
											worketplaceAdministration.deleteRating(toChangeRating,
													new AsyncCallback<Void>() {
														public void onFailure(Throwable caught) {
															Window.alert(
																	"Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
														}

														public void onSuccess(Void result) {
															renderFormSuccess();
															Window.alert("Die Bewertung wurde erfolgreich gelöscht");
														}
													});
										}
									}
								});
								panel.add(deleteButton);
								form.setWidget(2, 1, panel);
							} else {
								if (addHeadline != null) {
									root.add(addHeadline);
								}
								final Button saveButton = new Button("Bewertung anlegen");
								saveButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										if (ratingInput.getSelectedValue().length() == 0) {
											Window.alert("Bitte vergeben Sie eine Bewertung");
										} else if (descriptionInput.getText().length() == 0) {
											Window.alert("Bitte beschreiben Sie Ihre Bewertung genauer");
										} else {
											worketplaceAdministration.rateApplication(currentApplication,
													Float.parseFloat(ratingInput.getSelectedValue()),
													descriptionInput.getText(), new AsyncCallback<Enrollment>() {
														@Override
														public void onFailure(Throwable caught) {
															Window.alert(
																	"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
														}

														@Override
														public void onSuccess(Enrollment result) {
															renderFormSuccess();
															Window.alert("Die Bewerbung wurde bewertet");
															if (result instanceof Enrollment) {
																mainPanel.setForm(
																		new EnrollmentForm(result, false, true));
															}
														}
													});
										}
									}
								});
								form.setWidget(2, 1, saveButton);
							}
							root.add(form);
						}
					}
				};
				// Schedule the timer to check if all RPC calls finished
				// each 400 milliseconds
				t.scheduleRepeating(400);
			}
		}
		new RpcWrapper();
	}
}