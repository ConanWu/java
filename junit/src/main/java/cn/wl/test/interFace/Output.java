package cn.wl.test.interFace;

public interface Output extends InterfaceA, InterfaceB{
    public static final int MAX_CACHE_LINE = 50;

    public abstract void out();

    public abstract void getData(String msg);

}
