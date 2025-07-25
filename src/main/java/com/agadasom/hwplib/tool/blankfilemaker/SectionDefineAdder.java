package com.agadasom.hwplib.tool.blankfilemaker;

import com.agadasom.hwplib.object.bodytext.control.ControlSectionDefine;
import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderSectionDefine;
import com.agadasom.hwplib.object.bodytext.control.sectiondefine.FootEndNoteShape;
import com.agadasom.hwplib.object.bodytext.control.sectiondefine.PageBorderFill;
import com.agadasom.hwplib.object.bodytext.control.sectiondefine.PageDef;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderThickness;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderType;

public class SectionDefineAdder {
    public static void add(Paragraph paragraph) {
        ControlSectionDefine sectionDefine = (ControlSectionDefine) paragraph.addNewControl(ControlType.SectionDefine);
        header(sectionDefine.getHeader());
        pageDef(sectionDefine.getPageDef());
        footNoteShape(sectionDefine.getFootNoteShape());
        endNoteShape(sectionDefine.getEndNoteShape());
        pageBorderFill(sectionDefine.getBothPageBorderFill());
        pageBorderFill(sectionDefine.getEvenPageBorderFill());
        pageBorderFill(sectionDefine.getOddPageBorderFill());
    }

    private static void header(CtrlHeaderSectionDefine header) {
        header.getProperty().setValue(0);
        header.setColumnGap(1134);
        header.setVerticalLineAlign(0);
        header.setHorizontalLineAlign(0);
        header.setDefaultTabGap(8000);
        header.setNumberParaShapeId(1);
        header.setPageStartNumber(0);
        header.setImageStartNumber(0);
        header.setTableStartNumber(0);
        header.setEquationStartNumber(0);
        header.setDefaultLanguage(0);
    }

    private static void pageDef(PageDef pageDef) {
        pageDef.setPaperWidth(59528);
        pageDef.setPaperHeight(84188);
        pageDef.setLeftMargin(8504);
        pageDef.setRightMargin(8504);
        pageDef.setTopMargin(5668);
        pageDef.setBottomMargin(4252);
        pageDef.setHeaderMargin(4252);
        pageDef.setFooterMargin(4252);
        pageDef.setGutterMargin(0);
        pageDef.getProperty().setValue(0);
    }

    private static void footNoteShape(FootEndNoteShape footNoteShape) {
        footNoteShape.getProperty().setValue(0);
        footNoteShape.getUserSymbol().fromUTF16LEString("\u0000");
        footNoteShape.getBeforeDecorativeLetter().fromUTF16LEString("\u0000");
        footNoteShape.getAfterDecorativeLetter().fromUTF16LEString(")");
        footNoteShape.setStartNumber(1);
        footNoteShape.setDivideLineLength(-1);
        footNoteShape.setDivideLineTopMargin(850);
        footNoteShape.setDivideLineBottomMargin(567);
        footNoteShape.setBetweenNotesMargin(283);
        footNoteShape.getDivideLine().setType(BorderType.Solid);
        footNoteShape.getDivideLine().setThickness(BorderThickness.MM0_12);
        footNoteShape.getDivideLine().getColor().setValue(0);
        footNoteShape.setUnknown(0);
    }

    private static void endNoteShape(FootEndNoteShape endNoteShape) {
        endNoteShape.getProperty().setValue(0);
        endNoteShape.getUserSymbol().fromUTF16LEString("\u0000");
        endNoteShape.getBeforeDecorativeLetter().fromUTF16LEString("\u0000");
        endNoteShape.getAfterDecorativeLetter().fromUTF16LEString(")");
        endNoteShape.setStartNumber(1);
        endNoteShape.setDivideLineLength(14692344);
        endNoteShape.setDivideLineTopMargin(850);
        endNoteShape.setDivideLineBottomMargin(567);
        endNoteShape.setBetweenNotesMargin(0);
        endNoteShape.getDivideLine().setType(BorderType.Solid);
        endNoteShape.getDivideLine().setThickness(BorderThickness.MM0_12);
        endNoteShape.getDivideLine().getColor().setValue(0);
        endNoteShape.setUnknown(0);
    }

    private static void pageBorderFill(PageBorderFill pageBorderFill) {
        pageBorderFill.getProperty().setValue(1);
        pageBorderFill.setLeftGap(1417);
        pageBorderFill.setRightGap(1417);
        pageBorderFill.setTopGap(1417);
        pageBorderFill.setBottomGap(1417);
        pageBorderFill.setBorderFillId(1);
    }
}
