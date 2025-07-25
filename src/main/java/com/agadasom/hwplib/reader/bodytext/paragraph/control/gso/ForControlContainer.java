package com.agadasom.hwplib.reader.bodytext.paragraph.control.gso;

import com.agadasom.hwplib.object.bodytext.control.gso.ControlContainer;
import com.agadasom.hwplib.object.bodytext.control.gso.GsoControl;
import com.agadasom.hwplib.object.bodytext.control.gso.shapecomponent.ShapeComponentContainer;
import com.agadasom.hwplib.util.compoundFile.reader.StreamReader;

/**
 * 묶은 컨트롤의 나머지 부분을 읽기 위한 객체
 *
 * @author neolord
 */
public class ForControlContainer {
    /**
     * 묶은 컨트롤의 나머지 부분을 읽는다.
     *
     * @param container 묶은 컨트롤
     * @param sr        스트림 리더
     * @throws Exception
     */
    public static void readRest(ControlContainer container, StreamReader sr)
            throws Exception {
        ShapeComponentContainer scc = (ShapeComponentContainer) container
                .getShapeComponent();
        int childCount = scc.getChildControlIdList().size();
        for (int index = 0; index < childCount; index++) {
            ForGsoControl fgc = new ForGsoControl();
            GsoControl gc = fgc.readInContainer(sr);
            container.addChildControl(gc);
        }
    }
}
