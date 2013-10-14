/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.finalize;

import filecomparator.helper.FileHelper;

/**
 *
 * @author panagiotis
 */
public class DefaultFinalizerStrategyImpl extends AbstractFinalizerStrategy{

    @Override
    public void finalize(FileHelper fileHelper) {
       if(fileHelper != null) fileHelper.clear();
    }
    
}
