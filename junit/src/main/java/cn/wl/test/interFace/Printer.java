package cn.wl.test.interFace;

public class Printer implements Output, Product {

    private String[] printData = new String[MAX_CACHE_LINE];
    private int dataNum = 0;

    public void out() {
        while (dataNum > 0) {
            System.out.println("printer will print: " + printData[0]);
            System.arraycopy(printData, 1, printData, 0, --dataNum);
        }
    }

    public void getData(String msg) {
        if (dataNum >= MAX_CACHE_LINE) {
            System.out.println("the output queue is overflow, add failed");
        } else {
            printData[dataNum++] = msg;
        }

    }

    public int getProduceTime() {
        return 45;
    }

}
