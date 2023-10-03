import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;

public class DataProcessing 
{
	
	public static List<ContinuousGlucoseMonitoring> readCGMData(String filename)
	{
	  List<ContinuousGlucoseMonitoring> listOfRecords = new ArrayList<ContinuousGlucoseMonitoring>();
		
	  try
	  {	
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line = "";
	    
	    String cabeceras = reader.readLine().trim();
	    System.out.println(cabeceras);
	    boolean finalizado = false;
	    
	    while(!finalizado)
	    {	
	    	line = reader.readLine();
	    	if(line != null)
	    	{
	    		StringTokenizer tokenized = new StringTokenizer(line, "|");
	    		List<String> recordedLine = new ArrayList<String>();
	    		while(tokenized.hasMoreTokens())
	    		{
	    			String elemento = tokenized.nextToken();
	    			recordedLine.add(eliminaEspacios(elemento));
	    		}
	    			
	    		if( recordedLine.size() == 7)
	    		{
	    			String patId = "";
	    			String cgmId = "";
	    			String deviceManufacturer = "";
	    			String deviceModel = "";
	    			String deviceType = "";
	    			String visit = "";
	    			
	    			cgmId = "cgm" + recordedLine.get(0);
	    			patId = recordedLine.get(1);
	    			
	    			if(!recordedLine.get(2).isEmpty())
	    				deviceManufacturer = recordedLine.get(2);
	    			
	    			if(!recordedLine.get(3).isEmpty())
	    				deviceModel = recordedLine.get(3);
	    			
	    			if(!recordedLine.get(4).isEmpty())
	    				deviceType = recordedLine.get(4);
	    			
	    			if(!recordedLine.get(5).isEmpty())
	    				visit = recordedLine.get(5);

	    			ContinuousGlucoseMonitoring rec = new ContinuousGlucoseMonitoring(patId, cgmId, deviceManufacturer, deviceModel, deviceType, visit);
	    			System.out.println(" " + rec.toString());
	    			listOfRecords.add(rec);
	    		}		
	    	}
	    	else
	    		finalizado = true;
		}
	    reader.close();
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", filename);
	    e.printStackTrace();
	  }
	  return listOfRecords;
	}
	
	
	public static List<Patient> readScreeningData(String filename)
	{
		List<Patient> listaPacientes = new ArrayList<Patient>();
	  try
	  {	
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line;
	    
	    boolean finalizado = false;
	    
	    while(!finalizado)
	    {	
	    	line = reader.readLine();
	    	if(line != null)
	    	{
	    		StringTokenizer tokenized = new StringTokenizer(line, "|");
	    		List<String> recordedLine = new ArrayList<String>();
	    		while(tokenized.hasMoreTokens())
	    		{
	    			String elemento = tokenized.nextToken();
	    			recordedLine.add(eliminaEspacios(elemento));
	    		}
	    		
	    		if(recordedLine.size() == 63)
	    		{
	    			String patientId = "";
		    		String gender = "";
		    		String ethnicity = "";
		    		String race = "";
		    		String preExisMedicCond = "";
		    		String isTakingMedication = "";
		    		
		    		if(!recordedLine.get(1).isEmpty())
	    				patientId = recordedLine.get(1);
		    		
		    		if(!recordedLine.get(5).isEmpty())
	    				gender = recordedLine.get(5);
		    		
		    		if(!recordedLine.get(6).isEmpty())
	    				ethnicity = recordedLine.get(6);
		    		
		    		if(!recordedLine.get(7).isEmpty())
	    				race = recordedLine.get(7);
		    		
		    		if(!recordedLine.get(53).isEmpty())
		    			preExisMedicCond = recordedLine.get(53);
		    		
		    		if(!recordedLine.get(54).isEmpty())
		    			isTakingMedication = recordedLine.get(54);
		    		
		    		Patient pat = new Patient(patientId, gender, ethnicity, race, preExisMedicCond, isTakingMedication);
		    		listaPacientes.add(pat);
		    		//System.out.println(pat.toString());
	    		}
	    	}
	    	else
	    		finalizado = true;
		}
	    reader.close();
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", filename);
	    e.printStackTrace();
	  }
	  return listaPacientes;
	}

	
	public static List<InsulineAdministration> readDeviceRelatedData(String filename)
	{
		List<InsulineAdministration> listaInsAdm = new ArrayList<InsulineAdministration>();
		 try
		  {	
		    BufferedReader reader = new BufferedReader(new FileReader(filename));
		    String line = "";
		    String cabeceras = reader.readLine().trim();
		    System.out.println(cabeceras);
		    
    		boolean finalizado = false;
		    
		    while(!finalizado)
		    {	
		    	line = reader.readLine();
		    	if(line != null)
		    	{
		    		StringTokenizer tokenized = new StringTokenizer(line, "|");
		    		List<String> recordedLine = new ArrayList<String>();
		    		while(tokenized.hasMoreTokens())
		    		{
		    			String elemento = tokenized.nextToken();
		    			recordedLine.add(eliminaEspacios(elemento));
		    		}
		    		
		    		if( recordedLine.size() == 63)
		    		{
		    			String patId = "";
		    			//Modalities for insuline administration
		    			String insModPump = "";//1 = yes
		    			String insInjections = "";//1 = yes
		    			String insInhaled = "";//1 = yes
		    			String insModNone = "";//1 = yes
		    			String pumpUse = "";
		    			String pumpType = "";
		    			float dailyInsulineUnits = 0.0f;
		    			String gcmUseStat = "";
		    			
		    			if(!recordedLine.get(1).isEmpty())
		    				patId = recordedLine.get(1);

		    			if(!recordedLine.get(20).isEmpty())
		    				insModPump = recordedLine.get(20);
		    			
		    			if(!recordedLine.get(21).isEmpty())
		    				insInjections = recordedLine.get(21);
		    			
		    			if(!recordedLine.get(22).isEmpty())
		    				insInhaled = recordedLine.get(22);
		    			
		    			if(!recordedLine.get(23).isEmpty())
		    				insModNone = recordedLine.get(23);
		    			
		    			if(!recordedLine.get(24).isEmpty())
		    				pumpUse = recordedLine.get(24);
		    			
		    			if(!recordedLine.get(25).isEmpty())
		    			{
		    				pumpType = recordedLine.get(25);
		    				if(pumpType.equalsIgnoreCase("MedtronicParadigm723(Revel)"))
		    					pumpType = "Medtronic Paradigm 723 (Revel)";
		    				else if(pumpType.equalsIgnoreCase("Tandemt:slim"))
		    					pumpType = "Tandem t:slim";
		    				else if(pumpType.equalsIgnoreCase("MedtronicParadigm523(Revel)"))
		    					pumpType = "Medtronic Paradigm 523 (Revel)";
		    				else if(pumpType.equalsIgnoreCase("Medtronic551(530G)"))
		    					pumpType = "Medtronic 551 (530G)";
		    				else if(pumpType.equalsIgnoreCase("RocheAccu-ChekSpiritCombo"))
		    					pumpType = "Roche Accu-Chek Spirit Combo";
		    				else if(pumpType.equalsIgnoreCase("Medtronic630G"))
		    					pumpType = "Medtronic 630G";
		    				else if(pumpType.equalsIgnoreCase("Medtronic751(530G)"))
		    					pumpType = "Medtronic 751 (530G)";
		    				else if(pumpType.equalsIgnoreCase("Medtronic754"))
		    					pumpType = "Medtronic 754";
		    				else if(pumpType.equalsIgnoreCase("Medtronic535"))
		    					pumpType = "Medtronic 535";
		    				else if(pumpType.equalsIgnoreCase("MiniMed630G"))
		    					pumpType = "MiniMed 630G";
		    				else if(pumpType.equalsIgnoreCase("MiniMed670G"))
		    					pumpType = "MiniMed 670G";
		    				else if(pumpType.equalsIgnoreCase("AnimasVibe"))
		    					pumpType = "Animas Vibe";
		    				else if(pumpType.equalsIgnoreCase("AnimasOneTouchPing"))
		    					pumpType = "Animas OneTouch Ping";
		    				else if(pumpType.equalsIgnoreCase("InsuletOmniPodInsulinManagementSystem"))
		    					pumpType = "Insulet OmniPod Insulin Management System";
		    			}
		    				
		    			if(!recordedLine.get(27).isEmpty())
		    				dailyInsulineUnits = Float.parseFloat(recordedLine.get(27));
		    			
		    			if(!recordedLine.get(37).isEmpty())
		    			{
		    				gcmUseStat = recordedLine.get(37);
		    				if(gcmUseStat.equalsIgnoreCase("Inpast,butnotcurrent"))
			    				gcmUseStat = "In past, but not current";
		    			}
		    				
		    			System.out.println("===================================================================");
		    			System.out.println("Patient Id => " + patId);
		    			//Insuline mode of administration
		    			String insulineModeAdm = "";
		    			if(insModPump.equalsIgnoreCase("1"))
		    			{
		    				insulineModeAdm = "Insuline Pump";
		    			}
		    			else if(insInjections.equalsIgnoreCase("1"))
							insulineModeAdm = "Insuline Injected";
		    			else if(insInhaled.equalsIgnoreCase("1"))
							insulineModeAdm = "Insuline Inhaled";
		    			else if(insModNone.equalsIgnoreCase("1"))
							insulineModeAdm = "None";

		    			InsulineAdministration insAdm = new InsulineAdministration(patId, "ins" + patId, insulineModeAdm,  pumpUse, pumpType, dailyInsulineUnits,  gcmUseStat);
		    			listaInsAdm.add(insAdm);
		    			//System.out.println(insAdm.toString());
		    		}		
		    	}
		    	else
		    		finalizado = true;
			}
		    reader.close();
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.");
		    e.printStackTrace();
		  }
		 return listaInsAdm;
	}
	
	
	public static List<PatientRecord> readPhysicalExamData(String filename)
	{
		List<PatientRecord> listOfRecords = new ArrayList<PatientRecord>();
		
	  try
	  {	
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line = "";
	    
	    String cabeceras = reader.readLine().trim();
	    System.out.println(cabeceras);
	    boolean finalizado = false;
	    
	    while(!finalizado)
	    {	
	    	line = reader.readLine();
	    	if(line != null)
	    	{
	    		StringTokenizer tokenized = new StringTokenizer(line, "|");
	    		List<String> recordedLine = new ArrayList<String>();
	    		while(tokenized.hasMoreTokens())
	    		{
	    			String elemento = tokenized.nextToken();
	    			recordedLine.add(eliminaEspacios(elemento));
	    		}
	    			
	    		if( recordedLine.size() == 23)
	    		{
	    			float weight = 0.0f, height = 0.0f, glucose = 0.0f, temperature = 0.0f;
	    			int systolic = 0, diastolic = 0, heartRate = 0;
	    			String visit = "";
	    			
	    			int recId = Integer.parseInt(recordedLine.get(0));
	    			String patId = recordedLine.get(1);
	    			
	    			if(!recordedLine.get(3).isEmpty())
	    				visit = recordedLine.get(3);
	    			
	    			if(!recordedLine.get(5).isEmpty())
	    				weight = Float.parseFloat(recordedLine.get(5));
	    			
	    			if(!recordedLine.get(8).isEmpty())
	    				height = Float.parseFloat(recordedLine.get(8));
	    			
	    			if(!recordedLine.get(11).isEmpty())
	    				systolic = Integer.parseInt(recordedLine.get(11));
	    			
	    			if(!recordedLine.get(12).isEmpty())
	    				diastolic = Integer.parseInt(recordedLine.get(12));
	    			
	    			if(!recordedLine.get(14).isEmpty())
	    				heartRate = Integer.parseInt(recordedLine.get(14));
	    			
	    			if(!recordedLine.get(16).isEmpty())
	    				temperature = Float.parseFloat(recordedLine.get(16));
	    			
	    			if(!recordedLine.get(19).isEmpty())
	    				glucose = Float.parseFloat(recordedLine.get(19));
	    			
	    			PatientRecord rec = new PatientRecord(patId, recId, visit, weight, height, systolic, diastolic, heartRate, temperature, glucose);
	    			//System.out.println(" " + rec.toString());
	    			listOfRecords.add(rec);
	    		}		
	    	}
	    	else
	    		finalizado = true;
		}
	    
	    reader.close();
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", filename);
	    e.printStackTrace();
	  }
	  
	  System.out.println(listOfRecords);
	  return listOfRecords;
	}
	
	
	public static String eliminaEspacios(String str)
	{
		String cad = "";
        for (int i = 0; i < str.length(); i++) 
        {
            if((str.charAt(i) > 32) && (str.charAt(i) < 126))
            	cad = cad + str.charAt(i);
        }
        //System.out.println(cad); 
        return cad;
	}
	
	public static void populatePatientClass(String ontoFile, List<Patient> listPatients)
	{
		try
		{	
			File file = new File(ontoFile);
			OWLOntologyManager man = OWLManager.createOWLOntologyManager();
			OWLOntology ontology = man.loadOntologyFromOntologyDocument(file);
			IRI iri = ontology.getOntologyID().getOntologyIRI();
			OWLOntologyFormat format = man.getOntologyFormat(ontology);
			System.out.println("Format detected " + format.toString());

			System.out.println("Loaded ontology: " + ontology);
			System.out.println("Ontology IRI: " + iri);
			System.out.println("Number of axioms: " + ontology.getAxiomCount());

			OWLDataFactory factory = man.getOWLDataFactory();

			for(int i=0; i < listPatients.size(); i++)
			{
				Patient eachPatient = listPatients.get(i);
				System.out.println("Insertando " + eachPatient.toString());

				OWLClass patient = factory.getOWLClass(IRI.create(iri + "#Patient"));
				OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(iri + "#Pat" + eachPatient.getPatientId()));
				OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(patient, ind);
				man.addAxiom( ontology, classAssertion);

				OWLDataProperty patientId = factory.getOWLDataProperty(IRI.create(iri + "#hasPatientID"));	 
				OWLLiteral literalPatientID = factory.getOWLTypedLiteral(eachPatient.getPatientId());
				OWLDataPropertyAssertionAxiom propPatID = factory.getOWLDataPropertyAssertionAxiom(patientId, ind, literalPatientID);
				man.addAxiom( ontology, propPatID);

				OWLDataProperty gender = factory.getOWLDataProperty(IRI.create(iri + "#hasGender"));
				OWLLiteral literalGender = factory.getOWLTypedLiteral(eachPatient.getGender());
				OWLDataPropertyAssertionAxiom propGender = factory.getOWLDataPropertyAssertionAxiom(gender, ind, literalGender);
				man.addAxiom( ontology, propGender);

				OWLDataProperty ethnicity = factory.getOWLDataProperty(IRI.create(iri + "#hasEthnicity"));
				OWLLiteral literalEthnicity = factory.getOWLTypedLiteral(eachPatient.getEthnicity()); 
				OWLDataPropertyAssertionAxiom propEthnicity = factory.getOWLDataPropertyAssertionAxiom(ethnicity, ind, literalEthnicity);
				man.addAxiom( ontology, propEthnicity);
				
				OWLDataProperty race = factory.getOWLDataProperty(IRI.create(iri + "#hasRace"));
				OWLLiteral literalRace = factory.getOWLTypedLiteral(eachPatient.getRace()); 
				OWLDataPropertyAssertionAxiom propRace = factory.getOWLDataPropertyAssertionAxiom(race, ind, literalRace);
				man.addAxiom( ontology, propRace);
				
				OWLDataProperty medicalCond = factory.getOWLDataProperty(IRI.create(iri + "#hasPreExisMedicCond"));
				OWLLiteral literalMedicCond = factory.getOWLTypedLiteral(eachPatient.getPreExisMedicCond()); 
				OWLDataPropertyAssertionAxiom propMedicCond = factory.getOWLDataPropertyAssertionAxiom(medicalCond, ind, literalMedicCond);
				man.addAxiom( ontology, propMedicCond);
				
				OWLDataProperty takingMedicament = factory.getOWLDataProperty(IRI.create(iri + "#isTakingMedication"));
				OWLLiteral literalMedicament = factory.getOWLTypedLiteral(eachPatient.getIsTakingMedication()); 
				OWLDataPropertyAssertionAxiom propMedicament = factory.getOWLDataPropertyAssertionAxiom(takingMedicament, ind, literalMedicament);
				man.addAxiom( ontology, propMedicament);
			}

			String fileOnt = new File(ontoFile).toURI().toString();

			man.saveOntology(ontology, new OWLXMLOntologyFormat(), IRI.create(fileOnt));

		}
		catch (OWLOntologyCreationException e) 
		{
			System.out.println("Ontology could not be loaded: " + e.getMessage());
		} 
		catch (OWLOntologyStorageException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void populatePatientRecords(String ontoFile, List<PatientRecord> patientRecords)
	{
		try
		{	
			File file = new File(ontoFile);
			OWLOntologyManager man = OWLManager.createOWLOntologyManager();
			OWLOntology ontology = man.loadOntologyFromOntologyDocument(file);
			IRI iri = ontology.getOntologyID().getOntologyIRI();
			OWLOntologyFormat format = man.getOntologyFormat(ontology);
			System.out.println("Format detected " + format.toString());

			System.out.println("Loaded ontology: " + ontology);
			System.out.println("Ontology IRI: " + iri);
			System.out.println("Number of axioms: " + ontology.getAxiomCount());

			OWLDataFactory factory = man.getOWLDataFactory();

			for(int i=0; i < patientRecords.size(); i++)
			{
				PatientRecord eachPatRecord = patientRecords.get(i);
				System.out.println("Insertando " + eachPatRecord.toString());
				
				OWLNamedIndividual pat = factory.getOWLNamedIndividual(IRI.create(iri + "#Pat" + eachPatRecord.getPatId()));
				
				//Se crea un registro para el paciente
				OWLClass patientRecord = factory.getOWLClass(IRI.create(iri + "#PatientRecord"));
				OWLNamedIndividual record = factory.getOWLNamedIndividual(IRI.create(iri + "#Rec" + eachPatRecord.getRecId()));
				OWLClassAssertionAxiom classAssertion2 = factory.getOWLClassAssertionAxiom(patientRecord, record);
				man.addAxiom( ontology, classAssertion2);
				
				//Se crean todas las data properties del patientRecord
				OWLDataProperty periodOfVisit = factory.getOWLDataProperty(IRI.create(iri + "#hasPeriodOfVisit"));
				OWLLiteral literalPeriodOfVisit = factory.getOWLTypedLiteral(eachPatRecord.getPeriodVisit());
				OWLDataPropertyAssertionAxiom propPeriodOfVisit = factory.getOWLDataPropertyAssertionAxiom(periodOfVisit, record, literalPeriodOfVisit);
				man.addAxiom( ontology, propPeriodOfVisit);
				
				OWLDataProperty weight = factory.getOWLDataProperty(IRI.create(iri + "#hasWeight"));
				OWLLiteral literalWeight = factory.getOWLTypedLiteral(eachPatRecord.getWeight());
				OWLDataPropertyAssertionAxiom propWeight = factory.getOWLDataPropertyAssertionAxiom(weight, record, literalWeight);
				man.addAxiom( ontology, propWeight);

				OWLDataProperty height = factory.getOWLDataProperty(IRI.create(iri + "#hasHeight"));
				OWLLiteral literalHeight = factory.getOWLTypedLiteral(eachPatRecord.getHeight()); 
				OWLDataPropertyAssertionAxiom propHeight = factory.getOWLDataPropertyAssertionAxiom(height, record, literalHeight);
				man.addAxiom( ontology, propHeight);
				
				OWLDataProperty systolic = factory.getOWLDataProperty(IRI.create(iri + "#hasSystolicBloodPress"));
				OWLLiteral literalSystolic = factory.getOWLTypedLiteral(eachPatRecord.getSystolic()); 
				OWLDataPropertyAssertionAxiom propSystolic = factory.getOWLDataPropertyAssertionAxiom(systolic, record, literalSystolic);
				man.addAxiom( ontology, propSystolic);
				
				OWLDataProperty diastolic = factory.getOWLDataProperty(IRI.create(iri + "#hasDiastolicBloodPress"));
				OWLLiteral literalDiastolic = factory.getOWLTypedLiteral(eachPatRecord.getDiastolic()); 
				OWLDataPropertyAssertionAxiom propDiastolic = factory.getOWLDataPropertyAssertionAxiom(diastolic, record, literalDiastolic);
				man.addAxiom( ontology, propDiastolic);
				
				OWLDataProperty heartRate = factory.getOWLDataProperty(IRI.create(iri + "#hasHeartRate"));
				OWLLiteral literalHeartRate = factory.getOWLTypedLiteral(eachPatRecord.getHeartRate()); 
				OWLDataPropertyAssertionAxiom propHeartRate = factory.getOWLDataPropertyAssertionAxiom(heartRate, record, literalHeartRate);
				man.addAxiom( ontology, propHeartRate);
				
				OWLDataProperty temperature = factory.getOWLDataProperty(IRI.create(iri + "#hasTemperature"));
				OWLLiteral literalTemperature = factory.getOWLTypedLiteral(eachPatRecord.getTemperature()); 
				OWLDataPropertyAssertionAxiom propTemperature = factory.getOWLDataPropertyAssertionAxiom(temperature, record, literalTemperature);
				man.addAxiom( ontology, propTemperature);
				
				OWLDataProperty glucose = factory.getOWLDataProperty(IRI.create(iri + "#hasBloodGlucose"));
				OWLLiteral literalGlucose = factory.getOWLTypedLiteral(eachPatRecord.getGlucose()); 
				OWLDataPropertyAssertionAxiom propGlucose = factory.getOWLDataPropertyAssertionAxiom(glucose, record, literalGlucose);
				man.addAxiom( ontology, propGlucose);
				
				//Se crea un vinculo entre pat y su record mediante la propiedad patientHasRecord
				OWLObjectProperty patHasRecord = factory.getOWLObjectProperty(IRI.create(iri + "#patientHasRecord"));
				OWLObjectPropertyAssertionAxiom patHasRecAssertion = factory.getOWLObjectPropertyAssertionAxiom(patHasRecord, pat, record);
				man.addAxiom(ontology, patHasRecAssertion);
			}

			String fileOnt = new File(ontoFile).toURI().toString();

			man.saveOntology(ontology, new OWLXMLOntologyFormat(), IRI.create(fileOnt));

		}
		catch (OWLOntologyCreationException e) 
		{
			System.out.println("Ontology could not be loaded: " + e.getMessage());
		} 
		catch (OWLOntologyStorageException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void populatePatientInsuline(String ontoFile, List<InsulineAdministration> insulineRecords)
	{
		try
		{	
			File file = new File(ontoFile);
			OWLOntologyManager man = OWLManager.createOWLOntologyManager();
			OWLOntology ontology = man.loadOntologyFromOntologyDocument(file);
			IRI iri = ontology.getOntologyID().getOntologyIRI();
			OWLOntologyFormat format = man.getOntologyFormat(ontology);
			System.out.println("Format detected " + format.toString());

			System.out.println("Loaded ontology: " + ontology);
			System.out.println("Ontology IRI: " + iri);
			System.out.println("Number of axioms: " + ontology.getAxiomCount());

			OWLDataFactory factory = man.getOWLDataFactory();

			for(int i=0; i < insulineRecords.size(); i++)
			{
				InsulineAdministration eachPatRecord = insulineRecords.get(i);
				System.out.println("Insertando " + eachPatRecord.toString());
				
				OWLNamedIndividual pat = factory.getOWLNamedIndividual(IRI.create(iri + "#Pat" + eachPatRecord.getPatId()));
				
				//Se crea un individuo de InsulineAdministration
				OWLClass insulineAdmClass = factory.getOWLClass(IRI.create(iri + "#InsulineAdministration"));
				OWLNamedIndividual insRecord = factory.getOWLNamedIndividual(IRI.create(iri + "#" + eachPatRecord.getInsAdId()));
				OWLClassAssertionAxiom classAssertion2 = factory.getOWLClassAssertionAxiom(insulineAdmClass, insRecord);
				man.addAxiom( ontology, classAssertion2);
				
				//Se crean todas las data properties del patientRecord
				OWLDataProperty modeOfInsAdm = factory.getOWLDataProperty(IRI.create(iri + "#hasModeOfInsAdm"));
				OWLLiteral literalModeOfIns = factory.getOWLTypedLiteral(eachPatRecord.getInsModeOfAdministration());
				OWLDataPropertyAssertionAxiom propModeOfIns = factory.getOWLDataPropertyAssertionAxiom(modeOfInsAdm, insRecord, literalModeOfIns);
				man.addAxiom( ontology, propModeOfIns);
				
				OWLDataProperty pumpUse = factory.getOWLDataProperty(IRI.create(iri + "#hasPumpUse"));
				OWLLiteral literalPumpUse = factory.getOWLTypedLiteral(eachPatRecord.getPumpUse());
				OWLDataPropertyAssertionAxiom propPumpUse = factory.getOWLDataPropertyAssertionAxiom(pumpUse, insRecord, literalPumpUse);
				man.addAxiom( ontology, propPumpUse);

				OWLDataProperty pumpType = factory.getOWLDataProperty(IRI.create(iri + "#usesPumpType"));
				OWLLiteral literalPumpType = factory.getOWLTypedLiteral(eachPatRecord.getPumpType()); 
				OWLDataPropertyAssertionAxiom propPumpType = factory.getOWLDataPropertyAssertionAxiom(pumpType, insRecord, literalPumpType);
				man.addAxiom( ontology, propPumpType);
				
				OWLDataProperty insulineUnits = factory.getOWLDataProperty(IRI.create(iri + "#takesDailyInsulineUnits"));
				OWLLiteral literalInsulineUnits = factory.getOWLTypedLiteral(eachPatRecord.getDailyInsulineUnits()); 
				OWLDataPropertyAssertionAxiom propInsulineUnits = factory.getOWLDataPropertyAssertionAxiom(insulineUnits, insRecord, literalInsulineUnits);
				man.addAxiom( ontology, propInsulineUnits);
				
				OWLDataProperty cgmUsageStatus = factory.getOWLDataProperty(IRI.create(iri + "#hasCGMUsageStatus"));
				OWLLiteral literalCgmUsageStatus = factory.getOWLTypedLiteral(eachPatRecord.getGcmUseStat()); 
				OWLDataPropertyAssertionAxiom propCGMUsageStatus = factory.getOWLDataPropertyAssertionAxiom(cgmUsageStatus, insRecord, literalCgmUsageStatus);
				man.addAxiom( ontology, propCGMUsageStatus);
				
				//Se crea un vinculo entre pat y su registro de insulina mediante la propiedad patientHasInsulineAdministration
				OWLObjectProperty patHasInsuline = factory.getOWLObjectProperty(IRI.create(iri + "#patientHasInsulineAdministration"));
				OWLObjectPropertyAssertionAxiom patHasRecAssertion = factory.getOWLObjectPropertyAssertionAxiom(patHasInsuline, pat, insRecord);
				man.addAxiom(ontology, patHasRecAssertion);
			}

			String fileOnt = new File(ontoFile).toURI().toString();

			man.saveOntology(ontology, new OWLXMLOntologyFormat(), IRI.create(fileOnt));

		}
		catch (OWLOntologyCreationException e) 
		{
			System.out.println("Ontology could not be loaded: " + e.getMessage());
		} 
		catch (OWLOntologyStorageException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void populatePatientCGMData(String ontoFile, List<ContinuousGlucoseMonitoring> cgmRecords)
	{
		try
		{	
			File file = new File(ontoFile);
			OWLOntologyManager man = OWLManager.createOWLOntologyManager();
			OWLOntology ontology = man.loadOntologyFromOntologyDocument(file);
			IRI iri = ontology.getOntologyID().getOntologyIRI();
			OWLOntologyFormat format = man.getOntologyFormat(ontology);
			System.out.println("Format detected " + format.toString());

			System.out.println("Loaded ontology: " + ontology);
			System.out.println("Ontology IRI: " + iri);
			System.out.println("Number of axioms: " + ontology.getAxiomCount());

			OWLDataFactory factory = man.getOWLDataFactory();

			for(int i=0; i < cgmRecords.size(); i++)
			{
				ContinuousGlucoseMonitoring eachCGMRecord = cgmRecords.get(i);
				System.out.println("Insertando " + eachCGMRecord.toString());
				
				OWLNamedIndividual pat = factory.getOWLNamedIndividual(IRI.create(iri + "#Pat" + eachCGMRecord.getPatId()));
				
				//Se crea un individuo de ContinuousGlucoseMonitoring
				OWLClass cgmClass = factory.getOWLClass(IRI.create(iri + "#ContinuousGlucoseMonitoring"));
				OWLNamedIndividual cgmRecord = factory.getOWLNamedIndividual(IRI.create(iri + "#" + eachCGMRecord.getCgmId()));
				OWLClassAssertionAxiom classAssertion2 = factory.getOWLClassAssertionAxiom(cgmClass, cgmRecord);
				man.addAxiom( ontology, classAssertion2);
				
				//Se crean todas las data properties del cgmRecord
				OWLDataProperty deviceManufacturer = factory.getOWLDataProperty(IRI.create(iri + "#hasDeviceManufacturer"));
				OWLLiteral literalDevMan = factory.getOWLTypedLiteral(eachCGMRecord.getDeviceManufacturer());
				OWLDataPropertyAssertionAxiom propDevMan = factory.getOWLDataPropertyAssertionAxiom(deviceManufacturer, cgmRecord, literalDevMan);
				man.addAxiom( ontology, propDevMan);
				
				OWLDataProperty deviceModel = factory.getOWLDataProperty(IRI.create(iri + "#usesDeviceModel"));
				OWLLiteral literalDevMod = factory.getOWLTypedLiteral(eachCGMRecord.getDeviceModel());
				OWLDataPropertyAssertionAxiom propDevMod = factory.getOWLDataPropertyAssertionAxiom(deviceModel, cgmRecord, literalDevMod);
				man.addAxiom( ontology, propDevMod);

				OWLDataProperty deviceType = factory.getOWLDataProperty(IRI.create(iri + "#hasDeviceType"));
				OWLLiteral literalDeviceType = factory.getOWLTypedLiteral(eachCGMRecord.getDeviceType()); 
				OWLDataPropertyAssertionAxiom propDeviceType = factory.getOWLDataPropertyAssertionAxiom(deviceType, cgmRecord, literalDeviceType);
				man.addAxiom( ontology, propDeviceType);
				
				OWLDataProperty visit = factory.getOWLDataProperty(IRI.create(iri + "#isRealizedDuring"));
				OWLLiteral literalVisit = factory.getOWLTypedLiteral(eachCGMRecord.getVisit()); 
				OWLDataPropertyAssertionAxiom propVisit = factory.getOWLDataPropertyAssertionAxiom(visit, cgmRecord, literalVisit);
				man.addAxiom( ontology, propVisit);
				
				//Se crea un vinculo entre pat y su registro de cgm mediante la propiedad patientHasInsulineAdministration
				OWLObjectProperty patHasCGM = factory.getOWLObjectProperty(IRI.create(iri + "#patientHasCGM"));
				OWLObjectPropertyAssertionAxiom patHasCGMAssertion = factory.getOWLObjectPropertyAssertionAxiom(patHasCGM, pat, cgmRecord);
				man.addAxiom(ontology, patHasCGMAssertion);
			}

			String fileOnt = new File(ontoFile).toURI().toString();

			man.saveOntology(ontology, new OWLXMLOntologyFormat(), IRI.create(fileOnt));

		}
		catch (OWLOntologyCreationException e) 
		{
			System.out.println("Ontology could not be loaded: " + e.getMessage());
		} 
		catch (OWLOntologyStorageException e) 
		{
			e.printStackTrace();
		}
	}
	
}
