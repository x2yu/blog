package cn.x2yu.blog.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FormatFile {

    /**
     * 格式化文章Date日期数据
     * */
    public String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM dd");
        String newDate = sdf.format(date);

        return newDate;
    }

    /**
     * 格式化留言Date日期数据
     * */
        public String formatCommentDate(Date date) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        return strDate;
    }

    /**
     * 格式化文章标题去除.md后缀
     * */
    public String formatArticleTitle(String title){


        String newStr = title.replace(".md"," ");
        return newStr;
    }

    /**
     * 对文章文件重命名
     * */
    public void articleRename(String oldName,String newName)throws Exception{

        String uploadDir= ResourceUtils.getURL("classpath:").getPath()+"/markdown";
        System.out.println(uploadDir);



//        Resource resource = new ClassPathResource("markdown/"+oldName);
//        File oldFile = resource.getFile();
//
//        String url = oldFile.getAbsolutePath();
//        String newurl = url.replace(oldName," ");
//
//        System.out.println(oldFile.getAbsolutePath());
//        System.out.println(oldFile.getCanonicalPath());
//        System.out.println(oldFile.getPath());
//
//
//        oldFile.renameTo(new File(newurl+newName+".txt"));

    }

    public static void main(String []args)throws Exception{
        FormatFile formatFile = new FormatFile();
        String  oldName = "测试.txt";
        String   newName = "我是新名字";

        formatFile.articleRename(oldName,newName);
    }
}
