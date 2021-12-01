package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import controller.FitnessDataManager;
import model.FitnessData;

public class ConsoleUI {

	private FitnessDataManager fitnessDataManager;
	private Scanner stdin;
	private String title;
	private Object outputFilename;
	private String currentData;

	public ConsoleUI(String title) {
			this.title = title;
			this.fitnessDataManager = new FitnessDataManager();
			this.outputFilename = null;
			this.stdin = new Scanner(System.in);
			
			System.out.print("Hello!\n\nWelcome to your Fitness Manager\n\nHere you can keep track"
					+ " of your fitness progress\nby tracking your calorie consuption, activite time\nand"
					+ " calories burnt. This helps you to make educated\ndecsions when it comes to your "
					+ "health.\n");
		
			int maxFitnessData = 10;
			String[] fitnessData = new String[maxFitnessData];
			
			int numFitnessData = 0;
			maxFitnessData = Integer.parseInt(getInput("\nHow many records"
					+ " would you like to process today?"));
			
			System.out.print("\nTo continue, please make a selection: \n");
			String menu = "\nFitness Data Manager\n";
			menu += "================\n";
			menu += "[A]dd Record\n";
			menu += "[D]isplay Records\n";
			menu += "[S]ave File\n";
			menu += "[L]oad File\n";
			menu += "Choice: ";

			String choice;

			choice = getInput(menu);

			while (maxFitnessData > numFitnessData) {			
				fitnessData[numFitnessData] = currentData;
				numFitnessData += 1;
			
				if (choice.equalsIgnoreCase("a")) {

					System.out.println("Enter <Food Description, Number of Calories, Exercise(mins), "
							+ "Calories Burnt, Weight>:");
					String[] fields = this.stdin.nextLine().split(",");
					
					String foodDescription = fields[0];
					int foodCalories = Integer.parseInt(fields[1]);
					double exerciseLength = Double.parseDouble(fields[2]);
					int caloriesBurnt = Integer.parseInt(fields[3]);
					double weight = Double.parseDouble(fields[4]);				
										
					
					try {
						this.fitnessDataManager.fitnessDataManagerAddData(foodDescription, foodCalories,
								exerciseLength, caloriesBurnt, weight);
					} catch (Exception e) {
						String message = "Error! " + e.getMessage() + "...\n";
						System.out.println(message);
					}
				}

				else if (choice.equalsIgnoreCase("d")) {

					String[] dataSummary = this.fitnessDataManager.getDataSummary();
					System.out.println(this.fitnessDataManager.fitnessDataManagerGetTableHeadings());
					int i = 0;
					while (i < dataSummary.length) {
						System.out.println(dataSummary[i].replace(',', '\t'));
						i += 1;
					}
				}

				else if (choice.equalsIgnoreCase("s")) {
					
					System.out.print("Enter file name");
					String loadCloud = this.stdin.nextLine();
					String outcome = this.fitnessDataManager.saveToFile(loadCloud);
					this.fitnessDataManager.readFile(loadCloud);

					while (loadCloud == null || loadCloud.strip().isEmpty() || loadCloud.equalsIgnoreCase("Admin")
							|| loadCloud.equalsIgnoreCase("root"))
					{

						System.out.print("Invalid name! Please re-enter");
						if (outcome != null)
							System.err.println(outcome);
					}
				}

				else if (choice.equalsIgnoreCase("l")) {
					
					System.out.print("Enter local file path");
					String fileName = this.stdin.nextLine();
					try {
						BufferedReader inFileObj = new BufferedReader(new FileReader(fileName));
						String rawLine = inFileObj.readLine();
						while (rawLine != null) {
							String[] fields = rawLine.split(",");
							String foodDescription = fields[0];
							int foodCalories = Integer.parseInt(fields[1]);
							double exerciseLength = Double.parseDouble(fields[2]);
							int caloriesBurnt = Integer.parseInt(fields[3]);
							double weight = Double.parseDouble(fields[4]);
		
							this.fitnessDataManager.fitnessDataManagerAddData(foodDescription, foodCalories,
									exerciseLength, caloriesBurnt, weight);
							rawLine = inFileObj.readLine();
						}

						inFileObj.close();
					} catch (Exception e) {
						String message = "Error! " + e.getMessage() + "...\n";
						System.out.println(message);
					}
				
			}
				choice = getInput(menu);
			}
		}
		private String getInput(String prompt) {
			System.out.print(prompt);
			return this.stdin.nextLine().strip();
		}

		public static void main(String[] args) {
			ConsoleUI obj = new ConsoleUI("The Fitness Portal");
		}

	}
