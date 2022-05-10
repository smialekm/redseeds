package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.IImageKeys;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class DescriptionTextAssistProcessor implements IContentAssistProcessor {
	
	private String lastError = "";

	@Override
	public ICompletionProposal[] computeCompletionProposals(
			ITextViewer viewer, int offset) {
		BusinessLayerFacade facade = DomainElementEditorControlFactory.getFacadeForControl(viewer.getTextWidget().getParent());
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		String currWord = "";
		int currOffset = offset - 1;
		char currChar;
		try {
			while (currOffset >= 0
					&& !Character.isWhitespace(currChar = viewer.getDocument()
							.getChar(currOffset))) {
				currWord = currChar + currWord;
				currOffset--;
			}
		} catch (BadLocationException e) {
			lastError = e.getMessage();
			Activator.log(e.getMessage(), IStatus.WARNING);
		}
		for(NotionDTO notion : facade.getAllNotions()) {
			if(notion.getName().startsWith(currWord)) {
//				IContextInformation ci 
//					= new ContextInformation(notion.getName(), notion.getDescription());
				CompletionProposal cp 
					= new CompletionProposal(notion.getName(), 
							offset - currWord.length(), currWord.length(), 
							notion.getName().length(), 
							Activator.getDefault().createImageDescriptorFor(
									IImageKeys.NOTION).createImage(), 
							notion.getName(), null, notion.getDescription());
				proposals.add(cp);
			}
		}
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	@Override
	public IContextInformation[] computeContextInformation(
			ITextViewer viewer, int offset) {
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return new char[] {  };
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return new char[] {  };
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		IContextInformationValidator civ = new IContextInformationValidator() {

			@Override
			public void install(IContextInformation info, ITextViewer viewer,
					int offset) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isContextInformationValid(int offset) {
				return true;
			}
			
		};
		return civ;
	}

	@Override
	public String getErrorMessage() {
		return lastError;
	}
	
}
