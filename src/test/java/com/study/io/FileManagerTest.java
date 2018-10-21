package com.study.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class FileManagerTest {

    private File[] files = new File[18];
    private String pathFrom = "temp";
    private String pathTo = "temp_to_copy";
    private String sep = File.separator;

    @Before
    public void before() throws IOException {
        files[0] = new File(pathFrom);
        files[0].mkdir();
        files[1] = new File(pathFrom + sep + "folder1");
        files[1].mkdir();
        files[2] = new File(pathFrom + sep + "folder2");
        files[2].mkdir();
        files[3] = new File(pathFrom + sep + "folder3");
        files[3].mkdir();
        files[4] = new File(pathFrom + sep + "folder1" + sep + "subfolder11");
        files[4].mkdir();
        files[5] = new File(pathFrom + sep + "folder1" + sep + "subfolder12");
        files[5].mkdir();
        files[6] = new File(pathFrom + sep + "folder2" + sep + "subfolder21");
        files[6].mkdir();
        files[7] = new File(pathFrom + sep + "folder1" + sep + "subfolder11" + sep + "tmp");
        files[7].mkdir();

        File file = new File(pathFrom + sep + "folder1" + sep + "subfolder11" + sep + "tmp" + sep + "tmp1.txt");
        file.createNewFile();
        String testMessage = "We are testing copy method";
        OutputStream os = new FileOutputStream(file);
        os.write(testMessage.getBytes());
        os.close();
        files[8] = file;

        files[9] = new File(pathFrom + sep + "folder1" + sep + "subfolder11" + sep + "subfile11.txt");
        files[9].createNewFile();
        files[10] = new File(pathFrom + sep + "folder1" + sep + "subfolder12" + sep + "subfile12.txt");
        files[10].createNewFile();
        files[11] = new File(pathFrom + sep + "folder1" + sep + "file11.txt");
        files[11].createNewFile();
        files[12] = new File(pathFrom + sep + "folder1" + sep + "file12.txt");
        files[12].createNewFile();
        files[13] = new File(pathFrom + sep + "folder2" + sep + "file21.txt");
        files[13].createNewFile();
        files[14] = new File(pathFrom + sep + "temp1.txt");
        files[14].createNewFile();
        files[15] = new File(pathFrom + sep + "temp2.txt");
        files[15].createNewFile();
        files[16] = new File(pathFrom + sep + "temp3.txt");
        files[16].createNewFile();
        files[17] = new File(pathFrom + sep + "temp3.txt");
        files[17].createNewFile();

        File tempForCopyDir = new File(pathTo);
        tempForCopyDir.mkdir();
    }

    @Test
    public void testCountFiles() {
        assertEquals(9, FileManager.countFiles(pathFrom));
    }

    @Test
    public void testCountDirs() {
        assertEquals(7, FileManager.countDirs(pathFrom));
    }

    @Test
    public void testCopy() throws IOException {
        FileManager.copy(pathFrom, pathTo);
        assertTrue(new File(pathTo + sep + pathFrom).exists());
        assertTrue(new File(pathTo + sep + pathFrom).isDirectory());
        File orig1 = new File(pathFrom + sep + "temp1.txt");
        assertTrue(orig1.exists());
        File dest1 = new File(pathTo + sep + pathFrom + sep + "temp1.txt");
        assertTrue(dest1.exists());
        assertEquals(orig1.length(), dest1.length());

        assertTrue(new File(pathFrom + sep + "folder1" + sep + "subfolder12").exists());
        assertTrue(new File(pathTo + sep + pathFrom + sep + "folder1" + sep + "subfolder12").exists());
        assertTrue(new File(pathTo + sep + pathFrom + sep + "folder1" + sep + "subfolder12").isDirectory());
    }

    @Test
    public void testMove() throws IOException {
        FileManager.move(pathFrom, pathTo);
        assertTrue(new File(pathTo + sep + pathFrom).exists());
        assertTrue(new File(pathTo + sep + pathFrom).isDirectory());
        assertFalse(new File(pathFrom + sep + "temp1.txt").exists());
        assertTrue(new File(pathTo + sep + pathFrom + sep + "temp1.txt").exists());

        assertTrue(new File(pathTo + sep + pathFrom + sep + "folder1" + sep + "subfolder12").exists());
        assertTrue(new File(pathTo + sep + pathFrom + sep + "folder1" + sep + "subfolder12").isDirectory());
    }

    @After
    public void after() {
        for (int i = files.length - 1; i >= 0; i--) {
            File file = files[i];
            if (file.exists()) {
                file.delete();
            }
            File destFile = new File(pathTo + sep + file.getPath());
            if (destFile.exists()) {
                destFile.delete();
            }
        }
        File tempForCopyDir = new File(pathTo);
        if (tempForCopyDir.exists()) {
            tempForCopyDir.delete();
        }
    }
}