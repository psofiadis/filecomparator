/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public abstract class AbstractFileAggregator implements FileAggregator{
    private FileHelper fileHelper = null;
    private Sort sortStrategy = null;
    private Compare compareStrategy = null;
    private Finalizer finalizeStrategory = null;
    
    
    public AbstractFileAggregator(){}
    public AbstractFileAggregator(Sort sortStrategy,Compare compareStrategy,Finalizer finalizeStrategory ){
        this.sortStrategy = sortStrategy;
        this.compareStrategy = compareStrategy;
        this.finalizeStrategory = finalizeStrategory;
    }
    
    @Override 
    public final void process(String[] args){
       try{
            System.out.println("Validating... ");
            fileHelper = validate(args); 

            System.out.println("Sorting... ");
            sort( this.sortStrategy);
            
            System.out.println("Preparing...");
            prepare();
            
            System.out.println("Comparing... ");
            compare(this.compareStrategy);

       }catch(Exception ex){
            System.out.println(ex.toString());
       }finally{
            System.out.println("Finalizing... ");
            finalize(this.finalizeStrategory);
       }
        
    }
    
    @Override
    public final FileHelper validate(String[] args) throws Exception {
        if(args == null || args.length != 3) throw new IllegalArgumentException(getUsageMessage());
        FileHelper fileHelper = new FileHelper();
        fileHelper.createAndValidateFiles(args);
        return fileHelper;
    }

    @Override
    public abstract void sort(Sort sortStrategy) throws IOException;

    @Override
    public abstract void compare(Compare compareStrategy) throws IOException;
    
    @Override
    public final void prepare(){
       fileHelper.setPrepareFilesForCompare(); 
    }

    @Override
    public abstract void finalize(Finalizer finalizeStrategy);
    
     private String getUsageMessage(){
        return "Input of these program requires three parameters\n"+
               "\t1. path to first input file\n"+
               "\t2. path to second input file\n"+
               "\t3. path to output file.";
    }

    public FileHelper getFileHelper() {
        return fileHelper;
    }
    
}
