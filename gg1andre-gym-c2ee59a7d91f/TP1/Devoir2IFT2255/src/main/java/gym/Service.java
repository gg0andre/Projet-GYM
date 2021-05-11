package gym;

public class Service {	
	private String timeStamp, startDate, endDate, openingHour, proNum, serviceCode, comments;
	private int capacity;
	private float serviceFees;
	private boolean[] recurrences;
	
	private Seance[] listeSeances;
	
	public Service( String timeStamp, String startDate, String endDate, String openingHour,
					Professionnel p, String serviceCode, String comments, int capacity, float serviceFees, boolean[] recurrences) {
		this.timeStamp = timeStamp;
		this.startDate = startDate;
		this.endDate = endDate;
		this.openingHour = openingHour;
		this.proNum = p.numeroProfessionnel;
		this.serviceCode = serviceCode;
		this.comments = comments;
		this.capacity = capacity;
		this.serviceFees = serviceFees;
		this.recurrences = recurrences;
		
		listeSeances = new Seance[7];
		
		//Initialize array
		for(int i = 0; i < 7; i++) {
			if( recurrences[i] ) {
				listeSeances[i] = new Seance(this,ConsoleUI.DAYS[i]);
			}
		}
		
		//Self-insert
		p.addService(this);
	}
	
	public void newInscription(Membre m, int selected) {
		listeSeances[selected].addMember(m);
	}

	
	public String getScheduleString() {
		String s = "";
		for(int i = 0; i < 7; i++) {
			if(recurrences[i]) {
				// Add a separator if it's not the first day
				if( s.length() > 0 )
					s += "-"+ConsoleUI.DAYS[i];
				else
					s += ConsoleUI.DAYS[i];
			}
		}
		
		return s;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}

	public String getProNum() {
		return proNum;
	}

	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public float getServiceFees() {
		return serviceFees;
	}

	public void setServiceFees(float serviceFees) {
		this.serviceFees = serviceFees;
	}

	public boolean[] getRecurrences() {
		return recurrences;
	}

	public void setRecurrences(boolean[] recurrences) {
		this.recurrences = recurrences;
	}

	public Seance[] getListeSeances() {
		return listeSeances;
	}

	public void setListeSeances(Seance[] listeSeances) {
		this.listeSeances = listeSeances;
	}

	public boolean hasSeance(int selected) {		
		return recurrences[selected];
	}
}
