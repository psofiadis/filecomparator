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
    private int largerFileIndex = 0;
    private int smallerFileIndex = 0;
    
    private File smallerSortedFile = null;
    private File largerSortedFile = null;
    
    public void createAndValidateFiles (String ... files) 
                                            throws IllegalArgumentException,RuntimeException{
        if(listOfFiles != null) throw new RuntimeException("Process has already been started");
        listOfFiles = new ArrayList<>();
        int argLength = files.length; 
        for(int i=0; i<argLength-1; i++){
            FileHolder fileSorter = new FileHolder(files[i], FileHolder.SOURCE,SHORTED_FILE_TYPE);
            fileSorter.validate();
            listOfFiles.add(fileSorter);
        }
        outputFile = new FileHolder(files[argLength-1], FileHolder.OUTPUT,SHORTED_FILE_TYPE);
        outputFile.validate();  
    } 
    
    public void setPrepareFilesForCompare(){
        if(listOfFiles.get(0).getSortedFile().length()>listOfFiles.get(1).getSortedFile().length()){
           smallerSortedFile = listOfFiles.get(1).getSortedFile();
           largerSortedFile = listOfFiles.get(0).getSortedFile();
        }else{
           smallerSortedFile = listOfFiles.get(0).getSortedFile();
           largerSortedFile = listOfFiles.get(1).getSortedFile(); 
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

    public File getSmallerSortedFile() {
        return smallerSortedFile;
    }

    public File getLargerSortedFile() {
        return largerSortedFile;
    }
     
}
