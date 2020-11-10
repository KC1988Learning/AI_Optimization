package org.algorithm.AStarSearch;

import java.util.ArrayList;
import java.util.List;

public class Test_Array_List {

    // This example demonstrates that an array stores the reference to the objects in it.
    // Therefore, after you add some elements of the array to a list, changing the
    // attributes of the elements would be reflected if you access the list.
    //
    // In Java, an array either stores primitive values (int, char, ...) or
    // references (aka pointers) to objects.
    // When an object is created using 'new', a memory space is allocated in the heap
    // and a reference is returned.
    
    public static void main(String[] args){
        Car[][] carArray = new Car[2][2];

        carArray[0][0] = new Car(100,100);
        carArray[0][1] = new Car(300,300);
        carArray[1][0] = new Car(500,500);
        carArray[1][1] = new Car(700,700);


        List<Car> carList = new ArrayList<>();
        carList.add(carArray[0][0]);
        carList.add(carArray[1][1]);
        System.out.println("==============Before changing anything===========");
        System.out.println("Height of the first car list: ");
        System.out.println(carList.get(0).getHeight());
        System.out.println(carList.get(1).getWidth());

        System.out.println("==============After changing the car height in the top left element===============");
        carArray[0][0].setHeight(1000);
        carArray[1][1].setWidth(4000);
        System.out.println("Height of the first car in the list: ");
        System.out.println(carList.get(0).getHeight());
        System.out.println(carList.get(1).getWidth());
    }
}
