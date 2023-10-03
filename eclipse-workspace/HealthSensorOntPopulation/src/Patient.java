public class Patient 
{
	private String patientId;
	private String gender;
	private String ethnicity;
	private String race;
	private String preExisMedicCond;
	private String isTakingMedication;
	
	public Patient(String patientId, String gender, String ethnicity, String race, String preExisMedicCond, String isTakingMedication) 
	{
		this.patientId = patientId;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.race = race;
		this.preExisMedicCond = preExisMedicCond;
		this.isTakingMedication = isTakingMedication;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	
	public String getPreExisMedicCond() {
		return preExisMedicCond;
	}

	public void setPreExisMedicCond(String preExisMedicCond) {
		this.preExisMedicCond = preExisMedicCond;
	}

	public String getIsTakingMedication() {
		return isTakingMedication;
	}

	public void setIsTakingMedication(String isTakingMedication) {
		this.isTakingMedication = isTakingMedication;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", gender=" + gender + ", ethnicity=" + ethnicity + ", race=" + race
				+ ", preExisMedicCond=" + preExisMedicCond + ", isTakingMedication=" + isTakingMedication + "]";
	}
}
