package com.codecool.krk.fileStream;

import java.io.*;


public class CustomFileStream {

    private InputStream is;
    private OutputStream os;
    private String fileName;
    private String destination;
    private final int bytes = 1024;
    private byte[] buffer = new byte[bytes];

    public CustomFileStream(String source, String destination) throws FileNotFoundException {
        this.fileName = source;
        this.destination = destination;
        this.is = new FileInputStream(source);
        this.os = new FileOutputStream(destination);
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


    public String getFileName() {

        return fileName;
    }


    public String getDestination() {

        return destination;
    }


    public int inputStreamLength() throws IOException {

        return this.is.read(buffer);
    }


    public void closeStreams() throws IOException {
        this.is.close();
        this.os.close();
    }
}
