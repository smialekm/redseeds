package eu.redseeds.common.diagram.image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.graphics.ImageData;

import eu.redseeds.common.Activator;
import eu.redseeds.common.SCProjectHelper;

/**
 * Manager for finding diagrams images.
 */
public class DiagramImageManager implements IDiagramImageManager {

	/** name of folder with diagrams images */
	private final static String DIAGRAM_IMAGE_FOLDER = ".UseCaseGraficView";

	private static IDiagramImageManager defaultInstance = new DiagramImageManager();

	private DiagramImageManager() {
	}

	/**
	 * Get instance of this class
	 *
	 * @return instance of this class
	 */
	public static IDiagramImageManager getInstance() {
		return defaultInstance;
	}

	/**
	 * Find image base on description
	 *
	 * @param imageToFindDescription
	 *            description of image to find
	 */
	@Override
	public ImageData findImage(final IImageNameResolver imageToFindDescription,
			IProject project) {
		// Activator.log("Looking for image data for: " +
		// imageToFindDescription.getImageName(), IStatus.INFO);
		return findImageInWorkspace(imageToFindDescription, project);
	}

	private ImageData findImageInWorkspace(
			final IImageNameResolver imageToFindDescription, IProject project) {
		IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
				.findFilesForLocationURI(
						project.getFolder(DIAGRAM_IMAGE_FOLDER).getFile(
								imageToFindDescription.getImageName())
								.getLocationURI());
		IFile file = null;
		if (files != null && files.length > 0) {
			file = files[0];
		}
		ImageData result = null;
		if (file != null) {
			try {
				InputStream contents = null;
				try {
					contents = file.getContents();
					result = new ImageData(contents);
				} catch (CoreException e) {
					// Activator.log(e.getMessage(), IStatus.WARNING);
				} finally {
					if (contents != null) {
						contents.close();
					}
				}
			} catch (IOException e) {
				// Activator.log(e.getMessage(), IStatus.WARNING);
			}
		}
		return result;
	}

	private static URI getDiagramImageFolderLoactionURI() {
		IProject project = getCurrentProject();
		URI uri = project.getFolder(DIAGRAM_IMAGE_FOLDER).getLocationURI();
		return uri;
	}

	private static IProject getCurrentProject() {
		return SCProjectHelper.getActiveProject();
	}

	private static void createDirectoryIfNotExist(File directory) {
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				Activator.log(
						"Couldn't create folder for diagram graphics. Folder name: "
								+ directory.getAbsolutePath(), IStatus.ERROR);
			}

		}
	}

	/**
	 * @return absolute path to folder with diagram images in active project
	 */
	@Override
	public String getDiagramImageFolderAbsolutePath() {
		String result = null;
		URI uri = getDiagramImageFolderLoactionURI();
		try {
			IFileStore fileStore = EFS.getStore(uri);
			File localDir = fileStore.toLocalFile(EFS.NONE, null);
			createDirectoryIfNotExist(localDir);
			result = localDir.getAbsolutePath();
		} catch (CoreException e) {
			Activator.log(e.getMessage(), IStatus.ERROR);
		}
		return result + File.separator;
	}

	/**
	 * @return name of folder with diagrams images
	 */
	public static String getDiagramImageFolder() {
		return DIAGRAM_IMAGE_FOLDER;
	}

	/**
	 * @return name of folder with diagrams images with system-dependent default
	 *         name-separator character before first and after lest character
	 */
	public static String getDiagramImageFolderWithOSSeparator() {
		return File.separator + getDiagramImageFolder() + File.separator;
	}

}
