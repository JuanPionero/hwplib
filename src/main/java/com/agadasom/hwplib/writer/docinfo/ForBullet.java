package com.agadasom.hwplib.writer.docinfo;

import com.agadasom.hwplib.object.docinfo.Bullet;
import com.agadasom.hwplib.object.etc.HWPTag;
import com.agadasom.hwplib.util.compoundFile.writer.StreamWriter;
import com.agadasom.hwplib.writer.docinfo.borderfill.ForFillInfo;

import java.io.IOException;

/**
 * 글머리표 레코드를 쓰기 위한 객체
 *
 * @author neolord
 */
public class ForBullet {
    /**
     * 글머리표 레코드를 쓴다.
     *
     * @param b  글머리표 레코드
     * @param sw 스트림 라이터
     * @throws IOException
     */
    public static void write(Bullet b, StreamWriter sw) throws IOException {
        recordHeader(sw);
        ForNumbering.paragraphHeadInfo(b.getParagraphHeadInfo(), sw);
        sw.writeWChar(b.getBulletChar().getBytes());
        if (b.getImageBullet()) {
            sw.writeUInt4(1);
        } else {
            sw.writeUInt4(0);
        }
        ForFillInfo.pictureInfo(b.getImageBulletInfo(), sw);
        sw.writeWChar(b.getCheckBulletChar().getBytes());
    }

    /**
     * 글머리표 레코드의 레코드 헤더를 쓴다.
     *
     * @param sw 스트림 라이터
     * @throws IOException
     */
    private static void recordHeader(StreamWriter sw) throws IOException {
        sw.writeRecordHeader(HWPTag.BULLET, 25);
    }
}
