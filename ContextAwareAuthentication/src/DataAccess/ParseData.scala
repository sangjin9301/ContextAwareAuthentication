package DataAccess

import java.util.LinkedList

import scala.io.Source

class ParseData {
  private val url = "https://dvikqteix2.execute-api.ap-northeast-2.amazonaws.com/prod/finopass/logs?types=phoneCall&userKey="

  def parseCDR(userKey: String): LinkedList[CDR] =
    {
      var html = Source.fromURL(url + userKey)
      var s = html.mkString.split("userKey")
      var userData = new LinkedList[CDR]

      var i = 1
      while (i < s.length) {
        var line = s(i).replace(" ", "")
        var cdr = new CDR
        cdr.setPhoneNumber(line.split("targetNumber\":")(1).split(",")(0).replace("\"", ""))
        cdr.setTime(line.split("timestamp\":")(1).split(",")(0).replace("\"", ""))
        cdr.setDuration(line.split("duration\":")(1).split(",")(0).replace("\"", ""))
        cdr.setDirection(line.split("isSent\":")(1).split(",")(0).replace("\"", ""))
        userData.add(cdr)

        i += 2
      }

      if (userData.isEmpty) println("userData is Empty!!")
      return userData
    }

  def parseLocation(path:String): Unit =
    {
      var i = 0
      var locationString:String = ""
      println("lines : "+Source.fromFile(path).getLines.length)
      for(line<-Source.fromFile(path).getLines){
        locationString += line+"\n"
        println((i/Source.fromFile(path).getLines.length)*100)
        i += 1
      }
      println("Parse Complit")
      var properties = locationString.split("\"timestampMs\" : ")
      for(data<-properties){
        println(data)
      }
    }
}