package com.agadasom.hwplib.reader.bodytext.paragraph.control.gso.part;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.util.binary.BitFlag;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 그리기 개체의 컨트롤 헤더 레코드를 읽는다.
 *
 * @author neolord
 */
public class ForCtrlHeaderGso {
    /**
     * 그리기 개체의 컨트롤 헤더 레코드를 읽는다.
     *
     * @param header 그리기 개체의 컨트롤 헤더 레코드
     * @param sr     스트림 리더
     * @throws IOException
     */
    public static void read(CtrlHeaderGso header, StreamReader sr)
            throws IOException {
        header.getProperty().setValue(sr.readUInt4());
        header.setyOffset(sr.readUInt4());
        header.setxOffset(sr.readUInt4());
        header.setWidth(sr.readUInt4());
        header.setHeight(sr.readUInt4());
        header.setzOrder(sr.readSInt4());
        header.setOutterMarginLeft(sr.readUInt2());
        header.setOutterMarginRight(sr.readUInt2());
        header.setOutterMarginTop(sr.readUInt2());
        header.setOutterMarginBottom(sr.readUInt2());
        header.setInstanceId(sr.readUInt4());

        if (sr.isEndOfRecord())
            return;

        int temp = sr.readSInt4();
        header.setPreventPageDivide(BitFlag.get(temp, 0));

        if (sr.isEndOfRecord())
            return;

        header.getExplanation().setBytes(sr.readHWPString());

        if (sr.isEndOfRecord())
            return;

        int length = (int) (sr.getCurrentRecordHeader().getSize() - sr.getCurrentPositionAfterHeader());
        byte[] unknown = new byte[length];
        sr.readBytes(unknown);
        header.setUnknown(unknown);
    }
}
