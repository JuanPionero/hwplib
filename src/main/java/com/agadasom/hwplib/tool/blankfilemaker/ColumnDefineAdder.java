package com.agadasom.hwplib.tool.blankfilemaker;

import com.agadasom.hwplib.object.bodytext.control.ControlColumnDefine;
import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderColumnDefine;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderThickness;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderType;

public class ColumnDefineAdder {
    public static void add(Paragraph paragraph) {
        ControlColumnDefine columnDefine = (ControlColumnDefine) paragraph.addNewControl(ControlType.ColumnDefine);
        header(columnDefine.getHeader());
    }

    private static void header(CtrlHeaderColumnDefine header) {
        header.getProperty().setValue(4100);
        header.setGapBetweenColumn(0);
        header.setProperty2(0);
        header.getDivideLine().setType(BorderType.None);
        header.getDivideLine().setThickness(BorderThickness.MM0_1);
        header.getDivideLine().getColor().setValue(0);
    }
}
