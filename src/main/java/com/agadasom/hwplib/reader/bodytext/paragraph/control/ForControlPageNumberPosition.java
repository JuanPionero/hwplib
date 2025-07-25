package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlPageNumberPosition;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderPageNumberPosition;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 쪽 번호 위치 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlPageNumberPosition {
    /**
     * 쪽 번호 위치 컨트롤을 읽는다.
     *
     * @param pgnp 쪽 번호 위치 컨트롤 객체
     * @param sr   스트림 리더
     * @throws IOException
     */
    public static void read(ControlPageNumberPosition pgnp, StreamReader sr)
            throws IOException {
        ctrlHeader(pgnp.getHeader(), sr);
    }

    /**
     * 쪽 번호 위치 컨트롤의 컨트롤 헤더 레코드를 읽는다.
     *
     * @param header 쪽 번호 위치 컨트롤 헤더 레코드
     * @param sr     스트림 리더
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderPageNumberPosition header,
            StreamReader sr) throws IOException {
        header.getProperty().setValue(sr.readUInt4());
        header.setNumber(sr.readUInt2());
        header.getUserSymbol().setBytes(sr.readWChar());
        header.getBeforeDecorationLetter().setBytes(sr.readWChar());
        header.getAfterDecorationLetter().setBytes(sr.readWChar());
    }
}
