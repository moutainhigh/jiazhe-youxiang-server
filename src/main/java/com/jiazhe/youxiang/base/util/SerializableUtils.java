package com.jiazhe.youxiang.base.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author TU
 * @description
 * @date 2018/11/6.
 */
public class SerializableUtils {

    public static String serialize(Session session) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            oos.close();
            return Base64.encodeToString(bos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("serialize session error :"+ e.getMessage());
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                throw new RuntimeException("serialize session error :"+ e.getMessage());
            }
        }
    }

    public static Session deserialize(String sessionStr) {
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            ois.close();
            return (Session) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("deserialize session error :"+ e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("deserialize session error :"+ e.getMessage());
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                throw new RuntimeException("deserialize session error:"+ e.getMessage());
            }
        }
    }

}
