package model.incident;

import java.util.*;

import evaluation.CostModel;
import model.incidentree.OpNode;
import model.log.*;

public abstract class Operator {
	public abstract Map<Integer, List<Occurrence>> 
	execute(Map<Integer, List<Occurrence>> occs, Map<Integer, List<Occurrence>> map, OpNode op);
	
	public abstract Map<Integer, List<Occurrence>> 
	execute(Map<Integer, List<Occurrence>> occs1, Map<Integer, List<Occurrence>> map1);

	public abstract long getResultSize1(long c1, long c2);
	public abstract CostModel getResultSize2(CostModel acti1, CostModel acti2);
	public abstract ProbModel estimate(ProbModel incidentHist1, ProbModel incidentHist2);
}
