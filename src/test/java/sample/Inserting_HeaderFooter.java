package sample;

import com.agadasom.hwplib.object.HWPFile;
import com.agadasom.hwplib.object.bodytext.control.ControlFooter;
import com.agadasom.hwplib.object.bodytext.control.ControlHeader;
import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.header.HeaderFooterApplyPage;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.tool.blankfilemaker.BlankFileMaker;
import com.agadasom.hwplib.writer.HWPWriter;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Inserting_HeaderFooter {
    public static void main(String[] args) throws Exception {
        HWPFile hwpFile = BlankFileMaker.make();
        if (hwpFile != null) {
            Paragraph firstPara = hwpFile.getBodyText().getSectionList().get(0).getParagraph(0);
            insertHeader(firstPara);
            insertFooter(firstPara);

            String writePath = "sample_hwp" + File.separator + "result-inserting-headerfooter.hwp";
            HWPWriter.toFile(hwpFile, writePath);
        }
    }

    private static void insertHeader(Paragraph para) throws UnsupportedEncodingException {
        para.getText().addExtendCharForHeader();

        ControlHeader header = (ControlHeader) para.addNewControl(ControlType.Header);
        header.getHeader().setCreateIndex(1);
        header.getHeader().setApplyPage(HeaderFooterApplyPage.BothPage);
        header.getListHeader().setParaCount(1);
        header.getListHeader().setTextWidth(42520);
        header.getListHeader().setTextHeight(4252);

        Paragraph paragraph = header.getParagraphList().addNewParagraph();
        paragraph.getHeader().setParaShapeId(1);
        paragraph.getHeader().setStyleId((short) 1);
        paragraph.createText();
        paragraph.getText().addString("머리글 입니다.");
        paragraph.createCharShape();
        paragraph.getCharShape().addParaCharShape(0, 2);
    }

    private static void insertFooter(Paragraph para) throws UnsupportedEncodingException {
        para.getText().addExtendCharForFooter();

        ControlFooter footer = (ControlFooter) para.addNewControl(ControlType.Footer);
        footer.getHeader().setCreateIndex(1);
        footer.getHeader().setApplyPage(HeaderFooterApplyPage.BothPage);
        footer.getListHeader().setParaCount(1);
        footer.getListHeader().setTextWidth(42520);
        footer.getListHeader().setTextHeight(4252);

        Paragraph paragraph = footer.getParagraphList().addNewParagraph();
        paragraph.getHeader().setParaShapeId(1);
        paragraph.getHeader().setStyleId((short) 1);
        paragraph.createText();
        paragraph.getText().addString("꼬리글 입니다.");
        paragraph.createCharShape();
        paragraph.getCharShape().addParaCharShape(0, 2);
    }
}
