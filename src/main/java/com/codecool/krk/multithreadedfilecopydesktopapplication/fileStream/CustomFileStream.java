package com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream;

import java.io.*;

public class CustomFileStream {

    private InputStream is;
    private OutputStream os;
    private String fileName;
    private byte[] buffer = new byte[1024];

    public CustomFileStream(String source, String destination) throws FileNotFoundException {
        this.fileName = source;
        this.is = new FileInputStream(source);
        this.os = new FileOutputStream(destination);
    }

    public String getFileName() {
        return fileName;
    }

    public String getSimpleFileName() {
        String[] splittedSource = fileName.split("/");
        String simpleFileName = splittedSource[splittedSource.length - 1];
        return simpleFileName;
    }

    public InputStream getInputStream() {
        return is;
    }

    public OutputStream getOutputStream() {
        return os;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public int inputStreamLength() throws IOException {
        return this.is.read(buffer);
    }

    public void closeStreams() throws IOException {
        this.is.close();
        this.os.close();
    }
}
