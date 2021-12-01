package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import model.FitnessData;

public class FitnessDataManager {
	private String tableHeadings;
    FitnessData newFitnessData;
    private FitnessData[] fitnessData;
    private int currentNumData;

public FitnessDataManager() {
    this.tableHeadings = "Food Description\tServing Size\tFood Calories";
    

    this.fitnessData = new FitnessData[30];
    this.currentNumData = 0;
}

public String readFile(String fileName) {
      String outcome = null;
      try {
        BufferedReader inFileObj = new BufferedReader(new FileReader(fileName));
        String rawLine= inFileObj.readLine();
          while(rawLine!=null) {
          String[] fields=rawLine.split(",");
          String foodDescription = fields[0];
          int foodCalories = Integer.parseInt(fields[1]);
          double exerciseLength = Double.parseDouble(fields[2]);
          int caloriesBurnt = Integer.parseInt(fields[3]);
          double weight = Double.parseDouble(fields[4]);

  
          fitnessDataManagerAddData(foodDescription, foodCalories,	exerciseLength, 
        		  caloriesBurnt, weight);;
    
          rawLine= inFileObj.readLine();
  }

      inFileObj.close();

       } catch (Exception e) {
      outcome = e.getMessage();
   }
      return outcome;
}

public String saveToFile(String fileName){
      String outcome = null;
      try {
        BufferedWriter outFileObj = new BufferedWriter(new FileWriter(fileName));
        int i = 0;
          while(i < this.currentNumData) {
          outFileObj.write(this.fitnessData[i] + "\n");
          i +=1;
    }
        outFileObj.close();
        
     } catch (Exception e) {
        outcome = e.getMessage();
  }
       return outcome;
}

public void fitnessDataManagerAddData(String foodDescription,int foodCalories, double exerciseLength, 
		int caloriesBurnt, double weight) throws Exception {
     if (this.currentNumData< this.fitnessData.length) {
     this.fitnessData[this.currentNumData] = new FitnessData(foodDescription, foodCalories,
    		 exerciseLength, caloriesBurnt, weight);
     this.currentNumData += 1;
  }
}


public String fitnessDataManagerGetTableHeadings() {
      this.tableHeadings= "Food Description\tFood Description\tCalories\tExercise(mins)"
      		+ "\tCalories Burnt\tWeight";
      return this.tableHeadings;
}

public String[] getDataSummary() {
      String[]dataSummary = new String[this.currentNumData];
      int i = 0;
      while (i < this.currentNumData) {
      dataSummary[i] = this.fitnessData[i].getFoodDescription() + "," + this.fitnessData[i].getFoodCalories() + "," 
      + this.fitnessData[i].getExerciseLength() +  "," + this.fitnessData[i].getCaloriesBurnt()+  "," 
      + this.fitnessData[i].getWeight();
     
      i += 1;
}

return dataSummary;

}

}