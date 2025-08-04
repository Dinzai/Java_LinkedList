//Brett Rogers

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Node 
{

    Node(int d) 
    {
        data = d;
    }

    int data;
    Node next;

}
class LinkedListPractice 
{

    Node head;


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

    // returns a new List
    public static LinkedListPractice Add(LinkedListPractice list, int newData) 
    {
        // create a new node

        Node newNode = new Node(newData);
        // check if list is empty
        if (list.head == null) {
            // if so, make a new node, the new node is head
            list.head = newNode;
        } else// otherwise if the list contains elements, add to end
        {
            Node last = list.head;// say hey, that last node starts at head
            while (last.next != null)// search for the next elements
            {
                last = last.next;// and set our search to the next node,
                                 // This is similar to us swapping
            }
            // once we have hit null,(the last element)
            // add that element to out list
            last.next = newNode;
        }
        return list;
    }

    public static LinkedListPractice RemoveByValue(LinkedListPractice list, int value)
    {
        Node previousNode = null;
        Node currentNode = list.head;
        //if found, set
        if(currentNode != null && currentNode.data == value)
        {
            list.head = currentNode.next;
        }
        //if not found, continue search
        while(currentNode != null && currentNode.data != value)
        {
            previousNode = currentNode;
            currentNode = currentNode.next;
            
        }
        //set
        if(currentNode != null)
        {
            previousNode.next = currentNode.next;
        }
        //value not found
        if(currentNode == null)
        {
            System.out.println("Remove by Value not found! ");
        }
        return list;
    }

    public static LinkedListPractice RemoveByIndex(LinkedListPractice list, int index)
    {
        Node previousNode = null;
        Node currentNode = list.head;
        int i = 0;

        if(index == 0 && currentNode != null)
            {
                list.head = currentNode.next;
                return list;
            }

        while(currentNode != null)
        {
            
            if(i == index)
            {
               previousNode.next = currentNode.next;
               break;
            }
            else
            {
                previousNode = currentNode;
                currentNode = currentNode.next;
                i++;
            }
        }

        if(currentNode == null)
        {
            System.out.println("Index to remove not found! ");
        }
        return list;

    }

    public void Swap(LinkedListPractice list, int a, int b)
    {
        Node nodeA = null;
        Node nodeB = null;
        Node currentNode = list.head;//find current node
        int i = 0;
        //don't compare data, find the index !
        while(currentNode != null)
        {
            if(i == a)
            {
                nodeA = currentNode;
            }
            if(i == b)
            {
                nodeB = currentNode;
            }
            currentNode = currentNode.next;
            i++;
        }//search through the nodes until a and b are found

        if(nodeA != null && nodeB != null)
        {
            int temp = nodeA.data;
            nodeA.data = nodeB.data;
            nodeB.data = temp;
        }
    }

    public static int LowestIndex(LinkedListPractice list, int start)
    {
        
        int currentIndex = 0;
        Node current = list.head;
        while(currentIndex < start && current != null)
        {
            current = current.next;
            currentIndex++;
        }//find index less than start
        int minimumIndex = currentIndex;//set
        int minimumNodeValue = current.data;
        
        while(current != null)
        {
            if(current.data < minimumNodeValue)
            {
                minimumNodeValue = current.data;
                minimumIndex = currentIndex;
                
            }
            current = current.next;
            currentIndex++;
        }
        return minimumIndex; // not the value, the index

    } 

    public static void SelectionSort(LinkedListPractice list)
    {
        Node currentNode = list.head;
        int index = 0;
        while(currentNode != null)
        {
            int j = LowestIndex(list, index);
            list.Swap(list, index, j);
            currentNode = currentNode.next;
            index++;
        }
        
    }


    public static void PrintList(LinkedListPractice list) {
        Node currentNode = list.head;// find the current head
        // search through the list of nodes
        while (currentNode != null) {
            System.out.println(currentNode.data + " ");// print
            // then swap to next, repeats until end
            currentNode = currentNode.next;
        }
    }
public static void main(String args[]) throws FileNotFoundException 
    {
        LinkedListPractice list = new LinkedListPractice();
        int[] array = ReadTextFile(5000, "random.txt");
        for(int i = 0; i < array.length; i++)
        {
            int temp = array[i];
            list = Add(list, temp);
        }
        SelectionSort(list);
        RemoveByIndex(list, 0);
        PrintList(list);
 
    }

}


