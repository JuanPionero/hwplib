package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.reader.HWPReader;
import com.agadasom.hwplib.tool.textextractor.TextExtractMethod;
import com.agadasom.hwplib.tool.textextractor.TextExtractor;

import java.io.File;

public class Extracting_Text_With_ParaHead {
    public static void main(String[] args) throws Exception {
        String filename = "sample_hwp" + File.separator + "extracting_ParaHead.hwp";
        HWPFile hwpFile = HWPReader.fromFile(filename);

        String str = TextExtractor.extract(hwpFile, TextExtractMethod.AppendControlTextAfterParagraphText);
        System.out.println(str);
    }
}
