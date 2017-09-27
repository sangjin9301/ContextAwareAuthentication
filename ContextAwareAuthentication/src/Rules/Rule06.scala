
package Rules

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.GregorianCalendar
import java.util.LinkedList

import scala.collection.immutable.HashMap

import DataAccess.Location

/**
 * 2017-09-20
 * @author SANGJIN-NAM
 *
 * Rule 05 : Compare base Day of week Location Rate, one day and one six month
 *
 */


class Rule06 {
  
  var listOfSun = new LinkedList[Location]
  var listOfMon = new LinkedList[Location]
  var listOfTues = new LinkedList[Location]
  var listOfWed = new LinkedList[Location]
  var listOfThur = new LinkedList[Location]
  var listOfFri = new LinkedList[Location]
  var listOfSat = new LinkedList[Location]
  
  def getDayOfWeek(list : LinkedList[Location]): Int = 
   { 
     var cal = new GregorianCalendar
     var i:Int = 0
     while(i < 50000/*list.size*/)
     {
       var loc = list.get(i)
       var ts = new Timestamp(list.get(i).getTimestamp.toLong)
       cal.setTime(ts) 
       var dow = cal.get(java.util.Calendar.DAY_OF_WEEK) match {
         // 1 = sunday; 2 = monday
         case 1 => listOfSun.add(loc)
         case 2 => listOfMon.add(loc)
         case 3 => listOfTues.add(loc)
         case 4 => listOfWed.add(loc)
         case 5 => listOfThur.add(loc)
         case 6 => listOfFri.add(loc)
         case 7 => listOfSat.add(loc)
       }
       i += 1
     }
     var formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss")
     var today = formatter.format(cal.getTime)
     var ts = Timestamp.valueOf(today)
     cal.setTime(ts)
     var nowDOW = cal.get(java.util.Calendar.DAY_OF_WEEK)
     
     return nowDOW
   }
  
//  def compareData(){
//    val Day = 8400000
//    var DOW = getDayOfWeek
//    var targetList: LinkedList[Location] = null
//    var i = 0
//    DOW match {
//         // 1 = sunday; 2 = monday
//         case 1 => targetList = listOfSun
//         case 2 => targetList = listOfMon
//         case 3 => targetList = listOfTues
//         case 4 => targetList = listOfWed
//         case 5 => targetList = listOfThur
//         case 6 => targetList = listOfFri
//         case 7 => targetList = listOfSat
//       }
//    var targetTS = targetList.get(0).timestamp
//    while(i < targetList.size()){
//      var term = targetTS - Day
//      if(targetList)
//      
//    }
//  }
  
  def test: Unit =
  {
    var targetList:LinkedList[LinkedList[Double]] = new LinkedList[LinkedList[Double]]
    var i:Int = 0
    println("===================TEST======================")
    println("Size :: "+listOfSun.size())
    while(i < 1200/*listOfSun.size()*/) // 왜 1224 에서 멈출까..
    {
      var ts = listOfSun.get(i).getTimestamp
      var nextTs = ts - 8400000
      
      var j:Int = i
      var inde:Int = 0
      var tempList:LinkedList[Double] = new LinkedList[Double]
      while(listOfSun.get(j).getTimestamp >= nextTs)
      {
        tempList.add(listOfSun.get(j).getLatitude)
        inde = j
        j += 1
      }
      targetList.add(tempList)
      i = inde
    }
    // 여기까지 하루 단위 (인덱스)로 위치를 나눔
    i = 0
    
    var properties : LinkedList[HashMap[Double, Int]] = new LinkedList[HashMap[Double, Int]]
    
    while(i < targetList.size())
    {
      var j = 0
      var map = new HashMap[Double, Int]
      var dayList: LinkedList[Double] = targetList.get(i)
      while(j < dayList.size())
      {
        if(!map.contains(dayList.get(j))) 
          map += ((dayList.get(j) -> 1))
        else
        {
          var curr = map.get(dayList.get(j))
          map -= (dayList.get(j))
          map += ((dayList.get(j) -> (curr.get + 1)))
        }
        j+=1
      }
      properties.add(map)
      i+=1
    }
    
    i=0
    var reqList = new LinkedList[Double]
    var baseList = new LinkedList[LinkedList[Double]]
    var reqArr = properties.get(1).toArray
    while(i < reqArr.length){
      reqList.add(reqArr(i)._1)
      i += 1
    }
    
    i=2
    var j = 0
    while(i < properties.size()){
      var tempMap = properties.get(i)
      var arr = new LinkedList[Double]
      while(j < reqList.size()){
        if(!tempMap.contains(reqList.get(j))) tempMap += (reqList.get(j) -> 0)
        var data = tempMap.get(reqList.get(j)).get
        arr.add(data.toDouble/(properties.size-1.0))
        j += 1
      }
      i += 1
      baseList.add(arr)
    }
    
    
//    var t1:Int = 0
//    while(t1 < properties.size())
//    {
//      var t2:Int = 0
//      var tt = properties.get(t1).toArray
//      var sum = 0
//      while(t2 < tt.length)
//      {
//        sum += tt(t2)._2
//        t2 += 1
//      }
//      
//      t2 = 0
//      var map = new HashMap[Double, Double]
//      while(t2 < tt.length)
//      {
//        map += (tt(t2)._1 -> (tt(t2)._2 / sum.toDouble)*100)
//        println("day:"+t1+" :: "+map.get(tt(t2)._1).get)
//        t2 += 1
//      }
//      
//      t1 += 1
//    }
  }
}