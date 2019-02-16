package org.pet.social.common.utils;

import java.io.Serializable;
import java.util.logging.Logger;

public final class LoggerCreator {
    private static volatile Logger instance;

    private LoggerCreator(){ }

    private static Logger getInstance(Class<?> cl) {
        if (instance == null) {
            synchronized (cl) {
                if (instance == null) {
                    instance = Logger.getLogger(cl.getName());
                }
            }
        }
        return instance;
    }

    public static void makeInfo(Class<?> cl, Serializable message) {
        getInstance(cl).info(message.toString());
    }

    public static void makeWarning(Class<?> cl, Serializable message){
        getInstance(cl).warning(message.toString());
    }

    public static void makeSevere(Class<?> cl, Serializable message){
        getInstance(cl).severe(message.toString());
    }
}
