package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.ControlHiddenComment;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.ForParagraphList;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

/**
 * 숨은 설명 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlHiddenComment {
    /**
     * 숨은 설명 컨트롤
     */
    private ControlHiddenComment tcmt;
    /**
     * 스트림 리더
     */
    private StreamReader sr;

    /**
     * 생성자
     */
    public ForControlHiddenComment() {
    }

    /**
     * 숨은 설명 컨트롤을 읽는다.
     *
     * @param tcmt 숨은 설명 컨트롤
     * @param sr   스트림 리더
     * @throws Exception
     */
    public void read(ControlHiddenComment tcmt, StreamReader sr)
            throws Exception {
        this.tcmt = tcmt;
        this.sr = sr;

        listHeader();
        paragraphList();
    }

    /**
     * 숨은 설명 컨트롤의 문단 리스트 헤더 레코드을 읽는다.
     *
     * @throws Exception
     */
    private void listHeader() throws Exception {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.LIST_HEADER) {
            tcmt.getListHeader().setParaCount(sr.readSInt4());
            tcmt.getListHeader().getProperty().setValue(sr.readUInt4());
            sr.skipToEndRecord();
        } else {
            throw new Exception("List header must be located.");
        }
    }

    /**
     * 문단 리스트를 읽는다.
     *
     * @throws Exception
     */
    private void paragraphList() throws Exception {
        ForParagraphList.read(tcmt.getParagraphList(), sr);
    }
}
