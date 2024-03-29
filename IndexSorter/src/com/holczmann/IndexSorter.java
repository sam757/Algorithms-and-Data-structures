package com.holczmann;

import java.util.Arrays;
import java.util.Random;

public class IndexSorter {
	
	/**
	 * Java implemented quick sort
	 * @param a: array as in/out parameter
	 * @return runtime of the sorting
	 */
	public static long quicksort(int[] a) {
		long startTime = System.nanoTime();
	
		Arrays.sort(a);
		
		long finishTime = System.nanoTime();
		long runTime = finishTime - startTime;
		return runTime;
	}
	
	/**
	 * Java implemented bin sort
	 * @param a: array as in/out parameter
	 * @return runtime of the sorting
	 */
	public static long binsort(int[] a) {
		long startTime = System.nanoTime();
	
		BinSorter.binsort(a);
		
		long finishTime = System.nanoTime();
		long runTime = finishTime - startTime;
		return runTime;
	}
	
	/**
	 * Index Sorter
	 * @param a: array as in/out parameter
	 * @return runtime of the sorting
	 */
	public static long indexsort(int[] a) {
		long startTime = System.nanoTime();

		// Search for the maximal and minimal number in the array
		int maxItem = 0;
		int minItem = Integer.MAX_VALUE;
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] > maxItem) maxItem = a[i];
			if (a[i] < minItem) minItem = a[i];
		}
				
		// Create helper array. Size is the maximal number from the input.
		// Use the number from input array as index and set true by this index in the helper array. 
		boolean[] b = new boolean[maxItem + 1];
		for (int i = 0; i < a.length; i++) {
			b[a[i]] = true;
		}
		
		
		// Loop over the helper array and use the index as value and set back into the original array.
		int pointer = 0;
		for (int i = minItem; i < b.length; i++) {
			if (b[i]) {
				a[pointer] = i;
				pointer++;
			}
		}
		
		long finishTime = System.nanoTime();
		long runTime = finishTime - startTime;
	
		return runTime;
	}
	
	/**
	 * Reference implementation of count sort
	 * @param a: array as in/out parameter
	 * @return runtime of the sorting
	 */
	public static long countsort(int[] a) {
		long startTime = System.nanoTime();
	
		int[] b = CountSorter.countSort(a);
		
		long finishTime = System.nanoTime();
		long runTime = finishTime - startTime;
		
		// Copy the sorted elements into original array
		for (int i = 0; i < a.length; i++) {
			a[i] = b[i];
		}
		
		
		return runTime;
	}
	
	/**
	 * Fill array with random distinct numbers
	 * @param a: array as in/out parameter
	 * @param range: interval of natural numeric number 
	 */
	private static void generateSample(int[] a, int range) {
		Random rand = new Random();
		int i = 0;
		boolean done = false;
		while (!done) {
			int number = rand.nextInt(range);
			boolean found = false;
			for (int j = 0; j < i && !found; j++) {
				if (a[j] == number) {
					found = true;
				}
			}
			if(!found) {
				a[i] = number;
				i++;
				if (i == a.length) done = true;
			}
		}
	}

	/**
	 * IndexSorter vs QuickSorter test application
	 * @param args: number of samples to be generated as int;
	 * 				size of the array as int;
	 * 				range of sortable numbers as int;  
	 * 				print statistic only as boolean
	 */
	public static void main(String[] args) {
		// Default size, range and print the sample
		int size = 1000;
		int range = 10000;
		int numberOfSamples = 100;
		boolean statisticOnly = true;
		int quickWin = 0;
		int indexWin = 0;
		int countWin = 0;
		int binWin = 0;
		
		// Read the input parameters
		if (args.length > 0) {
		    try {
		    	numberOfSamples = Integer.parseInt(args[0]);
		        size = Integer.parseInt(args[1]);
		        range = Integer.parseInt(args[2]);
		        statisticOnly = Boolean.parseBoolean(args[3]);
		    } catch (NumberFormatException e) {
		        System.out.println("Argument" + args[0] + " must be an integer.");
		        System.out.println("Argument" + args[1] + " must be an integer.");
		        System.out.println("Argument" + args[2] + " must be an integer.");
		        System.out.println("Argument" + args[3] + " must be an boolean.");
		        System.exit(1);        
		    } catch (Exception e) {
		        System.out.println("Argument: number of samples to be generated as int;	size of the array as int; range of sortable numbers as int;	print statistic only as boolean");
		    } 		
		}
		
		if(range < size) {
			System.out.println("Size of the array must be less then the number range!");
			System.exit(1); 
		}
		
		// Sample Generation
		for (int count = 0; count < numberOfSamples; count++) {
			int[] originalSample = new int[size];
			IndexSorter.generateSample(originalSample, range);
			System.out.println("Sample generated (size:" + size + " / intervall: " + range + ") ");
			if (!statisticOnly)
				System.out.println(Arrays.toString(originalSample));

			// QuickSort
			int[] a = originalSample.clone();

			//long runTimeQuick = IndexSorter.guicksort(a);
			long runTimeQuick = IndexSorter.quicksort(a);
			
			System.out.println("QuickSort (" + runTimeQuick + "ns) ");
			if (!statisticOnly)
				System.out.println(Arrays.toString(a));

			// CountSort
			a = originalSample.clone();

			long runTimeCount= IndexSorter.countsort(a);

			System.out.println("CountSort (" + runTimeCount + "ns) ");
			if (!statisticOnly)
					System.out.println(Arrays.toString(a));
			
			// IndexSort
			a = originalSample.clone();

			long runTimeIndex = IndexSorter.indexsort(a);

			System.out.println("IndexSort (" + runTimeIndex + "ns) ");
			if (!statisticOnly)
				System.out.println(Arrays.toString(a));
			
			// BinSort
			a = originalSample.clone();

			long runTimeBin = IndexSorter.binsort(a);

			System.out.println("BinSort (" + runTimeBin + "ns) ");
			if (!statisticOnly)
				System.out.println(Arrays.toString(a));
			
			//long percentage = ((runTimeQuick - runTimeIndex) * 100) / runTimeQuick;
			//System.out.println("Runtime reduction (QuickSort - IndexSort / QuickSort) : " + percentage + "% (" + (runTimeQuick - runTimeIndex) + "ns)" );
			
			if (runTimeQuick < runTimeIndex && runTimeQuick < runTimeCount && runTimeQuick < runTimeBin) quickWin++;
			else if (runTimeIndex < runTimeQuick && runTimeIndex < runTimeCount && runTimeIndex < runTimeBin) indexWin++;
			else if (runTimeCount < runTimeQuick && runTimeCount < runTimeIndex && runTimeCount < runTimeBin) countWin++;
			else binWin++;	
			
			
			
			
					
		}// for
		System.out.println("Wins QuickSort:" + quickWin + " IndexWin:" + indexWin + " CountWin: " + countWin + " binWin:" + binWin);
	}// main
}
