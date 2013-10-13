
package filecomparator.init;

import java.io.File;

/**
 *
 * @author panagiotis
 */

public class Launcher {
    private static Launcher _instance;
    
    private Launcher(){
        System.out.println("Initializing program ... ");
    }
   
    private void init(String args[]){
        try{
            validate(args); 
        }catch(Exception ex){
            System.out.println("");
        }finally{
            
        }
        
    }
    
    private void validate(String args[]) throws Exception{
        if(args == null || args.length != 3) throw new IllegalArgumentException(getUsageMessage());
        for(int i=0; i<3; i++){
            String fileName = args[i];
            if(i < 2){
                
                String lockFileName = fileName+".lck";
                File lockFile = new File(lockFileName);
                if(lockFile.exists()) throw new Exception(getInProgressError(fileName));
                File sourceFile = new File(fileName);     
                if(!sourceFile.exists()) throw new IllegalArgumentException();
                
            }else if(i == 2){
                
            }
        }
    }
    
    private String getUsageMessage(){
        return "Input of these program requires three parameters\n"+
               "\t1. path to first input file\n"+
               "\t2. path to second input file\n"+
               "\t3. path to output file.";
    }
    
    private String getFileDoesNotExistError(String fileName){
       return "File " + fileName + " not found"; 
    }
    
    private String getInProgressError(String fileName){
       return "File " + fileName + " is already being processed";
    }
    
    public static synchronized void initProgram(String[] args){
    if(_instance == null)
        _instance = new Launcher();
    _instance.init(args);
}
}



