package eu.redseeds.sc.slicing;

public enum SliceType {
	MINIMAL_SLICE { public String toString() {return "minimal slice";} },
	MAXIMAL_SLICE { public String toString() {return "maximal slice";} } ,
	IDEAL_SLICE { public String toString() {return "ideal slice";} },
	DOMAIN_INCLUDING_SLICE { public String toString() {return "domain including slice";} },
	/* trace slice must be the last one, as it is not really a slice and thus not used for import an so on */
	TRACE_SLICE { public String toString() {return "show traces";} };
}
