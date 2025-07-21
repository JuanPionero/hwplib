package com.agadasom.hwplib.reader.bodytext.paragraph.control.secd;

import com.agadasom.hwplib.object.bodytext.control.sectiondefine.FootEndNoteShape;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderThickness;
import com.agadasom.hwplib.object.docinfo.borderfill.BorderType;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 각주/미주 모양 레코드를 읽기 위한 객체
 *
 * @author neolord
 */
public class ForFootEndNoteShape {
        /**
         * 각주/미주 모양 레코드를 읽는다.
         *
         * @param fens 각주/미주 모양 레코드
         * @param sr   스트림 리더
         * @throws IOException
         */
        public static void read(FootEndNoteShape fens, StreamReader sr)
                        throws IOException {
                fens.getProperty().setValue(sr.readUInt4());
                fens.getUserSymbol().setBytes(sr.readWChar());
                fens.getBeforeDecorativeLetter().setBytes(sr.readWChar());
                fens.getAfterDecorativeLetter().setBytes(sr.readWChar());
                fens.setStartNumber(sr.readUInt2());
                fens.setDivideLineLength(sr.readUInt4());
                fens.setDivideLineTopMargin(sr.readUInt2());
                fens.setDivideLineBottomMargin(sr.readUInt2());
                fens.setBetweenNotesMargin(sr.readUInt2());
                fens.getDivideLine().setType(BorderType.valueOf((byte) sr
                                .readUInt1()));
                fens.getDivideLine().setThickness(BorderThickness.valueOf((byte) sr
                                .readUInt1()));
                fens.getDivideLine().getColor().setValue(sr.readUInt4());

                if (sr.isEndOfRecord())
                        return;

                fens.setUnknown(sr.readUInt4());
        }
}
