package sample;

import com.agadasom.hwplib.reader.HWPReader;
import com.agadasom.hwplib.tool.textextractor.TextExtractMethod;
import com.agadasom.hwplib.tool.textextractor.TextExtractorListener;

import java.io.File;

public class Extracting_Text_From_Big_File {
    static class MyListener implements TextExtractorListener {
        public void paragraphText(String text) {
            System.out.print(text);
        }
    }

    public static void main(String[] args) throws Exception {
        HWPReader.forExtractText("sample_hwp" + File.separator + "big_file.hwp", new MyListener(),
                TextExtractMethod.InsertControlTextBetweenParagraphText);
    }
}
