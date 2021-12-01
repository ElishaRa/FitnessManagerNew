package model;

public class FitnessData {
	private String foodDescription;
	private int foodCalories;
	private double exerciseLength;
	private int caloriesBurnt;
	private double weight;
	
	public FitnessData(String foodDescription,int foodCalories, double exerciseLength, 
			int caloriesBurnt, double weight) throws Exception {
	   if (foodDescription.isBlank())
	     throw new Exception("Description is blank");
	     this.foodDescription = foodDescription;

	   if (foodCalories < 0)
	     throw new Exception("Calories cannot be negative");
	     this.foodCalories = foodCalories;
	     
	    if (exerciseLength < 0 )
	     throw new Exception("Exercise Length cannot be negative");
	     this.exerciseLength = exerciseLength;
	   

		if(caloriesBurnt < 0)
		 throw new Exception("Calories Burnt cannot be negative");
		 this.caloriesBurnt=caloriesBurnt;
		 
		if(weight < 0)
		 throw new Exception("Weight cannot be negative");
		 this.weight=weight;
		 }


		public String getFoodDescription() {
			return this.foodDescription;
		}

		public int getFoodCalories() {
			return this.foodCalories;
		}
		
		public double getExerciseLength() {
			return this.exerciseLength;
			}
		
		public int getCaloriesBurnt() {
			return this.caloriesBurnt;
		}
		
		public double getWeight() {
			return this.weight;
		}

		public String toString() {
			String message = "";
			message += this.foodDescription + ",";
			message += this.foodCalories + ",";
			message += this.exerciseLength + ",";
			message += this.caloriesBurnt + ",";		
			message += this.weight ;

			return message;

		}
	}
