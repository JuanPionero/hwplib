package com.agadasom.hwplib.object.bodytext.control;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderNewNumber;

/**
 * 새 번호 지정 컨트롤
 *
 * @author neolord
 */
public class ControlNewNumber extends Control {
    /**
     * 생성자
     */
    public ControlNewNumber() {
        super(new CtrlHeaderNewNumber());
    }

    /**
     * 새 번호 지정용 컨트롤 헤더를 반환한다.
     *
     * @return 새 번호 지정용 컨트롤 헤더
     */
    public CtrlHeaderNewNumber getHeader() {
        return (CtrlHeaderNewNumber) header;
    }

    @Override
    public Control clone() {
        ControlNewNumber cloned = new ControlNewNumber();
        cloned.copyControlPart(this);
        return cloned;
    }
}
