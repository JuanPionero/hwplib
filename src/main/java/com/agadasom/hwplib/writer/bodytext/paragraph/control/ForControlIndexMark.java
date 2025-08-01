package com.agadasom.hwplib.writer.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlIndexMark;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderIndexMark;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

/**
 * 찾아보기 표식 컨트롤을 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForControlIndexMark {
    /**
     * 찾아보기 표식 컨트롤을 쓴다.
     *
     * @param im 찾아보기 표식 컨트롤
     * @param sw 스트림 라이터
     * @throws IOException
     */
    public static void write(ControlIndexMark im, StreamWriter sw)
            throws IOException {
        ctrlHeader(im.getHeader(), sw);
    }

    /**
     * 찾아보기 표식 컨트롤의 컨트롤 헤더 레코드를 쓴다.
     *
     * @param h  찾아보기 표식 컨트롤의 컨트롤 헤더 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderIndexMark h, StreamWriter sw)
            throws IOException {
        recordHeader(h, sw);
        sw.writeUInt4(h.getCtrlId());

        sw.writeHWPString(h.getKeyword1());
        sw.writeHWPString(h.getKeyword2());
    }

    /**
     * 컨트롤 헤더 레코드의 레코드 헤더를 쓴다.
     *
     * @param h  컨트롤 헤더 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(CtrlHeaderIndexMark h, StreamWriter sw)
            throws IOException {
        sw.writeRecordHeader(HWPTag.CTRL_HEADER, getSize(h));
    }

    /**
     * 컨트롤 헤더 레코드의 크기를 반환한다.
     *
     * @param h 컨트롤 헤더 레코드
     * @return 컨트롤 헤더 레코드의 크기
     */
    private static int getSize(CtrlHeaderIndexMark h) {
        int size = 0;
        size += 4;
        size += h.getKeyword1().getWCharsSize();
        size += h.getKeyword2().getWCharsSize();
        return size;
    }
}
