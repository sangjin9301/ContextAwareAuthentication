package DataAccess

import scala.io.Source

class ParseData {
  private val url = "https://dvikqteix2.execute-api.ap-northeast-2.amazonaws.com/prod/finopass/logs?types=phoneCall&userKey="

  def parseCDR(userKey: String): Set[CDR] =
    {
      var html = Source.fromURL(url + userKey)
      var s = html.mkString.split("userKey")
      var userData = Set[CDR]()

      var i = 1
      while (i < s.length) {
        var line = s(i).replace(" ", "")
        var cdr = new CDR
        cdr.setPhoneNumber(line.split("targetNumber\":")(1).split(",")(0).replace("\"", ""))
        cdr.setTime(line.split("timestamp\":")(1).split(",")(0).replace("\"", ""))
        cdr.setDuration(line.split("duration\":")(1).split(",")(0).replace("\"", ""))
        cdr.setDirection(line.split("isSent\":")(1).split(",")(0).replace("\"", ""))
        println(cdr.getTime())
        userData.+=(cdr)

        i += 2
      }

      if (userData.isEmpty) println("userData is Empty!!")
      return userData
    }
}