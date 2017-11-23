import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class kMeanClassifier {
	
  public static Point2D[] centroid;
  public static Point2D[] dataSet;
  public static int dataCluster[]; // storing the corespoding custer of each data
  public static int OldDataCluster[];
  public static int iteration = 0; // this is for me recording, out of this assignment scope
  public static int no_of_data;
  public static int k;
  
  
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please input K");
		//specific K
		k = in.nextInt();
		
		File file = new File(args[0]);
		//
		Classifier(k,file);
		
		
	}

	private static void Classifier(int k, File file) {
		// TODO Auto-generated method stub
		
		try (Scanner br= new Scanner (file)){
			// distance between data and centorid
			double dist;
			double minDist = Double.MAX_VALUE;
			// read no_of_data
			
			//build up dataset 
			
			//setting x,y 
			
			double x;
			double y;
			
			no_of_data = (int)br.nextInt();
			//creating the container for dataset
			initDateSet(no_of_data);
			br.nextLine();
			//start building content for dataset
			for (int i=0;i < no_of_data; i++) {
				//split the data entry
				String dataEntry = br.nextLine();
				String[] dataEntryArray = dataEntry.split(" ");
				//get the x and y coordinate
				 //setting up the content of dataSet
				
				x = Double.parseDouble(dataEntryArray[1]);
				y = Double.parseDouble(dataEntryArray[2]);
				dataSet[i] = new Point2D.Double(x,y);
				
				
			}
			
			initCentroid(k);
			init_cluster_of_each_dataPoint(no_of_data);
			//start counting the dist between the centroids and data point
			do {
				for (int j =0; j<no_of_data; j++) {
					//reset minDist
					minDist = Double.MAX_VALUE;
					for (int r=0; r< k; r++) {
						dist = dataSet[j].distance(centroid[r]);
						if (dist < minDist) {
							minDist = dist;
							dataCluster[j] = r; //store the corresponding cluster
						}
					
					}
				
				}
				iteration++;
				//iteration at least set to 2 so that when we could do checking CentroidToPrevios one ; coz we start check when we get the second new distribution of each data point to each centroid
			}while(!isSameCentroidToPrevios(no_of_data) );
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		printResult();
	}

	private static boolean isSameCentroidToPrevios(int no_of_data) {
		// TODO Auto-generated method stub
		//no_of_point_in that centroid
		double no_of_points = 0;
		double new_x_of_centroid;
		double new_y_of_centroid;
		if (Arrays.equals(dataCluster, OldDataCluster)){
			return true;
		}
		else {
			//update each centroid centroid start from 1...k (index start from 0)
			for (int i=0;i<k;i++) {
				//reset all point and new x and y for other centroid
				no_of_points = 0;
				new_x_of_centroid = 0;
				new_y_of_centroid = 0;
				for (int j=0;j<no_of_data;j++) {
					if (dataCluster[j] == k) {
						no_of_points++;
						new_x_of_centroid =+ dataSet[j].getX();
						new_y_of_centroid =+ dataSet[j].getY();
					}
					centroid[i].setLocation(new_x_of_centroid/no_of_points, new_y_of_centroid/no_of_points);
					
				}
				
			}
			
			
		}
		OldDataCluster = dataCluster.clone();
		return false;
	}

	private static void init_cluster_of_each_dataPoint(int no_of_data) {
		// TODO Auto-generated method stub
		//To store each value of cluster of each data point
		dataCluster = new int[no_of_data];
		
		OldDataCluster = new int[no_of_data];
		//to make sure the first camparing would go on!
		for (int i=0; i< no_of_data;i++) {
		OldDataCluster[i] = k+1;
		}
	}

	private static void initDateSet(double no_of_data) {
		// TODO Auto-generated method stub
		dataSet = new Point2D[(int) no_of_data];
		
	}

	private static void initCentroid(int k) {
		// TODO Auto-generated method stub
		centroid = new Point2D[k];
		
		int[] random_index = new Random().ints(0, no_of_data).distinct().limit(k).toArray();
		
//		init the point to cenroid
		for (int i=0; i<k;i++) {
			
			centroid[i] = new Point2D.Double(dataSet[random_index[i]].getX(),dataSet[random_index[i]].getY());
//		centroid[i] = new Point2D.Double(dataSet[i].getX(),dataSet[i].getY());
		}
		
	}

	private static void printResult() {
		// TODO Auto-generated method stub
		String result ="";
		for (int i=0;i<k; i++) {
			//RESET THE STRING
			result="";
			for (int j=0; j<no_of_data;j++) {
				
				if(dataCluster[j] == i) {
					result += "" + j + " ";
//				result +=  "[" + dataSet[j].getX()+ "," +dataSet[j].getY()+ "]" +" ";
				}
			}
			System.out.println("Cluster "+ i +": "+  result);
		}
		
		
	}
}
	
