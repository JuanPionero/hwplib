package com.agadasom.hwplib.tool.blankfilemaker;

import com.agadasom.hwplib.object.bodytext.Section;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.object.bodytext.paragraph.ParagraphList;
import com.agadasom.hwplib.object.bodytext.paragraph.charshape.ParaCharShape;
import com.agadasom.hwplib.object.bodytext.paragraph.header.ParaHeader;
import com.agadasom.hwplib.object.bodytext.paragraph.lineseg.LineSegItem;
import com.agadasom.hwplib.object.bodytext.paragraph.lineseg.ParaLineSeg;
import com.agadasom.hwplib.object.bodytext.paragraph.text.ParaText;

public class EmptyParagraphAdder {
    public static void add(Section section) {
        Paragraph paragraph = section.addNewParagraph();
        header(paragraph.getHeader());
        text(paragraph);
        charShape(paragraph);
        lineSeg(paragraph);
        SectionDefineAdder.add(paragraph);
        ColumnDefineAdder.add(paragraph);
    }

    private static void header(ParaHeader header) {
        header.setLastInList(true);
        header.setCharacterCount(17);
        header.getControlMask().setValue(4);
        header.setParaShapeId(3);
        header.setStyleId((short) 0);
        header.getDivideSort().setValue((short) 3);
        header.setCharShapeCount(1);
        header.setRangeTagCount(0);
        header.setLineAlignCount(1);
        header.setInstanceID(0);
        header.setIsMergedByTrack(0);
    }

    public static void add(ParagraphList paragraphList) {
        Paragraph paragraph = paragraphList.addNewParagraph();
        header2(paragraph.getHeader());
        charShape(paragraph);
    }

    private static void header2(ParaHeader header) {
        header.setLastInList(true);
        header.setCharacterCount(17);
        header.getControlMask().setValue(4);
        header.setParaShapeId(3);
        header.setStyleId((short) 0);
        header.getDivideSort().setValue((short) 0);
        header.setCharShapeCount(1);
        header.setRangeTagCount(0);
        header.setLineAlignCount(1);
        header.setInstanceID(0);
        header.setIsMergedByTrack(0);
    }

    private static void text(Paragraph paragraph) {
        paragraph.createText();
        ParaText paraText = paragraph.getText();

        paraText.addExtendCharForSectionDefine();
        paraText.addExtendCharForColumnDefine();
    }

    private static void charShape(Paragraph paragraph) {
        paragraph.createCharShape();
        ParaCharShape charShape = paragraph.getCharShape();
        charShape.addParaCharShape(0, 0);
    }

    private static void lineSeg(Paragraph paragraph) {
        paragraph.createLineSeg();
        ParaLineSeg lineSeg = paragraph.getLineSeg();
        LineSegItem item = lineSeg.addNewLineSegItem();
        item.setTextStartPosition(0);
        item.setLineVerticalPosition(0);
        item.setLineHeight(1000);
        item.setTextPartHeight(1000);
        item.setDistanceBaseLineToLineVerticalPosition(850);
        item.setLineSpace(600);
        item.setStartPositionFromColumn(0);
        item.setSegmentWidth(42520);
        item.getTag().setValue(393216);
    }
}
