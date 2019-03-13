package main;

import java.io.IOException;
import java.util.*;

//import java.Instrumentation;

public class testClass {
    
    public static int[] populateArray(){
        //fill the array with ordered values from 1 to 99999
        int arr[] = new int[100];
        int value = 0;
        double tempDouble;
        for (int i = 0; i < arr.length-1; i++) {
            tempDouble = (((Math.random()%99999)* 10000) % 99999) + 1;
            value = (int) tempDouble;
            arr[i] = value;
        }
        //shuffle the array
        int index, temp;
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        
        Instrumentation ins = Instrumentation.getInstance();
        
        ins.activate(true);

        ins.comment("Main Testing");
        ins.startTiming("Main");


        //------------------------Instrumentation testing-------------------
        ins.comment("Instrumentation Testing");
        ins.startTiming("Instrumentation");
        ins.stopTiming("Instrumentation");
        ins.comment("\n-------------------");


        //------------------------Algorithm setup------------------------------
        
        int randArray[] = new int[100]; //this will hold our random unsorted array
        int test[] = new int[100]; //this will be where we put our sorted array
        ins.comment("Populate array Testing");
        ins.startTiming("Populate array");
        randArray = populateArray();
        ins.stopTiming("Populate array");
        ins.comment("\n-------------------");

        test = randArray.clone();

        // 1 - 99999
        
        //---------------------------Bubble sort-------------------------------------

        ins.comment("Bubble Sort Testing");
        System.out.println("Bubble Sort Testing");
        BubbleSort2Algorithm alg1 = new BubbleSort2Algorithm();
        alg1.init();
        
        for (int var : randArray) {
            System.out.print(var + " ");
        }    
        
        ins.startTiming("bubbleSort");
        alg1.sort(test);
        ins.stopTiming("bubbleSort");
    
        System.out.println("Moves: " + alg1.getTotalMoves());
        System.out.println("Compares: " + alg1.getTotalCompares());
        for(int var: test) {
        	System.out.print(var + " ");
        }
        test = randArray.clone();
        System.out.println("\n");
        

        ins.comment("\n-------------------");
        
        //----------------------------Quick sort------------------------------------
        // include instrumentation in quick,merge and selection sort classes

        ins.comment("Quick Sort Testing");

        System.out.println("Quick Sort Testing");
        NaiveQuickSortAlgorithm alg2 = new NaiveQuickSortAlgorithm();
        alg2.init();
       

        for (int var : randArray) {
            System.out.print(var + " ");
        }
        System.out.println();
        
        ins.startTiming("quickSort");
        alg2.sort(test);
        ins.stopTiming("quickSort");
        
        System.out.println("Moves: " + alg2.getTotalMoves());
        System.out.println("Compares: " + alg2.getTotalCompares());
        
        for(int var: test) {
        	System.out.print(var + " ");
        }

        test = randArray.clone();
        System.out.println("\n");

        ins.comment("\n-------------------");
        
        //--------------------------------Merge sort--------------------------------


        ins.comment("Merge Sort Testing");
        System.out.println("Merge Sort Testing");
        ExtraStorageMergeSortAlgorithm alg3 = new ExtraStorageMergeSortAlgorithm();
        alg3.init();
        
        //test = populateArray(); //fill 
        //System.out.println();
        for (int var : randArray) {
            System.out.print(var + " ");
        }    
        System.out.println();
        
        ins.startTiming("mergeSort");
        test = alg3.sort(test);
        ins.stopTiming("mergeSort");
        
        System.out.println("Moves: " + alg3.getTotalMoves());
        System.out.println("Compares: " + alg3.getTotalCompares());
        for(int var: test) {
        	System.out.print(var + " ");
        }
        test = randArray.clone();
        System.out.println("\n");
        
        ins.comment("\n-------------------");
        
        //---------------------------------Selection sort-------------------------------
        ins.comment("Selection Sort Testing");
        ins.comment("Testing Selection Sort");

        System.out.println("Selecton Sort Testing");
        SelectionSortAlgorithm alg4 = new SelectionSortAlgorithm();
        alg4.init();
              
        //test = populateArray(); //fill 
        //System.out.println();
        for (int var : randArray) {
            System.out.print(var + " ");
        }    
        System.out.println();
        
        ins.startTiming("selectionSort");
        test = alg4.sort(test);
        ins.stopTiming("selectionSort");        
        
        System.out.println("Moves: " + alg4.getTotalMoves());
        System.out.println("Compares: " + alg4.getTotalCompares());
        for(int var: test) {
        	System.out.print(var + " ");
        }
        test = randArray.clone();
        ins.comment("\n-------------------");


        //------------------------bubble sort with instrumentation in class--------------------------
        ins.comment("Bubble sort with instrumentation  Testing");
        Bubble_with_instrumentation algA = new Bubble_with_instrumentation();
        algA.init();
        ins.startTiming("bubbleSort with instrumentation main");
        algA.sort(test);
        ins.stopTiming("bubbleSort with instrumentation main");
        ins.comment("\n-------------------");
        test = randArray.clone();

        //------------------------Quick sort with instrumentation in class--------------------------
        ins.comment("Quick sort with instrumentation  Testing");
        Quick_with_instrumentation algB = new Quick_with_instrumentation();
        algB.init();
        ins.startTiming("Quick with instrumentation main");
        algB.sort(test);
        ins.stopTiming("Quick with instrumentation main");
        ins.comment("\n-------------------");
        test = randArray.clone();

        //------------------------Merge sort with instrumentation in class--------------------------
        ins.comment("Merge sort with instrumentation  Testing");
        Merge_with_instrumentation algC = new Merge_with_instrumentation();
        algC.init();
        ins.startTiming("Merge with instrumentation main");
        algC.sort(test);
        ins.stopTiming("Merge with instrumentation main");
        ins.comment("\n-------------------");
        test = randArray.clone();

        //------------------------Selection sort with instrumentation in class--------------------------
        ins.comment("Selection sort with instrumentation  Testing");
        Selection_with_instrumentation algD = new Selection_with_instrumentation();
        algD.init();
        ins.startTiming("Selection with instrumentation main");
        algD.sort(test);
        ins.stopTiming("Selection with instrumentation main");
        ins.comment("\n-------------------");

        //------------------------End of main--------------------------


        ins.stopTiming("Main");

        ins.comment("\n-------------------");
        ins.dump("testing");
        

    }


}
