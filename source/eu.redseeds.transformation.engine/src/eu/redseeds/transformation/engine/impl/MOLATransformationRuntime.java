package eu.redseeds.transformation.engine.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class MOLATransformationRuntime {
	public String jarFile;
	public String mainClassName;
	public Class<?> mainClass;
	public Constructor<?> ctr;
	public Method molaProcedure;
}
