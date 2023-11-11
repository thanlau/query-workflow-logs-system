package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import evaluation.*;
import model.incident.*;
import model.log.*;


public class Main {
	enum Type{
		A, B
	}
	public static List<LogRecord> existsrecords;
	public static List<LogRecord> deltarecords;
	public static List<List<LogRecord>> tempRecords;
	public static int count = 0;
//	public static long t1 = 0;
//	public static long t2 = 0;
//	public static long t3 = 0;
//	public static long t4 = 0;
//	public static long t5 = 0;
	
	public static void main(String[] args){
		//1. load instance/pattern 
		String[] qs1 = {"read|check_faq"};
		List<LogRecord> existsrecords = new ArrayList<LogRecord>();
		List<LogRecord> deltarecords = new ArrayList<LogRecord>();
		Incident incident = new Incident(qs1[0]);
		System.gc();
		BufferedReader br = null;
	    try {
//	    	br = new BufferedReader(new FileReader("data/sample_1000k.txt"));
	    	br = new BufferedReader(new FileReader("data/sample_log.txt"));
	        String line = br.readLine();
			long t1 = System.currentTimeMillis();
	        while (line != null) {
	        	if(line.length() == 0){
	        		line = br.readLine();
	        		continue;
	        	}
	        	
	        	count++;
	        	LogRecord record = new LogRecord(line);
	        	if(count < 3) {
	        		deltarecords.add(record);
	        		line = br.readLine();
	        		continue;
	        	}
	

	        	deltarecords.add(record);
	        	Log log = new Log();
	        	log.records=deltarecords;
	        	QueryEngine.queryEngine.log = log;
	        	QueryEngine.queryEngine.query(incident);
//	        	List<Occurrence> res = QueryEngine.queryEngine.queryOcc(incident, log);
//	        	System.out.println(res.toString());

//	        	deltarecords= new ArrayList<LogRecord>(); 
//	        	deltarecords.add(record);
//	        	Log log = new Log();
//	        	log.records=deltarecords;
//	        	QueryEngine.queryEngine.log = log;
////	        	Incident[]res = Optimizer.generateOptimalTree(incident, 0);
////				QueryEngine.queryEngine.query(res[0]);
//				QueryEngine.queryEngine.query(incident);

	        	deltarecords= new ArrayList<LogRecord>(); 
	        	
	        	if(count%2000 == 0){
	        		System.out.println(count + " lines processed.");
	        		long t2 = System.currentTimeMillis();
	     	        System.out.println("running time: " + (t2 - t1));
	        	}
	        	
	            line = br.readLine();
	        }
	    }catch(Exception e){
	    	System.out.println(e.toString());
	    	System.out.println("load log file failed! ");
	    	e.printStackTrace();
	    }finally
		{
		    try
		    {
		        if ( br != null)
		        br.close( );
		    }
		    catch ( Exception e)
		    {
		    }
		}
	}
	
//	static String[] actis = {"read", "open_trust"};

//	public static void main(String[] args){
//		Log log = new Log();
//		log.loadFile("data/output_01.txt");
//		QueryEngine.queryEngine.log = log;
//		
//	}
	
//	public static void testRecordWithCond(Log log, int model){
//		System.out.println("Start testing single record with cond:");
//		String[] qs = {"open_trust[support_12=1]", "MAINDATA[admdate=20070104]", "INJDETS[inj_city=santa_barbara]",
//				"PROTECT[protective=none]", "TRANSFER[los=0]", "VITALS[gcs=0]", "TTDETLS[speciality=sur]",
//				"RADIOLOG[study=ct_scan]", "TOXIANAL[substance=marijuana]", "PERHIST[pregnant=y]"};
//		test(qs, model);
//	}
//	public static void testRecord(Log log, int model){
//		System.out.println("Start testing single record:");
//		String[] qs = {"read", "open", "MAINDATA", "PROTECT", "PERHIST", "MTOS", "LAB",
//				"HEMO", "INJDETS", "GENMECH", "NARRATIV"};
//		test(qs, model);
//	}
//	
	/*
	 * Notes:
	 * large test case: "EMERG.RADIOLOG" with # of records 37927 and 32669 resp.
	 */
//	public static void testConsOp(Log log, int model){
//		System.out.println("Start testing cons op:");
//		String[] qs = {"read.open", "TRANSFER.TRA", "ICU.HOSPREV", "PRECONDS.HOSPREV",
//				"MTOS.QAISSUE", "INJDETS.PROTECT", "FLDDETAI.INJDETS", "VITALS.FLDDETAI",
//				"TREATMEN.LAB", "MORTDETS.ORGANS"};
//		test(qs, model);
//	}
	
	/*
	 * op: operator
	 * numOp: # of operator
	 * log: log
	 * model: 0 -- no optimizer
	 * 		  1 -- cost model with assumption that no repeated acti
	 * 		  2 -- without the assumption
	 */
	
	//model: 1 -- cost model with assumption that no repeated acti, 2 -- withouth the assumption
//	public static void testWithOptimizer(char op, int numOp, Log log, int model){
//		System.out.println("Start testing multi cons op:");
//		int numQuery = 10;
//		String[] qs = generateIncidentwithOptimizer(op, numOp, numQuery, model);
//		test(qs, model);
//	}
//	
	
	
//	private static String[] generateIncident(char op, int numOp, int numQuery) {
//		String[] res = new String[numQuery];
//		List<String> ops = new ArrayList<String>(QueryEngine.queryEngine.operators.keySet());
//		Random rand = new Random();
//		for(int i=0; i<numQuery; i++){
//			StringBuilder sb = new StringBuilder();
//			sb.append(actis[rand.nextInt(actis.length)]);
//			for(int j=0; j<numOp; j++){
//				if(op == '*'){
//					sb.append(ops.get(rand.nextInt(ops.size())));
//				}else
//					sb.append(op);
//				sb.append(actis[rand.nextInt(actis.length)]);
//			}
//			res[i] = sb.toString();
//			System.out.println("New query: " + res[i]);
//		}
//		return res;
//	}
//	
//	private static String[] generateIncidentwithOptimizer(char op, int numOp, int numQuery, int model) {
//		String[] res = new String[numQuery];
//		Random rand = new Random();
//		for(int i=0; i<numQuery; i++){
//			StringBuilder sb = new StringBuilder();
//			sb.append(actis[rand.nextInt(actis.length)]);
//			for(int j=0; j<numOp; j++){
//				sb.append(op);
//				sb.append(actis[rand.nextInt(actis.length)]);
//			}
//			res[i] = sb.toString();
//			System.out.println("New query: " + res[i]);
//		}
//		return res;
//	}

//	public static void testSeqOp(Log log){
//		//System.out.println("Start testing sequ op:");
//		String[] qs = {"read:open_trust", "TRANSFER:TRA", "ICU:HOSPREV", "PRECONDS:HOSPREV",
//				"MTOS:QAISSUE", "INJDETS:PROTECT", "FLDDETAI:INJDETS", "VITALS:FLDDETAI",
//				"TREATMEN:LAB", "MORTDETS:ORGANS"};
//		test(qs, 0);
//	}
//	
//	public static void testOrOp(Log log){
//		System.out.println("Start testing or op:");
//		String[] qs = {"read|open_trust", "TRANSFER|TRA", "ICU|HOSPREV", "PRECONDS|HOSPREV",
//				"MTOS|QAISSUE", "INJDETS|PROTECT", "FLDDETAI|INJDETS", "VITALS|FLDDETAI",
//				"TREATMEN|LAB", "MORTDETS|ORGANS"};
//		test(qs, 0);
//	}
//	
//	public static void testParaOp(Log log){
//		System.out.println("Start testing para op:");
//		String[] qs = {"ICU+INJDETS", "TRANSFER+TRA", "ICU+HOSPREV", "PRECONDS+HOSPREV",
//				"MTOS+QAISSUE", "INJDETS+PROTECT", "FLDDETAI+INJDETS", "VITALS+FLDDETAI",
//				"TREATMEN+LAB", "MORTDETS+ORGANS"};
//		test(qs, 0);
//	}
	
	// Test optimizer effectiveness
//	public static void test(String[] qs, int model, Incident incident){
//		double[] optimizer_time = new double[qs.length];
//		double[] best_time = new double[qs.length];
//		double[] worst_time = new double[qs.length];
//		for(int i=0; i<qs.length; i++){
//			//Incident incident = new Incident(qs[i]);
//			if(model <= 0){
//				return;
//			}
//			long opti1 = System.nanoTime();
//			Incident[] res = Optimizer.generateOptimalTree(incident, model); //TODO: Investigate. This opti functiron does not copy over Occs
//			System.out.println("res"+ res);
//			long opti2 = System.nanoTime();
//			opti2 -= opti1;
//			optimizer_time[i] = opti2;
////				System.out.println(incident.tree.toString());
////				System.out.println(incident.optiTree);
////				System.out.println("Opti time (millis) for test case " + i + " : " + opti2);
//		
//			long total1 = 0, total2 = 0;
			/*for(int j=0; j<10; j++) {
				System.gc();
				long t1 = System.nanoTime();
				QueryEngine.queryEngine.query(res[1]);
				long t2 = System.nanoTime();
				t2 -= t1;
				total1 += t2;
			}
			worst_time[i] = total1/10;
			
			for(int j=0; j<10; j++) {
				System.gc();
				long t1 = System.nanoTime();
				QueryEngine.queryEngine.query(res[0]);
				long t2 = System.nanoTime();
				t2 -= t1;
				total2 += t2;
			}
				
			best_time[i] = total2/10;
			System.out.println("Finish case " + i);
			*/
			//System.out.println(incident.tree.toString());
//			QueryEngine.queryEngine.query(incident);
//			
//			System.out.println("OCCS size Main - Test "+ incident.tree.root.occs.size());
//		}
		
//		for(int i=0; i<qs.length; i++){
////			System.out.println(optimizer_time[i] + "\t" + best_time[i] + "\t" + worst_time[i]);
//		}
//	}
	
	// Test runtime for each type of operator
//	public static void testRuntime(String[] qs, int model){
//		long total_time = 0;
//		for(int i=0; i<qs.length; i++){
//			
//			Incident incident = new Incident(qs[i]);
//			if(model <= 0){
//				return;
//			}
//			long total = 0;
//			for(int j=0; j<10; j++){
//				System.gc();
//				long t1 = System.nanoTime();
//				QueryEngine.queryEngine.query(incident);
//				long t2 = System.nanoTime();
//				total += t2 - t1;
//			}
//			
//			
//			total_time += total/10;
//			
//		}
//		
//		System.out.println("Average time: " + total_time/qs.length);
//	}
//	
//	public static void testOptimizerAccuracy(String[] qs, int model){
//		long time = 0, max = 0, min = Integer.MAX_VALUE;
//		long optiTime = 0, maxOpti = 0, minOpti = Integer.MAX_VALUE;
//		int[] counts = {0, 0, 0, 0, 0, 0, 0};
//		for(int i=0; i<qs.length; i++){
//			Incident incident = new Incident(qs[i]);
//			if(model > 0){
//				long opti1 = System.nanoTime();
//				double res = Optimizer.generateOptimalTree_performance(incident, model);
//				if(res <= 0.0001){
//					counts[0]++;
//				}else if(res <= 0.01){
//					counts[1]++;
//				}else if(res <= 0.02){
//					counts[2]++;
//				}else if(res <= 0.05){
//					counts[3] ++;
//				}else if(res <= 0.1){
//					counts[4]++;
//				}else if(res <= 0.2){
//					counts[5]++;
//				}else if(res <= 0.5){
//					counts[6]++;
//				}
//				long opti2 = System.nanoTime();
//				opti2 -= opti1;
//				optiTime += opti2;
//				maxOpti = Math.max(maxOpti, opti2);
//				minOpti = Math.min(minOpti, opti2);
////				System.out.println(incident.tree.toString());
////				System.out.println(incident.optiTree);
////				System.out.println("Opti time (millis) for test case " + i + " : " + opti2);
//			}
//			long t1 = System.currentTimeMillis();
//			QueryEngine.queryEngine.query(incident);
//			long t2 = System.currentTimeMillis();
//			t2 -= t1;
//			time += t2;
//			max = Math.max(max, t2);
//			min = Math.min(min, t2);
//			System.out.println("Running time(milis) for test case " + i + " : " + t2);
//		}
//		System.out.println("Correct prediction: ");
//		int curcount = 0;
//		for(int x: counts){
//			System.out.println(x+curcount);
//			curcount += x;
//		}
//		System.out.println("Total test cases: " + qs.length);
//		System.out.println("Average runtime: " + time/qs.length);
////		System.out.println("Max runtime: " + max);
////		System.out.println("Min runtime: " + min);
//		if(model > 0) 
//			System.out.println("Average opti time: " + optiTime/qs.length);
//		System.out.println();
//	}

	
	
	
}