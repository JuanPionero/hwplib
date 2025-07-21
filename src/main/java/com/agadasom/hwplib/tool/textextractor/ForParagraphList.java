package com.agadasom.hwplib.tool.textextractor;

import com.agadasom.hwplib.object.bodytext.ParagraphListInterface;
import com.agadasom.hwplib.object.bodytext.paragraph.Paragraph;
import com.agadasom.hwplib.tool.textextractor.paraHead.ParaHeadMaker;

import java.io.UnsupportedEncodingException;

/**
 * 문단 리스트를 위한 텍스트 추출기 객체
 *
 * @author neolord
 */
public class ForParagraphList {
    /**
     * 문단 리스트에서 텍스트를 추출한다.
     *
     * @param paragraphList 문단 리스트
     * @param tem           텍스트 추출 방법
     * @param paraHeadMaker 문단 번호/글머리표 생성기
     * @param sb            추출된 텍스트를 저정할 StringBuffer 객체
     * @throws UnsupportedEncodingException
     */
    public static void extract(ParagraphListInterface paragraphList,
            TextExtractMethod tem,
            ParaHeadMaker paraHeadMaker,
            StringBuffer sb) throws UnsupportedEncodingException {
        extract(paragraphList,
                new TextExtractOption(tem, true),
                paraHeadMaker,
                sb);
    }

    /**
     * 문단 리스트에서 텍스트를 추출한다.
     *
     * @param paragraphList 문단 리스트
     * @param option        추출 옵션
     * @param paraHeadMaker 문단 번호/글머리표 생성기
     * @param sb            추출된 텍스트를 저정할 StringBuffer 객체
     * @throws UnsupportedEncodingException
     */
    public static void extract(ParagraphListInterface paragraphList,
            TextExtractOption option,
            ParaHeadMaker paraHeadMaker,
            StringBuffer sb) throws UnsupportedEncodingException {
        if (paragraphList == null || paragraphList.getParagraphCount() == 0) {
            return;
        }

        for (int index = 0; index < paragraphList.getParagraphCount(); index++) {
            boolean isLast = index == paragraphList.getParagraphCount() - 1;

            ForParagraph.extract(paragraphList.getParagraph(index),
                    ForParagraph.ParaStart, ForParagraph.ParaEnd,
                    !isLast,
                    option,
                    paraHeadMaker,
                    sb);
        }
    }

    public static void extract(ParagraphListInterface paragraphList,
            int startParaIndex,
            int startCharIndex,
            int endParaIndex,
            int endCharIndex,
            TextExtractOption option,
            StringBuffer sb) throws UnsupportedEncodingException {
        if (startParaIndex == endParaIndex) {
            ForParagraph.extract(paragraphList.getParagraph(startParaIndex),
                    startCharIndex, endCharIndex,
                    false,
                    option,
                    null,
                    sb);
        } else {
            ForParagraph.extract(paragraphList.getParagraph(startParaIndex),
                    startCharIndex, ForParagraph.ParaEnd,
                    true,
                    option,
                    null,
                    sb);

            if (startParaIndex + 1 < endParaIndex) {
                for (int paraIndex = startParaIndex + 1; paraIndex <= endParaIndex - 1; paraIndex++) {
                    ForParagraph.extract(paragraphList.getParagraph(paraIndex),
                            ForParagraph.ParaStart, ForParagraph.ParaEnd,
                            true,
                            option,
                            null,
                            sb);
                }
            }

            ForParagraph.extract(paragraphList.getParagraph(endParaIndex),
                    ForParagraph.ParaStart, endCharIndex,
                    false,
                    option,
                    null,
                    sb);
        }
    }

    public static void extract(ParagraphListInterface paragraphList,
            int startParaIndex,
            int startCharIndex,
            int endParaIndex,
            int endCharIndex,
            boolean appendLF,
            TextExtractMethod tem,
            StringBuffer sb) throws UnsupportedEncodingException {
        extract(paragraphList,
                startParaIndex,
                startCharIndex,
                endParaIndex,
                endCharIndex,
                new TextExtractOption(tem, appendLF),
                sb);
    }
}
