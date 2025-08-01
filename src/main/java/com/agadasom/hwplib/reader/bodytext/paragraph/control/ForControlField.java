package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.ControlField;
import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderField;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.paragraph.control.bookmark.ForCtrlData;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 필드 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlField {
    /**
     * 필드 컨트롤을 읽는다.
     *
     * @param field 필드 컨트롤
     * @param sr    스트림 리더
     * @throws IOException
     */
    public static void read(ControlField field, StreamReader sr)
            throws IOException {
        ctrlHeader(field.getHeader(), sr);
        ctrlData(field, sr);
    }

    /**
     * 필드 컨트롤의 컨트롤 헤더 레코드을 읽는다.
     *
     * @param h  필드 컨트롤의 컨트롤 헤더
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderField h, StreamReader sr)
            throws IOException {
        h.getProperty().setValue(sr.readUInt4());
        h.setEtcProperty(sr.readUInt1());
        h.getCommand().setBytes(sr.readHWPString());
        h.setInstanceId(sr.readUInt4());

        if (sr.isEndOfRecord() == false
                && h.getCtrlId() == ControlType.FIELD_UNKNOWN.getCtrlId()) {
            h.setMemoIndex(sr.readSInt4());
        }

        if (sr.isEndOfRecord())
            return;

        unknownBytes(sr);
    }

    /**
     * 알려지지 않은 byte을 처리한다.
     *
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void unknownBytes(StreamReader sr) throws IOException {
        sr.skipToEndRecord();
    }

    /**
     * 컨트롤 데이터 레코드를 읽는다.
     *
     * @param field 필드 컨트롤
     * @param sr    스트림 리더
     * @throws IOException
     */
    private static void ctrlData(ControlField field, StreamReader sr)
            throws IOException {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.CTRL_DATA) {
            field.createCtrlData();
            ForCtrlData.read(field.getCtrlData(), sr);
        }
    }
}
