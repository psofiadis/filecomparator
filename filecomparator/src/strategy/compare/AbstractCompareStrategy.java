package strategy.compare;

import filecomparator.helper.FileHelper;

/**
 *
 * @author panagiotis
 */
public abstract class AbstractCompareStrategy implements Compare{
    
    @Override
    public abstract void compare(FileHelper helper);
    
}
