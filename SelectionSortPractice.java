import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
//Brett Rogers
//Practice 
class SelectionSortPractice
{

     /**
     * Reads a text file --BR
     */
    public static int[] ReadTextFile(int limit, String fileName) throws FileNotFoundException {//Found a youtube video on how to read a file 
        File file = new File(fileName);						//adjusted to our needs				
        Scanner scan = new Scanner(file);
		int[] array = new int[limit];//this was hard coded, but this allows for more tests, quickly
		int iteration = 0;//instead of a for loop for iterating the arrays max size, use the while loop to iterate manually
        while (scan.hasNextLine() && iteration < array.length) //if there are more lines to read, and iteration hasn't reached max,
		{                                                      //go ahead and fill values in array
			array[iteration] = Integer.parseInt(scan.nextLine());			
			iteration++;
        }
		//return array; //array.length is the max amount, 100000 values, send it back 
		return Arrays.copyOf(array, iteration);//took some research, had no idea this was a thing! 

    }

    //Swap elements
    public static void Swap(int[] array, int index, int otherIndex)
    {
        int temp = array[index];
        array[index] = array[otherIndex];
        array[otherIndex] = temp;
    }

    //selection sort algorithm
    //find the lowest element in the list, swap them with start, repeat

    public static int LowestIndex(int[] array, int start)
    {
        //assume start is low index, set it
        int lowIndex = start; 
        //loop through the list from start
        for(int i = start; i < array.length; i++)
        {
            //search for lowest number
            if(array[i] < array[lowIndex])//if current element in list is less than low
            {
                //when found, low index is set to current search element
                lowIndex = i;
            }
            
        }
        return lowIndex;// hand back the lowest index to be swapped
    }


    public static void SelectionSort(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            int j = LowestIndex(array, i);
            Swap(array, i, j);
        }
    }

    public static void main(String args[]) throws FileNotFoundException
    {
        int[] array = ReadTextFile(5000, "random.txt");
        
        //System.out.println("Array Before Sort: " + Arrays.toString(array));

        SelectionSort(array);

        System.out.println("Array After Sort: \n" + Arrays.toString(array));


        
    }
}