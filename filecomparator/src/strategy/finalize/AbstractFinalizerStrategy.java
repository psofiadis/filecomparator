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
public abstract class AbstractFinalizerStrategy implements Finalizer{

    @Override
    public abstract void finalize(FileHelper fileHelper);
    
}
