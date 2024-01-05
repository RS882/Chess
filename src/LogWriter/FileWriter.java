package LogWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriter {
    private static File createFile(String path) {
        File file = new File(path);
        try {
            System.out.println(
                    (file.createNewFile())?
                    String.format("File created <%s>.", file.getName()):
                    String.format("File <%s> already exists.", file.getName()));
        } catch (IOException e) {
            System.out.printf("File creation error <%s>.", e.getMessage());
        }
        return file;
    }
    public static void whriteToFile(String text){
        File file = createFile("chesslog.txt");
        try{
            FileOutputStream stream = new FileOutputStream(file,true);
            OutputStreamWriter writer =new OutputStreamWriter(stream,"UTF-8");
            writer.write("\n"+text);
            writer.close();
            stream.close();
            System.out.printf("Write to <%s> successful.", file.getName());

        } catch (IOException e) {
            System.out.printf("File write error: <%s>.", e.getMessage());
        }
    }
}
