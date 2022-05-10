package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.PhraseGrammar;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar.States;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.PhraseProvider;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

class SVOSentenceTextAssistProcessor implements IContentAssistProcessor {

	private final DomainSpecificationDTO domainSpecificationDTO;
	private final GenericSentenceContent genericSentenceContent;
	private final GenericSentenceWidgetWithTextViewer sentenceWidget;

	private interface ITermDTOWithSVOState extends TermDTO {
		public SVOSentenceGrammar.States getSVOState();
	}

	private static class TermDTOWithSVOState implements ITermDTOWithSVOState, Comparable<TermDTOWithSVOState> {
		private final TermDTO termDTO;
		private final SVOSentenceGrammar.States states;

		public TermDTOWithSVOState(TermDTO termDTO, SVOSentenceGrammar.States states) {
			super();
			this.termDTO = termDTO;
			this.states = states;
		}

		@Override
		public void delete() {
			termDTO.delete();
		}

		@Override
		public String getName() {
			return termDTO.getName();
		}

		@Override
		public long getSynonymUid() {
			return termDTO.getSynonymUid();
		}

		@Override
		public void setName(String name) {
			termDTO.setName(name);
		}

		@Override
		public void setSynonymUid(long uid) {
			termDTO.setSynonymUid(uid);
		}

		@Override
		public States getSVOState() {
			return states;
		}

		public int compareTo(TermDTOWithSVOState o) {
			return termDTO.getName().compareTo(o.getName());
		}

		@Override
		public boolean equals(Object obj) {
			return termDTO.equals(obj);
		}

		@Override
		public int hashCode() {
			return termDTO.hashCode();
		}

		@Override
		public boolean autoAssignSense() {
			return termDTO.autoAssignSense();
		}

		@Override
		public void autoAddAndAssignSense() {
			termDTO.autoAddAndAssignSense();
		}

//		@Override
//		public boolean isClipboardMember() {
//			return termDTO.isClipboardMember();
//		}
//
//		@Override
//		public void setClipboardMember(boolean member) {
//			termDTO.setClipboardMember(member);
//
//		}
		
		@Override
		public boolean isConsistent() {
			return termDTO.isConsistent();
		}

		@Override
		public TermDTO getTerm() {
			return termDTO;
		}
	
	}

	private interface ISVOStateAware {
		public SVOSentenceGrammar.States getSVOState();
	}
	private static class CompletionProposalMark implements ICompletionProposal,ISVOStateAware{
		private final ICompletionProposal completionProposal;
		private final GenericSentenceWidgetWithTextViewer sentenceWidget;
		private final SVOSentenceGrammar.States states;

		public CompletionProposalMark(ICompletionProposal completionProposal,GenericSentenceWidgetWithTextViewer sentenceWidget,SVOSentenceGrammar.States states) {
			super();
			this.completionProposal = completionProposal;
			this.sentenceWidget=sentenceWidget;
			this.states=states;
		}

		public void apply(IDocument document) {
			completionProposal.apply(document);
			markedCompletionProposal();
		}

		public String getAdditionalProposalInfo() {
			return completionProposal.getAdditionalProposalInfo();
		}

		public IContextInformation getContextInformation() {
			return completionProposal.getContextInformation();
		}

		public String getDisplayString() {
			return completionProposal.getDisplayString();
		}

		public Image getImage() {
			return completionProposal.getImage();
		}

		public Point getSelection(IDocument document) {
			return completionProposal.getSelection(document);
		}
		private void markedCompletionProposal(){
			sentenceWidget.markedCompletionProposal(this,states);
		}

		@Override
		public States getSVOState() {
			return states;
		}


	}

	private Map<SVOSentenceGrammar.States, PhraseGrammar.States> svoSentenceGrammarToPhraseGrammar = new EnumMap<SVOSentenceGrammar.States, PhraseGrammar.States>(
			SVOSentenceGrammar.States.class);
	private Map<PhraseGrammar.States, SVOSentenceGrammar.States> phraseGrammarToSvoSentenceGrammar = new EnumMap<PhraseGrammar.States, SVOSentenceGrammar.States>(
			PhraseGrammar.States.class);
	{
		// phraseGrammarToSVOSentenceGrammar.put(SVOSentenceGrammar.States.Determiner1,SVOSentenceGrammar.States.Determiner1);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Determiner2, PhraseGrammar.States.Determiner2);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Determiner3, PhraseGrammar.States.Determiner3);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.End, PhraseGrammar.States.End);
		// phraseGrammarToSVOSentenceGrammar.put(SVOSentenceGrammar.States.Modifier1,SVOSentenceGrammar.States.Modifier1);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Modifier2, PhraseGrammar.States.Modifier2);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Modifier3, PhraseGrammar.States.Modifier3);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Noun1, PhraseGrammar.States.Noun1);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Noun2, PhraseGrammar.States.Noun2);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Noun3, PhraseGrammar.States.Noun3);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Preposition, PhraseGrammar.States.Preposition);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Start, PhraseGrammar.States.Start);
		svoSentenceGrammarToPhraseGrammar.put(SVOSentenceGrammar.States.Verb, PhraseGrammar.States.Verb);

		// invers
		for (Entry<SVOSentenceGrammar.States, PhraseGrammar.States> svoPhraseEntry : svoSentenceGrammarToPhraseGrammar
				.entrySet()) {
			phraseGrammarToSvoSentenceGrammar.put(svoPhraseEntry.getValue(), svoPhraseEntry.getKey());
		}
	}

	public SVOSentenceTextAssistProcessor(DomainSpecificationDTO domainSpecificationDTO,
			GenericSentenceWidgetWithTextViewer sentenceWidget) {
		super();
		this.domainSpecificationDTO = domainSpecificationDTO;
		this.genericSentenceContent = sentenceWidget.getMarker();
		this.sentenceWidget=sentenceWidget;
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {

		
		int lastMarkPosition=genericSentenceContent.lastMarkPosition;

		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();

		Set<ITermDTOWithSVOState> allowedTerms = new TreeSet<ITermDTOWithSVOState>();

		String currWord = "";
		int currOffset = offset - 1;
		char currChar;
		try {
			while (currOffset >= lastMarkPosition ) {
				currChar = viewer.getDocument().getChar(currOffset);
				currWord = currChar + currWord;
				currOffset--;
			}

		} catch (BadLocationException e) {
			Activator.log(e.getMessage(), IStatus.WARNING);
		}
		String trimCurrWord=currWord.trim();

		for (Map.Entry<Object, String> stateEntry : genericSentenceContent.getNextSates().entrySet()) {
			Object state = stateEntry.getKey();
			if (state != null) {
				PhraseGrammar.States convertState = convertState(state);
				// if state is Noun1 (first sentence) then we take only actore
				// and system
				if (convertState != PhraseGrammar.States.Noun1) {
					allowedTerms.addAll(getNotions(convertState, trimCurrWord));
				}
				allowedTerms.addAll(getActors(convertState, trimCurrWord));
				allowedTerms.addAll(getSystemElements(convertState, trimCurrWord));
			}

		}
		for (ITermDTOWithSVOState completionProposalText : allowedTerms) {

			String compleTxt = completionProposalText.getName();

			CompletionProposal completionProposal = new CompletionProposal(compleTxt,
					lastMarkPosition, currWord.length(),compleTxt.length());


			result.add(new CompletionProposalMark(completionProposal,sentenceWidget,completionProposalText.getSVOState()));
		}

		return result.toArray(new ICompletionProposal[0]);
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	private PhraseGrammar.States convertState(Object state) {
		if (state instanceof SVOSentenceGrammar.States) {
			SVOSentenceGrammar.States svoState = (SVOSentenceGrammar.States) state;
			return findPhraseGrammarStates(svoState);
		}
		if (state instanceof PhraseGrammar.States) {
			PhraseGrammar.States phraseState = (PhraseGrammar.States) state;
			return phraseState;
		}
		return null;
	}

	private PhraseGrammar.States findPhraseGrammarStates(SVOSentenceGrammar.States svoState) {
		return svoSentenceGrammarToPhraseGrammar.get(svoState);
	}

	private List<ITermDTOWithSVOState> getActors(PhraseGrammar.States state, String startWith) {
		List<ITermDTOWithSVOState> result = new ArrayList<ITermDTOWithSVOState>();

		List<ActorsPackageDTO> actorsPackages = domainSpecificationDTO.getActorsPackageDTOList();
		for (ActorsPackageDTO actorsPackageDTO : actorsPackages) {
			result.addAll(getActorsFromActorsPackage(actorsPackageDTO, state, startWith));
		}
		return result;
	}

	private List<ITermDTOWithSVOState> getActorsFromActorsPackage(ActorsPackageDTO actorsPackageDTO,
			PhraseGrammar.States state, String startWith) {
		List<ITermDTOWithSVOState> result = new ArrayList<ITermDTOWithSVOState>();

		List<ActorDTO> actorDTOList = actorsPackageDTO.getActorDTOList();
		for (ActorDTO actorDTO : actorDTOList) {
			ITermDTOWithSVOState stateTerm = getTermForState(actorDTO.getNounPhrase(), state, startWith);
			if (stateTerm != null) {
				result.add(stateTerm);
			}
		}

		List<ActorsPackageDTO> actorsPackageDTOList = actorsPackageDTO.getActorsPackageDTOList();
		if (actorsPackageDTOList != null) {
			for (ActorsPackageDTO actorsPackageDTO2 : actorsPackageDTOList) {
				result.addAll(getActorsFromActorsPackage(actorsPackageDTO2, state, startWith));
			}
		}

		return result;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<ITermDTOWithSVOState> getNotions(PhraseGrammar.States state, String startWith) {
		List<ITermDTOWithSVOState> result = new ArrayList<ITermDTOWithSVOState>();
		List<NotionsPackageDTO> notionsPackages = domainSpecificationDTO.getNotionsPackageDTOList();
		for (NotionsPackageDTO notionsPackage : notionsPackages) {
			result.addAll(getNotionsFromNotionsPackage(notionsPackage, state, startWith));
		}
		return result;
	}

	private List<ITermDTOWithSVOState> getNotionsFromNotionsPackage(NotionsPackageDTO notionPackage,
			PhraseGrammar.States state, String startWith) {
		List<ITermDTOWithSVOState> result = new ArrayList<ITermDTOWithSVOState>();

		List<NotionDTO> notionDTOList = notionPackage.getNotionDTOList();
		for (NotionDTO notionDTO : notionDTOList) {
			List<DomainStatementDTO> domainStatementDTOList = notionDTO.getDomainStatementDTOList();
			for (DomainStatementDTO domainStatementDTO : domainStatementDTOList) {
				ITermDTOWithSVOState stateTerm = getTermForState(domainStatementDTO.getPhraseDTO(), state, startWith);
				if (stateTerm != null) {
					result.add(stateTerm);
				}
			}
		}

		List<NotionsPackageDTO> notionsPackageDTOList = notionPackage.getNotionsPackageDTOList();
		if (notionsPackageDTOList != null) {
			for (NotionsPackageDTO notionsPackageDTO : notionsPackageDTOList) {
				result.addAll(getNotionsFromNotionsPackage(notionsPackageDTO, state, startWith));
			}
		}

		return result;
	}

	private List<ITermDTOWithSVOState> getSystemElements(PhraseGrammar.States state, String startWith) {
		List<ITermDTOWithSVOState> result = new ArrayList<ITermDTOWithSVOState>();

		List<SystemElementsPackageDTO> systemElementsPackage = domainSpecificationDTO.getSystemElementsPackageDTOList();
		for (SystemElementsPackageDTO systemElementsPackageDTO : systemElementsPackage) {
			result.addAll(getSystemElementsFromSystemElementsPackage(systemElementsPackageDTO, state, startWith));
		}
		return result;
	}

	private List<ITermDTOWithSVOState> getSystemElementsFromSystemElementsPackage(
			SystemElementsPackageDTO systemElementsPackageDTO, PhraseGrammar.States state, String startWith) {
		List<ITermDTOWithSVOState> result = new ArrayList<ITermDTOWithSVOState>();
		List<SystemElementDTO> systemElementDTOList = systemElementsPackageDTO.getSystemElementDTOList();
		for (SystemElementDTO systemElementDTO : systemElementDTOList) {
			ITermDTOWithSVOState stateTerm = getTermForState(systemElementDTO.getNounPhrase(), state, startWith);
			if (stateTerm != null) {
				result.add(stateTerm);
			}
		}

		List<SystemElementsPackageDTO> systemElementsPackageDTOList = systemElementsPackageDTO
				.getSystemElementsPackageDTOList();
		if (systemElementsPackageDTOList != null) {
			for (SystemElementsPackageDTO systemElementsPackageDTO2 : systemElementsPackageDTOList) {
				result.addAll(getSystemElementsFromSystemElementsPackage(systemElementsPackageDTO2, state, startWith));
			}
		}

		return result;
	}

	private ITermDTOWithSVOState getTermForState(PhraseDTO phrase, PhraseGrammar.States state, String startWith) {
		PhraseProvider phraseProvider = new PhraseProvider();
		phraseProvider.setSentence(phrase);
		TermDTO stateTerm = phraseProvider.getStateTerm(state);
		if (stateTerm != null) {
			if (stateTerm.getName().startsWith(startWith)) {
				ITermDTOWithSVOState termDTOWithSVOState = new TermDTOWithSVOState(stateTerm,
						phraseGrammarToSvoSentenceGrammar.get(state));
				return termDTOWithSVOState;
			}
		}
		return null;
	}
}
