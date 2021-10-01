package org.firstinspires.ftc.teamcode.components;

import com.info1robotics.rvm.RVComponent;
import com.info1robotics.rvm.RVRegister;

public class SleepFunction extends RVComponent {
    @RVRegister
    public void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
