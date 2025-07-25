package com.agadasom.hwplib.reader.bodytext.paragraph;

import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.object.bodytext.paragraph.lineseg.LineSegItem;
import com.agadasom.hwplib.object.bodytext.paragraph.lineseg.ParaLineSeg;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 문단의 레이아웃 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForParaLineSeg {
    /**
     * 문단의 레이아웃 레코드를 읽는다.
     *
     * @param p  문단 객체
     * @param sr 스트림 리더
     * @throws IOException
     */
    public static void read(Paragraph p, StreamReader sr) throws IOException {
        p.createLineSeg();
        int count = p.getHeader().getLineAlignCount();
        if (count != 0) {
            ParaLineSeg pls = p.getLineSeg();
            for (int index = 0; index < count; index++) {
                paraLineSeqItem(pls.addNewLineSegItem(), sr);
            }
        } else {
            sr.nextRecord();
        }
    }

    /**
     * 한 라인의 레이아웃 정보를 읽는다.
     *
     * @param plsi 한 라인의 레이아웃 정보
     * @param sr   스트림 리더
     * @throws IOException
     */
    private static void paraLineSeqItem(LineSegItem plsi, StreamReader sr)
            throws IOException {
        plsi.setTextStartPosition(sr.readUInt4());
        plsi.setLineVerticalPosition(sr.readSInt4());
        plsi.setLineHeight(sr.readSInt4());
        plsi.setTextPartHeight(sr.readSInt4());
        plsi.setDistanceBaseLineToLineVerticalPosition(sr.readSInt4());
        plsi.setLineSpace(sr.readSInt4());
        plsi.setStartPositionFromColumn(sr.readSInt4());
        plsi.setSegmentWidth(sr.readSInt4());
        plsi.getTag().setValue(sr.readUInt4());
    }
}
