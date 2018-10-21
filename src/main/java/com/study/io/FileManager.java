package com.study.io;

import java.io.*;

public class FileManager {

    public static void main(String[] args) throws IOException {
        move("C:\\work\\java\\projects\\Test\\temp\\FarRus.lng", "C:\\work\\java\\projects\\Test\\temp2");
    }

    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {

        int fileCount = 0;
        File file = new File(path);
        if (file.isFile()) {
            fileCount++;
        } else {
            for (File subFile : file.listFiles()
            ) {
                fileCount += countFiles(subFile.getAbsolutePath());
            }
        }
        return fileCount;
    }

    // public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        int fileCount = 0;
        File file = new File(path);
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()
            ) {
                if (subFile.isDirectory()) {
                    fileCount++;
                    fileCount += countDirs(subFile.getAbsolutePath());
                }
            }
        }
        return fileCount;
    }

    public static void copy(String from, String to) throws IOException {
        File dirTo = new File(to);
        if (!(dirTo.exists() && dirTo.isDirectory())) {
            throw new FileNotFoundException("Destination directory " + to + " not found");
        }
        File fileFrom = new File(from);
        if (!fileFrom.exists()) {
            throw new FileNotFoundException("Target directory/file " + from + " not found");
        }
        if (fileFrom.isFile()) {
            copyFile(fileFrom, dirTo);
        } else {
            String targetDirname = dirTo + File.separator + fileFrom.getName();
            File targetDir = new File(targetDirname);
            targetDir.mkdir();
            for (File sourceFile : fileFrom.listFiles()) {
                String sourceFilename = sourceFile.getAbsolutePath();
                copy(sourceFilename, targetDirname);
            }
        }
    }

    private static void copyFile(File fileFrom, File dirTo) throws IOException {
        InputStream fileInputStream = null;
        OutputStream fileOnputStream = null;
        File fileTo = new File(dirTo.getAbsolutePath() + File.separator + fileFrom.getName());
        try {
            fileInputStream = new FileInputStream(fileFrom);
            fileOnputStream = new FileOutputStream(fileTo);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                fileOnputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Can't copy file " + fileFrom + " to " + dirTo.getAbsolutePath());
        } finally {
            fileInputStream.close();
            fileOnputStream.close();
        }
    }

    public static void move(String from, String to) throws IOException {
        File dirTo = new File(to);
        if (!(dirTo.exists() && dirTo.isDirectory())) {
            throw new FileNotFoundException("Destination directory " + to + " not found");
        }
        File fileFrom = new File(from);
        if (!fileFrom.exists()) {
            throw new FileNotFoundException("Target directory/file " + from + " not found");
        }
        File fileTo = new File(dirTo.getAbsolutePath() + File.separator + fileFrom.getName());
        fileFrom.renameTo(fileTo);

    }
}
