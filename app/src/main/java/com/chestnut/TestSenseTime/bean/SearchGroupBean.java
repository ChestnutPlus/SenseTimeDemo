package com.chestnut.TestSenseTime.bean;

import java.util.List;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/6/30 11:38
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class SearchGroupBean {

    /**
     * candidates : [{"person_id":"bccaa20317af43489edb333bcae8c0ce","name":"彭于晏","confidence":0.849696}]
     * face_id : 908540b565534104a5acb12fc3ac72c9
     * group_id : a6d39917b684430b94cea324ff09cfb5
     * status : OK
     * request_id : D6B20388-5D59-11E7-B820-9569849FC622
     */

    public String face_id;
    public String group_id;
    public String status;
    public String request_id;
    public List<CandidatesBean> candidates;

    public static class CandidatesBean {
        /**
         * person_id : bccaa20317af43489edb333bcae8c0ce
         * name : 彭于晏
         * confidence : 0.849696
         */
        public String person_id;
        public String name;
        public double confidence;
    }
}
