package com.agadasom.hwplib.object.bodytext.control;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderOverlappingLetter;

/**
 * 글자 겹침 컨트롤
 *
 * @author neolord
 */
public class ControlOverlappingLetter extends Control {
    /**
     * 생성자
     */
    public ControlOverlappingLetter() {
        super(new CtrlHeaderOverlappingLetter());
    }

    /**
     * 글자 겹침 용 컨트롤 헤더를 반환한다.
     *
     * @return 글자 겹침 용 컨트롤 헤더
     */
    public CtrlHeaderOverlappingLetter getHeader() {
        return (CtrlHeaderOverlappingLetter) header;
    }

    @Override
    public Control clone() {
        ControlOverlappingLetter cloned = new ControlOverlappingLetter();
        cloned.copyControlPart(this);
        return cloned;
    }
}
