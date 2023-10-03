
public class InsulineAdministration 
{
	private String patId;
	private String insAdId;
	private String insModeOfAdministration;
	private String pumpUse;
	private String pumpType;
	private float dailyInsulineUnits;
	private String gcmUseStat;
	
	public InsulineAdministration(String patId, String insAdId, String insModeOfAdministration, String pumpUse,
			String pumpType, float dailyInsulineUnits, String gcmUseStat) 
	{
		this.patId = patId;
		this.insAdId = insAdId;
		this.insModeOfAdministration = insModeOfAdministration;
		this.pumpUse = pumpUse;
		this.pumpType = pumpType;
		this.dailyInsulineUnits = dailyInsulineUnits;
		this.gcmUseStat = gcmUseStat;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public String getInsAdId() {
		return insAdId;
	}

	public void setInsAdId(String insAdId) {
		this.insAdId = insAdId;
	}

	public String getInsModeOfAdministration() {
		return insModeOfAdministration;
	}

	public void setInsModeOfAdministration(String insModeOfAdministration) {
		this.insModeOfAdministration = insModeOfAdministration;
	}

	public String getPumpUse() {
		return pumpUse;
	}

	public void setPumpUse(String pumpUse) {
		this.pumpUse = pumpUse;
	}

	public String getPumpType() {
		return pumpType;
	}

	public void setPumpType(String pumpType) {
		this.pumpType = pumpType;
	}

	public float getDailyInsulineUnits() {
		return dailyInsulineUnits;
	}

	public void setDailyInsulineUnits(float dailyInsulineUnits) {
		this.dailyInsulineUnits = dailyInsulineUnits;
	}

	public String getGcmUseStat() {
		return gcmUseStat;
	}

	public void setGcmUseStat(String gcmUseStat) {
		this.gcmUseStat = gcmUseStat;
	}

	@Override
	public String toString() 
	{
		return "InsulineAdministration [patId=" + patId + ", insAdId=" + insAdId + ", insModeOfAdministration="
				+ insModeOfAdministration + ", pumpUse=" + pumpUse + ", pumpType=" + pumpType + ", dailyInsulineUnits="
				+ dailyInsulineUnits + ", gcmUseStat=" + gcmUseStat + "]";
	}
	
}
