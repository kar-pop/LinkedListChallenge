package pop.kar;

 record Town(String townName, int distanceFromSydney) {

     @Override
     public boolean equals(Object o) {
         if(o instanceof Town t){
             return this.townName.equalsIgnoreCase(t.townName);
         }
         return false;
     }

     @Override
     public String toString() {
         return townName + "is " + distanceFromSydney + " miles from Sydney";
     }
 }
