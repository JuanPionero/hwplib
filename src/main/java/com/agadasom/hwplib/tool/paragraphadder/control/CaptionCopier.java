package com.agadasom.hwplib.tool.paragraphadder.control;

import com.agadasom.hwplib.object.bodytext.control.gso.caption.Caption;
import com.agadasom.hwplib.object.bodytext.control.gso.caption.ListHeaderForCaption;
import com.agadasom.hwplib.tool.paragraphadder.ParagraphCopier;
import com.agadasom.hwplib.tool.paragraphadder.docinfo.DocInfoAdder;

public class CaptionCopier {
    public static void copy(Caption source, Caption target, DocInfoAdder docInfoAdder) {
        ListHeaderForCaption sourceLH = source.getListHeader();
        ListHeaderForCaption targetLH = target.getListHeader();
        targetLH.copy(sourceLH);

        ParagraphCopier.listCopy(source.getParagraphList(), target.getParagraphList(), docInfoAdder);
    }
}
