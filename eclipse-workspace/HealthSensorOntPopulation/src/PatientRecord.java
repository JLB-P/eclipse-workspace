
public class PatientRecord 
{
	private String patId;
	private int recId;
	private String periodVisit;
	private float weight;
	private float height;
	private int systolic;
	private int diastolic;
	private int heartRate;
	private float temperature;
	private float glucose;
	
	public PatientRecord(String patId, int recId, String periodVisit, float weight, float height, int systolic,
			int diastolic, int heartRate, float temperature, float glucose) 
	{
		this.patId = patId;
		this.recId = recId;
		this.periodVisit = periodVisit;
		this.weight = weight;
		this.height = height;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.heartRate = heartRate;
		this.temperature = temperature;
		this.glucose = glucose;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getPeriodVisit() {
		return periodVisit;
	}

	public void setPeriodVisit(String periodVisit) {
		this.periodVisit = periodVisit;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getSystolic() {
		return systolic;
	}

	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}

	public int getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getGlucose() {
		return glucose;
	}

	public void setGlucose(float glucose) {
		this.glucose = glucose;
	}

	@Override
	public String toString() {
		return "PatientRecord [patId=" + patId + ", recId=" + recId + ", periodVisit=" + periodVisit + ", weight="
				+ weight + ", height=" + height + ", systolic=" + systolic + ", diastolic=" + diastolic + ", heartRate="
				+ heartRate + ", temperature=" + temperature + ", glucose=" + glucose + "]";
	}

}
