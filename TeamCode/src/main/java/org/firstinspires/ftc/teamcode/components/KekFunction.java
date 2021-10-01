package org.firstinspires.ftc.teamcode.components;

import com.info1robotics.rvm.RVComponent;
import com.info1robotics.rvm.RVRegister;

public class KekFunction extends RVComponent {

    int a = 0;

    @RVRegister
    public int printAndInc() { return a++; }



}
