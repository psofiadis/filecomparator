package strategy.compare;

import filecomparator.helper.FileHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;

/**
 *
 * @author panagiotis
 */
public class DefaultCompareStrategyImpl extends AbstractCompareStrategy{
    
    @Override
    public void compare(FileHelper fileHelper) throws IOException{
        
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String r1, String r2){
                return r1.compareTo(r2);}};
        
        File smallerFile = fileHelper.getSmallerSortedFile();
        File largerFile = fileHelper.getLargerSortedFile();
        File outputFile = fileHelper.getOutputFile();
        outputFile.getParentFile().mkdirs(); 
        outputFile.createNewFile();
        OutputStreamWriter outputWriter = new OutputStreamWriter(new FileOutputStream(outputFile, true), "UTF-8");
        BufferedWriter fbw = new BufferedWriter(outputWriter);
        
        //Splitting files according to size and keeping in memory indexing would be better?
        //TODO test split files and indexing
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(smallerFile), "UTF8"));
        try{
            String line;
            while ((line = br.readLine()) != null) {
               if(line.trim().isEmpty()) continue;
               BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(largerFile), "UTF8"));
               String line1;
               while ((line1 = br1.readLine()) != null) {
                  int comparisonResult = comparator.compare(line,line1);
                  if( comparisonResult == 0){
                      fbw.append(line);
                      fbw.newLine();
                  }else if( comparisonResult > 0){
                      continue;
                  }else{
                      br1.close();
                      break;
                  }
               }
            }
        }catch(Exception ex){
            fbw.close();
            br.close();
            
        }finally{
            fbw.close();
            br.close();
            
        }
    }
}
