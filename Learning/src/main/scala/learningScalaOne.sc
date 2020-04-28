


val piSinglePrecision : Float = 3.14159265f

val doublepi : Float = (piSinglePrecision*2)
println(f"Double pi is about $doublepi%.3f")



var first = 0
var second = 1
var count = 0
var n = 10
var sum = 0
println(first)
println(second)
while(count < n){
  sum = first+second
  println(sum)
  first = second
  second = sum
  count = count + 1
}


def upperCase(string:String) : String ={
  string.toUpperCase
}

upperCase("Hello")

def transformString(string:String, f:String => String): String ={
  f(string)
}


transformString("hello", string => string.toLowerCase());
transformString("hello", string => string.toUpperCase);

transformString("hello", upperCase)

val numbers = List(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
val numba:Boolean = (3%3) == 0

//numbers.filter()
//val filter_3 = numbers.filter((x:Int) => x//3 )

for(number <- numbers){
  if(number % 3 == 0) {
    println(number)
  }
}

val filter_numbers = numbers.filter(_%3 == 0)
for(number <- filter_numbers){
  println(number)
}