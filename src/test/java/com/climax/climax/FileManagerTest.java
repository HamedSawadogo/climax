package com.climax.climax;
import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.services.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("tester les méthodes relatives aux chemins de fichiers")
public class FileManagerTest {

    @Test
    @DisplayName("tester le retour de l'extension d'un fichier sans extension")
    public  void getFileExtensionTestWithoutExtension(){
        String filePath="testFile";
        assertThrows(FileNotFoundException.class,() -> {
            FileManager.getFileExtension(filePath);
        },"ce fichier est invalide!!!");
    }

    @Test
    @DisplayName("tester la validité d'un fichier")
    public  void testValidity(){
        String file1="file.csv";
        String file="path.path.file";
        boolean res=FileManager.isValidFilePath(file1);
        assertTrue(res);
    }
    @Test
    @DisplayName("tester  la non validité d'un fichier")
    public  void testNottValidity(){
        String fileWithoutExtension="file";
        String fileEmpty="/tmp/upload-dir16341518037424950169";
        String notValidFile=".";
        String file=".json";
        String nullFile=null;

        assertFalse(FileManager.isValidFilePath(fileWithoutExtension));
        assertFalse(FileManager.isValidFilePath(fileEmpty));

    }

    @Test
    @DisplayName("tester le retour de l'extension d'un fichier avec un fichier correct")
    public  void getFileExtensionTestWithString(){
       String filePath="testFile.php";
       String filePath2="path.path.json";
       String file=" /tmp/upload-dir3925142264466705759/file.xml";

       log.info(FileManager.getFileExtension(filePath));
       log.info(FileManager.getFileExtension(filePath2));

       assertEquals(FileManager.getFileExtension(filePath),"php");
       assertEquals(FileManager.getFileExtension(file),"xml");
       assertEquals(FileManager.getFileExtension(filePath2),"json");
    }
}
