package com.chestnut.TestSenseTime.bean;

import java.util.List;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/6/29 16:15
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class DetectBean {

    /**
     * image_id : ac22fe64457e4b1b964a5170d92a05dd
     * width : 470
     * height : 640
     * faces : [{"rect":{"left":185,"top":68,"right":279,"bottom":162},"face_id":"cf3d4dd29fde485dbcf585990cb8ec15","landmarks21":[{"x":193.555405,"y":85.47718},{"x":205.317078,"y":80.479012},{"x":219.132568,"y":82.024078},{"x":242.989624,"y":79.976791},{"x":255.80072,"y":76.438835},{"x":267.856079,"y":79.150536},{"x":199.993607,"y":95.596207},{"x":219.204071,"y":95.584641},{"x":245.528702,"y":93.533844},{"x":263.895935,"y":90.618515},{"x":222.611649,"y":122.181473},{"x":235.011246,"y":127.644913},{"x":245.976868,"y":120.454781},{"x":235.147736,"y":133.617584},{"x":235.816544,"y":141.46167},{"x":236.646805,"y":151.521591},{"x":209.855164,"y":94.114029},{"x":254.826019,"y":90.732506},{"x":234.142044,"y":120.590668},{"x":215.968491,"y":140.394974},{"x":253.369965,"y":137.76532}]}]
     * status : OK
     * request_id : E37A882C-5CA7-11E7-93F1-D47D7BEF9284
     */

    public String image_id;
    public int width;
    public int height;
    public String status;
    public String request_id;
    public List<FacesBean> faces;

    public static class FacesBean {
        /**
         * rect : {"left":185,"top":68,"right":279,"bottom":162}
         * face_id : cf3d4dd29fde485dbcf585990cb8ec15
         * landmarks21 : [{"x":193.555405,"y":85.47718},{"x":205.317078,"y":80.479012},{"x":219.132568,"y":82.024078},{"x":242.989624,"y":79.976791},{"x":255.80072,"y":76.438835},{"x":267.856079,"y":79.150536},{"x":199.993607,"y":95.596207},{"x":219.204071,"y":95.584641},{"x":245.528702,"y":93.533844},{"x":263.895935,"y":90.618515},{"x":222.611649,"y":122.181473},{"x":235.011246,"y":127.644913},{"x":245.976868,"y":120.454781},{"x":235.147736,"y":133.617584},{"x":235.816544,"y":141.46167},{"x":236.646805,"y":151.521591},{"x":209.855164,"y":94.114029},{"x":254.826019,"y":90.732506},{"x":234.142044,"y":120.590668},{"x":215.968491,"y":140.394974},{"x":253.369965,"y":137.76532}]
         */

        public RectBean rect;
        public String face_id;
        public List<Landmarks21Bean> landmarks21;

        public static class RectBean {
            /**
             * left : 185
             * top : 68
             * right : 279
             * bottom : 162
             */

            public int left;
            public int top;
            public int right;
            public int bottom;
        }

        public static class Landmarks21Bean {
            /**
             * x : 193.555405
             * y : 85.47718
             */

            public double x;
            public double y;
        }
    }
}
