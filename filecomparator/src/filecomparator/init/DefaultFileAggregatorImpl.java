package filecomparator.init;

import java.io.IOException;
import strategy.compare.Compare;
import strategy.compare.DefaultCompareStrategyImpl;
import strategy.finalize.DefaultFinalizerStrategyImpl;
import strategy.finalize.Finalizer;
import strategy.sort.DefaultSortStrategyImpl;
import strategy.sort.Sort;

/**
 *
 * @author panagiotis
 */
public class DefaultFileAggregatorImpl extends AbstractFileAggregator{
    
    public DefaultFileAggregatorImpl(){
        super(new DefaultSortStrategyImpl(),
              new DefaultCompareStrategyImpl(),
              new DefaultFinalizerStrategyImpl()
                );
    }
    

    @Override
    public void sort(Sort sortStrategy) throws IOException {
        sortStrategy.sort(this.getFileHelper());
    }

    @Override
    public void compare(Compare compareStrategy) throws IOException {
       compareStrategy.compare(this.getFileHelper());
    }

    @Override
    public void finalize(Finalizer finalizeStrategy) {
        finalizeStrategy.finalize(this.getFileHelper());
    }
    
}
