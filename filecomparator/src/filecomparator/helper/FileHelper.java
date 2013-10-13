package filecomparator.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import utils.FileHolder;

/**
 *
 * @author panagiotis
 */
public class FileHelper {
    
    public static final String SHORTED_FILE_TYPE = ".srt";
    
    private FileHolder outputFile;
    private List<FileHolder> listOfFiles = null;
    private int largerFileIndex = -1;
    
    public void createAndValidateFiles (String ... files) 
                                            throws IllegalArgumentException,RuntimeException{
         
         if(listOfFiles != null) throw new RuntimeException("Process has already been started");
         listOfFiles = new ArrayList<FileHolder>();
         int argLength = files.length;          
         long largerFileSize = 0L;
         try{
            for(int i=0; i<argLength-1; i++){
                FileHolder fileSorter = new FileHolder(files[i], FileHolder.SOURCE,SHORTED_FILE_TYPE);
                fileSorter.validate();
                if(largerFileSize < fileSorter.length()){
                    largerFileSize = fileSorter.length();
                    largerFileIndex = i;
                }
                listOfFiles.add(fileSorter);
            }
            FileHolder outputFile = new FileHolder(files[argLength-1], FileHolder.OUTPUT,SHORTED_FILE_TYPE);
            outputFile.validate();  
         }catch(IllegalArgumentException ex){
            clear();
            throw ex; 
         }catch(RuntimeException ex){
            clear();
            throw ex; 
         }
    }    

    public void clear(){
       if(listOfFiles == null) return;
       for(FileHolder sorter : listOfFiles ){
           sorter.getSortedFile().delete();
       }
       if(outputFile != null) outputFile.getSortedFile().delete();
    }
    
     public File getOutputFile() {
        return outputFile;
    }

    public List<FileHolder> getListOfFiles() {
        return listOfFiles;
    }

    public int getLargerFileIndex() {
        return largerFileIndex;
    }
     
     

}
