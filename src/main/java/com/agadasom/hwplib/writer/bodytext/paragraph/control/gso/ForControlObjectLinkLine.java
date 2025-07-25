package com.agadasom.hwplib.writer.bodytext.paragraph.control.gso;

import com.agadasom.hwplib.object.bodytext.control.gso.ControlObjectLinkLine;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentLineForObjectLinkLine;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.objectlinkline.ControlPoint;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

public class ForControlObjectLinkLine {
    /**
     * 객체 연결선 컨트롤의 나머지 부분을 쓴다.
     *
     * @param oll 객체 연결선 컨트롤
     * @param sw  스트림 라이터
     * @throws IOException
     */
    public static void writeRest(ControlObjectLinkLine oll, StreamWriter sw) throws IOException {
        sw.upRecordLevel();

        shapeComponentLine(oll.getShapeComponentLine(), sw);

        sw.downRecordLevel();
    }

    /**
     * 선 개체 속성 레코드를 쓴다.
     *
     * @param scl 선 개체 속성 레코드
     * @param sw  스트림 라이터
     * @throws IOException
     */
    private static void shapeComponentLine(
            ShapeComponentLineForObjectLinkLine scl, StreamWriter sw) throws IOException {
        recordHeader(sw, scl);

        sw.writeSInt4(scl.getStartX());
        sw.writeSInt4(scl.getStartY());
        sw.writeSInt4(scl.getEndX());
        sw.writeSInt4(scl.getEndY());
        sw.writeUInt4(scl.getType().getValue());
        sw.writeUInt4(scl.getStartSubjectID());
        sw.writeUInt4(scl.getStartSubjectIndex());
        sw.writeUInt4(scl.getEndSubjectID());
        sw.writeUInt4(scl.getEndSubjectIndex());

        sw.writeUInt4(scl.getControlPoints().size());
        for (ControlPoint cp : scl.getControlPoints()) {
            sw.writeUInt4(cp.getX());
            sw.writeUInt4(cp.getY());
            sw.writeUInt2(cp.getType());
        }

        sw.writeZero(4);
    }

    /**
     * 선 개체 속성 레코드를 쓴다.
     *
     * @param scl 선 개체 속성 레코드
     * @param sw  스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw,
            ShapeComponentLineForObjectLinkLine scl) throws IOException {
        sw.writeRecordHeader(HWPTag.SHAPE_COMPONENT_LINE, getSize(scl));
    }

    /**
     * 선 개체 속성 레코드의 크기를 반환한다.
     *
     * @param scl 선 개체 속성 레코드
     * @return 선 개체 속성 레코드의 크기
     */
    private static int getSize(ShapeComponentLineForObjectLinkLine scl) {
        return 40 + scl.getControlPoints().size() * 10 + 4;
    }
}
