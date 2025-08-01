package com.agadasom.hwplib.writer.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlAutoNumber;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderAutoNumber;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

/**
 * 자동 번호 컨트롤을 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForControlAutoNumber {
    /**
     * 자동 번호 컨트롤을 쓴다.
     *
     * @param an 자동 번호 컨트롤
     * @param sw 스트림 라이터
     * @throws IOException
     */
    public static void write(ControlAutoNumber an, StreamWriter sw)
            throws IOException {
        ctrlHeader(an.getHeader(), sw);
    }

    /**
     * 자동 번호 컨트롤의 컨트롤 헤더 레코드를 쓴다.
     *
     * @param h  자동 번호 컨트롤의 컨트롤 헤더 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderAutoNumber h, StreamWriter sw)
            throws IOException {
        recordHeader(sw);
        sw.writeUInt4(h.getCtrlId());

        sw.writeUInt4(h.getProperty().getValue());
        sw.writeUInt2(h.getNumber());
        sw.writeWChar(h.getUserSymbol().getBytes());
        sw.writeWChar(h.getBeforeDecorationLetter().getBytes());
        sw.writeWChar(h.getAfterDecorationLetter().getBytes());
    }

    /**
     * 컨트롤 헤더 레코드의 레코드 헤더를 쓴다.
     *
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.CTRL_HEADER, 16);
    }
}
