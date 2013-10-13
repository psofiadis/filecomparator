package strategy.sort;

import filecomparator.helper.FileHelper;
import java.io.IOException;

/**
 *
 * @author panagiotis
 */
public interface Sort {
    public void sort(FileHelper fileHelper) throws IOException;
}
