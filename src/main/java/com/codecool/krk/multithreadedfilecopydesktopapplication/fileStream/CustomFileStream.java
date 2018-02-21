package com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream;

import java.io.*;

public class CustomFileStream {

    private InputStream is;
    private OutputStream os;
    private byte[] buffer = new byte[1024];

    public CustomFileStream(String source, String destination) throws IOException {
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

    public int inputStreamLength() throws IOException {
        return this.is.read(buffer);
    }

    public void closeStreams() throws IOException {
        this.is.close();
        this.os.close();
    }
}
