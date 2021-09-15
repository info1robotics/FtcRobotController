package com.info1robotics.rvm;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8ScriptCompilationException;

import java.util.ArrayList;

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
            e.printStackTrace();
        }

        v8.getLocker().release();

    }

    public void runInitScript() {
        v8.getLocker().acquire();
        v8.executeJSFunction("init");
        v8.getLocker().release();
    }

    public void runTeleOpScript() {
        v8.getLocker().acquire();
        v8.executeJSFunction("teleop");
        v8.getLocker().release();
    }

    public void runAutonomousScript() {
        v8.getLocker().acquire();
        v8.executeJSFunction("autonomous");
        v8.getLocker().release();
    }

    public void registerPrintCallback(JavaVoidCallback callback) {
        printCallbacks.add(callback);
    }

}
