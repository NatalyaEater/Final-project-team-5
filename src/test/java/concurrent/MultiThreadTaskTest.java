/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package concurrent;

import model.Animal;
import model.MyArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author singl
 */
public class MultiThreadTaskTest
{
    public MultiThreadTaskTest()
    {
    }

    Animal getAnimal(String name, int age, float weight)
    {
        Animal.Builder builder = new Animal.Builder();
        builder.setAge(age);
        builder.setName(name);
        builder.setWeight(weight);
        return builder.build();
    }
    
    /**
     * Test of countOccurкences method, of class MultiThreadTask.
     */
    @Test
    public void testCountOccurrencesWithEmptyList()
    {
        MyArrayList list = null;
        int expectedResult = 0;
        
        int result = MultiThreadTask.countOccurrences(list, getAnimal("Kitty", 1, 1.0f));
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of countOccurкences method, of class MultiThreadTask.
     */
    @Test
    public void testCountOccurrencesWithFilledList()
    {
        MyArrayList list = new MyArrayList();
        final Animal animal = getAnimal("Kitty", 1, 1.0f);
        list.add(animal);
        list.add(animal);
        list.add(animal);
        
        int expectedResult = 3;
        
        int result = MultiThreadTask.countOccurrences(list, animal);
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of countOccurкences method, of class MultiThreadTask.
     */
    @Test
    public void testCountOccurrencesWithFilledListDifferentObjects()
    {
        MyArrayList list = new MyArrayList();
        
        int expectedResult = 15;
        
        for(int i = 0; i < expectedResult; ++i)
        {
            list.add(getAnimal("Kitty", 1, 1.0f));
            list.add(getAnimal("Mitty", 1, 1.0f));
        }
                
        int result = MultiThreadTask.countOccurrences(list, getAnimal("Kitty", 1, 1.0f));
        assertEquals(expectedResult, result);
    }
}
