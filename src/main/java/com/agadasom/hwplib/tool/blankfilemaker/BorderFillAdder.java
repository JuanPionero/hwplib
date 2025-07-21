package com.agadasom.hwplib.tool.blankfilemaker;

import com.agadasom.hwplib.object.docinfo.BorderFill;
import com.agadasom.hwplib.object.docinfo.DocInfo;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderThickness;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderType;
import com.agadasom.hwplib.object.docinfo.borderfill.EachBorder;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.PatternFill;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.PatternType;

public class BorderFillAdder {
    public static void add(DocInfo docInfo) {
        borderFill1(docInfo.addNewBorderFill());
        borderFill2(docInfo.addNewBorderFill());
    }

    private static void borderFill1(BorderFill bf) {
        bf.getProperty().setValue(0);
        border(bf.getLeftBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getRightBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getLeftBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getTopBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getBottomBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getDiagonalBorder(), BorderType.Solid, BorderThickness.MM0_1, 0);
        bf.getFillInfo().getType().setValue(0);
    }

    private static void border(EachBorder border, BorderType type, BorderThickness thickness, long color) {
        border.setType(type);
        border.setThickness(thickness);
        border.getColor().setValue(color);
    }

    private static void borderFill2(BorderFill bf) {
        bf.getProperty().setValue(0);
        border(bf.getLeftBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getRightBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getLeftBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getTopBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getBottomBorder(), BorderType.None, BorderThickness.MM0_1, 0);
        border(bf.getDiagonalBorder(), BorderType.Solid, BorderThickness.MM0_1, 0);
        bf.getFillInfo().getType().setPatternFill(true);
        bf.getFillInfo().createPatternFill();
        PatternFill pf = bf.getFillInfo().getPatternFill();
        pf.getBackColor().setValue(-1);
        pf.getPatternColor().setValue(-16777216);
        pf.setPatternType(PatternType.None);
    }
}
