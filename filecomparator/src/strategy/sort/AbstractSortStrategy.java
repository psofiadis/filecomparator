package strategy.sort;

import filecomparator.helper.FileHelper;
import java.io.IOException;

/**
 *
 * @author panagiotis
 */
public abstract class AbstractSortStrategy implements Sort{
    
    @Override
    public abstract void sort(FileHelper helper) throws IOException;
    
}
