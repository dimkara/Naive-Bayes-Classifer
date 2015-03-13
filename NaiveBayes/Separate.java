package NaiveBayes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Separate {
	
	public static void main(String[]args){
		Separate sep=new Separate();
		sep.readFile();
		System.out.println(Separate.total);
	}
    BufferedReader br;
    BufferedWriter bwdata;
    BufferedWriter bwvalid;
    BufferedWriter bweval;
    
    public static int total=0;
    private static int examplenumber=667;  // arithmos grammwn sinolikou dataset
    public Separate(){
    	
    	
    }
    
    public void readFile(){
    try{
    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\dataSet.txt"));
    	bweval = new BufferedWriter(new FileWriter("src\\NaiveBayes\\evaluation.txt"));
    	bwvalid = new BufferedWriter(new FileWriter("src\\NaiveBayes\\validation.txt"));
     	br=new BufferedReader(new FileReader("src\\NaiveBayes\\datasets\\SMSSpamCollection.txt"));
    	
     	bwdata.flush();
     	bweval.flush();
     	bwvalid.flush();
    	int counter=0;

    	int sixtpercent=(examplenumber*60)/100;
    	int twentypercent=(examplenumber*20)/100;
    	
    	String line=br.readLine();
    	
    	// training set
    	
    	while(line!=null && counter<sixtpercent){
    		 total++;
    	     bwdata.write(line);
    	     bwdata.newLine();
    	     line=br.readLine();
    	     counter++;
        }
    	
    	line=br.readLine();
    	
    	// evaluation set
    	while(line!=null && counter<sixtpercent+twentypercent){
    		 
    		 total++;
    	     bweval.write(line);
    	     
    	     bweval.newLine();
    	     line=br.readLine();
    	     counter++;
        }
    	
    	line=br.readLine();
    	
    	// validation set
    	
    	while(line!=null && counter<sixtpercent+twentypercent+twentypercent){
    		 total++;
    	     
    	     bwvalid.write(line);
    	     bwvalid.newLine();
    	     line=br.readLine();
    	     counter++;
        }
    	
    	bwdata.close();bweval.close();bwvalid.close();
    	
    	br.close();
    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	
    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set10.txt"));
    	bwdata.flush();
    	int counter2=0;
    	line=br.readLine();
    	
    	// 10%
    	int tenpercent=sixtpercent/10;
    	while(line!=null && counter2 < tenpercent){
    		 
    		bwdata.write(line);
    		bwdata.newLine();
    		line=br.readLine();
    		counter2++;
    	}
    	bwdata.close();
    	
    	
    	//20%
    	
br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	
    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set20.txt"));
    	bwdata.flush();
        counter2=0;
    	line=br.readLine();
    	
    	while(line!=null && counter2 < tenpercent*2){
    		 
    		bwdata.write(line);
    		bwdata.newLine();
    		line=br.readLine();
    		counter2++;
    	}
    	bwdata.close();
    	
    	
    	//30%
    	
    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	
    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set30.txt"));
    	    	bwdata.flush();
    	        counter2=0;
    	    	line=br.readLine();
    	    	
    	    	while(line!=null && counter2 < tenpercent*3){
    	    		 
    	    		bwdata.write(line);
    	    		bwdata.newLine();
    	    		line=br.readLine();
    	    		counter2++;
    	    	}
    	    	bwdata.close();
    	    	
    	    	
    	    	//40%
    	    	
    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	
    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set40.txt"));
    	    	    	bwdata.flush();
    	    	        counter2=0;
    	    	    	line=br.readLine();
    	    	    	
    	    	    	while(line!=null && counter2 < tenpercent*4){
    	    	    		 
    	    	    		bwdata.write(line);
    	    	    		bwdata.newLine();
    	    	    		line=br.readLine();
    	    	    		counter2++;
    	    	    	}
    	    	    	bwdata.close();
    	    	    	
    	    	    	
    	    	    	//50%
    	    	    	
    	    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	    	
    	    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set50.txt"));
    	    	    	    	bwdata.flush();
    	    	    	        counter2=0;
    	    	    	    	line=br.readLine();
    	    	    	    	
    	    	    	    	while(line!=null && counter2 < tenpercent*5){
    	    	    	    		 
    	    	    	    		bwdata.write(line);
    	    	    	    		bwdata.newLine();
    	    	    	    		line=br.readLine();
    	    	    	    		counter2++;
    	    	    	    	}
    	    	    	    	bwdata.close();
    	    	    	    	
    	    	    	    	
    	    	    	    	//60%
    	    	    	    	
    	    	    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	    	    	
    	    	    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set60.txt"));
    	    	    	    	    	bwdata.flush();
    	    	    	    	        counter2=0;
    	    	    	    	    	line=br.readLine();
    	    	    	    	    	
    	    	    	    	    	while(line!=null && counter2 < tenpercent*6){
    	    	    	    	    		 
    	    	    	    	    		bwdata.write(line);
    	    	    	    	    		bwdata.newLine();
    	    	    	    	    		line=br.readLine();
    	    	    	    	    		counter2++;
    	    	    	    	    	}
    	    	    	    	    	bwdata.close();
    	    	    	    	    	
    	    	    	    	    	
    	    	    	    	    	//70%
    	    	    	    	    	
    	    	    	    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	    	    	    	
    	    	    	    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set70.txt"));
    	    	    	    	    	    	bwdata.flush();
    	    	    	    	    	        counter2=0;
    	    	    	    	    	    	line=br.readLine();
    	    	    	    	    	    	
    	    	    	    	    	    	while(line!=null && counter2 < tenpercent*7){
    	    	    	    	    	    		 
    	    	    	    	    	    		bwdata.write(line);
    	    	    	    	    	    		bwdata.newLine();
    	    	    	    	    	    		line=br.readLine();
    	    	    	    	    	    		counter2++;
    	    	    	    	    	    	}
    	    	    	    	    	    	bwdata.close();
    	    	    	    	    	    	
    	    	    	    	    	    	
    	    	    	    	    	    	//80%
    	    	    	    	    	    	
    	    	    	    	    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	    	    	    	    	
    	    	    	    	    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set80.txt"));
    	    	    	    	    	    	    	bwdata.flush();
    	    	    	    	    	    	        counter2=0;
    	    	    	    	    	    	    	line=br.readLine();
    	    	    	    	    	    	    	
    	    	    	    	    	    	    	while(line!=null && counter2 < tenpercent*8){
    	    	    	    	    	    	    		 
    	    	    	    	    	    	    		bwdata.write(line);
    	    	    	    	    	    	    		bwdata.newLine();
    	    	    	    	    	    	    		line=br.readLine();
    	    	    	    	    	    	    		counter2++;
    	    	    	    	    	    	    	}
    	    	    	    	    	    	    	bwdata.close();
    	    	    	    	    	    	    	
    	    	    	    	    	    	    	
    	    	    	    	    	    	    	//90%
    	    	    	    	    	    	    	
    	    	    	    	    	    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\set90.txt"));
    	    	    	    	    	    	    	    	bwdata.flush();
    	    	    	    	    	    	    	        counter2=0;
    	    	    	    	    	    	    	    	line=br.readLine();
    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	while(line!=null && counter2 < tenpercent*9){
    	    	    	    	    	    	    	    		 
    	    	    	    	    	    	    	    		bwdata.write(line);
    	    	    	    	    	    	    	    		bwdata.newLine();
    	    	    	    	    	    	    	    		line=br.readLine();
    	    	    	    	    	    	    	    		counter2++;
    	    	    	    	    	    	    	    	}
    	    	    	    	    	    	    	    	bwdata.close();   	
    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	//100%
    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	br=new BufferedReader(new FileReader("src\\NaiveBayes\\dataSet.txt"));
    	    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	    	bwdata = new BufferedWriter(new FileWriter("src\\NaiveBayes\\datasets\\total.txt"));
    	    	    	    	    	    	    	    	    	bwdata.flush();
    	    	    	    	    	    	    	    	        counter2=0;
    	    	    	    	    	    	    	    	    	line=br.readLine();
    	    	    	    	    	    	    	    	    	
    	    	    	    	    	    	    	    	    	while(line!=null && counter2 < tenpercent*10){
    	    	    	    	    	    	    	    	    		 
    	    	    	    	    	    	    	    	    		bwdata.write(line);
    	    	    	    	    	    	    	    	    		bwdata.newLine();
    	    	    	    	    	    	    	    	    		line=br.readLine();
    	    	    	    	    	    	    	    	    		counter2++;
    	    	    	    	    	    	    	    	    	}
    	    	    	    	    	    	    	    	    	bwdata.close();
    	
        
    	
    	
    	
       }catch(Exception e){
	 	  e.printStackTrace();
	    }
    }
    
    public void writeFile(){
    	
    }
}
