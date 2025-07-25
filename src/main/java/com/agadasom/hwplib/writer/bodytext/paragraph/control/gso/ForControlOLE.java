package com.agadasom.hwplib.writer.bodytext.paragraph.control.gso;

import com.agadasom.hwplib.object.bodytext.control.gso.ControlOLE;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentOLE;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;

import java.io.IOException;

/**
 * OLE 컨트롤의 나머지 부분을 쓰기 위한 객체
 *
 * @author 박성균
 */
public class ForControlOLE {
    /**
     * OLE 컨트롤의 나머지 부분를 쓴다.
     *
     * @param ole OLE 컨트롤
     * @param sw  스트림 라이터
     * @throws IOException
     */
    public static void writeRest(ControlOLE ole, StreamWriter sw)
            throws IOException {
        sw.upRecordLevel();

        shapeComponentOLE(ole.getShapeComponentOLE(), sw);

        sw.downRecordLevel();
    }

    /**
     * OLE 개체 속성 레코드를 쓴다.
     *
     * @param sco OLE 개체 속성 레코드
     * @param sw  스트림 라이터
     * @throws IOException
     */
    private static void shapeComponentOLE(ShapeComponentOLE sco, StreamWriter sw)
            throws IOException {
        recordHeader(sw, sco);

        sw.writeUInt4(sco.getProperty().getValue());
        sw.writeSInt4(sco.getExtentWidth());
        sw.writeSInt4(sco.getExtentHeight());
        sw.writeUInt2(sco.getBinDataId());
        sw.writeUInt4(sco.getBorderColor().getValue());
        sw.writeSInt4(sco.getBorderThickness());
        sw.writeUInt4(sco.getBorderProperty().getValue());
        if (sco.getUnknown() != null) {
            sw.writeBytes(sco.getUnknown());
        }
    }

    /**
     * OLE 개체 속성 레코드의 레코드 헤더를 쓴다.
     *
     * @param sw  스트림 라이터
     * @param sco
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw, ShapeComponentOLE sco) throws IOException {
        sw.writeRecordHeader(HWPTag.SHAPE_COMPONENT_OLE, getSize(sco));
    }

    private static long getSize(ShapeComponentOLE sco) {
        if (sco.getUnknown() != null) {
            return 26 + sco.getUnknown().length;
        }
        return 26;
    }
}
