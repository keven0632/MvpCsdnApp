package keven.cihon.mvpcsdnapp.movie.bean;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengjian on 2017/9/14.
 */

public class Movie implements Serializable{

    /**
     * rating : {"max":10,"average":7.2,"stars":"40","min":0}
     * genres : ["剧情","动作","科幻"]
     * title : 猩球崛起3：终极之战
     * casts : [{"alt":"https://movie.douban.com/celebrity/1002708/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1375081883.31.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1375081883.31.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1375081883.31.jpg"},"name":"安迪·瑟金斯","id":"1002708"},{"alt":"https://movie.douban.com/celebrity/1053560/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/501.jpg","large":"https://img3.doubanio.com/img/celebrity/large/501.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/501.jpg"},"name":"伍迪·哈里森","id":"1053560"},{"alt":"https://movie.douban.com/celebrity/1035639/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/7723.jpg","large":"https://img3.doubanio.com/img/celebrity/large/7723.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/7723.jpg"},"name":"史蒂夫·茨恩","id":"1035639"}]
     * collect_count : 5441
     * original_title : War for the Planet of the Apes
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1045032/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/18161.jpg","large":"https://img3.doubanio.com/img/celebrity/large/18161.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/18161.jpg"},"name":"马特·里夫斯","id":"1045032"}]
     * year : 2017
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2494093630.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2494093630.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2494093630.webp"}
     * alt : https://movie.douban.com/subject/25808075/
     * id : 25808075
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    protected Movie(Parcel in) {
        title = in.readString();
        collect_count = in.readInt();
        original_title = in.readString();
        subtype = in.readString();
        year = in.readString();
        alt = in.readString();
        id = in.readString();
        genres = in.createStringArrayList();
    }



    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }



    public static class RatingBean implements Serializable{
        /**
         * max : 10
         * average : 7.2
         * stars : 40
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean implements Serializable{
        /**
         * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2494093630.webp
         * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2494093630.webp
         * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2494093630.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1002708/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1375081883.31.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1375081883.31.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1375081883.31.jpg"}
         * name : 安迪·瑟金斯
         * id : 1002708
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean implements Serializable{
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/1375081883.31.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/1375081883.31.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/1375081883.31.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1045032/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/18161.jpg","large":"https://img3.doubanio.com/img/celebrity/large/18161.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/18161.jpg"}
         * name : 马特·里夫斯
         * id : 1045032
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX implements Serializable{
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/18161.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/18161.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/18161.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", collect_count=" + collect_count +
                ", original_title='" + original_title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }
}
