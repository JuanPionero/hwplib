package com.agadasom.hwplib.object.bodytext.control.ctrlheader;

import com.agadasom.hwplib.object.bodytext.control.ControlType;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.header.HeaderFooterApplyPage;

/**
 * 머리말 컨트롤을 위한 컨트롤 헤더 레코드
 *
 * @author neolord
 */
public class CtrlHeaderHeader extends CtrlHeader {
    /**
     * 머리글이 적용될 범위(페이지 종류)
     */
    private HeaderFooterApplyPage applyPage;
    /**
     * 생성 순서 (??)
     */
    private int createIndex;

    /**
     * 생성자
     */
    public CtrlHeaderHeader() {
        super(ControlType.Header.getCtrlId());
    }

    /**
     * 머리말이 적용될 범위를 반환한다.
     *
     * @return 머리말이 적용될 범위
     */
    public HeaderFooterApplyPage getApplyPage() {
        return applyPage;
    }

    /**
     * 머리말이 적용될 범위를 설정한다.
     *
     * @param applyPage 머리말이 적용될 범위
     */
    public void setApplyPage(HeaderFooterApplyPage applyPage) {
        this.applyPage = applyPage;
    }

    /**
     * 생성 순서를 반환한다.
     *
     * @return 생성 순서
     */
    public int getCreateIndex() {
        return createIndex;
    }

    /**
     * 생성 순서를 설정한다.
     *
     * @param createIndex 생성 순서
     */
    public void setCreateIndex(int createIndex) {
        this.createIndex = createIndex;
    }

    @Override
    public void copy(CtrlHeader from) {
        CtrlHeaderHeader from2 = (CtrlHeaderHeader) from;
        applyPage = from2.applyPage;
        createIndex = from2.createIndex;
    }
}
