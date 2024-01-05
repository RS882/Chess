package LogWriter;

import ChessBoard.ChessBoard;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileWriter {
    private static int countOfRecords=1 ;
    private static final String name =  System.currentTimeMillis()+"";
    private final static File file = createFile(name+".log");


    private static File createFile(String path) {
        File file = new File(path);
        try {
            if(!file.createNewFile())
                System.out.printf("File <%s> already exists.%n", file.getName());
//            System.out.println(
//                    (file.createNewFile()) ?
//                           String.format("File created <%s>.%n", file.getName()):
//                            String.format("File <%s> already exists.%n", file.getName()));
        } catch (IOException e) {
            System.out.printf("File creation error <%s>.%n", e.getMessage());
        }
        return file;
    }

    public static void writeRecordToFile(String text) {
        try {
           String rec = String.format("%d. <%s> move.\n %s %s",
                   countOfRecords,
                   ChessBoard.getColorOfMove()?"White":"Black",
                   text, getDateAndTimeNow());
            writeToFile(rec);
            countOfRecords++;
        } catch (IOException e) {
            System.out.printf("File write error: <%s>.%n", e.getMessage());
        }
    }


    public static void writeToFileStartText() {
        try {
            String text = String.format("Chess game #%s started.%s",
                    name,
                    getDateAndTimeNow());
            writeToFile(text);
        } catch (IOException e) {
            System.out.printf("File write error: <%s>.%n", e.getMessage());
        }
    }

    private static void writeToFile(String text) throws IOException {
        FileOutputStream stream = new FileOutputStream(file, true);
        OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
        writer.write(text + "\n");
        writer.close();
        stream.close();
     //   System.out.printf("Write to <%s> successful.%n", file.getName());
    }
    private static String getDateAndTimeNow(){
        return String.format("{%s %s}",
                LocalDate.now(),
                LocalTime.now());
    }

}
