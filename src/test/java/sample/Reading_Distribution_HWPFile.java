package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.reader.HWPReader;

public class Reading_Distribution_HWPFile {
    public static void main(String[] args) throws Exception {
        HWPFile hwpFile = HWPReader.fromFile("sample_hwp/distribution.hwp");
    }
}
