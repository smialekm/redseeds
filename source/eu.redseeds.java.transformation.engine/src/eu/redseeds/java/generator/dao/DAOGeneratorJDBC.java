package eu.redseeds.java.generator.dao;

import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.interfaces.InterfaceRealization;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class DAOGeneratorJDBC {
	private SCFacade scFacade;
	private GeneratorUtils genUtils;

	public DAOGeneratorJDBC() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
	}

	public void genDAOs() {
		genDAOUtilPackage();
		genDAOImplClasses();
		genDAOStandardClasses();
	}
	
	private void genDAOUtilPackage() {
		PackageDTO daoPack = scFacade.getPackage("DAO");
		
		PackageDTO utilPack = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		utilPack.setName("util");
		ClassDTO connectionFactoryClass = (ClassDTO) scFacade.getFacade().createClass();
		connectionFactoryClass.setName("ConnectionFactory");
		ClassDTO daoManagerClass = (ClassDTO) scFacade.getFacade().createClass();
		daoManagerClass.setName("DAOManager");
		ClassDTO daoUtilsClass = (ClassDTO) scFacade.getFacade().createClass();
		daoUtilsClass.setName("DAOUtils");
		InterfaceDTO daoCommandInterface = (InterfaceDTO) scFacade.getFacade().createInterface();
		daoCommandInterface.setName("DAOCommand");
		ClassDTO sqlBuilderClass = (ClassDTO) scFacade.getFacade().createClass();
		sqlBuilderClass.setName("SQLBuilder");
		
		utilPack.addClass(connectionFactoryClass);
		utilPack.addClass(daoManagerClass);
		utilPack.addClass(daoUtilsClass);
		utilPack.addInterface(daoCommandInterface);
		utilPack.addClass(sqlBuilderClass);
		daoPack.addPackage(utilPack);
	}
	
	private void genDAOImplClasses() {
		PackageDTO daoPack = scFacade.getPackage("DAO");
		Interface iObjectDAO = scFacade.getFacade().createInterface();
		iObjectDAO.setName("IObjectDAO");
		daoPack.addInterface((InterfaceDTO)iObjectDAO);
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		Interface list = (Interface) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		scFacade.createDependency(iObjectDAO, new NamedElement[]{list});
		
		genIObjectDAOOperations(iObjectDAO);
		
		PackageDTO dtoPack = scFacade.getPackage("DTO");
		for(ClassDTO dtoClass : dtoPack.getClassDTOList()){
			Notion notion = scFacade.getLinkedNotion(dtoClass, "DTO");
			String notionNameCamel = genUtils.toCamelCase(((NotionDTO)notion).getName());
			ClassDTO daoClass = (ClassDTO) scFacade.getFacade().createClass();
			daoClass.setName(notionNameCamel + "DAO");
			scFacade.createMappingBetween(notion, (Class)daoClass, "DAO");
			daoPack.addClass(daoClass);
			
			InterfaceRealization interfaceRealization = scFacade.getFacade().createInterfaceRealization();
			interfaceRealization.addClient((Class)daoClass);
			interfaceRealization.addSupplier(iObjectDAO);
			
			genDAOImplImports(daoClass);
			
			PackageDTO sqlPack = scFacade.getPackage("sql");
			Interface sqlConnection = (Interface) scFacade.getElementFromPackage(sqlPack, "Connection", Interface.class);
			PrimitiveType constrType = scFacade.getFacade().createPrimitiveType();
			constrType.setName("");
			Property connection = scFacade.getFacade().createProperty();
			connection.setName("connection");
			connection.setVisibility(VisibilityKind.PRIVATE);
			connection.addType(sqlConnection);
			Operation constr = scFacade.createOperation(daoClass.getName(), VisibilityKind.PUBLIC,
														new Object[][]{ {"connection", sqlConnection}, {null, ""} });
			((Class)daoClass).addOwnedAttribute(connection);
			daoClass.addOperation((OperationDTO) constr);
			
			genUtils.genCode(constr, "this.connection = connection;");
			
			genDAOImplOperations(dtoClass, daoClass);
		}
	}
	
	private void genDAOImplImports(ClassDTO daoClass) {
		//PackageDTO reflectPack = scFacade.getPackage("reflect");
		//Class constructor = (Class) scFacade.getElementFromPackage(reflectPack, "Constructor", Class.class);
		//Class field = (Class) scFacade.getElementFromPackage(reflectPack, "Field", Class.class);
		//Class exception = (Class) scFacade.getElementFromPackage(reflectPack, "InvocationTargetException", Class.class);
		
		PackageDTO sqlPack = scFacade.getPackage("sql");
		Interface connection = (Interface) scFacade.getElementFromPackage(sqlPack, "Connection", Interface.class);
		Class resultSet = (Class) scFacade.getElementFromPackage(sqlPack, "ResultSet", Class.class);
		Class sqlException = (Class) scFacade.getElementFromPackage(sqlPack, "SQLException", Class.class);
		Interface statement = (Interface) scFacade.getElementFromPackage(sqlPack, "Statement", Interface.class);
		
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		Interface list = (Interface) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		Class arraylist = (Class) scFacade.getElementFromPackage(utilPack, "ArrayList", Class.class);
		//Class date = (Class) scFacade.getElementFromPackage(utilPack, "Date", Class.class);
		
		PackageDTO daoPack = scFacade.getPackage("DAO");
		PackageDTO daoUtilPack = (PackageDTO) scFacade.getElementFromPackage(daoPack, "util", Package.class);
		Class daoUtils = (Class) scFacade.getElementFromPackage(daoUtilPack, "DAOUtils", Class.class);
		Class sqlBuilder = (Class) scFacade.getElementFromPackage(daoUtilPack, "SQLBuilder", Class.class);
		
		NamedElement[] suppliers = new NamedElement[]{connection, resultSet, sqlException, statement, 
														list, arraylist, daoUtils, sqlBuilder};
		scFacade.createDependency((Class)daoClass, suppliers);
	}

	private void genIObjectDAOOperations(Interface objectDAO) {
		Operation read = scFacade.createOperation("read", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "List<?>"}, {"o", "Object"} });
		Operation read2 = scFacade.createOperation("read", VisibilityKind.PUBLIC, 
				new Object[][]{ {"id", "int"}, {null, "Object"} });
		Operation read3 = scFacade.createOperation("read", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "List<?>"}, {"objects", "List<?>"} });
		Operation read4 = scFacade.createOperation("readAll", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "List<?>"} });
		
		Operation insert = scFacade.createOperation("insert", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"o", "Object"} });
		Operation insertList = scFacade.createOperation("insertList", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int[]"}, {"objects", "List<?>"} });
		
		Operation update = scFacade.createOperation("update", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"o", "Object"} });
		Operation updateList = scFacade.createOperation("updateList", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int[]"}, {"objects", "List<?>"} });
		
		Operation delete = scFacade.createOperation("delete", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"o", "Object"} });
		Operation delete2 = scFacade.createOperation("delete", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"id", "int"} });
		Operation deleteList = scFacade.createOperation("deleteList", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int[]"}, {"objects", "List<?>"} });
		
		objectDAO.addOwnedOperation(read);
		objectDAO.addOwnedOperation(read2);
		objectDAO.addOwnedOperation(read3);
		objectDAO.addOwnedOperation(read4);
		objectDAO.addOwnedOperation(insert);
		objectDAO.addOwnedOperation(insertList);
		objectDAO.addOwnedOperation(update);
		objectDAO.addOwnedOperation(updateList);
		objectDAO.addOwnedOperation(delete);
		objectDAO.addOwnedOperation(delete2);
		objectDAO.addOwnedOperation(deleteList);
	}

	private void genDAOImplOperations(ClassDTO dtoClass, ClassDTO daoClass) {
		Operation read = scFacade.createOperation("read", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "List<"+ dtoClass.getName() +">"}, {"o", "Object"} });
		Operation read2 = scFacade.createOperation("read", VisibilityKind.PUBLIC, 
				new Object[][]{ {"id", "int"}, {null, dtoClass} });
		Operation read3 = scFacade.createOperation("read", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "List<"+ dtoClass.getName() +">"}, {"objects", "List<?>"} });
		Operation read4 = scFacade.createOperation("readAll", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "List<"+ dtoClass.getName() +">"} });
		
		Operation insert = scFacade.createOperation("insert", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"o", "Object"} });
		Operation insertList = scFacade.createOperation("insertList", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int[]"}, {"objects", "List<?>"} });
		
		Operation update = scFacade.createOperation("update", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"o", "Object"} });
		Operation updateList = scFacade.createOperation("updateList", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int[]"}, {"objects", "List<?>"} });
		
		Operation delete = scFacade.createOperation("delete", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"o", "Object"} });
		Operation delete2 = scFacade.createOperation("delete", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int"}, {"id", "int"} });
		Operation deleteList = scFacade.createOperation("deleteList", VisibilityKind.PUBLIC, 
				new Object[][]{ {null, "int[]"}, {"objects", "List<?>"} });
		
		Operation getRowsCount = scFacade.createOperation("getRowsCount", VisibilityKind.PRIVATE, 
				new Object[][]{ {null, "int"}, {"tableName", "String"} });
		
		genUtils.genCode(read, readByObjectImpl(dtoClass.getName()));
		genUtils.genCode(read2, readByIdImpl(dtoClass.getName()));
		genUtils.genCode(read3, readByObjectsListImpl(dtoClass.getName()));
		genUtils.genCode(read4, readAllImpl(dtoClass.getName()));
		genUtils.genCode(insert, insertImpl(dtoClass.getName()));
		genUtils.genCode(insertList, insertListImpl(dtoClass.getName()));
		genUtils.genCode(update, updateImpl(dtoClass.getName()));
		genUtils.genCode(updateList, updateListImpl(dtoClass.getName()));
		genUtils.genCode(delete, deleteByObjectImpl(dtoClass.getName()));
		genUtils.genCode(delete2, deleteByIdImpl(dtoClass.getName()));
		genUtils.genCode(deleteList, deleteListImpl(dtoClass.getName()));
		genUtils.genCode(getRowsCount, getRowsCountImpl());
		
		((Class)daoClass).addOwnedOperation(read);
		((Class)daoClass).addOwnedOperation(read2);
		((Class)daoClass).addOwnedOperation(read3);
		((Class)daoClass).addOwnedOperation(read4);
		((Class)daoClass).addOwnedOperation(insert);
		((Class)daoClass).addOwnedOperation(insertList);
		((Class)daoClass).addOwnedOperation(update);
		((Class)daoClass).addOwnedOperation(updateList);
		((Class)daoClass).addOwnedOperation(delete);
		((Class)daoClass).addOwnedOperation(delete2);
		((Class)daoClass).addOwnedOperation(deleteList);
		((Class)daoClass).addOwnedOperation(getRowsCount);
	}
	
	private String readByObjectImpl(String dto){
		String code = new StringBuilder()
		.append("List<"+dto+"> list = new ArrayList<"+dto+">();\n")
		.append("Statement stm = null;\n")
		.append("String sql = SQLBuilder.getSQLRead(o, "+dto+".class);\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	ResultSet result = stm.executeQuery(sql);\n")
		.append("	list = (List<"+dto+">) DAOUtils.mapToDTO(result, "+dto+".class);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return list;\n")
		.toString();
		return code;
	}
	
	private String readByIdImpl(String dto){
		String code = new StringBuilder()
		.append(dto+" res = new "+dto+"();\n")
		.append("String tableName = res.getClass().getSimpleName();\n")
		.append("String idName = DAOUtils.genDTOIdName(tableName);\n")
		.append("String sql = \"select * from \" + tableName + \" where \" idName + \" = \" + id + \";\";\n")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	ResultSet result = stm.executeQuery(sql);\n")
		.append("	res = ("+dto+") DAOUtils.mapToDTO(result, "+dto+".class).get(0);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String readByObjectsListImpl(String dto) {
		String code = new StringBuilder()
		.append("List<"+dto+"> res = new ArrayList<"+dto+">();\n")
		.append("List<"+dto+"> internal_res = new ArrayList<"+dto+">();\n")
		.append("for(Object obj : objects){\n")
		.append("	internal_res = read(obj);\n")
		.append("	res.addAll(internal_res);\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String readAllImpl(String dto){
		String code = new StringBuilder()
		.append("List<"+dto+"> res = new ArrayList<"+dto+">();\n")
		.append("String tableName = res.getClass().getSimpleName();\n")
		.append("Statement stm = null;\n")
		.append("String sql = \"select * from \" + tableName + \";\"\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	ResultSet result = stm.executeQuery(sql);\n")
		.append("	res = (List<"+dto+">) DAOUtils.mapToDTO(result, "+dto+".class);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String insertImpl(String dto) {
		String code = new StringBuilder()
		.append("int res = 0;\n")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	int id = getRowsCount(o.getClass().getSimpleName());\n")
		.append("	String sql = SQLBuilder.getSQLInsert(o, id, "+dto+".class);\n")
		.append("	res = stm.executeUpdate(sql);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String insertListImpl(String dto) {
		String code = new StringBuilder()
		.append("int[] res = null;\n")
		.append("Statement stm = null;\n")
		.append("int id = 0;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	if(!objects.isEmpty() && objects != null)")
		.append("		id = getRowsCount(objects.get(0).getClass().getSimpleName());\n")
		.append("	for(Object obj : objects){\n")
		.append("		stm.addBatch(SQLBuilder.getSQLInsert(obj, id, "+dto+".class));\n")
		.append(" 		id++;\n")
		.append(" 	}\n")
		.append(" 	res = stm.executeBatch();\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String updateImpl(String dto) {
		String code = new StringBuilder()
		.append("int res = 0;\n")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	int id = getRowsCount(o.getClass().getSimpleName());\n")
		.append("	String sql = SQLBuilder.getSQLUpdate(o, "+dto+".class);\n")
		.append("	res = stm.executeUpdate(sql);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String updateListImpl(String dto) {
		String code = new StringBuilder()
		.append("int[] res = null;\n")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	for(Object obj : objects){\n")
		.append("		stm.addBatch(SQLBuilder.getSQLUpdate(obj, "+dto+".class));\n")
		.append(" 	}\n")
		.append(" 	res = stm.executeBatch();\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String deleteByObjectImpl(String dto) {
		String code = new StringBuilder()
		.append("int res = 0;\n")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	String sql = SQLBuilder.getSQLDelete(o, "+dto+".class);\n")
		.append("	res = stm.executeUpdate(sql);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String deleteByIdImpl(String dto) {
		String code = new StringBuilder()
		.append("int res = 0;\n")
		.append("String tableName = "+dto+".class.getSimpleName();")
		.append("String idname = DAOUtils.genDTOIdName(tableName);")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	String sql = \"delete from \" + tableName + \" where \" + idname + \" = \" + id + \";\";\n")
		.append("	res = stm.executeUpdate(sql);\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String deleteListImpl(String dto) {
		String code = new StringBuilder()
		.append("int[] res = null;\n")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	for(Object obj : objects){\n")
		.append("		stm.addBatch(SQLBuilder.getSQLDelete(obj, "+dto+".class));\n")
		.append(" 	}\n")
		.append(" 	res = stm.executeBatch();\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return res;\n")
		.toString();
		return code;
	}
	
	private String getRowsCountImpl() {
		String code = new StringBuilder()
		.append("int rowsNo = 0;\n")
		.append("String idname = DAOUtils.genDTOIdName(tableName);")
		.append("String countRowsSQL = \"select \" + idname + \", count(*) from \" + tableName + \" group by \" + idname + \";\";")
		.append("Statement stm = null;\n")
		.append("try {\n")
		.append("	stm = this.connection.createStatement();\n")
		.append("	ResultSet result = stm.executeQuery(countRowsSQL);\n")
		.append("	if(result.last()){\n")
		.append("		rowsNo = result.getInt(1);\n")
		.append("	}\n")
		.append("	rowsNo++;\n")
		.append("} catch (SQLException e) {\n")
		.append("	e.printStackTrace();\n")
		.append("}\n")
		.append("finally{\n")
		.append("	if(stm != null){\n")
		.append("		try {\n")
		.append("			stm.close();\n")
		.append("		} catch (SQLException e) {\n")
		.append("			e.printStackTrace();\n")
		.append("		}\n")
		.append("	}\n")
		.append("}\n")
		.append("return rowsNo;\n")
		.toString();
		return code;
	}
	
	private void genDAOStandardClasses() {
		genConnectionFactoryOperations();
		genDAOManagerOperations();
		genDAOUtilsOperations();
		genIDAOCommandOperations();
		genSQLBuilderOperations();
	}
	
	private void genConnectionFactoryOperations() {
		
	}
	
	private void genDAOManagerOperations() {
	
	}
	
	private void genIDAOCommandOperations() {
	
	}
	
	private void genDAOUtilsOperations() {
		
	}
	
	private void genSQLBuilderOperations() {
		
	}
	
}
