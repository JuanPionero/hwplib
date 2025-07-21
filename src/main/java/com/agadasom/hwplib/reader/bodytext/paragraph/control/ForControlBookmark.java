package com.agadasom.hwplib.reader.bodytext.paragraph.control;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.ControlBookmark;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.paragraph.control.bookmark.ForCtrlData;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

/**
 * 책갈피 컨트롤을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlBookmark {
    /**
     * 책갈피 컨트롤을 읽는다.
     *
     * @param b  책갈피 컨트롤
     * @param sr 스트림 리더
     * @throws Exception
     */
    public static void read(ControlBookmark b, StreamReader sr)
            throws Exception {
        ctrlData(b, sr);
    }

    /**
     * 컨트롤 데이터 레코드를 읽는다.
     *
     * @param b  컨트롤 데이터 레코드
     * @param sr 스트림 리더
     * @throws Exception
     */
    private static void ctrlData(ControlBookmark b, StreamReader sr)
            throws Exception {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.CTRL_DATA) {
            b.createCtrlData();
            ForCtrlData.read(b.getCtrlData(), sr);
        }
    }
}
