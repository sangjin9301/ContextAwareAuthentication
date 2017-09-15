package DataAccess

import java.util.LinkedList

import scala.io.Source
import org.json.JSONObject
import org.json.JSONArray

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
      var strList = new LinkedList[String]
      var LocationList = new LinkedList[Location]
      var i = 0
      var locationString:String = ""
      println("파일 읽는 중...")
      locationString = Source.fromFile(path).mkString
      
      println("JSON_Object 생성 중...")
      var jsonOb = new JSONObject(locationString)
      
      println("파싱 중...")
      var arr: JSONArray = jsonOb.getJSONArray("locations")
      while(i<arr.length()){
        var loc = new Location
        loc.setTimestamp(arr.getJSONObject(i).getString("timestampMs").toDouble)
        loc.setLatitude(arr.getJSONObject(i).getString("latitudeE7").toDouble)
        loc.setLongitude(arr.getJSONObject(i).getString("longitudeE7").toDouble)
        loc.setAccuracy(arr.getJSONObject(i).getString("accuracy").toInt)
        LocationList.add(loc)
        i += 1
      }
      println("끝")
    }
}