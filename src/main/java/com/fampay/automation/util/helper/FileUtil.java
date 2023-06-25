/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.helper;

import com.fampay.automation.util.logging.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

/**
 * @author manoj
 */
public class FileUtil {
    private static File f;
    private static String name;
    public static String getAppAbsolutePath(String path){
        try {
            f = new File(path);
            if (f.exists())
                return f.getAbsolutePath();
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return null;
    }
    public static void saveImageFile(String path, File file) throws IOException {
        if (isPathExist(path)) {

            FileUtils.copyFile(file, new File(path + "\\" + file.getName().replace(".png", ".jpeg")));
        } else
            throw new IOException(path + " does exsist");
    }

    static public byte[] readVideo(String name) {
        try {
            File video = new File(name);
            FileInputStream r = new FileInputStream(video);
            return r.readAllBytes();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return new byte[0];
        }
    }

    public static void saveRecording(String path, String file) {

        System.out.println("entered started to save");
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(file.getBytes());
            FileOutputStream out = new FileOutputStream(path);
            LogUtil.getConsoleLogger().info(" saveing ...");
            out.write(decodedBytes);
            LogUtil.getConsoleLogger().info("written");
            out.close();
        }

        // Catch block to handle the exceptions
        catch (Exception e) {

            // Display exception on console
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
    }

    public static boolean isPathExist(String path) {
        try {
            f = new File(path);
            return f.exists();
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
    }



}
