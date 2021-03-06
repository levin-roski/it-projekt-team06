package de.worketplace.team06.shared.bo;

/**
 * 
 */
public class Team extends OrgaUnit {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Default constructor
     */
    public Team() {
    }
	
	/**
     * Variable für den Namen des Teams
     */
    private String teamName;
    
    /**
     * Variable für die Anzahl der Mitglieder eines Teams
     */
    private Integer membercount;

	/**
	 * Auslesen des Namens für das Team
	 * @return teamName
	 */
	public String getName() {
		return teamName;
	}
	
	/**
	 * Auslesen der Mitgliederzahl für das Team
	 * @return membercount
	 */
	public Integer getMembercount() {
		return membercount;
	}
	
	/**
	 * Setzen des Namens für das Team
	 * @param name
	 */
	public void setName(String name) {
		this.teamName = name;
	}

	/**
	 * Setzen der Mitgliederzahl für das Team
	 * @param membercount
	 */
	public void setMembercount(Integer membercount) {
		this.membercount = membercount;
	}

}