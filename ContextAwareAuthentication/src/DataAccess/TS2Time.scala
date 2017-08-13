package DataAccess

import java.text.SimpleDateFormat
import java.util.Date
import java.sql.Timestamp

object TS2Time {

  def toTime(lowData: Long): String =
    {
      var timeFormat:SimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss")
      
      var ts:Timestamp = new Timestamp(lowData)
      var date:Date = new Date(ts.getTime)
      
//      println(ts.getHours+"-Hours")
//      println(ts.getMinutes+"-Minutes")
//      println(ts.getSeconds+"-Seconds")
//      println(ts.getYear+"-Year")
//      println(ts.getMonth+"-Month")
//      println(ts.getDate+"-Date")

      return timeFormat.format(date)
      
    }

}