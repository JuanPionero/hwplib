package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.ControlFooter;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.header.HeaderFooterApplyPage;
import com.agadasom.hwplib.object.bodytext.control.headerfooter.ListHeaderForHeaderFooter;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.ForParagraphList;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 꼬리말 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlFooter {
    /**
     * 꼬리말 컨트롤
     */
    private ControlFooter foot;
    /**
     * 스트림 리더
     */
    private StreamReader sr;

    /**
     * 생성자
     */
    public ForControlFooter() {
    }

    /**
     * 꼬리말 컨트롤을 읽는다.
     *
     * @param foot 꼬리말 컨트롤
     * @param sr   스트림 리더
     * @throws Exception
     */
    public void read(ControlFooter foot, StreamReader sr) throws Exception {
        this.foot = foot;
        this.sr = sr;

        ctrlHeader();
        listHeader();
        paragraphList();
    }

    /**
     * 꼬리말 컨트롤의 컨트롤 헤더 레코드를 읽는다.
     *
     * @throws IOException
     */
    private void ctrlHeader() throws IOException {
        foot.getHeader().setApplyPage(
                HeaderFooterApplyPage.valueOf((byte) sr.readUInt4()));
        if (sr.getCurrentRecordHeader().getSize() > sr.getCurrentPositionAfterHeader()) {
            foot.getHeader().setCreateIndex(sr.readSInt4());
        }
    }

    /**
     * 꼬리말 컨트롤의 문단 리스트 헤더 레코드를 읽는다.
     *
     * @throws Exception
     */
    private void listHeader() throws Exception {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.LIST_HEADER) {
            ListHeaderForHeaderFooter lh = foot.getListHeader();
            lh.setParaCount(sr.readSInt4());
            lh.getProperty().setValue(sr.readUInt4());
            lh.setTextWidth(sr.readUInt4());
            lh.setTextHeight(sr.readUInt4());
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
        ForParagraphList.read(foot.getParagraphList(), sr);
    }
}
