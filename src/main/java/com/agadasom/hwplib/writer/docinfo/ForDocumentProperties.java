package com.agadasom.hwplib.writer.docinfo;

import com.agadasom.hwplib.object.docinfo.DocumentProperties;
import com.agadasom.hwplib.object.docinfo.documentproperties.CaretPosition;
import com.agadasom.hwplib.object.docinfo.documentproperties.StartNumber;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

/**
 * 문서 속성 레코드를 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForDocumentProperties {
    /**
     * 문서 속성 레코드를 쓴다.
     *
     * @param dp 문서 속성 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    public static void write(DocumentProperties dp, StreamWriter sw)
            throws IOException {
        recordHeader(sw);

        sw.writeUInt2(dp.getSectionCount());
        startNumber(dp.getStartNumber(), sw);
        caretPosition(dp.getCaretPosition(), sw);
    }

    /**
     * 문서 속성 레코드의 레코드 헤더를 쓴다.
     *
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.DOCUMENT_PROPERTIES, 26);
    }

    /**
     * 시작 번호 부분을 쓴다.
     *
     * @param sn 시작 번호 객체
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void startNumber(StartNumber sn, StreamWriter sw)
            throws IOException {
        sw.writeUInt2(sn.getPage());
        sw.writeUInt2(sn.getFootnote());
        sw.writeUInt2(sn.getEndnote());
        sw.writeUInt2(sn.getPicture());
        sw.writeUInt2(sn.getTable());
        sw.writeUInt2(sn.getEquation());
    }

    /**
     * 캐릿 위치 부분을 쓴다.
     *
     * @param cp 캐릿 위치 객체
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void caretPosition(CaretPosition cp, StreamWriter sw)
            throws IOException {
        sw.writeUInt4(cp.getListID());
        sw.writeUInt4(cp.getParagraphID());
        sw.writeUInt4(cp.getPositionInParagraph());
    }
}
