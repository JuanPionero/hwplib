package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.bodytext.control.ControlColumnDefine;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderColumnDefine;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.columndefine.ColumnInfo;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderThickness;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderType;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 단 정의 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlColumnDefine {
    /**
     * 단 정의 컨트롤을 읽는다.
     *
     * @param cd 단 정의 컨트롤
     * @param sr 스트림 리더
     * @throws IOException
     */
    public static void read(ControlColumnDefine cd, StreamReader sr)
            throws IOException {
        ctrlHeader(cd.getHeader(), sr);
    }

    /**
     * 단 정의 컨트롤 헤더 레코드를 읽는다.
     *
     * @param h  단 정의 컨트롤의 컨트롤 헤더 레코드
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void ctrlHeader(CtrlHeaderColumnDefine h, StreamReader sr)
            throws IOException {
        h.getProperty().setValue(sr.readUInt2());

        int count = h.getProperty().getColumnCount();
        boolean sameWidth = h.getProperty().isSameWidth();
        if (count < 2 || sameWidth == true) {
            h.setGapBetweenColumn(sr.readUInt2());
            h.setProperty2(sr.readUInt2());
        } else {
            h.setProperty2(sr.readUInt2());
            columnInfos(h, sr);
        }

        h.getDivideLine().setType(BorderType.valueOf((byte) sr.readUInt1()));
        h.getDivideLine().setThickness(BorderThickness.valueOf((byte) sr.readUInt1()));
        h.getDivideLine().getColor().setValue(sr.readUInt4());

        sr.skipToEndRecord();
    }

    /**
     * 단 정의 컨트롤의 컨트롤 헤더 레코드의 단 정보 부분를 읽는다.
     *
     * @param h  단 정의 컨트롤의 컨트롤 헤더 레코드
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void columnInfos(CtrlHeaderColumnDefine h, StreamReader sr)
            throws IOException {
        int count = h.getProperty().getColumnCount();
        for (int index = 0; index < count; index++) {
            ColumnInfo ci = h.addNewColumnInfo();
            ci.setWidth(sr.readUInt2());
            ci.setGap(sr.readUInt2());
        }
    }
}
