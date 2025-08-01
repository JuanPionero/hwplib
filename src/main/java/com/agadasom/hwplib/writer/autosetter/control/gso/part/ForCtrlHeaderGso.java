package com.agadasom.hwplib.writer.autosetter.control.gso.part;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.object.bodytext.control.gso.caption.Caption;
import com.agadasom.hwplib.writer.autosetter.InstanceID;

/**
 * 그리기 개체의 컨트롤 헤더 레코드를 쓰기 전에 자동 설정하기 위한 객체
 *
 * @author neolord
 */
public class ForCtrlHeaderGso {
    /**
     * 그리기 개체의 컨트롤 헤더 레코드를 자동 설정한다.
     *
     * @param h   그리기 개체의 컨트롤 헤더 레코드
     * @param iid 인스턴스 아이디
     */
    public static void autoSet(CtrlHeaderGso h, Caption c, InstanceID iid) {
        if (h == null) {
            return;
        }
        h.setInstanceId(iid.get());

        if (c != null) {
            h.getProperty().setHasCaption(true);
        } else {
            h.getProperty().setHasCaption(false);
        }
    }
}
