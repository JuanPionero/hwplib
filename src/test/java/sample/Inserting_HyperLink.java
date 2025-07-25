package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.Section;
import com.agadasom.hwplib.object.bodytext.control.ControlField;
import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.tool.blankfilemaker.BlankFileMaker;
import com.agadasom.hwplib.writer.HWPWriter;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Inserting_HyperLink {
    public static void main(String[] args) throws Exception {
        HWPFile hwpFile = BlankFileMaker.make();
        if (hwpFile != null) {
            Section s = hwpFile.getBodyText().getSectionList().get(0);
            int count = s.getParagraphCount();
            for (int index = 0; index < count; index++) {
                insertHyperLink(hwpFile.getBodyText().getSectionList().get(0).getParagraph(index));
            }

            String writePath = "sample_hwp" + File.separator + "result-inserting-hyperlink.hwp";
            HWPWriter.toFile(hwpFile, writePath);
        }
    }

    private static void insertHyperLink(Paragraph paragraph) throws UnsupportedEncodingException {
        paragraph.getText().addString("이것은 ");
        paragraph.getText().addExtendCharForHyperlinkStart();
        paragraph.getText().addString("다음 사이트");
        paragraph.getText().addExtendCharForHyperlinkEnd();
        paragraph.getText().addString("로 가는 링크입니다.");

        ControlField field = (ControlField) paragraph.addNewControl(ControlType.FIELD_HYPERLINK.getCtrlId());
        field.getHeader().getCommand()
                .fromUTF16LEString("https\\://www.dogfoot.kr/aaa.jsp\\?aaa=bb&ccc=dd" + ";1;0;0;");
    }
}
