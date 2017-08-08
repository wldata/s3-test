package com.waterlinedata;

import org.apache.hadoop.fs.FileStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintUtil {

    public static final String lineFormat = "%s%s%3s %s%s%10s %s %s";
    private static final String ourLineFormat = "%1$1s %2$2s %3$12s:%4$-12s %5$-40s %6$s";

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void printFileStatus(FileStatus fileStatus) {
        String permission = fileStatus.getPermission().toString();
        boolean isDirectory = fileStatus.isDirectory();
        String fileType = isDirectory ? "d" : "f";
        String owner = fileStatus.getOwner();
        String group = fileStatus.getGroup();
        String filePath = fileStatus.getPath().toString();
        Date date = new Date(fileStatus.getModificationTime());


        String stringFormat = String.format(ourLineFormat, fileType, permission, owner, group, date, filePath);
        System.out.println(stringFormat);
    }

    public static void printHadoopStyle(FileStatus stat) {
        String line = String.format(lineFormat,
                (stat.isDirectory() ? "d" : "-"),
                stat.getPermission() + (stat.getPermission().getAclBit() ? "+" : " "),
                (stat.isFile() ? stat.getReplication() : "-"),
                stat.getOwner(),
                stat.getGroup(),
                String.valueOf(stat.getLen()),
                dateFormat.format(new Date(stat.getModificationTime())),
                stat.getPath().toString()
        );
        System.out.println(line);
    }
}
