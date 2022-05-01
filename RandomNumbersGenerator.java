package com.jasci.biz.AdminModule.controller;
/**
* @Program: RandomNumbersGenerator.java
* @Description: The RandomNumbersGenerator program generates million of random numbers and output to file. Also, sorts random numbers output to another file.
* @Author: Dipali Shah
* @Version: 1.0
* @Date: 2022-05-01 
*/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.jasci.exception.JASCIEXCEPTION;
 
//Main class
public class RandomNumbersGenerator {
	
	// Driver Method
    public static void main(String[] args)
    {
    	 String directoryPath="C:\\RandomNumbers";
    	 String randomNumbersFile="randomNumbers.txt";
    	 String sortedRandomNumbersFile="sortedRandomNumbers.txt";
    	 
    	// Returns a stream of streamSize(1000000) pseudorandom int values  between 0 (inclusive), 100000000(exclusive)      
        IntStream evenIntStream = ThreadLocalRandom.current().ints(1000000,0,100000000);
     		   
        //returns a Stream consisting of the max(1000000) elements of this stream, each boxed to an Integer
        Stream<Integer> stream = evenIntStream.limit(1000000).boxed();
    		
        //collecting Stream elements into a list
    	ArrayList<Integer> list = (ArrayList<Integer>) stream.collect(Collectors.toList());
    	     
    	//Create New directory, file to output generated random numbers       
    	writeFile(directoryPath, randomNumbersFile, list);
    	         
    	//Collections.sort() are used to sort the List
    	Collections.sort(list);
    	
    	//Create New file to output sorted random numbers  
    	writeFile(directoryPath, sortedRandomNumbersFile, list);
    	            	
    	System.out.println("Millions of Random Numbers are generated");    	       
    }
    
    /**
	 * @author Dipali Shah
	 * @Date: 2022-05-01
	 * @Description:This function is used to create file and write into file
	 * @param directoryName String
	 * @param fileName String
	 * @param randomNumList ArrayList<Integer>
	 */
    private static void writeFile(String directoryName, String fileName, ArrayList<Integer> randomNumList){
        
    	//Creates parent directories if not created 
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();            
        }

        File file = new File(directoryName + "\\" + fileName);
        try{
        	
        	//Create File if file does not exist, otherwise delete existed file and create new file
        	if(!file.createNewFile()) {				
				file.delete();
			    file = new File(directoryName + "\\" + fileName);				
			}
			
        	//Write once file is created
			if(file!=null) {	
				
				// Create a FileWriter object
	            // to write in the file
				FileWriter writer = new FileWriter(directoryName + "\\" + fileName);
			
				for(Integer str: randomNumList) {
				  writer.write(str + System.lineSeparator());
				}
				
				// Closing the file writing connection
				writer.close();
			}
        }
        // Catch block to handle if exception occurs
        catch (IOException e){
        	// Print the exception
            e.printStackTrace();
            System.exit(-1);
        }
    } //end of private static void writeFile()
}
 