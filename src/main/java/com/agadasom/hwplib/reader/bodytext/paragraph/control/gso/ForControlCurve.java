package com.agadasom.hwplib.reader.bodytext.paragraph.control.gso;

import com.agadasom.hwplib.object.RecordHeader;
import com.agadasom.hwplib.object.bodytext.control.gso.ControlCurve;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentCurve;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.curve.CurveSegmentType;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.polygon.PositionXY;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.reader.bodytext.paragraph.control.gso.part.ForTextBox;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 곡선 컨트롤의 나머지 부분을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlCurve {
    /**
     * 곡선 컨트롤의 나머지 부분을 읽는다.
     *
     * @param curve 곡선 컨트롤
     * @param sr    스트림 리더
     * @throws Exception
     */
    public static void readRest(ControlCurve curve, StreamReader sr)
            throws Exception {
        RecordHeader rh = sr.readRecordHeader();
        if (rh.getTagID() == HWPTag.LIST_HEADER) {
            curve.createTextBox();
            ForTextBox.read(curve.getTextBox(), sr);

            if (sr.isImmediatelyAfterReadingHeader() == false) {
                rh = sr.readRecordHeader();
            }
        }
        if (rh.getTagID() == HWPTag.SHAPE_COMPONENT_CURVE) {
            shapeComponentCurve(curve.getShapeComponentCurve(), sr);
        }
    }

    /**
     * 곡선 개체 속성 레코드를 읽는다.
     *
     * @param scc 곡선 개체 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void shapeComponentCurve(ShapeComponentCurve scc,
            StreamReader sr) throws IOException {
        int positionCount = sr.readSInt4();
        for (int index = 0; index < positionCount; index++) {
            PositionXY p = scc.addNewPosition();
            p.setX(sr.readSInt4());
            p.setY(sr.readSInt4());
        }
        for (int index = 0; index < positionCount - 1; index++) {
            CurveSegmentType cst = CurveSegmentType.valueOf((byte) sr
                    .readUInt1());
            scc.addCurveSegmentType(cst);
        }
        sr.skip(4);
    }
}
