package com.agadasom.hwplib.reader.bodytext.paragraph;

import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.object.bodytext.paragraph.rangetag.ParaRangeTag;
import com.agadasom.hwplib.object.bodytext.paragraph.rangetag.RangeTagItem;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 문서의 영역 테그 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForParaRangeTag {
    /**
     * 문서의 영역 태그 레코드를 읽는다.
     *
     * @param p  문단 객체
     * @param sr 스트림 리더
     * @throws Exception
     */
    public static void read(Paragraph p, StreamReader sr)
            throws Exception {
        p.createRangeTag();

        if (sr.getCurrentRecordHeader().getSize() != 0) {
            ParaRangeTag prt = p.getRangeTag();
            long count = sr.getCurrentRecordHeader().getSize() / 12;
            for (long index = 0; index < count; index++) {
                RangeTagItem rti = prt.addNewRangeTagItem();
                rti.setRangeStart(sr.readUInt4());
                rti.setRangeEnd(sr.readUInt4());
                tag(rti, sr);
            }
        } else {
            sr.nextRecord();
        }
    }

    /**
     * 영역 태그 아이템의 영역 부분을 읽는다.
     *
     * @param rti 영역 태그 아이템
     * @param sr  스트림 리더
     * @throws IOException
     * @throws Exception
     */
    private static void tag(RangeTagItem rti, StreamReader sr)
            throws IOException, Exception {
        byte[] data = new byte[3];
        sr.readBytes(data);
        rti.setData(data);
        rti.setSort(sr.readUInt1());
    }
}
