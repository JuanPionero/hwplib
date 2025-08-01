package com.agadasom.hwplib.object.bodytext.paragraph;

import com.agadasom.hwplib.object.bodytext.ParagraphListInterface;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 문단 리스트를 나타내는 객체
 *
 * @author neolord
 */
public class ParagraphList implements ParagraphListInterface {
    /**
     * 문단 리스트
     */
    private final ArrayList<Paragraph> paragraphList;

    /**
     * 생성자
     */
    public ParagraphList() {
        paragraphList = new ArrayList<>();
    }

    /**
     * 새로운 문단를 생성하고 리스트에 추가한다.
     *
     * @return 새로 생성된 문단
     */
    @Override
    public Paragraph addNewParagraph() {
        Paragraph p = new Paragraph();
        paragraphList.add(p);
        return p;
    }

    /**
     * 문단를 리스트에 추가한다.
     * 
     * @param para 추가할 문단.
     */
    public void addParagraph(Paragraph para) {
        paragraphList.add(para);
    }

    /**
     * 문단 개수를 반환한다.
     *
     * @return 문단 개수
     */
    @Override
    public int getParagraphCount() {
        return paragraphList.size();
    }

    /**
     * index 번째의 문단을 반환한다.
     *
     * @param index 찾고자 하는 문단의 순번
     * @return index 번째의 문단
     */
    @Override
    public Paragraph getParagraph(int index) {
        return paragraphList.get(index);
    }

    @Override
    public Paragraph[] getParagraphs() {
        return paragraphList.toArray(Paragraph.Zero_Array);
    }

    /**
     * index 번째의 문단을 삭제한다.
     *
     * @param index 삭제할 문단의 순번
     */
    @Override
    public void deleteParagraph(int index) {
        paragraphList.remove(index);
    }

    /**
     * 모든 문단을 삭제한다.
     */
    @Override
    public void deleteAllParagraphs() {
        paragraphList.clear();
    }

    @Override
    public void insertParagraph(int index, Paragraph para) {
        paragraphList.add(index, para);
    }

    @Override
    public Paragraph insertNewParagraph(int index) {
        Paragraph p = new Paragraph();
        paragraphList.add(index, p);
        return p;
    }

    /**
     * Iterator<Paragraph> 객체를 반환한다.
     *
     * @return Iterator<Paragraph> 객체
     */
    @Override
    public Iterator<Paragraph> iterator() {
        return paragraphList.iterator();
    }

    /**
     * 문단 리스트의 일반 문자열을 반환한다.
     *
     * @return 문단 리스트의 일반 문자열
     * @throws UnsupportedEncodingException
     */
    public String getNormalString() throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        for (Paragraph p : paragraphList) {
            sb.append(p.getNormalString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void copy(ParagraphList from) {
        paragraphList.clear();
        for (Paragraph paragraph : from.paragraphList) {
            paragraphList.add(paragraph.clone());
        }
    }
}
