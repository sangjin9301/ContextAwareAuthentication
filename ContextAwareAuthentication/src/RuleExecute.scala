import java.util.LinkedList
import DataAccess.CDR
import Rules.Rule03
import Rules.Rule02
import Rules.Rule01
import Rules.Rule04


object RuleExecute {
  def execute(datas: LinkedList[CDR]):Unit=
  {
    var rule1 = new Rule01
    rule1.setData(datas)
    rule1.compareData
    var rule2 = new Rule02
    rule2.setData(datas)
    rule2.compareData
    var rule3 = new Rule03
    rule3.setData(datas)
    rule3.compareData
    var rule4 = new Rule04
    rule4.setData(datas)
    rule4.compareData(99) // score = 80% 
  }
}