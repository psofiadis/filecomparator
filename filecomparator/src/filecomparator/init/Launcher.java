package filecomparator.init;

import filecomparator.helper.FileHelper;

/**
 *
 * @author panagiotis
 */

public class Launcher {
    
    private static Launcher _instance;
    private FileHelper fileHelper;
    
    private Launcher(){
        System.out.println("Initializing program ... ");
    }
   
    private void run(String args[]){
        (new DefaultFileAggregatorImpl()).process(args);
    }
    
    public static synchronized void initProgram(String[] args){
        if(_instance == null)
            _instance = new Launcher();
        _instance.run(args);
    }
}



