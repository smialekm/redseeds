package eu.redseeds.ea.converter.impl;

import org.sparx.Diagram;

import eu.redseeds.common.diagram.image.IImageNameResolver;

public class DiagramImageNameResolver {

	public static IImageNameResolver getInstanceImageNameResolver(org.sparx.Diagram dgrm) {
		return new NameResolverForDiagram(dgrm);
	}

	private static class NameResolverForDiagram implements IImageNameResolver {

		private final org.sparx.Diagram dgrm;

		public NameResolverForDiagram(Diagram dgrm) {
			super();
			this.dgrm = dgrm;
			valid();
		}

		private void valid() {
			if (dgrm == null) {
				throw new IllegalStateException("org.sparx.Diagram is null!");
			}
		}

		@Override
		public String getImageName() {
			return dgrm.GetName() + IMG_EXTENSION;
		}

	}

}
