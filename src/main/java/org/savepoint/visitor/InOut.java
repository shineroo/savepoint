package org.savepoint.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void writeFile(String string, String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(fileName);
            writer.write(string);
            writer.close();
        }
        catch(IOException ex) {
            System.out.println("fuck! " + ex.getMessage());
        }
    }
}
