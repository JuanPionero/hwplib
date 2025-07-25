package com.agadasom.hwplib.object.bodytext.control.sectiondefine;

import com.agadasom.hwplib.object.bodytext.paragraph.ParagraphList;

/**
 * 바탕 페이지 정보를 나타내는 객체
 *
 * @author neolord
 */
public class BatangPageInfo {
    /**
     * 문단 리스트 헤더
     */
    private ListHeaderForBatangPage listHeader;
    /**
     * 문단 리스트
     */
    private ParagraphList paragraphList;

    /**
     * 생성자
     */
    public BatangPageInfo() {
        listHeader = new ListHeaderForBatangPage();
        paragraphList = new ParagraphList();
    }

    /**
     * 문단 리스트 헤더를 반환한다.
     *
     * @return 문단 리스트 헤더
     */
    public ListHeaderForBatangPage getListHeader() {
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

    public BatangPageInfo clone() {
        BatangPageInfo cloned = new BatangPageInfo();
        cloned.listHeader.copy(listHeader);
        cloned.paragraphList.copy(paragraphList);
        return cloned;
    }
}
