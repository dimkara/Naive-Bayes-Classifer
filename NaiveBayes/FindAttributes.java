package NaiveBayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;


public class FindAttributes {
	 public static String cat1="spam";    // category name for C=1
	 public static String cat0="ham";    // category name for C=0
	 private static int position=0;
	 
	 private double ham;    // number of ham messages
     private double spam;    // number of spam messages
     public static double total;    // total number of messages
     private double pcspam;   // P(C=1)
     private double pcham;    // P(C=0)
     private double hc;         // entropy
    
     private HashMap<String,Double> spamwords;   // contains the words which are included in spam categories and the number of appearances into spam messages
     private HashMap<String,Double> hamwords;    // contains the words which are included in ham categories and the number of appearances into ham messages
     private HashMap<String,Double> igset; 
     
     public ArrayList  <String> attributes;    // contains the X attributes which we selected
     private static String path="src\\NaiveBayes\\datasets\\total.txt";
     private static double maxAttributes=320;     // the number of attributes which we are going to use
     
     BufferedReader br;
     
     // periexei lekseis tis opoies den epeksergazomaste giati den mas dinoun xrisimes plirofories
     public static String stop_list = "one two have other three four five six seven eight nine ten ... ....  i've they'll we'll i'd i'm ! it's i'm you're and are but by for into not such that the their then there these they this was will with";
     public static Map<String, Integer> stopwords = new HashMap<String, Integer>();    // hashmap pou periexei tis stopwords
     
     public static void main(String[]args){
    	 FindAttributes d=new FindAttributes();
    	 d.search();
    	 d.sort(d.getAttr(), d.getAttr().size());
    	 NaiveBayes nb=new NaiveBayes(d);
    	 nb.newMessage();
    	// d.print();
    	 System.out.println("Plithos Idiotitwn :"+d.attributes.size()+"  "+"Arithmos keimenwn datasets: "+FindAttributes.total+"  "+"Test no : "+NaiveBayes.total+"   Sfalmata: "+NaiveBayes.sfalmata+"  "+(NaiveBayes.total-NaiveBayes.sfalmata)/NaiveBayes.total);
     }
     
     public FindAttributes(){
    	 
    	 spamwords = new HashMap<String,Double>();
    	 igset = new HashMap<String,Double>();
    	 hamwords = new HashMap<String,Double>();
    	 ham=0;
    	 spam=0;
    	 total=0;
         pcspam=0;
         pcham=0;
         hc=0;
         stopwords=this.builtStopWordsList();
         attributes = new ArrayList<String>();
     }
     
    
     public double getSpam(){
    	 return spam;
     }
     
     public double getHam(){
    	 return ham;
     }
     public double getPcspam(){
    	 return this.pcspam;
     }
     
     public double getPcham(){
    	 return this.pcham;
     }
     
     public double getTotal(){
    	 return this.total;
     }
     
     public double getSpamVal(String s){           
           if(spamwords.containsKey(s)==false){
        	   return 0;
           }
           return spamwords.get(s);
    	 
     }
     
     public double getHamVal(String s){       
    	 if(hamwords.containsKey(s)==false){
      	   return 0;
         }
    	 return hamwords.get(s);
     }
     
     public ArrayList <String> getAttr(){
    	 return attributes;
     }
     
     
     // we calculate the values of : spam, ham, total, pcspam, pcham
     // Episis tha valoume sto spamwords kathe leksi pou emfanizetai se spam keimeno kai to poses fores emfanizetai kai sto hamwords kathe leksi pou emfanizetai se ham keimeno kai to poses fores emfanizetai
     public void search(){
    	 try{
       	     br =new BufferedReader(new FileReader(path));
       	     String line=br.readLine();
       	  
    		 while(line!=null){  // for each example
    			 
    			 HashSet <String> spamset = new HashSet<String>();  
    			 
    			 HashSet <String> hamset = new HashSet<String>();
    			
    			 String[] catArray = line.split(" ");
    			   
    			 
    			 if(catArray[position].trim().equals(cat1)){   // if the word in position "position" is cat1
    				 
    				 spam++;
    				 
    				 line=line.replaceAll(cat1, "");
    				  
    				 line=line.trim();


    				 String[] lineArray=line.split(" ");
    				
    				 for(int i=0;i<lineArray.length;i++){
    					 
    					 //removing the dots, commas, etc
    					 
    					 lineArray[i]=lineArray[i].replace("?", "");
    					 lineArray[i]=lineArray[i].replace("-", "");
    					 lineArray[i]=lineArray[i].replace(">", "");
    					 lineArray[i]=lineArray[i].replace("<", "");
    					 lineArray[i]=lineArray[i].replace(")", "");
    					 lineArray[i]=lineArray[i].replace("(", "");
    					 lineArray[i]=lineArray[i].replace(":", "");
    					 lineArray[i]=lineArray[i].replace(".", "");
    					 lineArray[i]=lineArray[i].replace("!", "");
    					 lineArray[i]=lineArray[i].replace(",", "");
    					 lineArray[i]=lineArray[i].toLowerCase();
    					 
    					 // an mia leksi den anikei sta stopwords kai exei mikos>2
    					 if(stopwords.containsKey(lineArray[i])==false && lineArray[i].trim().length()>2){  
    						  
    						  if(spamwords.containsKey(lineArray[i])){    // an exoume ksanasinantisei autin tin leksi se allo keimeno katigorias cat1
    							  
    							  if(spamset.contains(lineArray[i])==false)   // an einai i prwti fora pou tin vriskoume sto sigkekrimeno keimeno
    							  {
    								  // vazoume tin leksi sto antistoixo hashmap kai auksanoume kata ena to value pou eixe proigoumenws
    								  
    								  spamwords.put(lineArray[i].trim(), spamwords.get(lineArray[i].trim())+1);   
    								  
    								  //prosethetoume tin leksi sto hashset me onoma spamset. auto to kanoume etsi an p.x. mia leksi iparxei 10 fores se ena spam keimeno kai kamia se alla spam
    								  // na exei value 1 kai oxi value 10
    								  
    								  spamset.add(lineArray[i].trim());
    							  }


    							  
    						   }
    						   else{   // an i leksi den iparxei sto hashmap spamwords
    							  
    							   if(spamset.contains(lineArray[i])==false)   // an den iparxei sto spamset
    							   {
    							       spamwords.put(lineArray[i].trim(), 1.0);  // prosthetoume tin leksi sto spamwords
    							       spamset.add(lineArray[i].trim());    // prosthetoume tin leksi sto spamset
    							   }


    						  }
    					 }
    				 }
    				  
    			 }
    			 
    			// Epanalamvanoume tin diadikasia pou kaname parapanw, alla gia tin periptwsi pou i leksi sti thesi position einai cat1 
    			 if(catArray[position].trim().equals(cat0)){
    				 ham++;
    				 line=line.replaceAll(cat0, "");
    				 line=line.trim();


    				 String[] lineArray=line.split(" ");
    				 for(int i=0;i<lineArray.length;i++){
    					 lineArray[i]=lineArray[i].replace("?", "");
    					 lineArray[i]=lineArray[i].replace("-", "");
    					 lineArray[i]=lineArray[i].replace(">", "");
    					 lineArray[i]=lineArray[i].replace("<", "");
    					 lineArray[i]=lineArray[i].replace(")", "");
    					 lineArray[i]=lineArray[i].replace("(", "");
    					 lineArray[i]=lineArray[i].replace(":", "");
    					 lineArray[i]=lineArray[i].replace(".", "");
    					 lineArray[i]=lineArray[i].replace("!", "");
    					 lineArray[i]=lineArray[i].replace(",", "");
    					 lineArray[i]=lineArray[i].toLowerCase();
    					  
    					 if(stopwords.containsKey(lineArray[i])==false && lineArray[i].trim().length()>2){
    						  if(hamwords.containsKey(lineArray[i])){
    							  if(hamset.contains(lineArray[i])==false)
    							  {

    							      hamwords.put(lineArray[i].trim(), hamwords.get(lineArray[i].trim())+1);
    							      hamset.add(lineArray[i].trim());
    							  }
    						  }
    						  else{
    							  if(hamset.contains(lineArray[i])==false)
    							  {
    							     hamwords.put(lineArray[i].trim(), 1.0);
    							     hamset.add(lineArray[i].trim());
    							  }

    						  }
    					 }
    				 }
    			 }
    			 
    			 line=br.readLine();
    		 }
    		 
    		 // Afou epeksergastoume ola ta paradeigmata
    		 
    		 total = ham + spam;    
    		 
    		 pcham = ham / total;   // ham/total i pithanotita P(C=0)
    		 pcspam = spam / total;  // spam/total i pithanotita P(C=1)
    		 
    		 hc=-this.pcham*logbasetwo(pcham)- this.pcspam*logbasetwo(pcspam);   // H(C) = - P(C=1)*log(P(C=1)) - P(C=0)*log(P(C=0))
    	
    		 
    		 ig();   // kaloume tin methodo ig
    		 
    		 
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 }
     }
     
     
     public void ig(){     // ipologizoume to information gain gia kathe leksi pou iparxei sto spawords kai sto hamwords
    	 
        Iterator<String> it= spamwords.keySet().iterator();   // arxika ipologizoume to ig gia tis lekseis pou iparxoun sto spamwords
         
        while(it.hasNext()){
        	
        	String key=(String)it.next();    //  i metavliti spamval exei to onoma tis leksis
            
        	double spamval=spamwords.get(key);  // i metavliti spamval exei ws timi to se posa minimata emfanizetai i leksi tis metavlitis key
        	 
        	
        	double hamval;
        	
        	if(hamwords.containsKey(key)){   // an i leksi key emfanizetai kai se ham minimata
        		
        	     hamval = hamwords.get(key); // i metavliti hamval pairnei ws timi to poses fores emfanizetai i key se ham minimata
        	    
        	}
        	else{
        		  hamval=0;   // an den emfanizetai se kana ham i leksi key, tote i hamval pairnei tin timi 0
        		 
        	}
        	
        	
        	
            if(hamval==0){    
            	
            	if(spamval==spam){   // an mia leksi emfanizetai se ola ta spam messages kai se kanena ham
            		
            		double ig=1;   // tote exoume megisto information gain. prosthetoume tin leksi 
            			  
				    igset.put(key, ig);
						       
				    this.attributes.add(key);   // prosthetoume tin leksi sto Vector attributes
            	}
            	else{  // if hamval<spam
            		
            		 double px1 = spamval/total;  // P(X=1)
            		 double div=spamval/spam;     // P(C=1|X=1)
            		 
            		 double px0 = (total-spamval)/total;  // P(X=0)
            		 double div0= (spam-spamval)/spam;   // P(C=1|X=0)
            		 
            	     double ig= hc - px1*div*logbasetwo(div) - px0*div0*logbasetwo(div0);
            	     igset.put(key, ig);
            	     
            	 
            	    	 
		    			 this.attributes.add(key);
		    	 
            	 
            	}
            }
            else{   // if hamval>0
            	
        	double totalval=spamval+hamval;
        	
        	double p1=spamval/totalval;   // P(C=1|X=1)
        	 
        	double p2=hamval/totalval;   // P(C=0|X=1)
         	
        	double p3 = (total-spamval)/(total-totalval);   // P(C=1|X=0)
        	
        	double p4 = (total-hamval)/(total-totalval);  // P(C=0|X=0)
        	
        	
        	
        	double px1=totalval/total;   // P(X=1)
        	
        	double px0=(total - totalval)/total;   // P(X=0)
        	
        	
        	double hcx1=-p1*logbasetwo(p1)- p2*logbasetwo(p2);   // H( C=c | X=1)
        	
        	double hcx0=-p3*logbasetwo(p3)- p4*logbasetwo(p4);   // H( C=c | X=0)
        	
            double ig = hc - px1*hcx1 - px0*hcx0;
            
            igset.put(key, ig);
           
            	 
   			 this.attributes.add(key);
   		 
   	       
        	 
          }
        	
           
        	
        }
        
        
         
        Iterator <String> it2= hamwords.keySet().iterator();

        while(it2.hasNext()){    // tha ipologisoume to information gain gia tis lekseis pou emfanizontai mono se ham minimata
        	
        	String key=(String)it2.next();
            if(spamwords.containsKey(key)){
            	continue;
            }
        	double hamval=hamwords.get(key);
        	
        	if(hamval==ham){   // an mia leksi emfanizetai se ola ta ham messages kai se kanena spam
        		double ig=1;
        		
        		igset.put(key, ig);
     			
 	    	    this.attributes.add(key);
 	    	    
        	}
        	else{
        		 double px1 = hamval/total;
        		 double div=hamval/ham;
        		 
        		 double px0 = (total-hamval)/total;
        		 double div0= (ham-hamval)/ham;
        		 
        	      
        	     double ig= hc - px1*div*logbasetwo(div)- px0*div0*logbasetwo(div0);
        	     
        	     
        	     igset.put(key, ig);
        	  
        	    	  
	    			 this.attributes.add(key);
	    	 
        	   
        	}
        }
         
        System.out.println(" Plithos idiotitwn pou tha xrisimopoiithoun : "+this.attributes.size());
        
     }
     
     
     public double logbasetwo(double x){   // ipologizoume ton logarithmo me vasi to 2 tis timis x
    	 return  Math.log(x)/Math.log(2);
     }
     
     public Map<String, Integer> builtStopWordsList() {   // prosthetoume ta stopwords sto hashmap
         Map map = new HashMap();
         String[] stopwords = stop_list.split(" ");
         for (String stopword : stopwords) {
             map.put(stopword, 1);
         }
         return map;
     }
     
     public HashMap<String,Double> getIgSet(){
    	 return this.igset;
     }
     
     public  void sort(ArrayList <String> array, int n){
     	  for (int i = 1; i < n; i++){
     	  int j = i;
     	  double B = igset.get(array.get(i));
     	  String B2=array.get(i);
     	  while ((j > 0) && (igset.get(array.get(j-1)) < B)){
     		  
     		array.add(j, array.get(j-1));
     		array.remove(j+1);
     		 
     	      
     	  j--;
     	  }
         	array.add(j, B2);
         	array.remove(j+1);
     	}
     	 changeAttr();  
     }
     
     public void changeAttr(){
    	 ArrayList<String> a=new ArrayList<String>();
    	 for(int i=0;i<maxAttributes;i++){
    		 a.add(attributes.get(i));
    	 }
    	 attributes=a;
     }
     
     public void print(){
    	 for(int i=0;i<attributes.size();i++){
    		 String key=attributes.get(i);
    		 System.out.println(key+"  "+igset.get(key));
    	 }
     }
}

 
