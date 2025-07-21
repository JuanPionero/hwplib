package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.Section;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.reader.HWPReader;
import com.agadasom.hwplib.writer.HWPWriter;

import java.io.File;

/**
 * 기존 파일을 변경하는 샘플 프로그램.
 */
public class SimpleEditing_HWPFile {
    public static void main(String[] args) throws Exception {
        String filename = "sample_hwp" + File.separator + "blank.hwp";

        HWPFile hwpFile = HWPReader.fromFile(filename);
        if (hwpFile != null) {
            Section s = hwpFile.getBodyText().getSectionList().get(0);
            Paragraph firstParagraph = s.getParagraph(0);
            firstParagraph.getText().addString("이것은 추가된 문자열입니다.");

            String writePath = filename.substring(0, 11) + "result-editing-" + filename.substring(11);
            HWPWriter.toFile(hwpFile, writePath);
        }
    }
}
