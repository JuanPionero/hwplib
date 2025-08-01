package com.agadasom.hwplib.reader.bodytext.paragraph.control.gso.part;

import com.agadasom.hwplib.object.bodytext.control.gso.caption.Caption;
import com.agadasom.hwplib.object.bodytext.control.gso.caption.ListHeaderForCaption;
import com.agadasom.hwplib.reader.bodytext.ForParagraphList;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 캡션 정보을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForCaption {
    /**
     * 캡션 정보을 읽는다.
     *
     * @param caption 캡션 정보
     * @param sr      스트림 리더
     * @throws Exception
     */
    public static void read(Caption caption, StreamReader sr) throws Exception {
        listHeader(caption.getListHeader(), sr);
        ForParagraphList.read(caption.getParagraphList(), sr);
    }

    /**
     * 캡션 정보의 문단 리스트 헤더 레코드를 읽는다.
     *
     * @param listHeader 캡션 정보의 문단 리스트 헤더 레코드
     * @param sr         스트림 리더
     * @throws IOException
     */
    private static void listHeader(ListHeaderForCaption listHeader,
            StreamReader sr) throws IOException {
        listHeader.setParaCount(sr.readSInt4());
        listHeader.getProperty().setValue(sr.readUInt4());
        listHeader.getCaptionProperty().setValue(sr.readUInt4());
        listHeader.setCaptionWidth(sr.readUInt4());
        listHeader.setSpaceBetweenCaptionAndFrame(sr.readUInt2());
        listHeader.setTextWidth(sr.readUInt4());
        // 버전에 따라 8bytes가 있을 수 있음.
        sr.skipToEndRecord();
    }
}
