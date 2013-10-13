package utils;

import java.io.File;

/**
 *
 * @author panagiotis
 */
public class FileHolder extends File{
    
    public static final String SOURCE = "Input";
    public static final String OUTPUT = "Output";
    
    private final String type;
    private File sortedFile;
    
    
    public FileHolder( String filename,String type, String shortedfileType){
        super(filename);
        this.type = type;
        sortedFile = new File(filename+shortedfileType);
    }  
    
    public void validate() 
                        throws IllegalArgumentException,RuntimeException {
        if(!this.exists() && this.type.equals(SOURCE)) 
            throw new IllegalArgumentException("Source file does not exist " + this.getName());
        if(this.sortedFile.exists())
            throw new RuntimeException("File is already being sorted " + this.getName());
    }
    
    public String getType() {
        return type;
    }

    public File getSortedFile() {
        return sortedFile;
    }
    
}
