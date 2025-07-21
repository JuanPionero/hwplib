package com.agadasom.hwplib.reader.bodytext.paragraph.control.secd;

import com.agadasom.hwplib.object.bodytext.control.sectiondefine.PageBorderFill;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 쪽 테두리/배경 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForPageBorderFill {
    /**
     * 쪽 테두리/배경 레코드를 읽는다.
     *
     * @param pbf 쪽 테두리/배경 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    public static void read(PageBorderFill pbf, StreamReader sr)
            throws IOException {
        pbf.getProperty().setValue(sr.readUInt4());
        pbf.setLeftGap(sr.readUInt2());
        pbf.setRightGap(sr.readUInt2());
        pbf.setTopGap(sr.readUInt2());
        pbf.setBottomGap(sr.readUInt2());
        pbf.setBorderFillId(sr.readUInt2());
    }
}
