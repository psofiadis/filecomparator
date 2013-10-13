package strategy.sort;

import filecomparator.helper.FileHelper;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import utils.sorting.ExternalSort;
import utils.FileHolder;

/**
 *
 * @author panagiotis
 */
public class DefaultSortStrategyImpl extends AbstractSortStrategy{
    
    @Override
    public void sort(FileHelper fileHelper) throws IOException{
        
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String r1, String r2){
                return r1.compareTo(r2);}};
        
        FileHolder sortedLargerFile = fileHelper.getListOfFiles().get(0);
        List<File> l1 = ExternalSort.sortInBatch(sortedLargerFile, comparator) ;
        ExternalSort.mergeSortedFiles(l1, sortedLargerFile.getSortedFile(), comparator);
        
        FileHolder sortedSmallerFile = fileHelper.getListOfFiles().get(1);
        List<File> l2 = ExternalSort.sortInBatch(sortedSmallerFile, comparator) ;
        ExternalSort.mergeSortedFiles(l2, sortedSmallerFile.getSortedFile(), comparator);
    }
}
