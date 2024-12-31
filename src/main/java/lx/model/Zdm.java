package lx.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ZDM")
public class Zdm implements Crawlable {

    //主键用值得买接口提供的id
    @Id
    @JSONField(name = "article_id")
    String articleId;

    @JSONField(name = "article_title")
    String title;

    @JSONField(name = "article_url")
    String url;
    @JSONField(name = "article_pic_url")
    String picUrl;

    @JSONField(name = "article_price")
    String price;
    @JSONField(name = "article_rating")
    String voted;

    @JSONField(name = "article_comment")
    String comments;
    @JSONField(name = "article_mall")
    String articleMall;

    //不持久化进数据库
    @Transient
    @JSONField(name = "timesort")
    String timesort;

    String article_time;

    Boolean pushed;

    @Override
    public String toHtmlTr() {
        return "<tr>" +
                "<td><img src='" + picUrl + "'/></td>" +
                "<td>" + "<a target='_blank' href='" + url + "'>" + title + "</a></td>" +
                "<td>" + price + "</td>" +
                "<td>" + voted + "/" + comments + "</td>" +
                "<td>" + articleMall + "</td>" +
                "</tr>";
    }

    @Override
    public Integer obtainSortOrder() {
        return Integer.valueOf(comments);
    }

    public Zdm() {
        pushed = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Zdm) {
            return articleId.equals(((Zdm) obj).articleId);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleId);
    }
}
