package eu.redseeds.scl.model.impl.sclkernel;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.sclkernel.DetailedDesignModelImpl;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.uml.auxiliaryconstructs.models.Model;
import eu.redseeds.scl.uml.classes.kernel.PackagableElement;
import eu.redseeds.scl.uml.classes.kernel.Package;

public class DetailedDesignModelDTOImpl extends DetailedDesignModelImpl
		implements DetailedDesignModelDTO {

	public static String DETAILED_DESIGN_NAME = "Detailed Design";

	public DetailedDesignModelDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return DETAILED_DESIGN_NAME;
	}

	@Override
	public void addPackage(PackageDTO pack) {
		if (getUmlModelList().size() == 0) {
			Model model = ((SCLGraph) super.getGraph())
					.createUml$auxiliaryconstructs$models$Model();
			model.addPackagedElement((Package) pack);
			addUmlModel(model);
		} else {
			getUmlModelList().get(0).addPackagedElement((Package) pack);
		}
	}

	@Override
	public List<PackageDTO> getPackageDTOList() {
		List<PackageDTO> packages = new ArrayList<PackageDTO>();
		List<? extends Model> models = getUmlModelList();
		if (models != null) {
			if (models.size() > 0) {
				List<? extends PackagableElement> elems = models.get(0)
						.getPackagedElementList();
				for (int i = 0; i < elems.size(); i++) {
					if (elems.get(i) instanceof Package) {
						packages.add((PackageDTO) elems.get(i));
					}
				}
			}
		}
		return packages;
	}

}
