import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

enum FileAction {
    OPEN,
    SAVE
}

public class FileManager {

    JFrame parent;

    public FileManager(JFrame parent) {
        this.parent = parent;
    }

    public File performFileDialog(String filterString, String fileExtension, FileAction fileAction) throws Exception {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(filterString, fileExtension);
        chooser.setFileFilter(filter);
        switch (fileAction) {
            case OPEN:

                if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
                    return chooser.getSelectedFile();
                }
                break;

            case SAVE:
                if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
                    return chooser.getSelectedFile();
                }
                break;

            default:
                throw new UnsupportedOperationException("Not supported file operation");
        }
        return null;
    }
}