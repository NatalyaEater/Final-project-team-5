/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fill;

import model.Animal;
import model.MyArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Алексей Калинин
 */
public class RandomFillerTest
{
    public RandomFillerTest()
    {
    }

    @Test
    public void testFillCount()
    {
        RandomFiller filler = new RandomFiller();
        MyArrayList<Animal> array = new MyArrayList<>();
        int arraySize = 10;
        filler.fill(array, arraySize);
        
        assertEquals(arraySize, array.size());
        for(int i = 0; i < arraySize; ++i)
        {
            assertNotEquals(array.get(i), null);
        }
    }
    
    @Test
    public void testNullCollection()
    {
        RandomFiller filler = new RandomFiller();
        MyArrayList<Animal> array = null;
        int arraySize = 10;
        filler.fill(array, arraySize);
    }
    
    @Test
    public void testNullRepeatCount()
    {
        RandomFiller filler = new RandomFiller();
        MyArrayList<Animal> array = new MyArrayList<>();
        int arraySize = 0;
        filler.fill(array, arraySize);
        assertEquals(arraySize, array.size());
    }
}
