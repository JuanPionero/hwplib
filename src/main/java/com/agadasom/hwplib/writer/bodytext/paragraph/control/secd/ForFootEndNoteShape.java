package com.agadasom.hwplib.writer.bodytext.paragraph.control.secd;

import com.agadasom.hwplib.object.bodytext.control.sectiondefine.FootEndNoteShape;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

/**
 * 각주/미주 모양 레코드를 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForFootEndNoteShape {
    /**
     * 각주/미주 모양 레코드를 쓴다.
     *
     * @param fens 각주/미주 모양 레코드
     * @param sw   스트림 라이터
     * @throws IOException
     */
    public static void write(FootEndNoteShape fens, StreamWriter sw)
            throws IOException {
        if (fens == null) {
            return;
        }

        recordHeader(sw);

        sw.writeUInt4(fens.getProperty().getValue());
        sw.writeWChar(fens.getUserSymbol().getBytes());
        sw.writeWChar(fens.getBeforeDecorativeLetter().getBytes());
        sw.writeWChar(fens.getAfterDecorativeLetter().getBytes());
        sw.writeUInt2(fens.getStartNumber());
        sw.writeUInt4(fens.getDivideLineLength());
        sw.writeUInt2(fens.getDivideLineTopMargin());
        sw.writeUInt2(fens.getDivideLineBottomMargin());
        sw.writeUInt2(fens.getBetweenNotesMargin());
        sw.writeUInt1(fens.getDivideLine().getType().getValue());
        sw.writeUInt1(fens.getDivideLine().getThickness().getValue());
        sw.writeUInt4(fens.getDivideLine().getColor().getValue());
    }

    /**
     * 각주/미주 모양 레코드의 레코드 헤더를 쓴다.
     *
     * @param sw
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.FOOTNOTE_SHAPE, 28);
    }
}
