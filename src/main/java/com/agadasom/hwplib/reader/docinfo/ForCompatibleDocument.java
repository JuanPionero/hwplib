package com.agadasom.hwplib.reader.docinfo;

import com.agadasom.hwplib.object.docinfo.CompatibleDocument;
import com.agadasom.hwplib.object.docinfo.compatibledocument.CompatibleDocumentSort;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 호환 문서 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForCompatibleDocument {
    /**
     * 호환 문서 정보를 읽는다.
     *
     * @param cd 호환 문서 레코드
     * @param sr 스트림 리더
     * @throws IOException
     */
    public static void read(CompatibleDocument cd,
            StreamReader sr) throws IOException {
        cd.setTargetProgream(CompatibleDocumentSort.valueOf((byte) sr.readUInt4()));
    }
}
