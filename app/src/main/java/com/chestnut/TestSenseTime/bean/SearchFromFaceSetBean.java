package com.chestnut.TestSenseTime.bean;

import java.util.List;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/6/30 9:52
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class SearchFromFaceSetBean {

    /**
     * candidates : [{"face_id":"c74df9b413a44a9ab3060b7170989fa9","confidence":1}]
     * face_id : c74df9b413a44a9ab3060b7170989fa9
     * status : OK
     */

    public String face_id;
    public String status;
    public List<CandidatesBean> candidates;

    public static class CandidatesBean {
        /**
         * face_id : c74df9b413a44a9ab3060b7170989fa9
         * confidence : 1
         */
        public String face_id;
        public int confidence;
    }
}
