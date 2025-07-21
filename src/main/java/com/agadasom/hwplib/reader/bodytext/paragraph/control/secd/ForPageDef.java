package com.agadasom.hwplib.reader.bodytext.paragraph.control.secd;

import com.agadasom.hwplib.object.bodytext.control.sectiondefine.PageDef;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 용지 설정 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForPageDef {
    /**
     * 용지 설정 레코드를 읽는다.
     *
     * @param pd 용지 설정 레코드
     * @param sr 스트림 리더
     * @throws IOException
     */
    public static void read(PageDef pd, StreamReader sr)
            throws IOException {
        pd.setPaperWidth(sr.readUInt4());
        pd.setPaperHeight(sr.readUInt4());
        pd.setLeftMargin(sr.readUInt4());
        pd.setRightMargin(sr.readUInt4());
        pd.setTopMargin(sr.readUInt4());
        pd.setBottomMargin(sr.readUInt4());
        pd.setHeaderMargin(sr.readUInt4());
        pd.setFooterMargin(sr.readUInt4());
        pd.setGutterMargin(sr.readUInt4());
        pd.getProperty().setValue(sr.readUInt4());
    }
}
