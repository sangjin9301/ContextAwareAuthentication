import java.io.BufferedWriter
import java.io.FileWriter
import java.util.LinkedList
import java.util.List

import scala.collection.mutable.ArrayBuffer

import com.opencsv.CSVWriter

import DataAccess.Location


object CSVmanager {
  
  def writeLocation(list : LinkedList[Location]): Unit = {
    val outputFile = new BufferedWriter(new FileWriter("C:\\Users\\SANGJIN-NAM\\Desktop\\data\\location.csv"))
    val csvWriter = new CSVWriter(outputFile)
    
    val csvFields = Array("timestamp", "latitude", "longitude", "accuracy")
    var listOfRecords = new LinkedList[Array[String]]
    listOfRecords.add(csvFields)
    
    var latitude = new ArrayBuffer[String]
    var longitude = new ArrayBuffer[String]
    var accuracy = new ArrayBuffer[String]
    
    var i = 0
    while(i < 500){
      var timestamp = new ArrayBuffer[String]
      timestamp += list.get(i).getTimestamp.toString
      timestamp += list.get(i).getLatitude.toString
      timestamp += list.get(i).getLongitude.toString
      timestamp += list.get(i).getAccuracy.toString
//      latitude += list.get(i).getLatitude.toString
//      longitude += list.get(i).getLongitude.toString
//      accuracy += list.get(i).getAccuracy.toString
      listOfRecords.add(timestamp.toArray)
      i += 1
      println(i)
    }
    
    csvWriter.writeAll(listOfRecords)
    println("csv file 쓰기 완료!")
    outputFile.close()
  }
      
}