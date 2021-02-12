package org.firstinspires.ftc.teamcode.movement;

public class Power {
    public double fl, fr, bl, br;

    public Power() {}

    public Power(double speed) {
        fl = fr = bl = br = speed;
    }

    public Power(double fl, double fr, double bl, double br) {
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
    }
}
