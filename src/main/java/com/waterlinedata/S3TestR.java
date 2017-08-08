package com.waterlinedata;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class S3TestR {

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

        Path myPath = new Path(pathStr);
        listDirsRecursively(myPath, fileSystem);
    }

    private static void listDirsRecursively(Path path, FileSystem fileSystem) throws IOException {
        FileStatus mainFileStatus = fileSystem.getFileStatus(path);
        Path absPath = mainFileStatus.getPath();
        FileStatus[] fileStatuses = fileSystem.listStatus(absPath);
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.getPath().equals(path)) {
                System.out.println("\tSkipping path : " + fileStatus.getPath().toString());
                continue;
            }
            PrintUtil.printHadoopStyle(fileStatus);
            if (fileStatus.isDirectory()) {
                listDirsRecursively(fileStatus.getPath(), fileSystem);
            }
        }
    }
}
