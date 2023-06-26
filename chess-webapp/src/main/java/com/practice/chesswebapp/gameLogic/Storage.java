package com.practice.chesswebapp.gameLogic;

import java.io.*;

public class Storage {
    private static final String relativeFolder = "games/";

    public static Object get(String fileName) {
        System.out.println("Storage.get("+relativeFolder + fileName+")");
        Object obj = null;
        try{
            FileInputStream fis = new FileInputStream(relativeFolder + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            return obj;
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return obj;
    }

    public static void put(Object obj, String fileName) {
        try{
            File file = new File(relativeFolder + fileName);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            FileOutputStream fos = new FileOutputStream(relativeFolder + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
        }
    }
}
