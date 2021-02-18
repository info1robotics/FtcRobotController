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

    public void add(Power other)
    {
        fl += other.fl;
        fr += other.fr;
        bl += other.bl;
        br += other.br;
    }

    public Power minus(Power other)
    {
        return new Power(fl - other.fl, fr - other.fr, bl - other.bl, br - other.br);
    }

    public Power divide(float x)
    {
        return new Power(fl / x, fr / x, bl / x, br / x);
    }

    public boolean isZero()
    {
        return fl == 0 && fr == 0 && bl == 0 && br == 0;
    }
}
