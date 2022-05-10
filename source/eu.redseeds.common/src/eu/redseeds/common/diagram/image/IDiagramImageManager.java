package eu.redseeds.common.diagram.image;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.ImageData;

public interface IDiagramImageManager {
	public ImageData findImage(IImageNameResolver imageToFindDescription,IProject project);
	public String getDiagramImageFolderAbsolutePath();
}
