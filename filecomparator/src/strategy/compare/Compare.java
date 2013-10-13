package strategy.compare;

import filecomparator.helper.FileHelper;
import java.io.IOException;

/**
 *
 * @author panagiotis
 */
public interface Compare {
    public void compare(FileHelper fileHelper) throws IOException;
}
