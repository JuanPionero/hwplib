package com.agadasom.hwplib.writer.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlFootnote;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderFootnote;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;
import com.agadasom.hwplib.writer.bodytext.paragraph.ForParagraphList;
import com.agadasom.hwplib.writer.bodytext.paragraph.control.endnote.ForListHeaderForFootnodeEndnote;

import java.io.IOException;

/**
 * 각주 컨트롤을 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForControlFootnote {
    /**
     * 각주 컨트롤을 쓴다.
     *
     * @param fn 각주 컨트롤
     * @param sw 스트림 라이터
     * @throws Exception
     */
    public static void write(ControlFootnote fn, StreamWriter sw)
            throws Exception {
        ctrlHeader(fn.getHeader(), sw);

        sw.upRecordLevel();

        ForListHeaderForFootnodeEndnote.write(fn.getListHeader(), sw);
        ForParagraphList.write(fn.getParagraphList(), sw);

        sw.downRecordLevel();
    }

    /**
     * 각주 컨트롤의 컨트롤 헤더 레코드를 쓴다.
     *
     * @param h  각주 컨트롤의 컨트롤 헤더 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderFootnote h, StreamWriter sw)
            throws IOException {
        recordHeader(sw);
        sw.writeUInt4(h.getCtrlId());

        sw.writeUInt4(h.getNumber());
        sw.writeWChar(h.getBeforeDecorationLetter().getBytes());
        sw.writeWChar(h.getAfterDecorationLetter().getBytes());
        sw.writeUInt4(h.getNumberShape().getValue());
        sw.writeUInt4(h.getInstanceId());

    }

    /**
     * 컨트롤 헤더 레코드를 쓴다.
     *
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.CTRL_HEADER, 20);
    }
}
