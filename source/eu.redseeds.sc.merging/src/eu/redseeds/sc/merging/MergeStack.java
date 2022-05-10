package eu.redseeds.sc.merging;

import java.util.Arrays;
import java.util.Stack;

import eu.redseeds.engine.navigator.providers.AdapterFactory;
import eu.redseeds.engine.navigator.providers.IProvider;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
//import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.sclkernel.SCLElement;

/**
 * Stack of clipboard SCL elements used during merging.
 *
 */
public class MergeStack extends Stack<Object> {

	private static final long serialVersionUID = 1L;
	
	private ClipboardDTO clipboard;

	public MergeStack(ClipboardDTO clipboard) {
		super();
		this.clipboard = clipboard;
	}
	
	/**
	 * Builds the stack (based on clipboard specified in constructor)
	 * @return
	 */
	public boolean build() {
		if(clipboard == null) {
			return false;
		}
		Stack<Object> objects = new Stack<Object>();
		
		objects.add(clipboard);
		
		while(!objects.isEmpty()) {
			Object obj = objects.pop();
			IProvider provider = AdapterFactory.adapt(obj, null);
			Object[] children = provider.getChildren(obj);
			this.addAll(Arrays.asList(children));
			objects.addAll(Arrays.asList(children));
		}
		
		BusinessLayerFacade facade = SCProjectContainer.instance()
			.getSCProject(clipboard).getBusinessLayerFacade();
		
//		//workaround -- TODO
//		for(NotionDTO n : facade.getAllNotions()) {
//			this.add(n);
////			if(clipboard.isClipboardMember((SCLElement)n)) {
////				this.add(n);
////			}
//		}
		
		for(TermDTO term : facade.getAllTerms()) {
			if(clipboard.isClipboardMember((SCLElement)term)) {
				this.add(term);
			}
		}
		
		return true;
	}

}
