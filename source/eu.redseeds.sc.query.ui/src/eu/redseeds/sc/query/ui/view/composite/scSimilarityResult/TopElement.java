/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;
/**
 * This are the elements that are visibility under SCProject in tree with scSimilarityResult
 */
enum TopElement {
	REQS_PACKS {
		@Override
		public String getName() {
			return "Requirements packages";
		}
	},
	DOMAIN {
		@Override
		public String getName() {
			return "Domain";
		}
	};
	public abstract String getName();
}