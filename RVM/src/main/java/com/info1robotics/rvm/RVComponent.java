package com.info1robotics.rvm;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

abstract public class RVComponent {
    public RVComponent() {
        RVRuntime.getInstance().v8.getLocker().acquire();
        for(Method method : getClass().getDeclaredMethods()) {
            if(method.getAnnotation(RVRegister.class) != null) {
                //
                RVRuntime.getInstance().v8.registerJavaMethod(this, method.getName(), method.getName(), method.getParameterTypes());
            }
        }
        RVRuntime.getInstance().v8.getLocker().release();
    }
}

