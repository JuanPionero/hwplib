package com.agadasom.hwplib.reader.bodytext.paragraph.control.gso;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.gso.ControlArc;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentArc;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.arc.ArcType;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.paragraph.control.gso.part.ForTextBox;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 호 컨트롤의 나머지 부분을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlArc {
    /**
     * 호 컨트롤의 나머지 부분을 읽는다.
     *
     * @param arc 호 컨트롤
     * @param sr  스트림 리더
     * @throws Exception
     */
    public static void readRest(ControlArc arc, StreamReader sr) throws Exception {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.LIST_HEADER) {
            arc.createTextBox();
            ForTextBox.read(arc.getTextBox(), sr);

            if (sr.isImmediatelyAfterReadingHeader() == false) {
                rh = sr.readRecordHeader();
            }
        }
        if (rh.getTagID() == HWPTag.SHAPE_COMPONENT_ARC) {
            shapeComponentArc(arc.getShapeComponentArc(), sr);
        }
    }

    /**
     * 호 개체 속성 레코드를 읽는다.
     *
     * @param sca 호 개체 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void shapeComponentArc(ShapeComponentArc sca, StreamReader sr)
            throws IOException {
        sca.setArcType(ArcType.valueOf((byte) sr.readUInt1()));
        sca.setCenterX(sr.readSInt4());
        sca.setCenterY(sr.readSInt4());
        sca.setAxis1X(sr.readSInt4());
        sca.setAxis1Y(sr.readSInt4());
        sca.setAxis2X(sr.readSInt4());
        sca.setAxis2Y(sr.readSInt4());
    }
}
