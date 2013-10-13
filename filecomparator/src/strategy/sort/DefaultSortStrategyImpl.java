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
        FileHolder sorted = fileHelper.getListOfFiles().get(fileHelper.getLargerFileIndex());
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String r1, String r2){
                return r1.compareTo(r2);}};
        List<File> l = ExternalSort.sortInBatch(sorted, comparator) ;
        ExternalSort.mergeSortedFiles(l, sorted.getSortedFile(), comparator);
    }
}
