package zombe.core.util;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Color {
    public final int rgba, rgbt;
    public final byte tb, ab, rb, gb, bb;
    public final float tf, af, rf, gf, bf;

    public Color(int rgbt) {
        this.rgbt = rgbt;
        this.rgba = (rgbt & 0x00ffffff) | (0xff000000 - (rgbt & 0xff000000));
        this.tb = (byte) ((rgbt >>> 24) & 0xff);
        this.rb = (byte) ((rgbt >>> 16) & 0xff);
        this.gb = (byte) ((rgbt >>>  8) & 0xff);
        this.bb = (byte) ( rgbt         & 0xff);
        this.ab = (byte) (0xff - (this.tb & 0xff));
        this.tf = (this.tb & 0xff) / 255f;
        //this.af = (ab & 0xff) / 255f;
        this.af = 1f - this.tf;
        this.rf = (this.rb & 0xff) / 255f;
        this.gf = (this.gb & 0xff) / 255f;
        this.bf = (this.bb & 0xff) / 255f;
    }

    public Color(byte r, byte g, byte b, byte a) {
        this.tb = (byte) (255 - (a & 0x00ff));
        this.ab = a;
        this.rb = r;
        this.gb = g;
        this.bb = b;
        this.rgbt = ((((int) this.tb) << 24) & 0xff000000)
                  | ((((int) this.rb) << 16) & 0x00ff0000)
                  | ((((int) this.gb) <<  8) & 0x0000ff00)
                  | ( ((int) this.bb)        & 0x000000ff);
        this.rgba = (this.rgbt & 0x00ffffff) | (0xff000000 - (this.rgbt & 0xff000000));
        this.tf = (this.tb & 0xff) / 255f;
        this.af = (this.ab & 0xff) / 255f;
        this.rf = (this.rb & 0xff) / 255f;
        this.gf = (this.gb & 0xff) / 255f;
        this.bf = (this.bb & 0xff) / 255f;
    }

    public Color(byte r, byte g, byte b) {
        this(r, g, b, (byte) 255);
    }

    public Color(float r, float g, float b, float a) {
        this.af = clamp(a);
        this.rf = clamp(r);
        this.gf = clamp(g);
        this.bf = clamp(b);
        this.tf = 1f - this.af;
        this.ab = (byte) (this.af * 255.999999f);
        this.rb = (byte) (this.rf * 255.999999f);
        this.gb = (byte) (this.gf * 255.999999f);
        this.bb = (byte) (this.bf * 255.999999f);
        this.tb = (byte) (this.tf * 255.999999f);
        this.rgbt = (((this.tb & 0xff) << 24) & 0xff000000)
                  | (((this.rb & 0xff) << 16) & 0x00ff0000)
                  | (((this.gb & 0xff) <<  8) & 0x0000ff00)
                  | ( (this.bb & 0xff)        & 0x000000ff);
        this.rgba = (this.rgbt & 0x00ffffff) | (0xff000000 - (this.rgbt & 0xff000000));
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1f);
    }

    public static float clamp(float f) {
        return (f < 0f) ? 0f : (f > 1f) ? 1f : f;
    }

    @Nullable
    public static Color parse(@Nonnull String value) {
        return StringHelper.parseColor(value);
    }
}
