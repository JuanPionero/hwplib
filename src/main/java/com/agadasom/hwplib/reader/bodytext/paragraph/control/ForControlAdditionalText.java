package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlAdditionalText;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderAdditionalText;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.additionaltext.AdditionalTextPosition;
import com.agadasom.hwplib.object.docinfo.parashape.Alignment;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 덧말 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlAdditionalText {
    /**
     * 덧말 컨트롤을 읽는다.
     *
     * @param at 덧말 컨트롤
     * @param sr 스트림 리더
     * @throws IOException
     */
    public static void read(ControlAdditionalText at, StreamReader sr)
            throws IOException {
        ctrlHeader(at.getHeader(), sr);
    }

    /**
     * 덧말 컨트롤의 컨트롤 헤더 레코드를 읽는다.
     *
     * @param h  덧말 컨트롤의 컨트롤 헤더 레코드
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderAdditionalText h, StreamReader sr)
            throws IOException {
        h.getMainText().setBytes(sr.readHWPString());
        h.getSubText().setBytes(sr.readHWPString());
        h.setPosition(AdditionalTextPosition.valueOf((byte) sr.readUInt4()));
        h.setFsizeratio(sr.readUInt4());
        h.setOption(sr.readUInt4());
        h.setStyleId(sr.readUInt4());
        h.setAlignment(Alignment.valueOf((byte) sr.readUInt4()));
    }
}
