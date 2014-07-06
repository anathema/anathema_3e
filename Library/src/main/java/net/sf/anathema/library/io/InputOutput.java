package net.sf.anathema.library.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputOutput {

  public static final String ENCODING = "UTF-8";

  public static String toString(InputStream inputStream) throws IOException {
    return IOUtils.toString(inputStream, ENCODING);
  }

  public static String toString(File itemFile) throws IOException {
    return FileUtils.readFileToString(itemFile, ENCODING);
  }

  public static byte[] toByteArray(InputStream inputStream) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    IOUtils.copy(bufferedInputStream, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static void write(String fileName, OutputStream outputStream) throws IOException {
    IOUtils.write(fileName, outputStream);
  }

  public static void writeStringToFile(File file, String content) throws IOException {
    FileUtils.writeStringToFile(file, content, ENCODING);
  }

  public static void deleteDirectory(File directory) throws IOException {
    FileUtils.deleteDirectory(directory);
  }

  public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
    IOUtils.copy(inputStream, outputStream);
  }

  public static void forceDelete(File file) throws IOException {
    FileUtils.forceDelete(file);
  }

  public static void forceMkdir(File directory) throws IOException {
    FileUtils.forceMkdir(directory);
  }
}
