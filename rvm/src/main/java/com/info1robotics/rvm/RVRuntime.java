package com.info1robotics.rvm;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8ScriptCompilationException;
import com.eclipsesource.v8.V8ScriptException;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class RVRuntime {

    public V8 v8;

    private static RVRuntime inst;

    ArrayList<JavaVoidCallback> printCallbacks = new ArrayList<>();

    public static RVRuntime getInstance() {

        RVRuntime result = inst;

        if (result != null) {
            return result;
        }

        synchronized(RVRuntime.class) {
            if (inst == null) inst = new RVRuntime();

            return inst;
        }
    }

    RVRuntime() {
        v8 = V8.createV8Runtime();
        v8.getLocker().release();


        v8.getLocker().acquire();
        v8.registerJavaMethod(new JavaVoidCallback() {
            @Override
            public void invoke(V8Object v8Object, V8Array v8Array) {
                for(JavaVoidCallback callback : printCallbacks) {
                    callback.invoke(v8Object, v8Array);
                }
            }
        }, "print");


        v8.getLocker().release();
    }


    public void loadScript(String data) {
        v8.getLocker().acquire();

        try {
            v8.executeVoidScript(data);
        } catch (V8ScriptCompilationException e) {
            printMessage(e.getJSStackTrace());
        }

        v8.getLocker().release();

    }

    public void runInitScript() {
        v8.getLocker().acquire();
        try {
            v8.executeJSFunction("init");
        } catch(V8ScriptException e) {
            printMessage(e.getJSMessage());
        }
        v8.getLocker().release();
    }

    public void runTeleOpScript() {
        v8.getLocker().acquire();
        try {
            v8.executeJSFunction("teleop");
        } catch (V8ScriptException e) {
            printMessage(e.getJSMessage());
        }
        v8.getLocker().release();
    }

    public void runAutonomousScript() {
        v8.getLocker().acquire();
        try {
            v8.executeJSFunction("autonomous");
        } catch (V8ScriptException e) {
            printMessage(e.getJSMessage());
        }
        v8.getLocker().release();
    }

    public void registerPrintCallback(JavaVoidCallback callback) {
        printCallbacks.add(callback);
    }

    private void printMessage(String msg) {
        for (JavaVoidCallback printCallback :
                printCallbacks) {
            V8Array arr = new V8Array(v8);
            arr.push(msg);
            printCallback.invoke(v8, arr);
        }
    }

}