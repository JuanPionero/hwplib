package com.agadasom.hwplib.tool.paragraphadder.docinfo;

import com.agadasom.hwplib.object.docinfo.Style;
import com.agadasom.hwplib.util.StringUtil;

import java.util.HashMap;

/**
 * DocInfo에 Style을 복사하는 기능을 포함하는 클레스
 *
 * @author neolord
 */
public class StyleAdder {
    private DocInfoAdder docInfoAdder;
    private HashMap<Integer, Integer> idMatchingMap;

    public StyleAdder(DocInfoAdder docInfoAdder) {
        this.docInfoAdder = docInfoAdder;
        idMatchingMap = new HashMap<Integer, Integer>();
    }

    public int processById(int sourceId) {
        if (docInfoAdder.getSourceHWPFile() == docInfoAdder.getTargetHWPFile()) {
            return sourceId;
        }

        if (idMatchingMap.containsKey(sourceId)) {
            return idMatchingMap.get(sourceId);
        } else {
            // id == index
            Style source;
            try {
                source = docInfoAdder.getSourceHWPFile().getDocInfo().getStyleList().get(sourceId);
            } catch (Exception e) {
                return sourceId;
            }

            int id = findFromTarget(source, sourceId);
            if (id == -1) {
                id = addAndCopy(source, sourceId);
            }
            idMatchingMap.put(sourceId, id);
            return id;
        }
    }

    private int findFromTarget(Style source, int sourceId) {
        int count = docInfoAdder.getTargetHWPFile().getDocInfo().getStyleList().size();
        for (int index = 0; index < count; index++) {
            Style target = docInfoAdder.getTargetHWPFile().getDocInfo().getStyleList().get(index);
            if (equal(source, target, sourceId, index)) {
                return index;
            }
        }
        return -1;
    }

    private boolean equal(Style source, Style target, int sourceId, int targetId) {
        return StringUtil.equals(source.getHangulName(), target.getHangulName())
                && StringUtil.equals(source.getEnglishName(), target.getEnglishName())
                && source.getProeprty().getValue() == target.getProeprty().getValue()
                && equalNextStyleId(source.getNextStyleId(), target.getNextStyleId(), sourceId, targetId)
                && source.getLanguageId() == target.getLanguageId()
                && docInfoAdder.forParaShape().equalById(source.getParaShapeId(), target.getParaShapeId())
                && docInfoAdder.forCharShape().equalById(source.getCharShapeId(), target.getCharShapeId());
    }

    private boolean equalNextStyleId(short sourceNextStyleId, short targetNextStyleId, int sourceId, int targetId) {
        if (sourceNextStyleId == sourceId && targetNextStyleId == targetId) {
            return true;
        } else if (sourceNextStyleId == sourceId || targetNextStyleId == targetId) {
            return false;
        }
        return equalById(sourceNextStyleId, targetNextStyleId);
    }

    private int addAndCopy(Style source, int sourceId) {
        Style target = docInfoAdder.getTargetHWPFile().getDocInfo().addNewStyle();
        int targetId = docInfoAdder.getTargetHWPFile().getDocInfo().getStyleList().size() - 1;
        target.setHangulName(source.getHangulName());
        target.setEnglishName(source.getEnglishName());
        target.getProeprty().setValue(source.getProeprty().getValue());
        if (source.getNextStyleId() == sourceId) {
            target.setNextStyleId((short) targetId);
        } else {
            target.setNextStyleId((short) processById(source.getNextStyleId()));
        }
        target.setLanguageId(source.getLanguageId());
        target.setParaShapeId(docInfoAdder.forParaShape().processById(source.getParaShapeId()));
        target.setCharShapeId(docInfoAdder.forCharShape().processById(source.getCharShapeId()));
        return targetId;
    }

    public boolean equalById(short sourceId, short targetId) {
        Style source = docInfoAdder.getSourceHWPFile().getDocInfo().getStyleList().get(sourceId);
        Style target = docInfoAdder.getTargetHWPFile().getDocInfo().getStyleList().get(targetId);
        return equal(source, target, sourceId, targetId);
    }
}
