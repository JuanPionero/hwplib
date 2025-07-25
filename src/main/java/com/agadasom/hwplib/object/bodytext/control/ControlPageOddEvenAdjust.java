package com.agadasom.hwplib.object.bodytext.control;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderPageOddEvenAdjust;

/**
 *
 */
public class ControlPageOddEvenAdjust extends Control {
    /**
     * 생성자
     */
    public ControlPageOddEvenAdjust() {
        super(new CtrlHeaderPageOddEvenAdjust());
    }

    /**
     * 홀/짝수 조정(페이지 번호 제어) 컨트롤 용 컨트롤 헤더를 반환한다.
     *
     * @return 홀/짝수 조정(페이지 번호 제어) 컨트롤 헤더
     */
    public CtrlHeaderPageOddEvenAdjust getHeader() {
        return (CtrlHeaderPageOddEvenAdjust) header;
    }

    @Override
    public Control clone() {
        ControlPageOddEvenAdjust cloned = new ControlPageOddEvenAdjust();
        cloned.copyControlPart(this);
        return cloned;
    }
}
