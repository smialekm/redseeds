package eu.remics.navigator.tree;

public class SelectedTreeItem {
	
	/*private static XTopPackage selected;
	private static XTopPackage root;*/

	public static void setSelectedTreeObject(Object o) {
		/*if(((XTopPackage)o).getName().equals("")){
			root = (XTopPackage) o;
		}
		else{
			selected = (XTopPackage) o;
		}*/
	}

	public static void/*XTopPackage*/ getSelectedTreeObject(){
		/*if(selected == null)
			return root;
		return selected;*/
	}
	
	public static void/*XTopPackage*/ getTreeRoot(){
		//return (XTopPackage) root;
	}
}
