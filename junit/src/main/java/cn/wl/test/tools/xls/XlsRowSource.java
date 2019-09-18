package cn.wl.test.tools.xls;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.ByteSource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

public class XlsRowSource {

    private final Sheet sheet;
    private final List<String> header;
    private final BufferedInputStream inputStream;
    private final FormulaEvaluator evaluator;
    private final DataFormat dataFormat;
    private final DataFormatter dataFormatter = new DataFormatter(true);

    public XlsRowSource(FileInputStream fileInputStream) throws IOException {
        inputStream = new BufferedInputStream(fileInputStream);
        InputStream in = inputStream;
        Workbook workbook = null;
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        if (FileMagic.valueOf(in) == FileMagic.OLE2) {
            workbook = new HSSFWorkbook(in);
        } else if(FileMagic.valueOf(in) == FileMagic.OOXML) {
            workbook = new XSSFWorkbook(in);
        }
        checkNotNull(workbook, "The type of your input file is not supported");
        sheet = workbook.getSheetAt(0);
        header = createHeader();
        dataFormat = workbook.createDataFormat();
        evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        evaluator.evaluateAll();

    }

    public List<String> getHeader() {
        return checkNotNull(header, "Header can not be null");
    }
    private List<String> createHeader() {
        List<String> header = Iterables.getFirst(rowToList(), null);
        checkNotNull(header, "Missing header in file");
        return header;

    }

    public final Iterable<List<String>> rowToList() {
        return Iterables.transform(sheet, row -> {
            List<String> line = Lists.newArrayList();
            for (Cell cell : row) {
                line.add(cell.getStringCellValue());
            }
            return line;
        });
    }

    public Iterable<Map<String, String>> getRows(final List<String> customHeader, boolean evaluateDataFormula) {
        return Iterables.transform(Iterables.skip(sheet, 1), input -> {
            Map<String, String> mapRow = Maps.newLinkedHashMap();
            for (int i = 0; i < customHeader.size(); i++) {
                Cell cell = input.getCell(i);
                String key = customHeader.get(i);
                if (cell != null) {
                    mapRow.put(key, cell.getStringCellValue());
                }
            }
            return mapRow;
        });
    }
}
