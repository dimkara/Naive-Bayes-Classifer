package NaiveBayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;


public class NaiveBayes {
	BufferedReader br;
	Vector<String> message=new Vector<String>();   // includes the words of a new message
	FindAttributes data;
	public static double total=0;
	public static double sfalmata=0;
	private static String path="src\\NaiveBayes\\self\\set10.txt";
	public NaiveBayes(FindAttributes d){
		data=d;
	}
	
	
	public void newMessage(){   // elaborates a new message
		
		try{
			
			 
      	     br =new BufferedReader(new FileReader(path));
      	     String line=br.readLine();
      	     
   		     while(line!=null){
   		    	String[] lineArray=line.split(" ");
				String category=lineArray[0];
				 for(int i=1;i<lineArray.length;i++){
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
					 if(FindAttributes.stopwords.containsKey(lineArray[i])==false && lineArray[i].trim().length()>2){
						   
						 message.add(lineArray[i].trim());
						 
					 }
				 
				 }
   		    	boolean classif=this.classify();
				System.out.println(classif);
				if(classif){
					if(!category.equals(FindAttributes.cat0)){
						sfalmata++;
					}
				}
				else{
					if(!category.equals(FindAttributes.cat1)){
						sfalmata++;
					}
				}
				this.message.clear();
   		    	line=br.readLine();
   		    	total++;
   		     }
   		     
   		      
   		     
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public double spamProb(){   // calculates the probability P(C=1|X)
		
		ArrayList<String> s = data.getAttr();
		 
		double p=1;   // this variable contains the value of P(X=x|C=1)
		 
		for(String attr : s){   // for each attribute
			 
			if(message.contains(attr)){   // if the new message contains the attribute attr
				 
				 // Laplace smoothing
				
				 double i = data.getSpamVal(attr)+1;  
				 double j= data.getSpam()+2;
                 
				 // i/j = P( X=1 | C=1 )
				 
				 p*=i/j;  
				 
				 
				
			}
			else{
				
				// Laplace smoothing
				
				double i=data.getSpam() - data.getSpamVal(attr);
				i=i+1;
				double j=data.getSpam()+2;
				
				// i/j = P( X=0 | C=1 )
				
				p*=i/j;
			}
		  
		}
		// double pc1=data.getSpam()/data.getTotal();  // P(C=1)
		// p=pc1*p;
		System.out.println(p+"  SPAM PROPABILITY");
		return p;
	}
	
	public double hamProb(){
		ArrayList<String> s = data.getAttr();
		double p=1;
		 
		for(String attr : s){
			
			if(message.contains(attr)){
				 
				 
				 double i = data.getHamVal(attr)+1;
				 double j= data.getHam()+2;
				 p*=i/j;
				 
				 
				
			}
			else{
				double i=data.getHam() - data.getHamVal(attr);
				i = i+1;
				double j=data.getHam()+2;
				p*=i/j;
			}
		 
		}
		
		// double pc0=data.getHam()/data.getTotal();
		// p=pc0*p;
		
		System.out.println(p+"  HAM PROPABILITY");
		return p;
	}
	
	public boolean classify(){
	    double p1 = data.getSpam()/data.getTotal();
		p1 = p1 * spamProb();
		 
		
		double p2 = data.getHam()/data.getTotal();
		p2 = p2 * hamProb();    
		 
		if(p1>p2){
			System.out.println(FindAttributes.cat1);
			return false;
		}
		
		System.out.println(FindAttributes.cat0);
		return true;
		
	}
}
