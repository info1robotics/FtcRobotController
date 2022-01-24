package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.Date;

public abstract class NonBlockingWorker implements Runnable {

    protected LinearOpMode opMode;

    public NonBlockingWorker(LinearOpMode opMode)
    {
        this.opMode = opMode;
    }

}
