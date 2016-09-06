/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// scalastyle:off println
package org.apache.spark.examples

import org.apache.spark.sql.SparkSession

object SparkShortURL {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("SparkShorURL")
      .getOrCreate()

    val urlDF = spark.read.format("csv").load("hdfs://localhost:9000/data.csv")
    urlDF.createOrReplaceTempView("url")
    val ret = spark.sql("SELECT _c0 FROM url WHERE _c3 = 'US'")
    ret.show()
    spark.stop()
  }
}

