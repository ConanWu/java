package cn.wl.test.tools.xls;

import com.google.common.base.Throwables;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

public class XlsToMap {

    public static Iterable<Map<String, String>> readRows(File file) {
        XlsRowSource rowSource = getXlsRowSource(file);
        List<String> header = rowSource.getHeader();
        return rowSource.getRows(header, true);

    }

    public static XlsRowSource getXlsRowSource(File file) {
//        ByteSource inputStream = Files.asByteSource(file);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return new XlsRowSource(fileInputStream);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }

    }

}
