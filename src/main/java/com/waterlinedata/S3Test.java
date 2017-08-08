package com.waterlinedata;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

public class S3Test {

    public static void main(String[] args) throws Exception {

        String base = args[0];
        if (base == null) {
            base = "s3://wld-test";
        }
        String pathStr = args[1];
        if (pathStr == null) {
            pathStr = "/";
        }

        FileSystem fileSystem = FileSystem.get(new URI(base), new Configuration());

        Path path = new Path(pathStr);
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {

            PrintUtil.printHadoopStyle(fileStatus);

        }
    }
}
