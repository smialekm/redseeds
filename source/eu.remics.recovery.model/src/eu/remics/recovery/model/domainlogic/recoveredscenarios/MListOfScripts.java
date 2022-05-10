package eu.remics.recovery.model.domainlogic.recoveredscenarios;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;

public class MListOfScripts {
	
	/**
	 * Process script list
	 * 
	 * @param aListOfScripts list contains names of scripts
	 * @return number of scripts in incorrect format
	 * @throws TerminologyOperationFailureException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException
	 */
	public static int processes(List<String> aListOfScripts) throws TerminologyOperationFailureException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		int j=0;
		rmh.createPackagesSystemElementsAndActors();
		for (int i=0;i<aListOfScripts.size();i++){
			try {
				UseCaseDTO uc = MScript.load(aListOfScripts.get(i), null, false, rmh);
				rmh.getRecoveredScenariosPackage().addUseCase(uc);
				rmh.saveSCProject();
				aListOfScripts.remove(i);
				i--;
			} catch (IOException e){
				rmh.revertSCProject();
			} catch (SAXException e) {
				j++;
				rmh.revertSCProject();
			} catch (RuntimeException e){
				j++;
				rmh.revertSCProject();
				e.printStackTrace();
			} catch (TerminologyOperationFailureException e){
				rmh.revertSCProject();
				throw e;
			} catch (InstantiationException e) {
				rmh.revertSCProject();
				throw e;
			} catch (IllegalAccessException e) {
				rmh.revertSCProject();
				throw e;
			} catch (ClassNotFoundException e) {
				rmh.revertSCProject();
				throw e;
			}
		}
		return j;
	}
	
	/**
	 * Process script list with selected map folder
	 * 
	 * @param aListOfScripts list contains names of scripts
	 * @param aMapFolder path to map folder
	 * @return number of scripts in incorrect format
	 * @throws TerminologyOperationFailureException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 */
	public static int processes(List<String> aListOfScripts, String aMapFolder) throws TerminologyOperationFailureException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		int j=0;
		rmh.createPackagesSystemElementsAndActors();
		rmh.saveSCProject();
		for (int i=0;i<aListOfScripts.size();i++){
			try {
				UseCaseDTO uc = MScript.load(aListOfScripts.get(i), aMapFolder, false, rmh);
				rmh.getRecoveredScenariosPackage().addUseCase(uc);
				rmh.saveSCProject();
				aListOfScripts.remove(i);
				i--;
			} catch (IOException e){
				rmh.revertSCProject();
			} catch (SAXException e) {
				j++;
				rmh.revertSCProject();
			} catch (RuntimeException e){
				j++;
				rmh.revertSCProject();
			} catch (TerminologyOperationFailureException e){
				rmh.revertSCProject();
				throw e;
			} catch (InstantiationException e) {
				rmh.revertSCProject();
				throw e;
			} catch (IllegalAccessException e) {
				rmh.revertSCProject();
				throw e;
			} catch (ClassNotFoundException e) {
				rmh.revertSCProject();
				throw e;
			}
		}
		return j;
	}
	
	/**
	 * Process script list with creation of empty notions (without attributes)
	 * 
	 * @param aListOfScripts list contains names of scripts
	 * @return number of scripts in incorrect format
	 * @throws TerminologyOperationFailureException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException
	 */
	public static int processesWithEmptyNotions(List<String> aListOfScripts) throws TerminologyOperationFailureException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		int j=0;
		rmh.createPackagesSystemElementsAndActors();
		for (int i=0;i<aListOfScripts.size();i++){
			try {
				UseCaseDTO uc = MScript.load(aListOfScripts.get(i), null, true, rmh);
				rmh.getRecoveredScenariosPackage().addUseCase(uc);
				rmh.saveSCProject();
				aListOfScripts.remove(i);
				i--;
			} catch (IOException e){
				rmh.revertSCProject();
			} catch (SAXException e) {
				j++;
				rmh.revertSCProject();
			} catch (RuntimeException e){
				j++;
				rmh.revertSCProject();
			} catch (TerminologyOperationFailureException e){
				rmh.revertSCProject();
				throw e;
			} catch (InstantiationException e) {
				rmh.revertSCProject();
				throw e;
			} catch (IllegalAccessException e) {
				rmh.revertSCProject();
				throw e;
			} catch (ClassNotFoundException e) {
				rmh.revertSCProject();
				throw e;
			}
		}
		return j;
	}
	
}