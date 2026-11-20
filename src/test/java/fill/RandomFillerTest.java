/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fill;

import model.MyArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author singl
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
        MyArrayList array = new MyArrayList();
        int arraySize = 10;
        filler.fill(array, arraySize);
        
        assertEquals(arraySize, array.size());
        for(int i = 0; i < arraySize; ++i)
        {
            assertNotEquals(array.get(i), null);
        }
    }
    
}
