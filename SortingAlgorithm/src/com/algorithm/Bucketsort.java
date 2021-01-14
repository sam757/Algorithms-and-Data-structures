package com.algorithm;


import java.util.Arrays;

import java.util.Iterator;
import java.util.LinkedList;

public class Bucketsort {
	
	static void bucketsort(int[] A) {
		  int MaxKeyValue = 0;
		  for (int i = 0; i < A.length; i++) {
				if (A[i] > MaxKeyValue) MaxKeyValue = A[i];
			}
			    
		  LinkedList<Integer>[] B = new LinkedList[MaxKeyValue+1];
		  int item;
		  for (int i = 0; i <= MaxKeyValue; i++)
		    B[i] = new LinkedList();
		  for (int i = 0; i < A.length; i++) B[A[i]].add(A[i]);
		  int pos = 0;
		  for (int i=0; i<=MaxKeyValue; i++){
			  Iterator<Integer> iterator = B[i].iterator();
			  while (iterator.hasNext()) {
				  item = iterator.next();
				  A[pos++] = item;
			  }
			 }
		}
	  }
	