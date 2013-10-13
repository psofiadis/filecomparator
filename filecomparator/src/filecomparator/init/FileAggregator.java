package filecomparator.init;

import filecomparator.helper.FileHelper;
import java.io.IOException;
import strategy.compare.Compare;
import strategy.finalize.Finalizer;
import strategy.sort.Sort;

/**
 *
 * @author panagiotis
 */
public interface FileAggregator {
    public void process(String[] args)throws Exception;;
    public FileHelper validate(String[] args)throws Exception;
    public void sort(Sort sortStrategy) throws IOException;
    public void prepare();
    public void compare(Compare compareStrategy)throws IOException;
    public void finalize(Finalizer finalizeStrategy) throws Exception;
}
