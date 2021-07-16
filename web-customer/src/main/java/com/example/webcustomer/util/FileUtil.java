package com.example.webcustomer.util;

import gui.ava.html.parser.HtmlParser;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;
import gui.ava.html.renderer.ImageRendererImpl;

import java.time.LocalDateTime;

public class FileUtil {

    /**
     * 将网页保存为图片,返回图片路径
     * @author cxq
     * @date 2020/7/30
     * @param html
     */
    public String html2Img(String html,int width,int height) {
        String tempPath = "D:\\temp\\";
        HtmlParser htmlParser = new HtmlParserImpl();
        htmlParser.loadHtml(html);
        String filePath = tempPath + LocalDateTime.now().getSecond() + ".png";
        ImageRenderer imageRenderer = new ImageRendererImpl(htmlParser);
        imageRenderer.saveImage(filePath);
        return filePath;
    }
}
