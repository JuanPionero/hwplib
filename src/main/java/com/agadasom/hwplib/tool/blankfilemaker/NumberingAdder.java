package com.agadasom.hwplib.tool.blankfilemaker;

import com.agadasom.hwplib.object.docinfo.DocInfo;
import com.agadasom.hwplib.object.docinfo.Numbering;
import com.agadasom.hwplib.object.docinfo.numbering.LevelNumbering;

public class NumberingAdder {
    public static void add(DocInfo docInfo) {
        numbering1(docInfo.addNewNumbering());
    }

    private static void numbering1(Numbering numbering) {
        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(1);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(12);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("^1.");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(2);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(268);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("^2.");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(3);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(12);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("^3)");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(4);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(268);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("^4)");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(5);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(12);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("(^5)");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(6);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(268);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("(^6)");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(7);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(44);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(50);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(-1);
            levelNumbering.getNumberFormat().fromUTF16LEString("^7");
            levelNumbering.setStartNumber(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(8);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(0);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(0);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(0);
            levelNumbering.getNumberFormat().fromUTF16LEString(null);
            levelNumbering.setStartNumber(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(9);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(0);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(0);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(0);
            levelNumbering.getNumberFormat().fromUTF16LEString(null);
            levelNumbering.setStartNumber(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LevelNumbering levelNumbering = numbering.getLevelNumbering(10);
            levelNumbering.getParagraphHeadInfo().getProperty().setValue(0);
            levelNumbering.getParagraphHeadInfo().setCorrectionValueForWidth(0);
            levelNumbering.getParagraphHeadInfo().setDistanceFromBody(0);
            levelNumbering.getParagraphHeadInfo().setCharShapeID(0);
            levelNumbering.getNumberFormat().fromUTF16LEString(null);
            levelNumbering.setStartNumber(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
