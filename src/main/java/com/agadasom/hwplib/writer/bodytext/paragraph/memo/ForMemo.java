package com.agadasom.hwplib.writer.bodytext.paragraph.memo;

import com.agadasom.hwplib.object.bodytext.paragraph.memo.ListHeaderForMemo;
import com.agadasom.hwplib.object.bodytext.paragraph.memo.Memo;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;
import com.agadasom.hwplib.writer.bodytext.paragraph.ForParagraphList;

import java.io.IOException;

/**
 * 메모를 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForMemo {
    public static void write(Memo m, StreamWriter sw) throws Exception {
        ForMemoList.write(m.getMemoList(), sw);
        listHeader(m.getListHeader(), sw);
        ForParagraphList.write(m.getParagraphList(), sw);
    }

    /**
     * 메모의 리스트 헤더 레코드를 쓴다.
     *
     * @param li 메모의 리스트 헤더 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void listHeader(ListHeaderForMemo li, StreamWriter sw)
            throws IOException {
        recordHeader(sw);

        sw.writeSInt4(li.getParaCount());
        sw.writeUInt4(li.getProperty().getValue());
        sw.writeUInt4(li.getTextWidth());
        sw.writeUInt4(li.getTextHeight());
    }

    /**
     * 메모의 리스트 헤더 레코드의 레코드 헤더를 쓴다.
     *
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.LIST_HEADER, 16);
    }
}
