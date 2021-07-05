package com.example.webcustomer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;


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
        //File srcFile = new File(srcPath);
        // String s = testBufferedReader(srcPath);
        // testInputStreamReader(srcPath);
        // testFileReader(srcPath);

        // copyFile();

        // copyTxtFile();

        // 上传文件
        /*InputStream in = new FileInputStream("D:/temp/a/test.doc");
        MultipartFile file = new MockMultipartFile("testDoc.doc",in);
        uploadFile2(file);*/

        copyFileBuffer();
      copyFile3();


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
        String sourcePath = "D:/temp/a/test.pdf";
        String targetPath = "D:/temp/b/test.pdf";

        // 语法糖 try-with-resource
        try (InputStream is = new FileInputStream(sourcePath);
             FileOutputStream os = new FileOutputStream(targetPath) ) {

            while (is.available() > 0) {
                int length = is.read(b);
                os.write(b,0,length);
            }

            log.info("移文件完成：{}", targetPath);
        } catch (IOException e) {
            log.info("移文件异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 使用IO流复制txt文件
     * @author cxq
     * @date 2021/7/2
     * @return void
     */
    public static void copyTxtFile() {
        String sourcePath = "D:/temp/a/test.txt";
        String targetPath = "D:/temp/b/test.txt";

        try (FileReader fr = new FileReader(new File(sourcePath));
             FileWriter fw = new FileWriter(targetPath) ) {

            while (fr.ready()) {
                fw.write(fr.read());
            }

            log.info("移文件完成：{}", targetPath);
        } catch (IOException e) {
            log.info("移文件异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param file
     * @return fileName
     */
    public static String uploadFile(MultipartFile file) {
        String path = "D:/temp/upload/";
        String uuid = UUID.randomUUID().toString();
        int index = file.getName().lastIndexOf(".");
        String suffix = file.getName().substring(index);
        String fileName = uuid + suffix;
        String desFilePath = path + fileName;
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(desFilePath) ) {
            byte[] b = new byte[1024];
            while (in.available() > 0) {
                int length = in.available() > b.length ? b.length : in.available();
                in.read(b,0,length);
                out.write(b,0,length);
            }

            log.info("上传文件完成：{}", desFilePath);
        } catch (IOException e) {
            log.info("上传文件异常：{}", e.getMessage());
            e.printStackTrace();
        }

        return fileName;
    }

    /**
     * 上传文件
     * @param file
     * @return fileName
     */
    public static String uploadFile2(MultipartFile file) {
        String path = "D:/temp/upload/";
        String uuid = UUID.randomUUID().toString();
        int index = file.getName().lastIndexOf(".");
        String suffix = file.getName().substring(index);
        String fileName = uuid + suffix;
        String desFilePath = path + fileName;
        File localFile = new File(desFilePath);
        try {
            //将文件上传到本地路径
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 下载文件
     * @param fileName
     */
    public static void downloadFile(String fileName, HttpServletResponse response){
        String filePath = "D:/temp/upload/" + fileName;
        File file = new File(filePath);
        try (InputStream fis = new BufferedInputStream(new FileInputStream(file));
             OutputStream toClient = new BufferedOutputStream(response.getOutputStream());) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            response.reset();
            // 设置response的Header
            response.setContentType("application/x-download");
            //指定文件的名称
            response.addHeader("Content-Disposition","attachment;filename=" + fileName);
            toClient.write(buffer);
            toClient.flush();

            log.info("下载文件完成：{}", fileName);
        } catch (IOException e) {
            log.info("下载文件异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    //
    public static void copyFile3() {
        // 开始时间
        long begin = System.currentTimeMillis();
        byte[] b = new byte[1024*2];
        String sourcePath = "F:/temp/a/test.jar";
        String targetPath = "F:/temp/b/test2.jar";

        // 语法糖 try-with-resource
        try (InputStream is = new FileInputStream(sourcePath);
             FileOutputStream os = new FileOutputStream(targetPath) ) {

            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b,0,len);
            }

            log.info("移文件完成：{}", targetPath);
        } catch (IOException e) {
            log.info("移文件异常：{}", e.getMessage());
            e.printStackTrace();
        }

        // 用时毫秒
        System.out.println("节点流用时：" + (System.currentTimeMillis() - begin));//
    }

    /**
     * 使用缓冲流高效读写文件
     * 并且是线程安全的
     */
    public static void copyFileBuffer() {
        // 开始时间
        long begin = System.currentTimeMillis();

        byte[] b = new byte[1024*2];// 2kb
        String sourcePath = "F:/temp/a/test.jar";
        String targetPath = "F:/temp/b/test2.jar";

        // 语法糖 try-with-resource
        try (InputStream is = new FileInputStream(sourcePath);
             BufferedInputStream bis = new BufferedInputStream(is);
             FileOutputStream os = new FileOutputStream(targetPath);
             BufferedOutputStream bos = new BufferedOutputStream(os);) {

            int len;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            log.info("移文件完成：{}", targetPath);

        } catch (IOException e) {
            log.info("移文件异常：{}", e.getMessage());
            e.printStackTrace();
        }

        // 用时毫秒
        System.out.println("高效流用时：" +  (System.currentTimeMillis() - begin));//
    }

}
