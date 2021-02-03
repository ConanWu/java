import org.junit.Test;

import java.lang.reflect.Array;

public class TriangleTest {

    @Test
    public void triangleTest() {
        Triangle triangle = new Triangle();
        double a = triangle.callPerimeter();
        System.out.println(a);
    }

    @Test
    public void stringBufferTest() {
        StringBuffer sb = new StringBuffer();
        sb.append("asdf").append("~");
        String a = "a-2-3-f-b";
        String b = a;
        a="asdfasdf";


        System.out.println("asdf");
    }
}
