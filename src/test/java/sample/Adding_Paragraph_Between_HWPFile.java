package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.ParagraphListInterface;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.reader.HWPReader;
import com.agadasom.hwplib.tool.paragraphadder.ParagraphAdder;
import com.agadasom.hwplib.writer.HWPWriter;

import java.io.File;

/**
 * 다른 파일에 문단을 복사하여 추가하는 샘플 프로그램.
 */
public class Adding_Paragraph_Between_HWPFile {
    public static void main(String[] args) throws Exception {
        HWPFile sourceHWPFile = HWPReader.fromFile("sample_hwp" + File.separator + "source.hwp");
        HWPFile targetHWPFile = HWPReader.fromFile("sample_hwp" + File.separator + "target.hwp");

        if (sourceHWPFile != null && targetHWPFile != null) {

            ParagraphListInterface targetFirstSection = targetHWPFile.getBodyText().getSectionList().get(0);
            {
                Paragraph sourceParagraph = sourceHWPFile.getBodyText().getSectionList().get(0).getParagraph(0);

                ParagraphAdder paraAdder = new ParagraphAdder(targetHWPFile, targetFirstSection);
                paraAdder.add(sourceHWPFile, sourceParagraph);
            }
            {
                Paragraph sourceParagraph = sourceHWPFile.getBodyText().getSectionList().get(0).getParagraph(1);

                ParagraphAdder paraAdder = new ParagraphAdder(targetHWPFile, targetFirstSection);
                paraAdder.add(sourceHWPFile, sourceParagraph);
            }
            {
                Paragraph sourceParagraph = sourceHWPFile.getBodyText().getSectionList().get(0).getParagraph(5);

                ParagraphAdder paraAdder = new ParagraphAdder(targetHWPFile, targetFirstSection);
                paraAdder.add(sourceHWPFile, sourceParagraph);
            }

            HWPWriter.toFile(targetHWPFile, "sample_hwp" + File.separator + "result-adding-paragraph.hwp");
        }
    }
}