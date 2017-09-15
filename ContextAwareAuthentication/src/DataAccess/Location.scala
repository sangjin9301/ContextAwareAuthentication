package DataAccess

class Location {
  var timestamp: Double = 0
  var latitude: Double = 0
  var longitude: Double = 0
  var accuracy: Int = 0
  
  def setTimestamp(time : Double) : Unit = 
  {
    this.timestamp = time
  }
  def setLatitude(latitude : Double) : Unit = 
  {
    this.latitude = latitude
  }
  def setLongitude(longitude : Double) : Unit = 
  {
    this.longitude = longitude
  }
  def setAccuracy(accuracy : Int) : Unit = 
  {
    this.accuracy = accuracy
  }
  
  
  def getTimestamp : Double = 
  {
    return this.timestamp
  }
  def getLatitude : Double = 
  {
    return this.latitude
  }
  def getLongitude : Double = 
  {
    return this.longitude
  }
  def getAccuracy : Int = 
  {
    return this.accuracy
  }
  
}