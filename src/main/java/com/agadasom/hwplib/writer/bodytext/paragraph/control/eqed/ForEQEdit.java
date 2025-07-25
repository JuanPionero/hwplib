package com.agadasom.hwplib.writer.bodytext.paragraph.control.eqed;

import com.agadasom.hwplib.object.bodytext.control.equation.EQEdit;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

/**
 * 수식 정보 레코드를 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForEQEdit {
    /**
     * 수식 정보 레코드를 쓴다.
     *
     * @param ee 수식 정보 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    public static void write(EQEdit ee, StreamWriter sw) throws IOException {
        recordHeader(ee, sw);

        sw.writeUInt4(ee.getProperty());
        sw.writeHWPString(ee.getScript());
        sw.writeUInt4(ee.getLetterSize());
        sw.writeUInt4(ee.getLetterColor().getValue());
        sw.writeSInt2(ee.getBaseLine());
        sw.writeUInt2(ee.getUnknown());
        sw.writeHWPString(ee.getVersionInfo());
        sw.writeHWPString(ee.getFontName());
    }

    /**
     * 수식 정보 레코드의 레코드 헤더를 쓴다.
     *
     * @param ee 수식 정보 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(EQEdit ee, StreamWriter sw)
            throws IOException {
        sw.writeRecordHeader(HWPTag.EQEDIT, getSize(ee));
    }

    /**
     * 수식 정보 레코드의 크기를 반환한다.
     *
     * @param ee 수식 정보 레코드
     * @return 수식 정보 레코드의 크기
     */
    private static int getSize(EQEdit ee) {
        int size = 0;
        size += 4;
        size += ee.getScript().getWCharsSize();
        size += 12;
        size += ee.getVersionInfo().getWCharsSize();
        size += ee.getFontName().getWCharsSize();
        return size;
    }
}
