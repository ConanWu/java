package cn.wl.test.assertJ;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;


public class assertJTest {

    public void assertJTest01() {
        Map<String, Object> result = new HashMap<>();
        Condition condition = new Condition<>();
        assertThat(result).has(condition);
    }
}
