package strategy.compare;

import filecomparator.helper.FileHelper;
import java.io.IOException;

/**
 *
 * @author panagiotis
 */
public abstract class AbstractCompareStrategy implements Compare{
    
    @Override
    public abstract void compare(FileHelper helper) throws IOException;
    
}
