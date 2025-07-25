package com.agadasom.hwplib.writer.bodytext.paragraph.control.form;

import com.agadasom.hwplib.object.bodytext.control.ControlForm;
import com.agadasom.hwplib.object.bodytext.control.form.FormObject;
import com.agadasom.hwplib.object.etc.HWPString;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;
import com.agadasom.hwplib.writer.bodytext.paragraph.control.gso.part.ForCtrlHeaderGso;

import java.io.IOException;

public class ForControlForm {
    public static void write(ControlForm form, StreamWriter sw) throws IOException {
        ForCtrlHeaderGso.write(form.getHeader(), sw);

        sw.upRecordLevel();

        formObject(form.getFormObject(), sw);

        sw.downRecordLevel();
    }

    private static void formObject(FormObject fo, StreamWriter sw) throws IOException {
        HWPString propertiesString = fo.getProperties().toHWPString();
        recordHeader(fo, propertiesString, sw);
        sw.writeUInt4(fo.getType().getId());
        sw.writeUInt4(fo.getType().getId());
        sw.writeUInt2(propertiesString.getWCharsSize());
        sw.writeZero(2);
        sw.writeHWPString(propertiesString);
    }

    private static void recordHeader(FormObject fo, HWPString propertiesString, StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.FORM_OBJECT, getSize(fo, propertiesString));
    }

    private static long getSize(FormObject fo, HWPString propertiesString) {
        return 12 + propertiesString.getWCharsSize();
    }
}
