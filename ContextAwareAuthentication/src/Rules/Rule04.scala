package Rules

import java.sql.Timestamp
import java.util.HashMap
import java.util.LinkedList

import org.apache.commons.math3.distribution.NormalDistribution

import DataAccess.CDR
import org.apache.commons.math3.stat.descriptive.moment.Variance

/**
 * 2017-08-25
 * @author SANGJIN-NAM
 *
 * Rule 04 : Compare base Whole Frequency not each target Frequency, one month and one day
 *
 *
 */
class Rule04 {

  private var dataList = new LinkedList[Array[String]]

  def setData(datas: LinkedList[CDR]): Unit =
    {
      var i: Int = 0
      while (i < datas.size) {
        var node: CDR = datas.get(i)
        var phoneNumber: String = node.getPhoneNumber
        var time: String = node.getTime

        dataList.add(Array(datas.get(i).getTime(), datas.get(i).getPhoneNumber()))
        i += 1
      }

    }

  def getOneMonthFreq: Array[Double] =
    {

      var freqArr = new Array[Double](30)
      val oneDay: Long = 86400000
      var ts = new Timestamp(System.currentTimeMillis());
      var n: Int = 0

      if (this.dataList.isEmpty()) {
        println("DataList is Empty!!")
      }

      var i: Int = 0

      while (i < dataList.size()) {
        var arr = dataList.get(i)
        var number = arr(1)
        var gap = ts.getTime - arr(0).toLong
        gap = gap / oneDay

        gap match {
          case 73  => freqArr(0) += 1
          case 74  => freqArr(1) += 1
          case 75  => freqArr(2) += 1
          case 76  => freqArr(3) += 1
          case 77  => freqArr(4) += 1
          case 78  => freqArr(5) += 1
          case 79  => freqArr(6) += 1
          case 80  => freqArr(7) += 1
          case 81  => freqArr(8) += 1
          case 82  => freqArr(9) += 1
          case 83  => freqArr(10) += 1
          case 84  => freqArr(11) += 1
          case 85  => freqArr(12) += 1
          case 86  => freqArr(13) += 1
          case 87  => freqArr(14) += 1
          case 88  => freqArr(15) += 1
          case 89  => freqArr(16) += 1
          case 90  => freqArr(17) += 1
          case 91  => freqArr(18) += 1
          case 92  => freqArr(19) += 1
          case 93  => freqArr(20) += 1
          case 94  => freqArr(21) += 1
          case 95  => freqArr(22) += 1
          case 96  => freqArr(23) += 1
          case 97  => freqArr(24) += 1
          case 98  => freqArr(25) += 1
          case 99  => freqArr(26) += 1
          case 100 => freqArr(27) += 1
          case 101 => freqArr(28) += 1
          case 102 => freqArr(29) += 1
          case _   => ;
        }
        i += 1
      }

      return freqArr
    }

  def getOneDayFreq: Int =
    {
      var freq = 0
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
        if (gap == 79) freq += 1
        i += 1
      }
      return freq
    }

  //20%
  def compareData: Unit =
    {
      var baseFreq: Array[Double] = getOneMonthFreq
      var reqFreq: Double = getOneDayFreq
      if (reqFreq == 0) println("Request Data is Empty!!")
      else {
        var SD = new Variance
        var sum: Double = 0
        var i: Int = 0
        while (i <= 29) {
          sum += baseFreq(i)
          i += 1
        }
        var norm = new NormalDistribution(sum / 30, SD.evaluate(baseFreq))
        var sd = Math.sqrt(SD.evaluate(baseFreq))
        var prob: Double = 0
        //    var prob = norm.probability((0.5*sd)+norm.getMean, (0.6*sd)+norm.getMean)*2
        if (norm.getMean > reqFreq) {
          prob = norm.probability(reqFreq, norm.getMean) * 2
        } else prob = norm.probability(norm.getMean, reqFreq) * 2

        println("base m : " + norm.getMean)
        println("req m : " + reqFreq)
        println("Rule04 score : " + (1 - prob))
      }
    }
}

  