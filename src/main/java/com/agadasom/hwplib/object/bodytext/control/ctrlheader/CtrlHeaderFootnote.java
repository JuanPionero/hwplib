package com.agadasom.hwplib.object.bodytext.control.ctrlheader;

import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.sectiondefine.NumberShape;
import com.agadasom.hwplib.object.etc.HWPString;

/**
 * 각주(Foot Note) 컨트롤을 위한 컨트롤 헤더 레코드
 *
 * @author neolord
 */
public class CtrlHeaderFootnote extends CtrlHeader {
    /**
     * 각주 번호
     */
    private long number;
    /**
     * 앞 장식 문자
     */
    private HWPString beforeDecorationLetter;
    /**
     * 뒤 장식 문자
     */
    private HWPString afterDecorationLetter;
    /**
     * 번호 모양
     */
    private NumberShape numberShape;
    /**
     * 문서 내 각 개체에 대한 고유 아이디
     */
    private long instanceId;

    /**
     * 생성자
     */
    public CtrlHeaderFootnote() {
        super(ControlType.Footnote.getCtrlId());
        beforeDecorationLetter = new HWPString();
        afterDecorationLetter = new HWPString();
    }

    /**
     * 각주 번호를 반환한다.
     *
     * @return 각주 번호
     */
    public long getNumber() {
        return number;
    }

    /**
     * 각주 번호를 설정한다.
     *
     * @param number 각주 번호
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * 앞 장식 문자를 반환한다.
     *
     * @return 앞 장식 문자
     */
    public HWPString getBeforeDecorationLetter() {
        return beforeDecorationLetter;
    }

    /**
     * 뒤 장식 문자를 반환한다.
     *
     * @return 뒤 장식 문자
     */
    public HWPString getAfterDecorationLetter() {
        return afterDecorationLetter;
    }

    /**
     * 번호 모양을 반환한다.
     *
     * @return 번호 모양
     */
    public NumberShape getNumberShape() {
        return numberShape;
    }

    /**
     * 번호 모양을 설정한다.
     *
     * @param numberShape 번호 모양
     */
    public void setNumberShape(NumberShape numberShape) {
        this.numberShape = numberShape;
    }

    /**
     * 문서 내 각 개체에 대한 고유 아이디를 반환한다.
     *
     * @return 문서 내 각 개체에 대한 고유 아이디
     */
    public long getInstanceId() {
        return instanceId;
    }

    /**
     * 문서 내 각 개체에 대한 고유 아이디를 설정한다.
     *
     * @param instanceId 문서 내 각 개체에 대한 고유 아이디
     */
    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public void copy(CtrlHeader from) {
        CtrlHeaderFootnote from2 = (CtrlHeaderFootnote) from;
        number = from2.number;
        beforeDecorationLetter.copy(from2.beforeDecorationLetter);
        afterDecorationLetter.copy(from2.afterDecorationLetter);
        numberShape = from2.numberShape;
        instanceId = from2.instanceId;
    }
}
