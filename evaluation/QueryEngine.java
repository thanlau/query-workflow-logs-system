package evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.incident.*;
import model.incidentree.ActiNode;
import model.incidentree.IncidentTreeNode;
import model.incidentree.IncidentTreeNode.NodeType;
import model.log.Log;

public class QueryEngine {
	public static QueryEngine queryEngine = new QueryEngine();
	public HashMap<String, Operator> operators;
	public HashSet<OperatorRule> rules;
	public Log log;
	
	public QueryEngine(){
		addOperators();
		addRules();
	}

	private void addRules() {
		//System.out.println("QueryEngin Init");
		rules = new HashSet<OperatorRule>();
		rules.add(new AssociativeRule());
		rules.add(new ConditionRule());
		rules.add(new DistributiveRule());
		rules.add(new CommutativeRule());
	}

	private void addOperators() {
		operators = new HashMap<String, Operator>();
		operators.put(".", new ConsOperator());
		operators.put(":", new SequOperator());
		operators.put("+", new ParaOperator());
		operators.put("|", new ExclOperator());
		
	}
	
	public boolean isOperator(String string){
		if(operators.containsKey(string))
			return true;
		return false;
	}

	public List<Long> query(Incident incident) {
//		System.out.println("query");

		if(log == null){
			System.out.println("No log.");
			return new ArrayList<Long>();
		}
//		System.out.println(incident.tree.toString());
		Thread t = new Thread(incident.tree.root);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Evaluate Output: "+ incident.tree.root.occs);
		return incident.tree.getWids();
	}
	
	public List<Occurrence> queryOcc(Incident incident, Log log) {
		if(log == null)
			return new ArrayList<Occurrence>();
		this.log = log;
		Thread t = new Thread(incident.tree.root);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return incident.tree.getOcc();
	}
}
