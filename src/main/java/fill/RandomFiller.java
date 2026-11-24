/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fill;

import java.util.Random;
import model.Animal;
import model.MyArrayList;

/**
 *
 * @author Алексей Калинин
 */
public class RandomFiller
{
    public RandomFiller()
    {
        m_random = new Random();
    }

    public void fill(MyArrayList<Animal> collection, int fillCount)
    {
        if(collection == null || fillCount <= 0)
        {
            return;
        }
        
        String[] randomNames =
        {
            "Kitty",
            "Mitty",
            "Ritty",
            "Sitty",
            "Litty",
            "Gitty",
            "Zitty",
            "Citty",
            "Vitty"
        };
        
        for(int i = 0; i < fillCount; ++i)
        {
            Animal.Builder builder = new Animal.Builder();
            builder.setName(randomNames[m_random.nextInt(randomNames.length)]);
            builder.setAge(m_random.nextInt(101));
            builder.setWeight(m_random.nextDouble(1, 10000));
            collection.add(builder.build());
        }
    }
    
    private final Random m_random;
}
