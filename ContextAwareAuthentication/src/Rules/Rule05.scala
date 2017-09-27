
package Rules

import DataAccess.Location
import java.util.LinkedList

/**
 * 2017-09-17
 * @author SANGJIN-NAM
 *
 * Rule 05 : Compare base rate, one month and one week
 *
 */


class Rule05 {
  val oneDay: Long = 86400000 // nano
  val march_03_2014 : Long = 1393891200 // milli
  var list = new LinkedList[Location]
  var location = new LinkedList[Int]
  
  def Rule05(list : LinkedList[Location]): Unit = 
  {
    var i = 0
    this.list = list
    while(i < list.size())
    {
      var loc = list.get(i)      
      if((loc.getTimestamp > march_03_2014*100)&&(loc.getTimestamp < march_03_2014*100 + (oneDay * 7))){
        if(!location.contains(loc.getLatitude))location.add(loc.getLatitude)
      }
      
      if((loc.getTimestamp > march_03_2014*100 + (oneDay * 7))&&(loc.getTimestamp < march_03_2014*100 + (oneDay * 30)))
      i += 1
    }

  }
  
  
}