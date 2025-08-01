package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.ControlFootnote;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderFootnote;
import com.agadasom.hwplib.object.bodytext.control.footnoteendnote.ListHeaderForFootnodeEndnote;
import com.agadasom.hwplib.object.bodytext.control.sectiondefine.NumberShape;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.ForParagraphList;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 각주 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlFootnote {
    /**
     * 각주 컨트롤
     */
    private ControlFootnote fn;
    /**
     * 스트림 리더
     */
    private StreamReader sr;

    /**
     * 생성자
     */
    public ForControlFootnote() {
    }

    /**
     * 각주 컨트롤을 읽는다.
     *
     * @param fn 각주 컨트롤
     * @param sr 스트림 리더
     * @throws Exception
     */
    public void read(ControlFootnote fn, StreamReader sr) throws Exception {
        this.fn = fn;
        this.sr = sr;

        ctrlHeader();
        listHeader();
        paragraphList();
    }

    /**
     * 각주 컨트롤의 컨트롤 헤더 레코드를 읽는다.
     *
     * @throws IOException
     */
    private void ctrlHeader() throws IOException {
        CtrlHeaderFootnote h = fn.getHeader();
        h.setNumber(sr.readUInt4());
        h.getBeforeDecorationLetter().setBytes(sr.readWChar());
        h.getAfterDecorationLetter().setBytes(sr.readWChar());
        h.setNumberShape(NumberShape.valueOf((short) sr.readUInt4()));

        if (sr.isEndOfRecord())
            return;
        ;

        h.setInstanceId(sr.readUInt4());
    }

    /**
     * 각주 컨트롤의 문단 리스트 헤더 레코드를 읽는다.
     *
     * @throws Exception
     */
    private void listHeader() throws Exception {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.LIST_HEADER) {
            ListHeaderForFootnodeEndnote lh = fn.getListHeader();
            lh.setParaCount(sr.readSInt4());
            lh.getProperty().setValue(sr.readUInt4());
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
        ForParagraphList.read(fn.getParagraphList(), sr);
    }
}
