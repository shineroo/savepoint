package org.savepoint.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InOut {

    public static String readFile(String fileName)
    {
        try{
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            StringBuilder allFile = new StringBuilder();
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                allFile.append(data);
                if(reader.hasNextLine())
                    allFile.append("\n");
            }
            reader.close();
            return allFile.toString();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
            SavepointVisitorImpl.SYSTEM_OUT.append(ex.getMessage()).append("\n");
        }
        return null;
    }
}
