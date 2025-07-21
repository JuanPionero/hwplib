package com.agadasom.hwplib.reader.docinfo;

import com.agadasom.hwplib.object.docinfo.MemoShape;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderThickness;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderType;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

public class ForMemoShape {
    public static void read(MemoShape ms, StreamReader sr) throws IOException {
        ms.setWidth(sr.readUInt4());
        ms.setLineType(BorderType.valueOf(sr.readSInt1()));
        ms.setLineWidth(BorderThickness.valueOf(sr.readSInt1()));
        ms.getLineColor().setValue(sr.readUInt4());
        ms.getFillColor().setValue(sr.readUInt4());
        ms.getActiveColor().setValue(sr.readUInt4());
        ms.setUnknown(sr.readUInt4());
    }
}
