# s3-test


On an EMR cluster, build and copy the target/s3-test-1.0-SNAPSHOT.jar

Then:

```
export BASE=s3://testdata

# For trying out with local HDFS
#export BASE=/user/ec2-user


hdfs dfs -mkdir ${BASE}/t1/foo
hdfs dfs -touchz ${BASE}/t1/foo/f1.txt
hdfs dfs -touchz ${BASE}/t1/foo/f2.txt

java -cp ./s3-test-1.0-SNAPSHOT.jar:$(hadoop classpath) com.waterlinedata.S3Test ${BASE} /t1/foo
```

Now create the "_$folder$" folder and rerun

```
hdfs dfs -touchz ${BASE}/t1/foo/_\$folder\$

java -cp ./s3-test-1.0-SNAPSHOT.jar:$(hadoop classpath) com.waterlinedata.S3Test ${BASE} /t1/foo

```
and run the recursive test:

```
java -cp ./s3-test-1.0-SNAPSHOT.jar:$(hadoop classpath) com.waterlinedata.S3TestR ${BASE} /t1/foo
```
