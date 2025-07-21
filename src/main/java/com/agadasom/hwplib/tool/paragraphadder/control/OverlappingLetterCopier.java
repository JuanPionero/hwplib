package com.agadasom.hwplib.tool.paragraphadder.control;

import com.agadasom.hwplib.object.bodytext.control.ControlOverlappingLetter;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderOverlappingLetter;
import com.agadasom.hwplib.object.etc.HWPString;
import com.agadasom.hwplib.tool.paragraphadder.docinfo.DocInfoAdder;

public class OverlappingLetterCopier {
    public static void copy(ControlOverlappingLetter source, ControlOverlappingLetter target,
            DocInfoAdder docInfoAdder) {
        header(source.getHeader(), target.getHeader(), docInfoAdder);
        CtrlDataCopier.copy(source, target, docInfoAdder);
    }

    private static void header(CtrlHeaderOverlappingLetter source, CtrlHeaderOverlappingLetter target,
            DocInfoAdder docInfoAdder) {
        target.setBorderType(source.getBorderType());
        target.setExpendInsideLetter(source.getExpendInsideLetter());
        target.setInternalFontSize(source.getInternalFontSize());

        for (HWPString s : source.getOverlappingLetterList()) {
            target.addOverlappingLetter(s.clone());
        }

        for (Long charShapeId : source.getCharShapeIdList()) {
            target.addCharShapeId((docInfoAdder == null) ? charShapeId.intValue()
                    : docInfoAdder.forCharShape().processById(charShapeId.intValue()));
        }
    }
}