package com.agadasom.hwplib.reader.bodytext.paragraph;

import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 문단의 글자 모양 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForParaCharShape {
    /**
     * 문단의 글자 모양 레코드를 읽는다.
     *
     * @param paragraph 문단 객체
     * @param sr        스트림 리더
     * @throws IOException
     */
    public static void read(Paragraph paragraph, StreamReader sr)
            throws IOException {
        paragraph.createCharShape();

        int count = paragraph.getHeader().getCharShapeCount();
        if (count != 0) {
            for (int index = 0; index < count; index++) {
                long position = sr.readUInt4();
                long charShapeId = sr.readUInt4();

                paragraph.getCharShape().addParaCharShape(position, charShapeId);
            }
        } else {
            sr.nextRecord();
        }
    }
}
