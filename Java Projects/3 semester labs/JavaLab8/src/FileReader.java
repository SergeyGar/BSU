import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileReader extends JFileChooser {
    public String defaultPath;
    public String pathToFile;
    //  private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH);

    public FileReader(String defaultPath){
        this.defaultPath = defaultPath;
        setDialogTitle("Choose file: ");
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setCurrentDirectory(new File(defaultPath));
    }
    public Vector<Train> read()throws FileNotFoundException, IOException, ParseException, IllegalArgumentException {

        int returnValue = showOpenDialog(null);
        //  LocalDateTime

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            pathToFile = new String(getSelectedFile().getAbsolutePath());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getSelectedFile().getAbsoluteFile())));
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(getSelectedFile().getAbsolutePath()));
            Vector<Train> result = new Vector<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("-");
                int trainNumber = Integer.parseInt(data[0]);
                String destinationName = data[1];
                LocalDateTime datetime1 = LocalDateTime.parse(data[2], DateTimeFormatter.ofPattern("H:m d.M.y", Locale.ENGLISH));
                LocalDateTime datetime2 = LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("H:m d.M.y", Locale.ENGLISH));
                result.add(new Train(trainNumber, destinationName, datetime1, datetime2));
            }
            bufferedReader.close();
            return result;
        } else {
            return null;
        }
    }
}
