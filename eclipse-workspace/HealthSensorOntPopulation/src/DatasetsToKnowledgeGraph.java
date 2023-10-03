import java.util.List;

public class DatasetsToKnowledgeGraph 
{
	public static void main(String[] args) 
	{	
		//1. Retrieve and process general Patient data
		List<Patient> lista = DataProcessing.readScreeningData("Datasets/DiabScreening.txt");
		
		//2. Register Patient data into KG
		DataProcessing.populatePatientClass("Ontologies/WISDM1.owl", lista);
		
		//3. Retrieve and process physical exam Patient records
		List<PatientRecord> registros = DataProcessing.readPhysicalExamData("Datasets/DiabPhysExam.txt");
		
		//4. Register Patient records into KG
		DataProcessing.populatePatientRecords("Ontologies/WISDM1.owl", registros);
		
		//5. Retrieve and process insulin device-related data
		List<InsulineAdministration> insulineList = DataProcessing.readDeviceRelatedData("Datasets/DiabScreening.txt");
		
		//6. Register insulin administration per Patient
		DataProcessing.populatePatientInsuline("Ontologies/WISDM2.owl", insulineList);
		
		//7. Retrieve and process Continuous Glucose Monitoring Patient data
		List<ContinuousGlucoseMonitoring> cgmList = DataProcessing.readCGMData("Datasets/DeviceUploads.txt");
		
		//8. Register Continuous Glucose Monitoring Patient data into KG
		DataProcessing.populatePatientCGMData("Ontologies/WISDM3.owl", cgmList);
	}
}
