import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

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
        System.out.println("asdf");
    }
}
