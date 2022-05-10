package eu.redseeds.common.diagram.image;
/**
 *
 * @see eu.redseeds.ea.converter.impl.DiagramImageNameResolver
 * @see eu.redseeds.sc.editor.rsl.editors.UseCaseImageNameResolver
 */
public interface IImageNameResolver {
	public static final String IMG_EXTENSION = ".bmp";
	public String getImageName();
}
