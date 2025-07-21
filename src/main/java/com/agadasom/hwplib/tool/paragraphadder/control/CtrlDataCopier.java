package com.agadasom.hwplib.tool.paragraphadder.control;

import com.agadasom.hwplib.object.bodytext.control.Control;
import com.agadasom.hwplib.tool.paragraphadder.docinfo.DocInfoAdder;

public class CtrlDataCopier {
    public static void copy(Control source, Control target, DocInfoAdder docInfoAdder) {
        if (source.getCtrlData() != null) {
            target.createCtrlData();
            ParameterSetCopier.copy(source.getCtrlData().getParameterSet(), target.getCtrlData().getParameterSet(),
                    docInfoAdder);
        } else {
            target.deleteCtrlData();
        }
    }
}
