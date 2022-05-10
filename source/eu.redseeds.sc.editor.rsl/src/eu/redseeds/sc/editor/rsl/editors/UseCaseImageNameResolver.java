package eu.redseeds.sc.editor.rsl.editors;

import eu.redseeds.common.diagram.image.IImageNameResolver;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class UseCaseImageNameResolver {

	public static IImageNameResolver getInstanceImageNameResolver(UseCaseDTO useCaseDTO) {
		return new UseCaseImageDescriptionForUseCaseDTO(useCaseDTO);
	}

	private static class UseCaseImageDescriptionForUseCaseDTO implements IImageNameResolver {
		private final UseCaseDTO useCaseDTO;

		public UseCaseImageDescriptionForUseCaseDTO(UseCaseDTO useCaseDTO) {
			super();
			this.useCaseDTO = useCaseDTO;
			valid();
		}

		private void valid() {
			if (useCaseDTO == null) {
				throw new IllegalStateException("constrainedLanguageScenarioDTO is null!");
			}
		}

		@Override
		public String getImageName() {
			return useCaseDTO.getName() + IMG_EXTENSION;
		}

	}
}
