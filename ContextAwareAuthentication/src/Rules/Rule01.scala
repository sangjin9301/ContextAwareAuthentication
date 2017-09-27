package Rules

import java.sql.Timestamp
import java.util.HashMap
import java.util.LinkedList

import DataAccess.CDR
import org.apache.commons.math3.random.RandomDataGenerator
import org.apache.commons.math3.stat.inference.TTest
import scala.collection.mutable.ArrayBuffer

/**
 * 2017-08-13
 * @author SANGJIN-NAM
 *
 * Rule 01 : Compare base Frequency, one month and one week
 *
 */
class Rule01 {

  private var phones = new LinkedList[String] // for count Phone
  private var dataList = new LinkedList[Array[String]]

  def setData(datas: LinkedList[CDR]): Unit =
    {
      var i: Int = 0
      while (i < datas.size) 
      {
        var node: CDR = datas.get(i)
        var phoneNumber: String = node.getPhoneNumber
        var time: String = node.getTime

        if (!phones.contains(phoneNumber)) phones.add(phoneNumber)

        dataList.add(Array(datas.get(i).getTime(), datas.get(i).getPhoneNumber()))
        i += 1
      }

    }

  def getOneMonthFreq: HashMap[String, Int] =
    {

      var freqMap = new HashMap[String, Int]
      val oneDay: Long = 86400000
      var ts = new Timestamp(System.currentTimeMillis());

      if (this.dataList.isEmpty()) {
        println("DataList is Empty!!")
      }

      var i: Int = 0

      while (i < dataList.size()) {
        var arr = dataList.get(i)
        var number = arr(1)
        var gap = ts.getTime - arr(0).toLong
        gap = gap / oneDay
        if ((gap >= 79)&&(gap <= 102)) {

          if (!freqMap.containsKey(number)) freqMap.put(number, 1)
          else {
            var value = freqMap.get(number) +1
            freqMap.remove(number)
            freqMap.put(number, value)
          }
        }
        i += 1
      }
      return freqMap
    }

  def getOneWeekFreq: HashMap[String, Int] =
    {
      var freqMap = new HashMap[String, Int]
      val oneDay: Long = 86400000
      var ts = new Timestamp(System.currentTimeMillis());

      if (this.dataList.isEmpty()) {
        println("DataList is Empty!!")
      }

      var i: Int = 0

      while (i < dataList.size()) {
        var arr = dataList.get(i)
        var number = arr(1)
        var gap = ts.getTime - arr(0).toLong
        gap = gap / oneDay
        if ((gap >= 97)&&(gap <= 104)) {

          if (!freqMap.containsKey(number)) freqMap.put(number, 1)
          else {
            var value = freqMap.get(number)+1
            freqMap.remove(number)
            freqMap.put(number, value)
          }

        }
        i += 1
      }
      return freqMap
    }

  def compareData: Unit =
    {
      var engine = new TTest
      var req: HashMap[String, Int] = getOneWeekFreq // why they have zero?
      var base: HashMap[String, Int] = getOneMonthFreq
      var result: Int = 0
      var i = 0

      var arrSet1 = new Array[Double](49)
      var arrSet2 = new Array[Double](49)
        
      while (i < phones.size) 
      {
        result = 0
        var num = phones.get(i)
        var reqFreq = req.get(num)
        var baseFreq = base.get(num)/4
        
        if(reqFreq == null) reqFreq = 0;
        if(baseFreq == null) baseFreq = 0;
        
        if((req.containsKey(num))||(base.containsKey(num)))
        {
          result = baseFreq - reqFreq
          arrSet1.update(i, reqFreq)
          arrSet2.update(i, baseFreq)
          
          

//          println(num + " : " + baseFreq+" - "+reqFreq+" = "+result)
        }
        i += 1
      }
      println("Rule01 : arrSet : "+ i)
      println("Rule01 : p-value is "+engine.pairedTTest(arrSet1, arrSet2))
    }
}

  