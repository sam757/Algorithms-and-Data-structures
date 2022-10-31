import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    
    public static int noItems;
    public static double[] value;
    public static double[] weight;
    public static double knapsackSize;
    public static int populationSize;
    public static int maxGenerations;
    public static double crossProb;
    public static double mutatProb;
    
    public static void main(String[] args){
        readInit("init.txt");
        KnapsackGA knapsack = new KnapsackGA(noItems, value, weight, knapsackSize, populationSize, maxGenerations, crossProb, mutatProb);
    }
    
    private static void readInit(String filename){
        try {
            Scanner input = new Scanner(new File(filename));
            noItems = input.nextInt();
            value = new double[noItems];
            weight = new double[noItems];
            for(int i = 0; i < noItems; i++)
                value[i] = input.nextDouble();
            for(int i = 0; i < noItems; i++)
                weight[i] = input.nextDouble();
            knapsackSize = input.nextDouble();
            populationSize= input.nextInt();
            maxGenerations = input.nextInt();
            crossProb = input.nextDouble();
            mutatProb = input.nextDouble();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found!");
            System.exit(1);
        }
    }
}
