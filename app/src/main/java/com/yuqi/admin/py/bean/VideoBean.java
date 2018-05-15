package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5/005.
 * 返回视频列表
 */
//{
//	"videos": [{
//            "video_id": 1,
//            "video_title": "测试1",
//            "video_content": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=37086&editionType=normal&source=aliyun",
//            "video_ThumbNailImage": "http://139.224.238.234:8021/image/15153800359116.jpg"
//            }, {
//            "video_id": 2,
//            "video_title": "测试2",
//            "video_content": "http://static.tripbe.com/videofiles/20121214/9533522808.f4v.mp4",
//            "video_ThumbNailImage": "http://139.224.238.234:8021/image/1516183257306c.png"
//            }],
//            "status": "200"
//            }
//
public class VideoBean {
    /**
     * videos : [{"video_id":1,"video_title":"测试1","video_content":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=37086&editionType=normal&source=aliyun","video_ThumbNailImage":"http://139.224.238.234:8021/image/15153800359116.jpg"},{"video_id":2,"video_title":"测试2","video_content":"http://static.tripbe.com/videofiles/20121214/9533522808.f4v.mp4","video_ThumbNailImage":"http://139.224.238.234:8021/image/1516183257306c.png"}]
     * status : 200
     */

    private String status;
    private List<VideosBean> videos;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        /**
         * video_id : 1
         * video_title : 测试1
         * video_content : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=37086&editionType=normal&source=aliyun
         * video_ThumbNailImage : http://139.224.238.234:8021/image/15153800359116.jpg
         */

        private int video_id;
        private String video_title;
        private String video_content;
        private String video_ThumbNailImage;

        public int getVideo_id() {
            return video_id;
        }

        public void setVideo_id(int video_id) {
            this.video_id = video_id;
        }

        public String getVideo_title() {
            return video_title;
        }

        public void setVideo_title(String video_title) {
            this.video_title = video_title;
        }

        public String getVideo_content() {
            return video_content;
        }

        public void setVideo_content(String video_content) {
            this.video_content = video_content;
        }

        public String getVideo_ThumbNailImage() {
            return video_ThumbNailImage;
        }

        public void setVideo_ThumbNailImage(String video_ThumbNailImage) {
            this.video_ThumbNailImage = video_ThumbNailImage;
        }
    }

}
