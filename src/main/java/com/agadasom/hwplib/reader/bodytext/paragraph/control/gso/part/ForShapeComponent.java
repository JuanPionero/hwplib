package com.agadasom.hwplib.reader.bodytext.paragraph.control.gso.part;

import com.agadasom.hwplib.object.bodytext.control.gso.GsoControl;
import com.agadasom.hwplib.object.bodytext.control.gso.GsoControlType;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.ShapeComponent;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.ShapeComponentContainer;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.ShapeComponentNormal;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.lineinfo.LineInfo;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.lineinfo.OutlineStyle;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.renderingnfo.Matrix;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.renderingnfo.RenderingInfo;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.renderingnfo.ScaleRotateMatrixPair;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo.ShadowInfo;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo.ShadowType;
import com.agadasom.hwplib.object.docinfo.borderfill.fillinfo.FillInfo;
import com.agadasom.hwplib.reader.docinfo.borderfill.ForFillInfo;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 그리기 개체의 객체 공통 속성 레코드을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForShapeComponent {
    /**
     * 그리기 개체의 객체 공통 속성 레코드를 읽는다.
     *
     * @param gsoControl 그리기 개체
     * @param sr         스트림 리더
     * @throws IOException
     */
    public static void read(GsoControl gsoControl, StreamReader sr)
            throws IOException {
        if (gsoControl.getGsoType() == GsoControlType.Container) {
            shapeComponentForContainer(
                    (ShapeComponentContainer) gsoControl.getShapeComponent(),
                    sr);
        } else {
            shapeComponentForNormal(
                    (ShapeComponentNormal) gsoControl.getShapeComponent(), sr);
        }
    }

    /**
     * 일반 컨트롤을 위한 객체 공통 속성 레코드을 읽는다.
     *
     * @param scn 객체 공통 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void shapeComponentForNormal(ShapeComponentNormal scn,
            StreamReader sr) throws IOException {
        commonPart(scn, sr);

        if (sr.isEndOfRecord())
            return;

        lineInfo(scn, sr);

        if (sr.isEndOfRecord())
            return;

        fillInfo(scn, sr);

        if (sr.isEndOfRecord())
            return;

        shadowInfo(scn, sr);

        if (sr.isEndOfRecord())
            return;

        scn.setInstid(sr.readUInt4());
        sr.skip(1);
        scn.getShadowInfo().setTransparent(sr.readUInt1());
    }

    /**
     * 객체 공통 속성 레코드의 공통 부분을 읽는다.
     *
     * @param sc 객체 공통 속성 레코드
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void commonPart(ShapeComponent sc, StreamReader sr)
            throws IOException {
        sc.setOffsetX(sr.readSInt4());
        sc.setOffsetY(sr.readSInt4());
        sc.setGroupingCount(sr.readUInt2());
        sc.setLocalFileVersion(sr.readUInt2());
        sc.setWidthAtCreate(sr.readSInt4());
        sc.setHeightAtCreate(sr.readSInt4());
        sc.setWidthAtCurrent(sr.readSInt4());
        sc.setHeightAtCurrent(sr.readSInt4());
        sc.getProperty().setValue(sr.readUInt4());
        sc.setRotateAngle(sr.readUInt2());
        sc.setRotateXCenter(sr.readSInt4());
        sc.setRotateYCenter(sr.readSInt4());

        renderingInfo(sc.getRenderingInfo(), sr);
    }

    /**
     * 객체 공통 속성 레코드의 rendering 정보를 읽는다.
     *
     * @param ri rendering 정보를 나타내는 객체
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void renderingInfo(RenderingInfo ri, StreamReader sr)
            throws IOException {
        int scaleRotateMatrixCount = sr.readUInt2();
        matrix(ri.getTranslationMatrix(), sr);
        for (int index = 0; index < scaleRotateMatrixCount; index++) {
            ScaleRotateMatrixPair srmp = ri.addNewScaleRotateMatrixPair();
            matrix(srmp.getScaleMatrix(), sr);
            matrix(srmp.getRotateMatrix(), sr);
        }
    }

    /**
     * 변환 행렬을 읽는다.
     *
     * @param m  변환 행렬 객체
     * @param sr 스트림 리더
     * @throws IOException
     */
    private static void matrix(Matrix m, StreamReader sr) throws IOException {
        for (int index = 0; index < 6; index++) {
            m.setValue(index, sr.readDouble());
        }
    }

    /**
     * 일반 컨트롤을 위한 객체 공통 속성 레코드의 line 정보를 읽는다.
     *
     * @param scn 일반 컨트롤을 위한 객체 공통 속성 레코드의 line 정보를 나타내는 객체
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void lineInfo(ShapeComponentNormal scn, StreamReader sr)
            throws IOException {
        scn.createLineInfo();
        LineInfo li = scn.getLineInfo();
        li.getColor().setValue(sr.readUInt4());
        li.setThickness(sr.readSInt4());
        li.getProperty().setValue(sr.readUInt4());
        li.setOutlineStyle(OutlineStyle.valueOf((byte) sr.readUInt1()));
    }

    /**
     * 일반 컨트롤을 위한 객체 공통 속성 레코드의 배경 정보를 읽는다.
     *
     * @param scn 일반 컨트롤을 위한 객체 공통 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void fillInfo(ShapeComponentNormal scn, StreamReader sr)
            throws IOException {
        scn.createFillInfo();
        FillInfo fi = scn.getFillInfo();
        ForFillInfo.read(fi, sr);
    }

    /**
     * 일반 컨트롤을 위한 객체 공통 속성 레코드의 그림자 정보를 읽는다.
     *
     * @param scn 일반 컨트롤을 위한 객체 공통 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void shadowInfo(ShapeComponentNormal scn, StreamReader sr)
            throws IOException {
        scn.createShadowInfo();
        ShadowInfo si = scn.getShadowInfo();
        si.setType(ShadowType.valueOf((byte) sr.readUInt4()));
        si.getColor().setValue(sr.readUInt4());
        si.setOffsetX(sr.readSInt4());
        si.setOffsetY(sr.readSInt4());
    }

    /**
     * 묶음 컨트롤을 위한 객체 공통 속성 레코드를 읽는다.
     *
     * @param scc 객체 공통 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void shapeComponentForContainer(ShapeComponentContainer scc,
            StreamReader sr) throws IOException {
        commonPart(scc, sr);
        childInfo(scc, sr);

        if (sr.isEndOfRecord())
            return;

        scc.setInstid(sr.readUInt4());
    }

    /**
     * 포함하고 있는 컨트롤에 대한 정보 부분을 읽는다.
     *
     * @param scc 묶음 컨트롤의 객체 공통 속성 레코드
     * @param sr  스트림 리더
     * @throws IOException
     */
    private static void childInfo(ShapeComponentContainer scc, StreamReader sr)
            throws IOException {
        int count = sr.readUInt2();
        for (int index = 0; index < count; index++) {
            long childId = sr.readUInt4();
            scc.addChildControlId(childId);
        }
    }
}
