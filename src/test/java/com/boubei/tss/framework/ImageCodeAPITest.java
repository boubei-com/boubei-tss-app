package com.boubei.tss.framework;

import com.boubei.tss.framework.img.ImageCodeAPI;

public class ImageCodeAPITest {

    public static void main(String[] args) {
        String msg = "123456789";
        String path = "barcode.png";
        ImageCodeAPI.generateFile(msg, path);
    }
    
}
