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
          case 125  => freqArr(0) += 1
          case 126  => freqArr(1) += 1
          case 127  => freqArr(2) += 1
          case 128  => freqArr(3) += 1
          case 129  => freqArr(4) += 1
          case 130  => freqArr(5) += 1
          case 131  => freqArr(6) += 1
          case 132  => freqArr(7) += 1
          case 133  => freqArr(8) += 1
          case 134  => freqArr(9) += 1
          case 135  => freqArr(10) += 1
          case 136  => freqArr(11) += 1
          case 137  => freqArr(12) += 1
          case 138  => freqArr(13) += 1
          case 139  => freqArr(14) += 1
          case 140  => freqArr(15) += 1
          case 141  => freqArr(16) += 1
          case 142  => freqArr(17) += 1
          case 143  => freqArr(18) += 1
          case 144  => freqArr(19) += 1
          case 145  => freqArr(20) += 1
          case 146  => freqArr(21) += 1
          case 147  => freqArr(22) += 1
          case 148  => freqArr(23) += 1
          case 149  => freqArr(24) += 1
          case 150  => freqArr(25) += 1
          case 151  => freqArr(26) += 1
          case 152 => freqArr(27) += 1
          case 153 => freqArr(28) += 1
          case 154 => freqArr(29) += 1
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
        if (gap == 102) freq += 1
        i += 1
      }
      return freq
    }

  //20%
  def compareData(per : Double): Unit =
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
        var norm = new NormalDistribution(sum/baseFreq.length, SD.evaluate(baseFreq))
        var sd = Math.sqrt(SD.evaluate(baseFreq))
        var prob: Double = 0
        
        per match {
          case 99 => prob = norm.probability((-0.01*sd)+norm.getMean, (0.01*sd)+norm.getMean) // Z = 0.01  --> 0.0040
          case 95 => prob = norm.probability((-0.06*sd)+norm.getMean, (0.06*sd)+norm.getMean) // Z = 0.06  --> 0.0239
          case 90 => prob = norm.probability((-0.12*sd)+norm.getMean, (0.12*sd)+norm.getMean) // Z = 0.12  --> 0.0478
          case 80 => prob = norm.probability((-0.25*sd)+norm.getMean, (0.25*sd)+norm.getMean) // Z = 0.25  --> 0.0978
          case 60 => prob = norm.probability((-0.52*sd)+norm.getMean, (0.52*sd)+norm.getMean) // Z = 0.52  --> 0.1985
          case 50 => prob = norm.probability((-0.67*sd)+norm.getMean, (0.67*sd)+norm.getMean) // Z = 0.67  --> 0.1985
          case _ => println("[99 , 95, 90 , 80, 60, 50]")
        }
//        prob = norm.probability(-(0.25*sd)+norm.getMean, (0.25*sd)+norm.getMean) // Z = 0.25  --> 0.948 
//        if (norm.getMean > reqFreq) {
//          prob = norm.probability(reqFreq, norm.getMean) * 2
//        } else prob = norm.probability(norm.getMean, reqFreq) * 2

        println("base m : " + norm.getMean + "  sig : "+sd)
        println("req m : " + reqFreq)
        println("Rule04 score : " + (1 - prob))
        println("###################################################################")
        println("test : " + (-0.25*sd)+norm.getMean + " < Z < " + (0.25*sd)+norm.getMean)
        println("###################################################################")
      }
    }
}

  