package Rules

import java.util.LinkedList

import DataAccess.CDR
import DataAccess.TS2Time

class Rule01 {

  private var phones = new LinkedList[String] // for count Phone
  private var dataList = new LinkedList[Array[String]]

  def setData(datas: Set[CDR]): Unit =
    {
      var pair = new Array[String](2)
      
      for (element <- datas) {
        var phoneNumber:String = element.getPhoneNumber
        var time:Long = element.getTime.toLong

        if (!phones.contains(phoneNumber)) phones.add(phoneNumber)

        pair(0) = TS2Time.toTime(time)
        pair(1) = phoneNumber
        
        dataList.add(pair)

      }
    }

}