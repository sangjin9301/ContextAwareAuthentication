package DataAccess

class Location {
  var timestamp: Double = 0
  var latitude: Int = 0
  var longitude: Int = 0
  var accuracy: Int = 0
  
  def setTimestamp(time : Double) : Unit = 
  {
    this.timestamp = time
  }
  def setLatitude(latitude : Int) : Unit = 
  {
    this.latitude = latitude
  }
  def setLongitude(longitude : Int) : Unit = 
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
  def getLatitude : Int = 
  {
    return this.latitude
  }
  def getLongitude : Int = 
  {
    return this.longitude
  }
  def getAccuracy : Int = 
  {
    return this.accuracy
  }
  
}