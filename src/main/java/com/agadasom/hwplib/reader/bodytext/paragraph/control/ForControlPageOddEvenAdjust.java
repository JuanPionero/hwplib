package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlPageOddEvenAdjust;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

public class ForControlPageOddEvenAdjust {
    public static void read(ControlPageOddEvenAdjust pgoea, StreamReader sr) throws IOException {
        pgoea.getHeader().getProperty().setValue(sr.readUInt4());
    }
}
