package dm3_gym_data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Inscription {
	private LocalDateTime creation;
	private LocalDate serviceDay;
	private String numPro;
	private String numSeance;
	private String numMembre;
	private boolean isConfirmed;
	
	public Inscription(Seance seance, LocalDate day, String numPro, String numSeance, String numMembre) {
		creation = LocalDateTime.now();
		serviceDay = day;
		this.numPro = numPro;
		this.numSeance = numSeance;
		this.numMembre = numMembre;
		isConfirmed = false;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public LocalDate getServiceDay() {
		return serviceDay;
	}

	public String getNumPro() {
		return numPro;
	}

	public String getNumSeance() {
		return numSeance;
	}

	public String getNumMembre() {
		return numMembre;
	}	
	
	public boolean isConfirmed() {
		return isConfirmed;
	}
	
	public void setConfirmed(boolean confirmed) {
		isConfirmed = confirmed;
	}
}
