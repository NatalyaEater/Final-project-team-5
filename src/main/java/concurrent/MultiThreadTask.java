package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import model.Animal;
import model.MyArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Алекесй Калинин
 */
public class MultiThreadTask
{
    public static int countOccurrences(MyArrayList<Animal> list, Animal value)
    {
        var result = new AtomicInteger(0);
        if(list == null || value == null)
        {
            return result.get();
        }
        
        final int threadCount = 4;
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        Future[] futures = new Future[threadCount];
        
        for(int i = 0; i < threadCount; ++i)
        {
            final int chunkSize = list.size() / threadCount;
            final int start = i * chunkSize;
            final int count = (i == (threadCount - 1))?
                    chunkSize + list.size() % threadCount : chunkSize;
            
            futures[i] = service.submit(() ->
            {
                for (int i1 = start; i1 < start + count; ++i1)
                {
                    if (list.get(i1).equals(value))
                    {
                        result.incrementAndGet();
                    }
                }
            });
        }
        
        for(int i = 0; i < threadCount; ++i)
        {
            try
            {
                futures[i].get();
            }
            catch (InterruptedException | ExecutionException e)
            {
            }
        }
        
        service.shutdown();
        return result.get();
    }
}
