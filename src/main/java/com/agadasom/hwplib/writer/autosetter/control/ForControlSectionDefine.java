package com.agadasom.hwplib.writer.autosetter.control;

import com.agadasom.hwplib.object.bodytext.control.ControlSectionDefine;
import com.agadasom.hwplib.object.bodytext.control.sectiondefine.BatangPageInfo;
import com.agadasom.hwplib.writer.autosetter.ForParagraphList;
import com.agadasom.hwplib.writer.autosetter.InstanceID;

/**
 * 구역 정의 컨트롤을 쓰기 전에 자동 설정하기 위한 객체
 *
 * @author neolord
 */
public class ForControlSectionDefine {
    /**
     * 구역 정의 컨트롤을 자동 설정한다.
     *
     * @param sd  구역 정의 컨트롤
     * @param iid 인스턴스 id
     */
    public static void autoSet(ControlSectionDefine sd, InstanceID iid) {
        batangPageInfos(sd, iid);
    }

    /**
     * 바탕 페이지 정보들을 자동 설정한다.
     *
     * @param sd  구역 정의 컨트롤
     * @param iid 인스턴스 id
     */
    private static void batangPageInfos(ControlSectionDefine sd, InstanceID iid) {
        for (BatangPageInfo bpi : sd.getBatangPageInfoList()) {
            listHeader(bpi);
            ForParagraphList.autoSet(bpi.getParagraphList(), iid);
        }
    }

    /**
     * 바탕 페이지 정보의 리스트 헤더 레코드를 자동 설정한다.
     *
     * @param bpi 바탕 페이지 정보 객체
     */
    private static void listHeader(BatangPageInfo bpi) {
        bpi.getListHeader().setParaCount(
                bpi.getParagraphList().getParagraphCount());
    }
}
