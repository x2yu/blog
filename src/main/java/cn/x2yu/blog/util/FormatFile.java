package cn.x2yu.blog.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FormatFile {

    /**
     * 格式化Date日期数据
     * */
    public String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM dd");

        String newDate = sdf.format(date);
        System.out.println(newDate);
        return newDate;
    }

    /**
     * 格式化文章标题去除.md后缀
     * */
    public String formatArticleTitle(String title){

        String newStr = title.replace(".md"," ");

        return newStr;
    }
}
