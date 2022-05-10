package eu.redseeds.sc.terminology.model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import de.uni_koblenz.jgwnl.JGWNL;
import de.uni_koblenz.jgwnl.client.JGWNLClient;
import de.uni_koblenz.jgwnl.client.cache.JGWNLCacheClient;
import de.uni_koblenz.jgwnl.exceptions.JGWNLException;
import de.uni_koblenz.jgwnl.info.POS;
import de.uni_koblenz.jgwnl.info.SynonymInfo;
import de.uni_koblenz.jgwnl.info.SynsetInfo;
import de.uni_koblenz.jgwnl.info.WordInfo;
import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;

public class RemoteJGWNL {

	private static RemoteJGWNL instance = null;
	private JGWNLClient remoteInstance = null;
	
	
	public JGWNLClient getJGWNLClient() {
		return remoteInstance;
	}

	private boolean connected = false;

	public static RemoteJGWNL getInstance() {
		if (instance == null) {
			instance = new RemoteJGWNL();
		}
		return instance;
	}

	protected RemoteJGWNL() {
		if (remoteInstance == null) {
			System.out.println("Connecting to JGWNL server " +Constants.getJGWNLAddress() );
			setConnected(connect(Constants.getJGWNLAddress()));
		}
	}

	/**
	 * Connects to a given (url) JGWNL server. If there is a connection to a
	 * server and new connection fails, the old one is preserved.
	 * 
	 * @param url
	 * @return true if connected successfully, false otherwise
	 */
	public boolean connect(String url) {
		System.out.println("Trying to connect to " + url);
		Activator.log("Connecting to the JGWNL server " + url, Status.INFO);
		JGWNLClient instance = null;
		try {
			instance = new JGWNLCacheClient((JGWNL) Naming.lookup(url));
		} catch (MalformedURLException e1) {
			Activator.log("Connecting to the JGWNL server failed. "
					+ e1.getMessage(), Status.WARNING);
			return false;
		} catch (RemoteException e1) {
			Activator.log("Connecting to the JGWNL server failed. "
					+ e1.getMessage(), Status.WARNING);
			return false;
		} catch (NotBoundException e1) {
			Activator.log("Connecting to the JGWNL server failed. "
					+ e1.getMessage(), Status.WARNING);
			return false;
		}
		// TODO show some message if not connected

		if (instance != null) {
			Activator.log("Connected successfully", Status.INFO);
			remoteInstance = instance;
			return true;
		}
		return false;
	}

	public long addNewSense(String lemma, String gloss, String pos,
			boolean exactSpelling) {
		long synonymUid = 0;
		long senseUid = 0;
		try {
			POS p = str2POS(pos);
			synonymUid = remoteInstance
					.addSynonym(lemma, p, exactSpelling);
			senseUid = remoteInstance.addSense(gloss, p);
			remoteInstance.addSynonymToSynset(synonymUid, senseUid);
			return synonymUid;
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),
					IStatus.ERROR);
			return synonymUid;
		} catch (JGWNLException e) {
			Activator
					.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
			return synonymUid;
		}
	}

	public void deleteSense(TermSenseDTO sense) {
		try {
			long syUid = sense.getUid();
			SynonymInfo si = remoteInstance.getSynonymInfo(syUid);
			SynsetInfo ssi = si.getParentSynsetInfo();
			remoteInstance.deleteSynonym(si);
			if (ssi.getSynonymInfos().isEmpty()) {
				remoteInstance.deleteSynset(ssi);
			}
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),
					IStatus.ERROR);
		} catch (JGWNLException e) {
			Activator
					.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
		}
	}

	public void addNewSynonym(String synonymLemma, TermSenseDTO sense) {
		try {
			POS p = POS.valueOf(sense.getType());
			long synsetUid = remoteInstance.getSynonymInfo(sense.getUid())
					.getParentSynsetInfo().getUid();
			long synonymUid = remoteInstance.addSynonym(synonymLemma, p);
			// System.out.println("pos = " + p + ", senseUid = " + senseUid +
			// ", synonymUid = " + synonymUid);
			remoteInstance.addSynonymToSynset(synonymUid, synsetUid);
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),
					IStatus.ERROR);
		} catch (JGWNLException e) {
			Activator
					.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
		}
	}

	public boolean saveRemoteDictionary(boolean makeBackupCopy) {
		boolean result = false;
		try {
			result = remoteInstance.save(makeBackupCopy);
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),
					IStatus.ERROR);
		} catch (JGWNLException e) {
			Activator
					.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
		}
		return result;
	}

	public List<String> getPOSNames() {
		List<String> posNames = new ArrayList<String>();
		for (POS value : POS.values()) {
			posNames.add(POS.getName(value));
		}
		return posNames;
	}

	public String getPOSName(POS pos) {
		return POS.getName(pos);
	}

	private POS str2POS(String posName) {
		if (posName.equals(POS.getName(POS.ADJECTIVE))) {
			return POS.ADJECTIVE;
		} else if (posName.equals(POS.getName(POS.ADVERB))) {
			return POS.ADVERB;
		} else if (posName.equals(POS.getName(POS.CONDITIONAL_CONJUNCTION))) {
			return POS.CONDITIONAL_CONJUNCTION;
		} else if (posName.equals(POS.getName(POS.DETERMINER))) {
			return POS.DETERMINER;
		} else if (posName.equals(POS.getName(POS.NOUN))) {
			return POS.NOUN;
		} else if (posName.equals(POS.getName(POS.PREPOSITION))) {
			return POS.PREPOSITION;
		} else if (posName.equals(POS.getName(POS.VERB))) {
			return POS.VERB;
		} else {
			return null;
		}
	}

	public TermSenseDTO[] getTermSenses(String term, String termType) {
		if (remoteInstance == null) {
			return new TermSenseDTO[0];
		}
		List<TermSenseDTO> result = new ArrayList<TermSenseDTO>();
		if (term == null) {
			return result.toArray(new TermSenseDTO[result.size()]);
		}
		try {
			WordInfo wi = remoteInstance.lookupWord(term);

			List<SynonymInfo> siList = wi.getSynonymInfos();
			for (SynonymInfo si : siList) {
				if (termType == null) {
					TermSenseDTO ts = new TermSenseDTOImpl();
					ts.setSense(si.getGloss());
					ts.setType(si.getPos().name());
					ts.setUid(si.getUid());
					ts.setLemma(si.getLemma());
					ts.setWordNetEntry(si.isWordNetEntry());
					findSynonyms(ts);
					result.add(ts);
				} else if (termType.equals(Constants.TERM_NOUN)) {
					if (si.getPos().equals(POS.NOUN)) {
						TermSenseDTO ts = new TermSenseDTOImpl();
						ts.setSense(si.getGloss());
						ts.setType(si.getPos().name());
						ts.setUid(si.getUid());
						ts.setLemma(si.getLemma());
						ts.setWordNetEntry(si.isWordNetEntry());
						result.add(ts);
					}
				} else if (termType.equals(Constants.TERM_VERB)) {
					if (si.getPos().equals(POS.VERB)) {
						TermSenseDTO ts = new TermSenseDTOImpl();
						ts.setSense(si.getGloss());
						ts.setType(si.getPos().name());
						ts.setUid(si.getUid());
						ts.setLemma(si.getLemma());
						ts.setWordNetEntry(si.isWordNetEntry());
						result.add(ts);
					}
				} else if (termType.equals(Constants.TERM_MODIFIER)) {
					TermSenseDTO ts = new TermSenseDTOImpl();
					ts.setSense(si.getGloss());
					ts.setType(si.getPos().name());
					ts.setUid(si.getUid());
					ts.setLemma(si.getLemma());
					ts.setWordNetEntry(si.isWordNetEntry());
					result.add(ts);
				} else if (termType.equals(Constants.TERM_DETERMINER)) {
					TermSenseDTO ts = new TermSenseDTOImpl();
					ts.setSense(si.getGloss());
					ts.setType(si.getPos().name());
					ts.setUid(si.getUid());
					ts.setLemma(si.getLemma());
					ts.setWordNetEntry(si.isWordNetEntry());
					result.add(ts);
				} else if (termType.equals(Constants.TERM_PREPOSITION)) {
					TermSenseDTO ts = new TermSenseDTOImpl();
					ts.setSense(si.getGloss());
					ts.setType(si.getPos().name());
					ts.setUid(si.getUid());
					ts.setLemma(si.getLemma());
					ts.setWordNetEntry(si.isWordNetEntry());
					result.add(ts);
				}
			}

		} catch (JGWNLException e) {
			Activator
					.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),
					IStatus.ERROR);
			setConnected(false);
		}
		TermSenseDTO[] senses = result.toArray(new TermSenseDTO[result.size()]);
		return senses;
	}

	private void findSynonyms(TermSenseDTO sense) {
		try {
			List<SynonymInfo> synonyms = remoteInstance.getSynonymInfo(
					sense.getUid()).getParentSynsetInfo().getSynonymInfos();
			List<TermSenseDTO> syList = new ArrayList<TermSenseDTO>();
			for (SynonymInfo si : synonyms) {
				if (si.getUid() != sense.getUid()) {
					TermSenseDTO s = getTermSenseDTO(si.getUid());
					if (s == null) {
						break;
					}
					s.setParent(sense);
					syList.add(s);
				}
			}
			sense.setSynonyms(syList);
		} catch (JGWNLException e) {
			Activator
					.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),
					IStatus.ERROR);
		}
	}

	public TermSenseDTO getTermSenseDTO(long id) {
		if (remoteInstance == null) {
			return null;
		}
		SynonymInfo si;
		TermSenseDTO ts = null;
		if (id != 0) {
			try {
				si = remoteInstance.getSynonymInfo(id);

				if (si != null) {
					ts = new TermSenseDTOImpl();
					ts.setSense(si.getGloss());
					ts.setType(si.getPos().name());
					ts.setUid(si.getUid());
					ts.setLemma(si.getLemma());
					ts.setWordNetEntry(si.isWordNetEntry());
				}
			} catch (JGWNLException e) {
				Activator.log("Terminology error: " + e.getMessage(),
						IStatus.ERROR);
			} catch (RemoteException e) {
				Activator.log("Terminology server error: " + e.getMessage(),
						IStatus.ERROR);
				setConnected(false);
			}
		}
		return ts;
	}


	public boolean isConnected() {
		if (remoteInstance == null) {
			setConnected(false);
			return false;
		}
		try {
			remoteInstance.isSaving();
		} catch (RemoteException e) {
			setConnected(false);
			return false;
		}
		setConnected(true);
		return true;
	}

	private void connectionFailedMB() {
		MessageBox warnMB = new MessageBox(SCProjectHelper.getShell(),
				SWT.ICON_WARNING | SWT.OK);

		warnMB.setMessage("Connecting to the Terminology server lost. Some of application funtions may be unavailable. "
						+ "\nPlease check your network connection and/or use the "
						+ "\"ReDSeeDS -> Configure Terminology server connection\" "
						+ "\nmenu option and specify a valid terminology server.");
		warnMB.setText("Terminology connection problem");
		warnMB.open();
	}

	private void connectionSuccessfulMB() {
		MessageBox messageBox = new MessageBox(SCProjectHelper.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);

		messageBox
				.setText("Re-connection to the Terminology server successful!");
		messageBox
				.setMessage("Successfully re-connected to a Terminology server.");

		messageBox.open();
	}

	private void setConnected(boolean connected) {
		if (connected == false && this.connected == true) {// lost connection
			if (!connect(Constants.getJGWNLAddress())) {// try to reconnect
				connectionFailedMB();
			}
		}
		if (connected == false && this.connected == false) {// no connection
			if (connect(Constants.getJGWNLAddress())) {// try to reconnect
				connectionSuccessfulMB();
			}
		}
		this.connected = connected;
	}

	public double similarity(long synonymUid1, long synonymUid2)
			throws RemoteException, JGWNLException {
		try {
			return remoteInstance.similarity(synonymUid1, synonymUid2);
		} catch (JGWNLException ex) {
			//System.out.println("Exception occured while comparing synonyms with uids " + synonymUid1 + " and " + synonymUid2);
			throw ex;
		}
	}

	public WordInfo lookupWord(String word) throws RemoteException,
			JGWNLException {
		return remoteInstance.lookupWord(word);
	}

	/**
	 * Returns true if term is isolated (the synonym has no lexical
	 * relationships and the corresponding synset has no semantic relationships)
	 * 
	 * @return
	 */
	public boolean isTermIsolated(long synonymUID) {
		if (remoteInstance == null) {
			getInstance();
		}
		try {
			return remoteInstance.isIsolated(synonymUID);
		} catch (JGWNLException e) {
			Activator.log("Terminology error: " + e.getMessage(), IStatus.ERROR);
			return false;
		} catch (RemoteException e) {
			Activator.log("Terminology server error: " + e.getMessage(),  IStatus.ERROR);
			setConnected(false);
			return false;
		}
	}
	
	public String getNounBaseForm(String name){
		TermSenseDTO[] senses = getTermSenses(name.toLowerCase(), Constants.TERM_NOUN);
		if (senses.length > 0){
			return senses[0].getLemma();
		} else
			return name.toLowerCase().trim().replaceAll("[ ]+"," ");
	}
	
	public String getVerbBaseForm(String name){
		TermSenseDTO[] senses = getTermSenses(name.toLowerCase(), Constants.TERM_VERB);
		if (senses.length > 0){
			return senses[0].getLemma();
		} else
			return name.toLowerCase().trim().replaceAll("[ ]+"," ");
	}
	
	public String getPrepositionBaseForm(String name){
		TermSenseDTO[] senses = getTermSenses(name.toLowerCase(), Constants.TERM_PREPOSITION);
		if (senses.length > 0){
			return senses[0].getLemma();
		} else
			return name.toLowerCase().trim().replaceAll("[ ]+"," ");
	}
	
}
