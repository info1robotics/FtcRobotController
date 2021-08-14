package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.jruby.embed.ScriptingContainer;

public class JRubyOpMode extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();
        main();
    }

    public void initialization() {
        ScriptingContainer container = new ScriptingContainer();
        container.parse("def main return 3 + 2 end");
        Integer result = 0;
        container.runRubyMethod(Integer.class, result, "main");
        telemetry.addLine(result.toString());
        telemetry.update();
    }

    public void main() {

    }

}
