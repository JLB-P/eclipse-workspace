
public class ContinuousGlucoseMonitoring 
{
	private String patId;
	private String cgmId;
	private String deviceManufacturer;
	private String deviceModel;
	private String deviceType;
	private String visit;
	
	public ContinuousGlucoseMonitoring(String patId, String cgmId, String deviceManufacturer, String deviceModel,
			String deviceType, String visit) 
	{
		this.patId = patId;
		this.cgmId = cgmId;
		this.deviceManufacturer = deviceManufacturer;
		this.deviceModel = deviceModel;
		this.deviceType = deviceType;
		this.visit = visit;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public String getCgmId() {
		return cgmId;
	}

	public void setCgmId(String cgmId) {
		this.cgmId = cgmId;
	}

	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	@Override
	public String toString() {
		return "ContinuousGlucoseMonitoring [patId=" + patId + ", cgmId=" + cgmId + ", deviceManufacturer="
				+ deviceManufacturer + ", deviceModel=" + deviceModel + ", deviceType=" + deviceType + ", visit="
				+ visit + "]";
	}
	
}
