package bridgeit.cabservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CabRideMain {

	static String location = "D:\\project\\training\\bridgelabz_rpf\\CabInvoiceService\\src\\data\\";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, NumberFormatException {
		int input;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Traveller name");
			String name = sc.nextLine();
			System.out.println("1. Add Ride\n2. View Rides\n3. Exit");
			input = sc.nextInt();
			switch (input) {
			case 1: {
				CabRide cabRide = null;
				Scanner sc1 = new Scanner(System.in);
				System.out.println("1. Normal Ride\n2. Premium Ride");
				int type = sc1.nextInt();
				System.out.println("Enter the Distance");
				int dis = sc.nextInt();
				System.out.println("Enter the Travel time in min");
				int time = sc.nextInt();
				if (type == 1) {
					cabRide = new CabRide(dis, time, "Normal");
				} else if (type == 2) {
					cabRide = new CabRide(dis, time, "Premium");
				}
	
				try {
					if (new CSVReader(new FileReader(location + name + ".csv")) != null) {
						CSVWriter writer = new CSVWriter(new FileWriter(location + name + ".csv", true));
						String[] record = cabRide.toString().split(",");
						writer.writeNext(record);
						writer.close();
					}
				} catch (FileNotFoundException ex) {
					File file = new File(location + name + ".csv");
					FileWriter outputfile = new FileWriter(file);
					CSVWriter writer = new CSVWriter(outputfile);

					String[] record = cabRide.toString().split(",");
					writer.writeNext(record);
					writer.close();
				}
				break;
			}

			case 2: {
				try {

					String fileName = location + name + ".csv";
					CSVReader csvReader = new CSVReader(new FileReader(fileName));
					String[] nextRecord;
					int totalFare = 0;
					int numOfRides = 0;
					int averageperride = 0;
					while ((nextRecord = csvReader.readNext()) != null) {
						totalFare = totalFare + Integer.parseInt(nextRecord[2]);
						numOfRides++;
					}
					System.out.println("Total Number of rides : " + numOfRides + "\nTotal Fare : " + totalFare
							+ "\nAverage fare per ride : " + totalFare / numOfRides);
				} catch (FileNotFoundException e) {
					System.out.println("Traveller Not Found");

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			default:
				break;
			}
		} while (input != 3);
	}

}
