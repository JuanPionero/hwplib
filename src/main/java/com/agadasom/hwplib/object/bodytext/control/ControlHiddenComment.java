package com.agadasom.hwplib.object.bodytext.control;

import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeader;
import com.agadasom.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderDefault;
import com.agadasom.hwplib.object.bodytext.control.hiddencomment.ListHeaderForHiddenComment;
import com.agadasom.hwplib.object.bodytext.paragraph.ParagraphList;

/**
 * 숨은 설명 컨트롤
 *
 * @author neolord
 */
public class ControlHiddenComment extends Control {
    /**
     * 숨은 설명 용 리스트 헤더
     */
    private ListHeaderForHiddenComment listHeader;
    /**
     * 문단 리스트
     */
    private ParagraphList paragraphList;

    /**
     * 생성자
     */
    public ControlHiddenComment() {
        super(new CtrlHeaderDefault(ControlType.HiddenComment.getCtrlId()));

        listHeader = new ListHeaderForHiddenComment();
        paragraphList = new ParagraphList();
    }

    /**
     * 컨트롤 헤더를 반환한다.
     *
     * @return 컨트롤 헤더
     */
    public CtrlHeader getHeader() {
        return header;
    }

    /**
     * 숨은 설명 용 리스트 헤더를 반환한다.
     *
     * @return 숨은 설명 용 리스트 헤더
     */
    public ListHeaderForHiddenComment getListHeader() {
        return listHeader;
    }

    /**
     * 문단 리스트를 반환한다.
     *
     * @return 문단 리스트
     */
    public ParagraphList getParagraphList() {
        return paragraphList;
    }

    @Override
    public Control clone() {
        ControlHiddenComment cloned = new ControlHiddenComment();
        cloned.copyControlPart(this);
        cloned.listHeader.copy(listHeader);
        cloned.paragraphList.copy(paragraphList);
        return cloned;
    }
}
