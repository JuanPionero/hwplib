package com.agadasom.hwplib.object.bodytext.control;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import com.agadasom.hwplib.object.bodytext.control.equation.EQEdit;
import com.agadasom.hwplib.object.bodytext.control.gso.caption.Caption;

/**
 * 수식 컨트롤
 *
 * @author neolord
 */
public class ControlEquation extends Control {
    /**
     * 캡션
     */
    private Caption caption;
    /**
     * 수식 정보
     */
    private EQEdit eqEdit;

    /**
     * 생성자
     */
    public ControlEquation() {
        super(new CtrlHeaderGso(ControlType.Equation));

        eqEdit = new EQEdit();
    }

    /**
     * 그리기 객체용 컨트롤 헤더를 반환한다.
     *
     * @return 그리기 객체용 컨트롤 헤더
     */
    public CtrlHeaderGso getHeader() {
        return (CtrlHeaderGso) header;
    }

    /**
     * 캡션 객체를 생성한다.
     */
    public void createCaption() {
        caption = new Caption();
    }

    /**
     * 캡션 객체를 삭제한다.
     */
    public void deleteCaption() {
        caption = null;
    }

    /**
     * 캡션 객체를 반환한다.
     *
     * @return 캡션 객체
     */
    public Caption getCaption() {
        return caption;
    }

    /**
     * 수식 정보 객체를 반환한다.
     *
     * @return 수식 정보 객체
     */
    public EQEdit getEQEdit() {
        return eqEdit;
    }

    @Override
    public Control clone() {
        ControlEquation cloned = new ControlEquation();
        cloned.copyControlPart(this);

        if (caption != null) {
            cloned.createCaption();
            cloned.caption.copy(caption);
        } else {
            cloned.caption = null;
        }

        cloned.eqEdit.copy(eqEdit);

        return cloned;
    }
}
