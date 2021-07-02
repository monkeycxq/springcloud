package com.example.webcustomer.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

/**
 * @author cxq
 * @date 2021/7/1
 * IO操作工具类
 *
 */
@Slf4j
public class IOUtil {

    private static byte[] bytes = new byte[2048];
    private static char[] chars = new char[1024];
    // IO流：
    //  1.字符流
    //      Reader:BufferedReader, InputStreamReader FileReader, StringReader, PipedReader, CharArrayReader, FilterReader PushbackReader
    //      Writer:BufferedWriter, OutputStreamWriter FileWriter, PrinterWriter, StringWriter, PipedWriter, CharArrayWriter, FilterWriter
    //  2.字节流
    //      InputStream:FileInputStream, FilterInputStream (BufferedInputStream,DataInputStream,PushbackInputStream), ObjectInputStream,
    //          PipedInputStream, SequenceInputStream, StringBufferInputStream, ByteArrayInputStream
    //      OutputStream:FileOutputStream, FilterOutputStream (BufferedOutputStream,DataOutputStream,PrintStream), ObjectInputStream,
    //          PipedInputStream, ByteArrayInputStream

    public static void main(String[] arg) throws IOException {
        String srcPath = "D:/temp/a/test.txt";
        String desPath = "D:/temp/b/test.txt";
        File srcFile = new File(srcPath);
        //String s = testBufferedReader(srcPath);
        //testInputStreamReader(srcPath);
        //testFileReader(srcPath);

        copyFile();

    }

    /**
     * BufferedReader: 属于处理流中的缓冲流，可以将读取的内容存在内存里面，有readLine()方法，它，用来读取一行
     *
     * 从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取。
     * 可以指定缓冲区大小，或者可以使用默认大小。 默认值足够大，可用于大多数用途。
     * 通常，由读取器做出的每个读取请求将引起对底层字符或字节流的相应读取请求。 因此，建议将BufferedReader包装在其read（）操作可能昂贵的读取器上，例如FileReader和InputStreamReader。
     * 例如 BufferedReader in  = new BufferedReader(new FileReader("foo.in"));
     * 将缓冲指定文件的输入。 没有缓冲，每次调用read（）或readLine（）可能会导致从文件中读取字节，转换成字符，然后返回，这可能非常低效。
     * 使用DataInputStreams进行文本输入的程序可以通过用适当的BufferedReader替换每个DataInputStream进行本地化。
     * @author cxq
     * @date 2021/7/2
     * @param filePath
     * @return void
     */
    public static String testBufferedReader(String filePath) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuffer b = new StringBuffer();
        while (true) {
            int ch = br.read();
            if (ch == -1) {
                break;
            }
            b.append((char) ch);
            /*String ch = br.readLine();
            if (ch == null) {
                break;
            }
            b.append(ch).append("\n");*/
        }
        br.close();
        return b.toString();
    }

    /**
     * InputStreamReader
     * 是从字节流到字符流的桥：它读取字节，并使用指定的charset将其解码为字符 。 它使用的字符集可以由名称指定，也可以被明确指定，或者可以接受平台的默认字符集。
     * 每个调用InputStreamReader的read（）方法之一可能会导致从底层字节输入流读取一个或多个字节。 为了使字节有效地转换为字符，可以从底层流读取比满足当前读取
     * 操作所需的更多字节。
     * @author cxq
     * @date 2021/7/2
     * @param filePath
     * @return void
     */
    public static void testInputStreamReader(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis);

        StringBuffer b = new StringBuffer();
        while (isr.read(chars) > -1) {
            b.append(new String(chars));
        }
        fis.close();

        System.out.println(b.toString());
    }


    /**
     * FileReader 是用于读取字符流
     * @author cxq
     * @date 2021/7/2
     * @param filePath
     * @return void
     */
    public static void testFileReader(String filePath) throws IOException{
        FileReader fr = new FileReader(new File(filePath));
        String encoding = fr.getEncoding();
        System.out.println("encoding:" + encoding);
        StringBuffer b = new StringBuffer();
        while(true){
            int ch = fr.read();
            if(ch == -1){
                break;
            }
            b.append((char) ch);
        }
        fr.close();
        System.out.println(b.toString());
    }

    /**
     * 使用IO流复制文件
     * 使用字节流可复制任何类型的文件
     * @author cxq
     * @date 2021/7/2
     * @return void
     */
    public static void copyFile() {
        byte[] b = new byte[1024];
        String sourcePath = "D:/temp/a/test.java";
        String targetPath = "D:/temp/b/test.java";

        // 语法糖
        try (InputStream is = new FileInputStream(sourcePath);
             FileOutputStream os = new FileOutputStream(targetPath) ) {

            while (is.available() > 0) {
                int length = is.available() > b.length ? b.length : is.available();
                is.read(b,0,length);
                os.write(b,0,length);
            }

            log.info("移文件完成：{}", targetPath);
        } catch (IOException e) {
            log.info("移文件异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
